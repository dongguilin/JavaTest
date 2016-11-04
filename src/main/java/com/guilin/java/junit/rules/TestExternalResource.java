package com.guilin.java.junit.rules;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by guilin on 2016/9/30.
 * ExternalResource is a base class for Rules (like TemporaryFolder) that set up an external resource before
 * a test (a file, socket, server, database connection, etc.), and guarantee to tear it down afterward
 */
public class TestExternalResource {
    ServerSocket serverSocket = null;

    @Rule
    public ExternalResource resource = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            System.out.println("invoke before method");
            serverSocket = new ServerSocket(1000);
        }

        @Override
        protected void after() {
            System.out.println("invoke after method");
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    @Test
    public void testFoo() {
        System.out.println(serverSocket);

    }
}
