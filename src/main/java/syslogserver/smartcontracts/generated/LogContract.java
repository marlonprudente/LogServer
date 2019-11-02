package syslogserver.smartcontracts.generated;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
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
    private static final String BINARY = "6080604052600060025534801561001557600080fd5b50600080546001600160a01b0319163317815543600155805260046020527f30000000000000000000000000000000000000000000000000000000000000007f17ef568e3e12ab5b9c7254a8d58478811de00f9e6eb34345acd53bf8fd09d3ec556105c4806100856000396000f3fe608060405234801561001057600080fd5b506004361061007d5760003560e01c80638b710a751161005b5780638b710a7514610136578063949d225d14610153578063d2796a991461015b578063f82c50f1146102055761007d565b80631124c05a146100825780632f92a050146101145780634a9100461461012e575b600080fd5b61009f6004803603602081101561009857600080fd5b5035610222565b6040805160208082528351818301528351919283929083019185019080838360005b838110156100d95781810151838201526020016100c1565b50505050905090810190601f1680156101065780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b61011c6102c3565b60408051918252519081900360200190f35b61011c6102ca565b61011c6004803603602081101561014c57600080fd5b50356102df565b61011c6102f1565b6102036004803603604081101561017157600080fd5b81019060208101813564010000000081111561018c57600080fd5b82018360208201111561019e57600080fd5b803590602001918460018302840111640100000000831117156101c057600080fd5b91908080601f01602080910402602001604051908101604052809392919081815260200183838082843760009201919091525092955050913592506102f7915050565b005b61009f6004803603602081101561021b57600080fd5b5035610438565b60008181526003602090815260409182902080548351601f60026000196101006001861615020190931692909204918201849004840281018401909452808452606093928301828280156102b75780601f1061028c576101008083540402835291602001916102b7565b820191906000526020600020905b81548152906001019060200180831161029a57829003601f168201915b50505050509050919050565b6001545b90565b60025460009081526004602052604090205490565b60046020526000908152604090205481565b60025490565b6000546001600160a01b0316331461030e57600080fd5b60025460009081526003602090815260409091208351610330928501906104d3565b5060028054600181019091556000908152600460209081526040918290205482518083018281528651929487949193920191908401908083835b602083106103895780518252601f19909201916020918201910161036a565b51815160209384036101000a60001901801990921691161790526040805192909401828103601f19018352845281519181019190912060028054600090815260049093528483209190915554815291909120548514935061043492505050576040518060600160405280602d815260200161056c602d913960036000600254815260200190815260200160002090805190602001906104299291906104d3565b506002805460010190555b5050565b60036020908152600091825260409182902080548351601f6002600019610100600186161502019093169290920491820184900484028101840190945280845290918301828280156104cb5780601f106104a0576101008083540402835291602001916104cb565b820191906000526020600020905b8154815290600101906020018083116104ae57829003601f168201915b505050505081565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061051457805160ff1916838001178555610541565b82800160010185558215610541579182015b82811115610541578251825591602001919060010190610526565b5061054d929150610551565b5090565b6102c791905b8082111561054d576000815560010161055756fe4572726f20616f2073616c766172206c6f67202d204861736820617475616c20696e636f6d7061746976656c2ea165627a7a723058200324291d4e67ef45ac797d124da0621e046ec0661f33c1697793ce680e264d630029";

    public static final String FUNC_GETLOGS = "getLogs";

    public static final String FUNC_GETBLOCKCREATIONNUMBER = "getBlockCreationNumber";

    public static final String FUNC_GETLASTHASH = "getLastHash";

    public static final String FUNC_BLOCKCHAINHASH = "blockchainHash";

    public static final String FUNC_SIZE = "size";

    public static final String FUNC_SENDLOG = "sendLog";

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

    public RemoteCall<byte[]> getLastHash() {
        final Function function = new Function(FUNC_GETLASTHASH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<byte[]> blockchainHash(BigInteger param0) {
        final Function function = new Function(FUNC_BLOCKCHAINHASH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<BigInteger> size() {
        final Function function = new Function(FUNC_SIZE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> sendLog(String _logString, byte[] currentHash) {
        final Function function = new Function(
                FUNC_SENDLOG, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_logString), 
                new org.web3j.abi.datatypes.generated.Bytes32(currentHash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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
