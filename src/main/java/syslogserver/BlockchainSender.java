/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syslogserver;

import com.syslogserver.Utils.Cryptograph;
import java.io.FileWriter;
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
import syslogserver.smartcontracts.generated.Greeter;
import syslogserver.smartcontracts.generated.LogContract;

/**
 *
 * @author Marlon
 */
public class BlockchainSender implements Runnable {

    Web3j web3j = Web3j.build(new HttpService("http://localhost:7545"));
    Credentials credentials = Credentials.create("0dca2308a055cf6083c7006c2f114c3e70bf9b5e2990b69127e8729bae97e05f");
    ContractGasProvider contractGasProvider = new DefaultGasProvider();
    Cryptograph cp;
    DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
    Date date = new Date();
    LogContract contract;
    List<String> logToBlockChain;

    public BlockchainSender(List<String> listaLog) {
        this.logToBlockChain = listaLog;
        contract = LogContract.load("0x5806ec4540eA6377dEc792e83adD2e5667505F62", web3j, credentials, contractGasProvider);
    }
    
    public synchronized boolean verificaLista(){
        return (this.logToBlockChain.isEmpty());
    }
    
    public synchronized void limpaLista(){
        this.logToBlockChain.clear();
    }

    @Override
    public void run() {
                try {
                    System.out.println("Oi");
                    if(!verificaLista()){
                    System.out.println("Entrei");
                    FileWriter arquivo = new FileWriter("logs/logsBlockChain.log", true);
                    //ADD to Blockchain
                    System.out.println("Adicionando ao Blockchain...");
                    String joinLogs = String.join(" ; ", logToBlockChain);

                    TransactionReceipt transactionReceipt = contract.sendLog(joinLogs).send();
                    System.out.println(transactionReceipt.getTransactionHash()); //Salvar Hash
                    arquivo.write(transactionReceipt.getTransactionHash());
                    arquivo.write(System.lineSeparator());
                    limpaLista();
                    arquivo.close();
                    }
                } catch (Exception e) {
                    System.out.println("Erro: " + e);
                }          


    }

}
