package com.guilin.java.thread.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by hadoop on 2015/12/20.
 * 串行的Web服务器
 */
public class SingleThreadWebServer1 {

    public SingleThreadWebServer1() throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            Socket connection = socket.accept();
            handleRequest(connection);
        }
    }

    private void handleRequest(Socket connection) {

    }

}
