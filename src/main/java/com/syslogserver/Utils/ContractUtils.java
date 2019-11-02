/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syslogserver.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.bouncycastle.util.encoders.Hex;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import static org.web3j.crypto.Hash.sha3;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import syslogserver.smartcontracts.generated.LogContract;

/**
 *
 * @author Marlon
 */
public class ContractUtils {

    Web3j web3j = Web3j.build(new HttpService("https://rinkeby.infura.io/v3/f87fd81cbde84e7fbffcdb1d88d1f68e"));
    Credentials credentials = Credentials.create("F94772BE8D828FE1AB39E0EDD9BDF7FE98B1E4BF287817187092D810983FFA14");
    ContractGasProvider contractGasProvider = new DefaultGasProvider();
    LogContract contract;
    String lastHash;

    public ContractUtils() {
        contract = LogContract.load("0xEa399bA8C1D6565279F3A24321d871B21f69eD0F", web3j, credentials, contractGasProvider);

    }

    private void getLastHash() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("logs/hashs.log"));
            while (br.ready()) {
                lastHash = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }

    private BigInteger estimateGas(List<String> listaLog, String currentHash) {
        String joinLogs = String.join(" ; ", listaLog);
        Function function = new Function("sendLog",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(joinLogs), new org.web3j.abi.datatypes.generated.Bytes32(sha3((currentHash + joinLogs).getBytes()))),
                Collections.<TypeReference<?>>emptyList());
        String functionEncoder = FunctionEncoder.encode(function);
        BigInteger gasLimit = contractGasProvider.getGasLimit("sendLog");
        BigInteger gasPrice = contractGasProvider.getGasPrice("sendLog");
        BigInteger estimateGas = BigInteger.ZERO;
        try {
            estimateGas = web3j.ethEstimateGas(Transaction.createFunctionCallTransaction(credentials.getAddress(), null, gasPrice, gasLimit, contract.getContractAddress(), functionEncoder)).send().getAmountUsed();
        } catch (IOException e) {
            System.out.println("Erro: " + e);
        }
        return estimateGas;
    }

    public boolean verificaLimiteEnvio(List<String> listaLog) {
        getLastHash();
        BigInteger gasLimit = contractGasProvider.getGasLimit("sendLog").subtract(BigInteger.valueOf(300000));
        BigInteger estimatedGas = estimateGas(listaLog, lastHash);
        System.out.println("GAS: " + estimatedGas);

        boolean retorno = estimatedGas.compareTo(gasLimit) > 0;
        System.out.println("Condição GAS: " + retorno);

        return retorno;
    }

}
