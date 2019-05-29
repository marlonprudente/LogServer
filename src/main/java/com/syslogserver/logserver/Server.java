/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syslogserver.logserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.logging.log4j.core.net.server.ObjectInputStreamLogEventBridge;
import org.apache.logging.log4j.core.net.server.TcpSocketServer;
import static org.apache.logging.log4j.core.net.server.TcpSocketServer.createSerializedSocketServer;
import syslogserver.SyslogServer;

/**
 *
 * @author Marlon
 */
public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(8000);
        System.out.println("Iniciando Servidor...");

        SyslogServer tcp = new SyslogServer(ss);

        tcp.run();
        
//        while(true){
//            
//        }


    }

}
