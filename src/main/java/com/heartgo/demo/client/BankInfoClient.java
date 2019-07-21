package com.heartgo.demo.client;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.heartgo.demo.contract.BankInfo;
import com.heartgo.demo.model.Bank;
import com.heartgo.demo.model.RESULT;
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

import static com.heartgo.demo.model.RESULT.*;

public class BankInfoClient {

    static Logger logger = LoggerFactory.getLogger(BankInfoClient.class);

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
        prop.setProperty("bankInfo_address", address);
        final Resource contractResource = new ClassPathResource("contract.properties");
        FileOutputStream fileOutputStream = new FileOutputStream(contractResource.getFile(), true);
        prop.store(fileOutputStream, "bankInfo address");
    }

    public String loadAssetAddr() throws Exception {
        // load Asset contact address from contract.properties
        Properties prop = new Properties();
        final Resource contractResource = new ClassPathResource("contract.properties");
        prop.load(contractResource.getInputStream());

        String contractAddress = prop.getProperty("bankInfo_address");
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
            BankInfo bankInfo = BankInfo.deploy(web3j, credentials, new StaticGasProvider(gasPrice, gasLimit)).send();
            System.out.println(" deploy Asset success, contract address is " + bankInfo.getContractAddress());

            recordAssetAddr(bankInfo.getContractAddress());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            System.out.println(" deploy Asset contract failed, error message is  " + e.getMessage());
        }
    }

    public Bank queryBankInfo(String bankId) {
        try {
            String contractAddress = loadAssetAddr();

            BankInfo bankInfo = BankInfo.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            Tuple2<BigInteger, String> result = bankInfo.select(bankId).send();
            System.out.println("result:" + JSONObject.toJSONString(result));
//            if (result.getValue1().compareTo(new BigInteger("0")) == 0) {
//                return null;
//            }
            return JSON.parseObject(result.getValue2(),Bank.class);

        } catch (Exception e) {
            logger.error(" queryUserInfo exception, error message is {}", e.getMessage());

            System.out.printf(" queryUserInfofailed, error message is %s\n", e.getMessage());
        }
        return null;
    }

    public RESULT saveBankInfo(Bank bank) {
        try {
            String contractAddress = loadAssetAddr();

            BankInfo bankInfo = BankInfo.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            TransactionReceipt receipt = bankInfo.insert(bank.getBankId(), JSON.toJSONString(bank)).send();
            System.out.println("receipt:" + JSONObject.toJSONString(receipt));

            List<BankInfo.RegisterEventEventResponse> response = bankInfo.getRegisterEventEvents(receipt);
            if (response.isEmpty()) {
                return DB_NO_RESP;
            }
            return OK;
        } catch (Exception e) {
            logger.error(" registerAssetAccount exception, error message is {}", e.getMessage());
            System.out.printf(" register asset account failed, error message is %s\n", e.getMessage());
            return DB_ERROR;
        }
    }

    public void updateBankAccount(Bank bank, Bank oldBank) {
        try {
            String contractAddress = loadAssetAddr();

            BankInfo bankInfo = BankInfo.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
//			String userJson = JSON.toJSONString(user);
            TransactionReceipt receipt = bankInfo.updateBankAccount(bank.getBankId(), JSONObject.toJSONString(bank), JSONObject.toJSONString(oldBank)).send();
//			List<RegisterEventEventResponse> response = bankInfo.getRegisterEventEvents(receipt);
//			if (!response.isEmpty()) {
//				if (response.get(0).ret.compareTo(new BigInteger("0")) == 0) {
//					System.out.printf(" registerUser success => asset: %s, value: %s \n");
//				} else {
//					System.out.printf(" registerUser failed, ret code is %s \n",
//							response.get(0).ret.toString());
//				}
//			} else {
//				System.out.println(" event log not found, maybe transaction not exec. ");
//			}
        } catch (Exception e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();

            logger.error(" registerAssetAccount exception, error message is {}", e.getMessage());
            System.out.printf(" register asset account failed, error message is %s\n", e.getMessage());
        }
    }

    public void deleteBank(String bankId) {
        try {
            String contractAddress = loadAssetAddr();

            BankInfo bankInfo = BankInfo.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
//			String userJson = JSON.toJSONString(user);
            TransactionReceipt receipt = bankInfo.remove(bankId).send();
//			List<RegisterEventEventResponse> response = bankInfo.getRegisterEventEvents(receipt);
//			if (!response.isEmpty()) {
//				if (response.get(0).ret.compareTo(new BigInteger("0")) == 0) {
//					System.out.printf(" registerUser success => asset: %s, value: %s \n");
//				} else {
//					System.out.printf(" registerUser failed, ret code is %s \n",
//							response.get(0).ret.toString());
//				}
//			} else {
//				System.out.println(" event log not found, maybe transaction not exec. ");
//			}
        } catch (Exception e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();

            logger.error(" registerAssetAccount exception, error message is {}", e.getMessage());
            System.out.printf(" register asset account failed, error message is %s\n", e.getMessage());
        }
    }

}
