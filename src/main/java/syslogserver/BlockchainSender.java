/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syslogserver;

import com.syslogserver.Utils.Cryptograph;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import syslogserver.smartcontracts.generated.LogContract;

/**
 *
 * @author Marlon
 */
public class BlockchainSender extends Thread {

    Web3j web3j = //Web3j.build(new HttpService("http://localhost:7545"));
            Web3j.build(new HttpService("https://rinkeby.infura.io/v3/f87fd81cbde84e7fbffcdb1d88d1f68e"));
    Credentials credentials = Credentials.create("F94772BE8D828FE1AB39E0EDD9BDF7FE98B1E4BF287817187092D810983FFA14");
    ContractGasProvider contractGasProvider = new DefaultGasProvider();
    Cryptograph cp;
//    DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
//    Date date = new Date();
    LogContract contract;
    List<String> logToBlockChain;

    public BlockchainSender(List<String> listaLog) {
        this.logToBlockChain = listaLog;
        contract = LogContract.load("0x718270ef3259d81f1ca0d00b11d2fc27e7a3b9f4", web3j, credentials, contractGasProvider);
    }
    
    public boolean verificaLista(){
        return (this.logToBlockChain.isEmpty());
    }
    
//        private void saveList() {
//        System.out.println("Tamanho lista BlockchainSender: " +  this.logToBlockChain.size());
//        File arq = new File("listaLogs.bak");
//        try {
//            arq.delete();
//            arq.createNewFile();
//
//            ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arq));
//            objOutput.writeObject(logToBlockChain);
//            objOutput.close();
//
//        } catch (IOException erro) {
//            System.out.printf("Erro ao salvar .bak de lista: ", erro.getMessage());
//        }
//    }
//    
//    public synchronized void limpaLista(){
//        this.logToBlockChain.clear();
//        saveList();
//    }

//    @Override
//    public void run() {
//          
//
//
//    }
    
    @Override
    public void start(){
                        try {
                    if(!verificaLista()){
                    FileWriter arquivo = new FileWriter("logs/logsTx.log", true);
                    //ADD to Blockchain
                    String joinLogs = String.join(" ; ", logToBlockChain);
                    TransactionReceipt transactionReceipt = contract.sendLog(joinLogs).send();
                    System.out.println("Hash Tx: " + transactionReceipt.getTransactionHash()); //Salvar Hash
                    arquivo.write(transactionReceipt.getTransactionHash());
                    arquivo.write(System.lineSeparator());
                    arquivo.close();
                    }
                } catch (Exception e) {
                    System.out.println("Erro: " + e);
                }
    }

}
