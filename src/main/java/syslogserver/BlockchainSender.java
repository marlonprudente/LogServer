/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syslogserver;

import com.syslogserver.Utils.Cryptograph;
import com.syslogserver.Utils.LocalChain;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.request.Transaction;
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

    Web3j web3j
            = //Web3j.build(new HttpService("http://localhost:7545"));
            Web3j.build(new HttpService("https://rinkeby.infura.io/v3/f87fd81cbde84e7fbffcdb1d88d1f68e"));
    Credentials credentials = Credentials.create("F94772BE8D828FE1AB39E0EDD9BDF7FE98B1E4BF287817187092D810983FFA14");
    ContractGasProvider contractGasProvider = new DefaultGasProvider();
    Cryptograph cp;
    LocalChain localChain;
//    DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
//    Date date = new Date();
    LogContract contract;
    List<String> logToBlockChain;

    public BlockchainSender(List<String> listaLog) {
        this.logToBlockChain = listaLog;
        localChain = getLocalChain();
        contract = LogContract.load("0xc2bd0Bf6603A7413efC2c35Ceeab7713AD4eC168", web3j, credentials, contractGasProvider);
        //LogContract.load("0x718270ef3259d81f1ca0d00b11d2fc27e7a3b9f4", web3j, credentials, contractGasProvider);
        try {
            cp = new Cryptograph("tccmarlonprudente");
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }

    private synchronized void toLocalChain() {
        File arq = new File("logs/localchain.lchain");
        try {
            arq.delete();
            arq.createNewFile();
            try (ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arq))) {
                objOutput.writeObject(localChain);
            }
        } catch (IOException erro) {
            System.out.printf("Erro ao salvar .lchain de localchain: ", erro.getMessage());
        }
    }

    private LocalChain getLocalChain() {
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

    public boolean verificaLista() {
        return (this.logToBlockChain.isEmpty());
    }

    @Override
    public void start() {
        try {
            if (!verificaLista()) {
                FileWriter arquivo = new FileWriter("logs/logsTx.log", true);
                //ADD to Blockchain
                String joinLogs = String.join(" ; ", logToBlockChain);
                localChain.addBlock(joinLogs);
                toLocalChain();
                TransactionReceipt transactionReceipt = contract.sendLog(joinLogs, localChain.getLatestBlock().hash).send();
                System.out.println("Hash Tx: " + transactionReceipt.getTransactionHash()); //Salvar Hash Transaction
                arquivo.write(transactionReceipt.getTransactionHash());
                arquivo.write(System.lineSeparator());
                arquivo.close();
                

            }
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }

}
