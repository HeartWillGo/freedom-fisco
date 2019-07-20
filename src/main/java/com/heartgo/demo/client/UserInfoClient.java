package com.heartgo.demo.client;


import com.alibaba.fastjson.JSON;
import com.heartgo.demo.contract.UserInfo;
import com.heartgo.demo.model.RESULT;
import com.heartgo.demo.model.User;
import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Properties;

public class UserInfoClient {

    static Logger logger = LoggerFactory.getLogger(UserInfoClient.class);

    private Web3j web3j;

    private Credentials credentials;

    public Web3j getWeb3j() {
        return web3j;
    }

    public void setWeb3j(Web3j web3j) {
        this.web3j = web3j;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public void recordAssetAddr(String address) throws FileNotFoundException, IOException {
        Properties prop = new Properties();
        prop.setProperty("user_info_address", address);
        final Resource contractResource = new ClassPathResource("contract.properties");
        FileOutputStream fileOutputStream = new FileOutputStream(contractResource.getFile(), true);
        prop.store(fileOutputStream, "contract address");
    }

    public String loadAssetAddr() throws Exception {
        // load Asset contact address from contract.properties
        Properties prop = new Properties();
        final Resource contractResource = new ClassPathResource("contract.properties");
        prop.load(contractResource.getInputStream());

        String contractAddress = prop.getProperty("user_info_address");
        if (contractAddress == null || contractAddress.trim().equals("")) {
            throw new Exception(" load Asset contract address failed, please deploy it first. ");
        }
        logger.info(" load Asset address from contract.properties, address is {}", contractAddress);
        return contractAddress;
    }

    public void initialize() throws Exception {

        // init the Service
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        Service service = context.getBean(Service.class);
        service.run();

        ChannelEthereumService channelEthereumService = new ChannelEthereumService();
        channelEthereumService.setChannelService(service);
        Web3j web3j = Web3j.build(channelEthereumService, 1);

        // init Credentials
        Credentials credentials = Credentials.create(Keys.createEcKeyPair());

        setCredentials(credentials);
        setWeb3j(web3j);

        logger.debug(" web3j is " + web3j + " ,credentials is " + credentials);
    }

    private static BigInteger gasPrice = new BigInteger("30000000");
    private static BigInteger gasLimit = new BigInteger("30000000");

    public void deployAssetAndRecordAddr() {

        try {
            UserInfo asset = UserInfo.deploy(web3j, credentials, new StaticGasProvider(gasPrice, gasLimit)).send();
            System.out.println(" deploy Asset success, contract address is " + asset.getContractAddress());

            recordAssetAddr(asset.getContractAddress());
        } catch (Exception e) {
            System.out.println(" deploy Asset contract failed, error message is  " + e.getMessage());
        }
    }

    public User queryUserInfo(String userId) {
        try {
            String contractAddress = loadAssetAddr();

            UserInfo userInfo = UserInfo.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            Tuple2<BigInteger, String> result = userInfo.select(userId).send();
            if (result.getValue1().compareTo(new BigInteger("0")) == 0) {
                System.out.printf(" %s UserInfo is not exist \n", userId);
                return null;
            }
            System.out.printf(" UserInfot %s, value %s \n", userId, result.getValue2());
            return (User) JSON.parse(result.getValue2());

        } catch (Exception e) {
            logger.error(" queryUserInfo exception, error message is {}", e.getMessage());

            System.out.printf(" queryUserInfofailed, error message is %s\n", e.getMessage());
            return null;
        }
    }

    public RESULT registerUser(User user) {
        try {
            String contractAddress = loadAssetAddr();

            UserInfo userInfo = UserInfo.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            String userJson = JSON.toJSONString(user);
            TransactionReceipt receipt = userInfo.insert(user.getUserId(), userJson).send();
            List<UserInfo.RegisterEventEventResponse> response = userInfo.getRegisterEventEvents(receipt);
            if (response.isEmpty()) {
                System.out.println(" event log not found, maybe transaction not exec. ");
                return RESULT.DB_NO_RESP;
            }
            // 数据库貌似允许重复值.暂时搁置.
            if (response.get(0).ret.equals(BigInteger.valueOf(0))) {
                return RESULT.USER_ALREADY_EXIST;
            }
            System.out.printf(" registerUser success => asset: %s, value: %s \n", user.getUserId(),
                    userJson);
            return RESULT.OK;

        } catch (Exception e) {
            logger.error(" registerAssetAccount exception, error message is {}", e.getMessage());
            System.out.printf(" register asset account failed, error message is %s\n", e.getMessage());
            return RESULT.DB_ERROR;
        }
    }


    public static void Usage() {
        System.out.println(" Usage:");
        System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.asset.client.AssetClient deploy");
        System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.asset.client.AssetClient query account");
        System.out.println(
                "\t java -cp conf/:lib/*:apps/* org.fisco.bcos.asset.client.AssetClient register account value");
        System.out.println(
                "\t java -cp conf/:lib/*:apps/* org.fisco.bcos.asset.client.AssetClient transfer from_account to_account amount");
        System.exit(0);
    }

    //	public static void main(String[] args) throws Exception {
//
//		if (args.length < 1) {
//			Usage();
//		}
//
//		AssetClient client = new AssetClient();
//		client.initialize();
//
//		switch (args[0]) {
//		case "deploy":
//			client.deployAssetAndRecordAddr();
//			break;
//		case "query":
//			if (args.length < 2) {
//				Usage();
//			}
//			client.queryAssetAmount(args[1]);
//			break;
//		case "register":
//			if (args.length < 3) {
//				Usage();
//			}
//			client.registerAssetAccount(args[1], new BigInteger(args[2]));
//			break;
//		case "transfer":
//			if (args.length < 4) {
//				Usage();
//			}
//			client.transferAsset(args[1], args[2], new BigInteger(args[3]));
//			break;
//		default: {
//			Usage();
//		}
//		}
//
//		System.exit(0);
//	}
    public static void main(String[] args) throws Exception {
        UserInfoClient userInfoClient = new UserInfoClient();
        userInfoClient.initialize();
        userInfoClient.deployAssetAndRecordAddr();
        User user = new User();
        user.setPassWord("9999");
        user.setUserPhone("134266");
        //userInfoClient.registerUser("heartgo", user);
        //userInfoClient.queryUserInfo("heartgo");
    }

}
