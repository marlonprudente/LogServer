package syslogserver.smartcontracts.generated;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.2.0.
 */
public class LogContract extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50600080546001600160a01b03191633179055436002556102fc806100366000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c80632f92a0501461004657806371d90acb14610060578063909e4ab614610108575b600080fd5b61004e610185565b60408051918252519081900360200190f35b6101066004803603602081101561007657600080fd5b81019060208101813564010000000081111561009157600080fd5b8201836020820111156100a357600080fd5b803590602001918460018302840111640100000000831117156100c557600080fd5b91908080601f01602080910402602001604051908101604052809392919081815260200183838082843760009201919091525092955061018c945050505050565b005b6101106101a3565b6040805160208082528351818301528351919283929083019185019080838360005b8381101561014a578181015183820152602001610132565b50505050905090810190601f1680156101775780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6002545b90565b805161019f906001906020840190610238565b5050565b60018054604080516020601f6002600019610100878916150201909516949094049384018190048102820181019092528281526060939092909183018282801561022e5780601f106102035761010080835404028352916020019161022e565b820191906000526020600020905b81548152906001019060200180831161021157829003601f168201915b5050505050905090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061027957805160ff19168380011785556102a6565b828001600101855582156102a6579182015b828111156102a657825182559160200191906001019061028b565b506102b29291506102b6565b5090565b61018991905b808211156102b257600081556001016102bc56fea165627a7a72305820d6949fb87573f1031c712a4fc02a37c7f251f5ee63f26b878623632dba84a03c0029";

    public static final String FUNC_GETBLOCKCREATIONNUMBER = "getBlockCreationNumber";

    public static final String FUNC_SENDLOG = "sendLog";

    public static final String FUNC_GETLOG = "getLog";

    @Deprecated
    protected LogContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected LogContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected LogContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected LogContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<BigInteger> getBlockCreationNumber() {
        final Function function = new Function(FUNC_GETBLOCKCREATIONNUMBER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> sendLog(String _logString) {
        final Function function = new Function(
                FUNC_SENDLOG, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_logString)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> getLog() {
        final Function function = new Function(FUNC_GETLOG, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static LogContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new LogContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static LogContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new LogContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static LogContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new LogContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static LogContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new LogContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<LogContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(LogContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<LogContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(LogContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<LogContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(LogContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<LogContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(LogContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
