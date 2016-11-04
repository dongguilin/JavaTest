package com.guilin.java.guava.eventbus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by guilin on 2016/11/2.
 */
public class EventBusChat {

    public static void main(String[] args) {
        EventBus channel = new EventBus();
        ServerSocket server;

        try {
            server = new ServerSocket(4444);//使用telnet ip 4444测试
            while (true) {
                Socket connection = server.accept();
                UserThread newUser = new UserThread(connection, channel);
                channel.register(newUser);
                newUser.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Person {
        private String name;
        private int age;

        public Person() {
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    private static class UserThread extends Thread {
        private Socket connection;
        private EventBus channel;
        private BufferedReader in;
        private PrintWriter out;

        public UserThread(Socket connection, EventBus channel) {
            this.connection = connection;
            this.channel = channel;

            try {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                out = new PrintWriter(connection.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }

        @Subscribe
        public void receiveMessage(String message) {
            if (out != null) {
                out.println(message);
                System.out.println("receiveMessage:" + message);
            }
        }

        @Subscribe
        public String receiveMessage(Person person) {
            System.out.println(person);
            return person.getName();
        }

        @Override
        public void run() {
            try {
                String input;
                while ((input = in.readLine()) != null) {
                    channel.post(input);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            channel.post(new Person("zhangsan", 15));
            System.out.println("unregister");

            //reached eof
            channel.unregister(this);
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            in = null;
            out = null;
        }
    }
}
