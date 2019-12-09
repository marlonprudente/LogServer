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
    private static final String BINARY = "60806040526000600255600060035534801561001a57600080fd5b50600080546001600160a01b03191633178155436001908155604080517f300000000000000000000000000000000000000000000000000000000000000081528151908190038301902060038054855260066020529190932092909255815401905561080a8061008b6000396000f3fe608060405234801561001057600080fd5b506004361061009e5760003560e01c80638b710a75116100665780638b710a751461017c578063949d225d14610199578063d2796a99146101a1578063f82c50f11461024b578063f99c754a146102685761009e565b80631124c05a146100a35780632f92a0501461013557806333a262d61461014f5780634a910046146101575780636560d63e1461015f575b600080fd5b6100c0600480360360208110156100b957600080fd5b5035610285565b6040805160208082528351818301528351919283929083019185019080838360005b838110156100fa5781810151838201526020016100e2565b50505050905090810190601f1680156101275780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b61013d610326565b60408051918252519081900360200190f35b6100c061032d565b61013d6103d1565b6100c06004803603602081101561017557600080fd5b50356103ea565b61013d6004803603602081101561019257600080fd5b5035610454565b61013d610466565b610249600480360360408110156101b757600080fd5b8101906020810181356401000000008111156101d257600080fd5b8201836020820111156101e457600080fd5b8035906020019184600183028401116401000000008311171561020657600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550509135925061046c915050565b005b6100c06004803603602081101561026157600080fd5b5035610643565b6100c06004803603602081101561027e57600080fd5b50356106de565b60008181526004602090815260409182902080548351601f600260001961010060018616150201909316929092049182018490048402810184019094528084526060939283018282801561031a5780601f106102ef5761010080835404028352916020019161031a565b820191906000526020600020905b8154815290600101906020018083116102fd57829003601f168201915b50505050509050919050565b6001545b90565b60028054600019908101600090815260056020908152604091829020805483516001821615610100029095011694909404601f810182900482028401820190925281835260609391908301828280156103c75780601f1061039c576101008083540402835291602001916103c7565b820191906000526020600020905b8154815290600101906020018083116103aa57829003601f168201915b5050505050905090565b6003546000190160009081526006602052604090205490565b60008181526005602090815260409182902080548351601f600260001961010060018616150201909316929092049182018490048402810184019094528084526060939283018282801561031a5780601f106102ef5761010080835404028352916020019161031a565b60066020526000908152604090205481565b60025490565b6000546001600160a01b0316331461048357600080fd5b600060066000600254815260200190815260200160002054836040516020018083815260200182805190602001908083835b602083106104d45780518252601f1990920191602091820191016104b5565b6001836020036101000a0380198251168184511680821785525050505050509050019250505060405160208183030381529060405280519060200120905081811461059e57604080518082018252601f81527f4572726f3a20657374652048617368206520696e636f6d7061746976656c2e0060208083019182526002546000908152600590915292909220905161056c9290610746565b506002546000908152600460209081526040909120845161058f92860190610746565b5060028054600101905561063e565b600254600090815260046020908152604090912084516105c092860190610746565b50604080518082018252601e81527f4461646f732061726d617a656e61646f7320636f6d207375636573736f2e00006020808301918252600254600090815260059091529290922090516106149290610746565b50600280546001908101909155600380546000908152600660205260409020839055805490910190555b505050565b60046020908152600091825260409182902080548351601f6002600019610100600186161502019093169290920491820184900484028101840190945280845290918301828280156106d65780601f106106ab576101008083540402835291602001916106d6565b820191906000526020600020905b8154815290600101906020018083116106b957829003601f168201915b505050505081565b60056020908152600091825260409182902080548351601f6002600019610100600186161502019093169290920491820184900484028101840190945280845290918301828280156106d65780601f106106ab576101008083540402835291602001916106d6565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061078757805160ff19168380011785556107b4565b828001600101855582156107b4579182015b828111156107b4578251825591602001919060010190610799565b506107c09291506107c4565b5090565b61032a91905b808211156107c057600081556001016107ca56fea165627a7a723058201ba8110f45db22caac6abae708d74f16d668f198e0c0f3a589340c68df32bf240029";

    public static final String FUNC_GETLOGS = "getLogs";

    public static final String FUNC_GETBLOCKCREATIONNUMBER = "getBlockCreationNumber";

    public static final String FUNC_GETLASTERROLOG = "getLastErroLog";

    public static final String FUNC_GETLASTHASH = "getLastHash";

    public static final String FUNC_GETERROLOGS = "getErroLogs";

    public static final String FUNC_BLOCKCHAINHASH = "blockchainHash";

    public static final String FUNC_SIZE = "size";

    public static final String FUNC_SENDLOG = "sendLog";

    public static final String FUNC_LOG = "log";

    public static final String FUNC_ERROLOGCHAIN = "ErroLogChain";

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

    public RemoteCall<String> getLastErroLog() {
        final Function function = new Function(FUNC_GETLASTERROLOG, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<byte[]> getLastHash() {
        final Function function = new Function(FUNC_GETLASTHASH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<String> getErroLogs(BigInteger i) {
        final Function function = new Function(FUNC_GETERROLOGS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(i)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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

    public RemoteCall<String> ErroLogChain(BigInteger param0) {
        final Function function = new Function(FUNC_ERROLOGCHAIN, 
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
