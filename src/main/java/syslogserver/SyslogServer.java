/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syslogserver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.core.LogEvent;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import syslogserver.smartcontracts.generated.Greeter;

/**
 *
 * @author Marlon
 */
public class SyslogServer extends Thread {

    ServerSocket sock;
    DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
    Date date = new Date();
    List<String> logToBlockChain = new ArrayList<>(); //list of log
    Web3j web3j = Web3j.build(new HttpService("http://localhost:7545"));
    
    Credentials credentials = Credentials.create("4b319828ce0d36e10d9453e950408639594756f849cd7ea336509729398f0aed");
    ContractGasProvider contractGasProvider = new DefaultGasProvider();

    public SyslogServer(ServerSocket serverSocket) throws IOException {
        this.sock = serverSocket;
    }

    @Override
    public void run() {
        try {
            //0x1AB9a5e75f96daFD4Bb78bCa70125DA9aEE2AF6C Contract on rinkeby
            Greeter contract = Greeter.load("0x487455333Cf07585AE0Ff43eEc37C91251220f0f", web3j, credentials, contractGasProvider);
            while (true) {
                Socket socket = sock.accept();
                FileWriter arquivo = new FileWriter("logs/" + dateFormat.format(date) + ".log", true);

                if (socket != null) {
                    try {
                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                        for (;;) {
                            LogEvent le = (LogEvent) ois.readObject();
                            String log = le.getLevel() + " | " + le.getTimeMillis() + " | [" + le.getThreadName() + "] " + le.getLoggerName() + " - " + le.getMessage().getFormattedMessage();
                            logToBlockChain.add(log);
                            System.out.println(log);
                        }
                    } catch (Exception e) {
                        System.out.println("Endgame! " + e);
                    } finally {
                        //ADD to Blockchain
                        System.out.println("Adicionando ao Blockchain...");
                        for (String l : logToBlockChain) {
                            System.out.println("lista: " + l);
                            TransactionReceipt transactionReceipt
                                    = contract.newGreeting(l).send();
                            System.out.println(transactionReceipt.getTransactionHash()); //Salvar Hash
                            arquivo.write(transactionReceipt.getTransactionHash());
                            arquivo.write(System.lineSeparator());
                            
                        }
                        logToBlockChain.clear();
                        arquivo.close();
                        String newGreeting = contract.greet().send();
                        System.out.println(newGreeting);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
    }
}
