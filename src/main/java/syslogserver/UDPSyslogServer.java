/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syslogserver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.core.LogEvent;

import org.productivity.java.syslog4j.SyslogConstants;
import org.productivity.java.syslog4j.SyslogRuntimeException;
import org.productivity.java.syslog4j.server.SyslogServerEventIF;
import org.productivity.java.syslog4j.server.impl.net.udp.UDPNetSyslogServer;

/**
 *
 * @author Marlon
 */
public class UDPSyslogServer implements Runnable {

    private final int maxBufferSize = 1024 * 65 + 1024;
    DatagramSocket sock;
    DatagramPacket wPacket = null;
    LogEvent logEvent = null;
    ObjectInputStream obj = null;
    ByteArrayInputStream bis = null;
    byte[] wBuffer = null;
    List<String> logToBlockChain = new ArrayList<>(); //list of log   
    BlockchainSender bcs;

    public UDPSyslogServer(DatagramSocket serverSocket, List<String> lista) throws IOException {
        //ConfigurationFactory.setConfigurationFactory(new ServerConfigurationFactory(""));
        this.sock = serverSocket;
        this.logToBlockChain = lista;
        if(lista.isEmpty()){
            this.logToBlockChain = getList();
        }
        wBuffer = new byte[maxBufferSize];
        wPacket = new DatagramPacket(wBuffer, wBuffer.length);
    }

    public synchronized void addString(String logString) {
        if (!this.logToBlockChain.contains(logString)) {
            this.logToBlockChain.add(logString);
            saveList();
            System.out.println("Tamanho lista UDPServer: " +  this.logToBlockChain.size());
            try {
                FileWriter arquivoLog = new FileWriter("logs/logServer.log", true);
                arquivoLog.write(logString);
                arquivoLog.write(System.lineSeparator());
                arquivoLog.close();
            } catch (IOException e) {
                System.out.println("Erro ao salvar log em logServer.log " + e);
            }

        }

    }

    private synchronized void saveList() {
        File arq = new File("listaLogs.bak");
        try {
            arq.delete();
            arq.createNewFile();

            ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arq));
            objOutput.writeObject(logToBlockChain);
            objOutput.close();

        } catch (IOException erro) {
            System.out.printf("Erro ao salvar .bak de lista: ", erro.getMessage());
        }
    }

    private List<String> getList() {
        ArrayList<String> lista = new ArrayList();
        try {
            File arq = new File("listaLogs.bak");
            if (arq.exists()) {
                ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
                lista = (ArrayList<String>) objInput.readObject();
                objInput.close();
            }
        } catch (IOException erro1) {
            System.out.printf("Erro: %s", erro1.getMessage());
        } catch (ClassNotFoundException erro2) {
            System.out.printf("Erro: %s", erro2.getMessage());
        }

        return lista;

    }

    @Override
    public void run() {
        try {
            while (true) {
                try {
                    sock.receive(wPacket);
                    
                    wBuffer = wPacket.getData();
                    String log = new String(wBuffer, StandardCharsets.UTF_8).trim();
                    addString(log);
                    System.out.println(log);
                    if (this.logToBlockChain.size() >= 20) {
                        this.bcs = new BlockchainSender(this.logToBlockChain);
                        bcs.run();
                    }
                } catch (Exception e) {
                    System.out.println("Endgame! " + e);
                }
            }
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
    }
}
