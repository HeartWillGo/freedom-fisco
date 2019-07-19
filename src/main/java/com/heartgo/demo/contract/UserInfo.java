package com.heartgo.demo.contract;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Int256;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.DefaultBlockParameter;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.request.BcosFilter;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version none.
 */
@SuppressWarnings("unchecked")
public class UserInfo extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5061002861002d640100000000026401000000009004565b610185565b600061100190508073ffffffffffffffffffffffffffffffffffffffff166356004b6a6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001806020018481038452600b8152602001807f745f757365725f696e666f000000000000000000000000000000000000000000815250602001848103835260078152602001807f757365725f6964000000000000000000000000000000000000000000000000008152506020018481038252600a8152602001807f757365725f76616c7565000000000000000000000000000000000000000000008152506020019350505050602060405180830381600087803b15801561014657600080fd5b505af115801561015a573d6000803e3d6000fd5b505050506040513d602081101561017057600080fd5b81019080805190602001909291905050505050565b610c5a806101946000396000f30060806040526004361061004c576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806306e63ff814610051578063fcd7e3c114610114575b600080fd5b34801561005d57600080fd5b506100fe600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506101fd565b6040518082815260200191505060405180910390f35b34801561012057600080fd5b5061017b600480360381019080803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929050505061075a565b6040518083815260200180602001828103825283818151815260200191508051906020019080838360005b838110156101c15780820151818401526020810190506101a6565b50505050905090810190601f1680156101ee5780820380516001836020036101000a031916815260200191505b50935050505060405180910390f35b60008060008061020b610b3f565b92508273ffffffffffffffffffffffffffffffffffffffff166313db93466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561027157600080fd5b505af1158015610285573d6000803e3d6000fd5b505050506040513d602081101561029b57600080fd5b810190808051906020019092919050505091508173ffffffffffffffffffffffffffffffffffffffff1663e942b516876040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260078152602001807f757365725f696400000000000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b8381101561036e578082015181840152602081019050610353565b50505050905090810190601f16801561039b5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b1580156103bb57600080fd5b505af11580156103cf573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff1663e942b516866040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001806020018381038352600a8152602001807f757365725f76616c756500000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b83811015610493578082015181840152602081019050610478565b50505050905090810190601f1680156104c05780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b1580156104e057600080fd5b505af11580156104f4573d6000803e3d6000fd5b505050508273ffffffffffffffffffffffffffffffffffffffff166331afac3687846040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b838110156105b3578082015181840152602081019050610598565b50505050905090810190601f1680156105e05780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b15801561060057600080fd5b505af1158015610614573d6000803e3d6000fd5b505050506040513d602081101561062a57600080fd5b810190808051906020019092919050505090507fe71002dee81d9ff68a8184c07ed89508062d232ea9979314fd048b99aca6f25e818787604051808481526020018060200180602001838103835285818151815260200191508051906020019080838360005b838110156106ab578082015181840152602081019050610690565b50505050905090810190601f1680156106d85780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b838110156107115780820151818401526020810190506106f6565b50505050905090810190601f16801561073e5780820380516001836020036101000a031916815260200191505b509550505050505060405180910390a180935050505092915050565b60006060600080600061076b610b3f565b92508273ffffffffffffffffffffffffffffffffffffffff1663e8434e39878573ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156107ee57600080fd5b505af1158015610802573d6000803e3d6000fd5b505050506040513d602081101561081857600080fd5b81019080805190602001909291905050506040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b838110156108c65780820151818401526020810190506108ab565b50505050905090810190601f1680156108f35780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b15801561091357600080fd5b505af1158015610927573d6000803e3d6000fd5b505050506040513d602081101561093d57600080fd5b810190808051906020019092919050505091508173ffffffffffffffffffffffffffffffffffffffff1663846719e060006040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b1580156109c057600080fd5b505af11580156109d4573d6000803e3d6000fd5b505050506040513d60208110156109ea57600080fd5b8101908080519060200190929190505050905060008173ffffffffffffffffffffffffffffffffffffffff16639c981fcb6040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018281038252600a8152602001807f757365725f76616c756500000000000000000000000000000000000000000000815250602001915050600060405180830381600087803b158015610a9f57600080fd5b505af1158015610ab3573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f820116820180604052506020811015610add57600080fd5b810190808051640100000000811115610af557600080fd5b82810190506020810184811115610b0b57600080fd5b8151856001820283011164010000000082111715610b2857600080fd5b505092919050505081915094509450505050915091565b600080600061100191508173ffffffffffffffffffffffffffffffffffffffff1663f23f63c96040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018281038252600b8152602001807f745f757365725f696e666f000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b158015610be957600080fd5b505af1158015610bfd573d6000803e3d6000fd5b505050506040513d6020811015610c1357600080fd5b810190808051906020019092919050505090508092505050905600a165627a7a72305820d2d6075b1133e7abc6f99b6eeb7c4193470bbe9aac867fbe1b9f1b1393ee99b40029";

    public static final String ABI = "[{\"constant\":false,\"inputs\":[{\"name\":\"user_id\",\"type\":\"string\"},{\"name\":\"user_value\",\"type\":\"string\"}],\"name\":\"insert\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"user_id\",\"type\":\"string\"}],\"name\":\"select\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"},{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"ret\",\"type\":\"int256\"},{\"indexed\":false,\"name\":\"user_id\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"user_value\",\"type\":\"string\"}],\"name\":\"RegisterEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"ret\",\"type\":\"int256\"},{\"indexed\":false,\"name\":\"from_account\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"to_account\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"TransferEvent\",\"type\":\"event\"}]";

    public static final String FUNC_INSERT = "insert";

    public static final String FUNC_SELECT = "select";

    public static final Event REGISTEREVENT_EVENT = new Event("RegisterEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event TRANSFEREVENT_EVENT = new Event("TransferEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected UserInfo(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected UserInfo(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected UserInfo(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected UserInfo(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> insert(String user_id, String user_value) {
        final Function function = new Function(
                FUNC_INSERT, 
                Arrays.<Type>asList(new Utf8String(user_id),
                new Utf8String(user_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void insert(String user_id, String user_value, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_INSERT, 
                Arrays.<Type>asList(new Utf8String(user_id),
                new Utf8String(user_value)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String insertSeq(String user_id, String user_value) {
        final Function function = new Function(
                FUNC_INSERT, 
                Arrays.<Type>asList(new Utf8String(user_id),
                new Utf8String(user_value)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<Tuple2<BigInteger, String>> select(String user_id) {
        final Function function = new Function(FUNC_SELECT, 
                Arrays.<Type>asList(new Utf8String(user_id)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple2<BigInteger, String>>(
                new Callable<Tuple2<BigInteger, String>>() {
                    @Override
                    public Tuple2<BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue());
                    }
                });
    }

    public List<RegisterEventEventResponse> getRegisterEventEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(REGISTEREVENT_EVENT, transactionReceipt);
        ArrayList<RegisterEventEventResponse> responses = new ArrayList<RegisterEventEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            RegisterEventEventResponse typedResponse = new RegisterEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.ret = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.user_id = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.user_value = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RegisterEventEventResponse> registerEventEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, RegisterEventEventResponse>() {
            @Override
            public RegisterEventEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(REGISTEREVENT_EVENT, log);
                RegisterEventEventResponse typedResponse = new RegisterEventEventResponse();
                typedResponse.log = log;
                typedResponse.ret = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.user_id = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.user_value = (String) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RegisterEventEventResponse> registerEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REGISTEREVENT_EVENT));
        return registerEventEventFlowable(filter);
    }

    public List<TransferEventEventResponse> getTransferEventEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFEREVENT_EVENT, transactionReceipt);
        ArrayList<TransferEventEventResponse> responses = new ArrayList<TransferEventEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            TransferEventEventResponse typedResponse = new TransferEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.ret = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.from_account = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.to_account = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventEventResponse> transferEventEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, TransferEventEventResponse>() {
            @Override
            public TransferEventEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFEREVENT_EVENT, log);
                TransferEventEventResponse typedResponse = new TransferEventEventResponse();
                typedResponse.log = log;
                typedResponse.ret = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.from_account = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.to_account = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventEventResponse> transferEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFEREVENT_EVENT));
        return transferEventEventFlowable(filter);
    }

    @Deprecated
    public static UserInfo load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new UserInfo(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static UserInfo load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new UserInfo(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static UserInfo load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new UserInfo(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static UserInfo load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new UserInfo(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<UserInfo> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(UserInfo.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<UserInfo> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(UserInfo.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<UserInfo> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(UserInfo.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<UserInfo> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(UserInfo.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class RegisterEventEventResponse {
        public Log log;

        public BigInteger ret;

        public String user_id;

        public String user_value;
    }

    public static class TransferEventEventResponse {
        public Log log;

        public BigInteger ret;

        public String from_account;

        public String to_account;

        public BigInteger amount;
    }
}
