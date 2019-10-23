/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syslogserver.Utils;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
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

    public ContractUtils() {
        contract = LogContract.load("0x718270ef3259d81f1ca0d00b11d2fc27e7a3b9f4", web3j, credentials, contractGasProvider);
    }

    private BigInteger estimateGas(List<String> listaLog) {
        String joinLogs = String.join(" ; ", listaLog);
        System.out.println("String to Contract: " + joinLogs);
        Function function = new Function("sendLog",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(joinLogs)),
                Collections.<TypeReference<?>>emptyList());
        String functionEncoder = FunctionEncoder.encode(function);
        System.out.println("Function Encoder: " + functionEncoder);
        BigInteger gasLimit = contractGasProvider.getGasLimit("sendLog");
        BigInteger gasPrice = contractGasProvider.getGasPrice("sendLog");
        
        System.out.println("Gas Limit: " + gasLimit);
        System.out.println("Gas Price: " + gasPrice);
        BigInteger estimateGas = BigInteger.ZERO;
        try {
            estimateGas = web3j.ethEstimateGas(Transaction.createFunctionCallTransaction(credentials.getAddress(), null, gasPrice, gasLimit, contract.getContractAddress(), functionEncoder)).send().getAmountUsed();
        } catch (IOException e) {
            System.out.println("Erro: " + e);
        }
        return estimateGas;
    }

    public boolean verificaLimiteEnvio(List<String> listaLog) {
        BigInteger gasLimit = contractGasProvider.getGasLimit("sendLog").subtract(BigInteger.valueOf(300000));
        BigInteger estimatedGas = estimateGas(listaLog);       
        System.out.println("Gas Limit - 300000: " + gasLimit);
        System.out.println("Estimated Gas: " + estimatedGas);

        if (estimatedGas.compareTo(gasLimit) > 0) {
            return true;
        } else {
            return false;
        }
    }

}
