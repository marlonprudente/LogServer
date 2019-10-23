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
    private static final String BINARY = "6080604052600060025534801561001557600080fd5b50600080546001600160a01b03191633179055436001556104288061003b6000396000f3fe608060405234801561001057600080fd5b50600436106100575760003560e01c80631124c05a1461005c5780632f92a050146100ee57806371d90acb14610108578063949d225d146101b0578063f82c50f1146101b8575b600080fd5b6100796004803603602081101561007257600080fd5b50356101d5565b6040805160208082528351818301528351919283929083019185019080838360005b838110156100b357818101518382015260200161009b565b50505050905090810190601f1680156100e05780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6100f6610276565b60408051918252519081900360200190f35b6101ae6004803603602081101561011e57600080fd5b81019060208101813564010000000081111561013957600080fd5b82018360208201111561014b57600080fd5b8035906020019184600183028401116401000000008311171561016d57600080fd5b91908080601f01602080910402602001604051908101604052809392919081815260200183838082843760009201919091525092955061027d945050505050565b005b6100f66102c3565b610079600480360360208110156101ce57600080fd5b50356102c9565b60008181526003602090815260409182902080548351601f600260001961010060018616150201909316929092049182018490048402810184019094528084526060939283018282801561026a5780601f1061023f5761010080835404028352916020019161026a565b820191906000526020600020905b81548152906001019060200180831161024d57829003601f168201915b50505050509050919050565b6001545b90565b6000546001600160a01b0316331461029457600080fd5b600254600090815260036020908152604090912082516102b692840190610364565b5050600280546001019055565b60025490565b60036020908152600091825260409182902080548351601f60026000196101006001861615020190931692909204918201849004840281018401909452808452909183018282801561035c5780601f106103315761010080835404028352916020019161035c565b820191906000526020600020905b81548152906001019060200180831161033f57829003601f168201915b505050505081565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106103a557805160ff19168380011785556103d2565b828001600101855582156103d2579182015b828111156103d25782518255916020019190600101906103b7565b506103de9291506103e2565b5090565b61027a91905b808211156103de57600081556001016103e856fea165627a7a723058209a1e5841409f177aff26db313ea6bdc33b4fafcfbe34a6962d81b8424669e4e90029";

    public static final String FUNC_GETLOGS = "getLogs";

    public static final String FUNC_GETBLOCKCREATIONNUMBER = "getBlockCreationNumber";

    public static final String FUNC_SENDLOG = "sendLog";

    public static final String FUNC_SIZE = "size";

    public static final String FUNC_LOG = "log";

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

    public RemoteCall<String> getLogs(BigInteger i) {
        final Function function = new Function(FUNC_GETLOGS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(i)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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

    public RemoteCall<BigInteger> size() {
        final Function function = new Function(FUNC_SIZE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> log(BigInteger param0) {
        final Function function = new Function(FUNC_LOG, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
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
