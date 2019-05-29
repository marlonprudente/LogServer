/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syslogserver.logserver;

import java.io.IOException;
import java.net.ServerSocket;
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

    }

}
