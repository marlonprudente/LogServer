/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syslogserver;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
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
public class UDPSyslogServer  implements Runnable {
    private final int maxBufferSize = 1024 * 65 + 1024;
    DatagramSocket  sock;
    DatagramPacket wPacket = null;
    LogEvent logEvent = null;
    ObjectInputStream obj = null;
    ByteArrayInputStream bis = null;
    byte[] wBuffer = null;
    List<String> logToBlockChain = new ArrayList<>(); //list of log   
    BlockchainSender bcs; 
    
    public UDPSyslogServer(DatagramSocket serverSocket, List<String> listaLog) throws IOException {
        //ConfigurationFactory.setConfigurationFactory(new ServerConfigurationFactory(""));
        this.sock = serverSocket;
        this.logToBlockChain = listaLog;
        this.bcs = new BlockchainSender(listaLog);
        wBuffer = new byte[maxBufferSize];
        wPacket = new DatagramPacket( wBuffer, wBuffer.length );
    }
    
    public synchronized void addString(String logString){
        if(!this.logToBlockChain.contains(logString))
            this.logToBlockChain.add(logString);
    }

    @Override
    public void run() {
        try {
            while (true) {
                    try {
                        sock.receive(wPacket);
                        SyslogServerEventIF event = new Rfc5424SyslogEvent(wBuffer, wPacket.getOffset(), wPacket.getLength());
                        wBuffer = wPacket.getData();
                        System.out.println(event);
                        addString(event.toString());                        
                        if(this.logToBlockChain.size() >= 20)
                            bcs.run();                        
                    } catch (Exception e) {
                        System.out.println("Endgame! " + e);
                    }                
            }
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
    }
}
