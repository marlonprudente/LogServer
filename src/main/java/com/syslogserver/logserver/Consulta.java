/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syslogserver.logserver;

import com.syslogserver.Utils.Block;
import com.syslogserver.Utils.Cryptograph;
import com.syslogserver.Utils.LocalChain;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
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
    
    private static LocalChain getLocalChain() {
        LocalChain localChain = null;
        try {
            File arq = new File("logs/localchain.lchain");
            if (arq.exists()) {
                ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
                localChain = (LocalChain) objInput.readObject();
                objInput.close();
            }
        } catch (IOException | ClassNotFoundException erro1) {
            System.out.printf("Erro: %s", erro1.getMessage());
        } finally {
            if (localChain == null) {
                localChain = new LocalChain();
            }            
        }
        return localChain;
    }
    
    public static void main(String[] args) throws InvalidKeySpecException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        Web3j web3j = //Web3j.build(new HttpService("http://127.0.0.1:7545"));
                 Web3j.build(new HttpService("https://rinkeby.infura.io/v3/f87fd81cbde84e7fbffcdb1d88d1f68e"));
        Credentials credentials = Credentials.create("F94772BE8D828FE1AB39E0EDD9BDF7FE98B1E4BF287817187092D810983FFA14");
        ContractGasProvider contractGasProvider = new DefaultGasProvider();
        Cryptograph cp = new Cryptograph("tccmarlonprudente");
        LocalChain localChain = getLocalChain();
        LogContract contract = LogContract.load("0xc2bd0Bf6603A7413efC2c35Ceeab7713AD4eC168", web3j, credentials, contractGasProvider);
        while (true) {

            System.out.println("1 - Consultar hash's Log no Blockchain");
            System.out.println("2 - Consultar Log no Blockchain");
            System.out.println("3 - Verificar se localChain é valido!");
            System.out.println("4 - Verificar ultimo log de Erro do blockchain.");
            System.out.println("5 - Consultar logs de Erro do blockchain.");
            System.out.println("6 - Consultar dados e logs do localChain.");
            Scanner scanner = new Scanner(System.in);
            Integer op = scanner.nextInt();
            switch (op) {
                case 1:
                    try {
                        BufferedReader br = new BufferedReader(new FileReader("logs/logsTx.log"));
                        while (br.ready()) {
                            String linha = br.readLine();
                            System.out.println("Hash: " + linha);
                            String data = web3j.ethGetTransactionByHash(linha.trim()).send().getTransaction().get().getInput();
                            byte[] bytes = Hex.decode(data.substring(202));
                            String dec = new String(bytes, "UTF-8");
                            System.out.println("Dados: " + dec);
                            //System.out.println(cp.decrypt(dec));
                        }
                        br.close();

                    } catch (Exception e) {
                        System.out.println("Erro case 1: " + e);
                    }
                    break;
                case 2:
                    try {
                        FileWriter logsOnBlockChain = new FileWriter("logs/logsBlockchain.log", false);
                        FileWriter logsTx = new FileWriter("logs/logsTx.log", false);
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
                        System.out.println("Erro case 2: " + e);
                    }                    
                    break;
                case 3:
                    if(localChain.isChainValid()){
                        System.out.println("LocalChain Valido, verificar ultimo hash no blockchain");
                        try{
                        byte[] blockChainHash = contract.getLastHash().send();
                        if(Arrays.equals(localChain.getLatestBlock().hash, blockChainHash)){
                            System.out.println("Ultimo Hash do localChain: " + Hex.toHexString(localChain.getLatestBlock().hash));
                            System.out.println("Ultimo Hash do Blockchain: " + Hex.toHexString(blockChainHash));
                            System.out.println("LocalChain está de acordo com o Blockchain.");
                        }else{
                            int contractSize = contract.size().send().intValue();
                            System.out.println("Size Local: " + localChain.getLocalChainSize());
                            System.out.println("Size contract: " + contractSize);                            
                            if(localChain.getLocalChainSize() != contractSize){
                                System.out.println("Tamanho do localchain diferente do blockchain.");
                                break;
                            }
                            for (int blocks = 0; blocks < contractSize; blocks++) {
                                Block currentBlock = localChain.getBlockByIndex(blocks);
                                byte[] blockChainBlockHash = contract.blockchainHash(BigInteger.valueOf(blocks)).send();
                                System.out.println("LocalChain Block Hash: " + Hex.toHexString(currentBlock.hash));
                                System.out.println("BlockChain Block Hash: " + Hex.toHexString(blockChainBlockHash));
                                System.out.println("");
                                if(!Arrays.equals(currentBlock.hash, blockChainBlockHash)){
                                    System.out.println("No bloco de numero: " + blocks + " ha inconsistencia dos dados.");
                                    System.out.println("ErroLogContract: " + contract.getErroLogs(BigInteger.valueOf(blocks)));
                                    System.out.println("Logs: " + contract.getLogs(BigInteger.valueOf(blocks)));
                                    break;
                                }
                            }
                        }
                        }catch(Exception e){
                            System.out.println("Erro: " + e);
                        }
                    }else{
                        System.out.println("LocalChain invalido!");
                    }
                    break;
                case 4:
                    try{                
                String erroLogChain = contract.ErroLogChain(BigInteger.valueOf(localChain.getLatestBlock().getIndex() - 1)).send();
                System.out.println("erroLogChain string: " + erroLogChain);
                if(erroLogChain.contains("Erro")){
                    System.out.println("Dados Incompativeis.");
                }
                    }catch(Exception e){
                        
                    }
                    break;
                case 5:
                    try{
                    int contractSize = contract.size().send().intValue();
                    for (int blocks = 0; blocks < contractSize; blocks++) {                            
                        System.out.println("Index: [" + blocks + "] | ErrorLog: [" + contract.getErroLogs(BigInteger.valueOf(blocks)).send() + "]");
                    }
                    }catch(Exception e){
                        System.out.println("Erro no case 5: " + e);
                    }
                    break;
                case 6:
                    System.out.println("Tamanho do localChain: " + localChain.getLocalChainSize());
                    for(int i = 0; i < localChain.getLocalChainSize(); i++){
                        //System.out.println("Dados: " + localChain.getBlockByIndex(i).getData());
                        System.out.println("Hash: " + Hex.toHexString(localChain.getBlockByIndex(i).hash));
                    }
                    break;

                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        }
    }

}
