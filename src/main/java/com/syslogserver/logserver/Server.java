/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syslogserver.logserver;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import syslogserver.BlockchainSender;
import syslogserver.SyslogServer;

/**
 *
 * @author Marlon
 */
public class Server {

    public static void main(String[] args) throws IOException {

        DatagramSocket ss = new DatagramSocket(514);
        List<String> log = new ArrayList<>();
        
        System.out.println("Iniciando Servidor...");
        
        SyslogServer tcp = new SyslogServer(ss, log);
        tcp.run();
        System.out.println("Servidor Iniciado!");
    }

}
