/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syslogserver;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.logging.log4j.core.LogEvent;

/**
 *
 * @author Marlon
 */
public class TCPSyslogServer implements Runnable {

    ServerSocket sock;

    public TCPSyslogServer(ServerSocket serverSocket) {
        this.sock = serverSocket;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket socket = sock.accept();
                if (socket != null) {
                    try {
                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                        for (;;) {
                            LogEvent le = (LogEvent) ois.readObject();
                            String log = le.getLevel() + " | " + le.getTimeMillis() + " | [" + le.getThreadName() + "] " + le.getLoggerName() + " - " + le.getMessage().getFormattedMessage();
                            System.out.println(">" + log);
                        }
                    } catch (Exception e) {

                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
