/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syslogserver.logserver;

import com.syslogserver.Utils.Cryptograph;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Scanner;
import javax.crypto.NoSuchPaddingException;
import org.bouncycastle.util.encoders.Hex;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import syslogserver.smartcontracts.generated.LogContract;

/**
 *
 * @author Marlon
 */
public class Consulta {

    public static void main(String[] args) throws InvalidKeySpecException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        Web3j web3j = //Web3j.build(new HttpService("http://127.0.0.1:7545"));
                 Web3j.build(new HttpService("https://rinkeby.infura.io/v3/f87fd81cbde84e7fbffcdb1d88d1f68e"));
        Credentials credentials = Credentials.create("F94772BE8D828FE1AB39E0EDD9BDF7FE98B1E4BF287817187092D810983FFA14");
        ContractGasProvider contractGasProvider = new DefaultGasProvider();
        Cryptograph cp = new Cryptograph("tccmarlonprudente");

        while (true) {

            System.out.println("1 - Consultar hash's Log no Blockchain");
            System.out.println("2 - Consultar Log no Blockchain");
            Scanner scanner = new Scanner(System.in);
            Integer op = scanner.nextInt();
            switch (op) {
                case 1:
                    try {
                        BufferedReader br = new BufferedReader(new FileReader("logs/logsTx.log"));
                        while (br.ready()) {
                            String linha = br.readLine();
                            System.out.println(linha);
                            String data = web3j.ethGetTransactionByHash(linha.trim()).send().getTransaction().get().getInput();
                            byte[] bytes = Hex.decode(data.substring(2));
                            String dec = new String(bytes, "UTF-8");
                            System.out.println("" + dec);
                            //System.out.println(cp.decrypt(dec));
                        }
                        br.close();

                    } catch (Exception e) {
                        System.out.println("Erro: " + e);
                    }
                    break;
                case 2:
                    try {

                        FileWriter logsOnBlockChain = new FileWriter("logs/logsBlockchain.log", false);
                        FileWriter logsTx = new FileWriter("logs/logsTx.log", false);
                        LogContract contract
                                = //LogContract.deploy(web3j, credentials, contractGasProvider).send();          
                                LogContract.load("0x718270ef3259d81f1ca0d00b11d2fc27e7a3b9f4", web3j, credentials, contractGasProvider);
                        long blockCriacao = contract.getBlockCreationNumber().send().longValue();
                        EthBlock.Block bloco = web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, true).send().getBlock();
                        long ultimoBloco = Integer.parseInt(bloco.getNumber().toString());
                        String myAddress = credentials.getAddress();
                        String contractAddress = contract.getContractAddress();
                        for (long i = blockCriacao; i <= ultimoBloco; i++) {
                            EthBlock.Block bl = web3j.ethGetBlockByNumber(DefaultBlockParameter.valueOf(BigInteger.valueOf(i)), true).send().getBlock();
                            List<EthBlock.TransactionResult> txlist = bl.getTransactions();
                            for (EthBlock.TransactionResult l : txlist) {
                                Transaction t = (Transaction) l.get();
                                //System.out.println("De: " + t.getFrom());
                                //System.out.println("Para: " + t.getTo());
                                if (t.getFrom().equalsIgnoreCase(myAddress)) {
                                    if (i != blockCriacao) {
                                        if (!t.getTo().equalsIgnoreCase(contractAddress)) {
                                            break;
                                        }
                                        logsTx.write(t.getHash());
                                        logsTx.write(System.lineSeparator()); 
                                        String data = web3j.ethGetTransactionByHash(t.getHash()).send().getTransaction().get().getInput();
                                        byte[] bytes = Hex.decode(data.substring(2));
                                        String decodedData = new String(bytes, "UTF-8");
                                        decodedData = decodedData.substring(68, decodedData.length());
                                        String[] listaChain = decodedData.split(" ; ");
                                        for (String a : listaChain) {
                                            logsOnBlockChain.write(a);
                                            logsOnBlockChain.write(System.lineSeparator());
                                            System.out.println("=> " + a);
                                        }
                                    }
                                }

                            }
                        }
                        logsOnBlockChain.close();
                        logsTx.close();
                    } catch (Exception e) {
                        System.out.println("Erro: " + e);
                    }
                    
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        }
    }

}
