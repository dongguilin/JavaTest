package com.guilin.java6.commonsnet;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.telnet.TelnetClient;

import java.io.*;
import java.util.List;

/**
 * Created by guilin1 on 15/7/29.
 */
public class TelnetClientTest {
    static TelnetClient tc = null;

    static BufferedWriter writer;

    public static void main(String[] args) throws Exception {

        String remoteip = "10.0.1.1";

        int remoteport = 23;

        tc = new TelnetClient();

        tc.connect(remoteip, remoteport);

        InputStream inputStream = tc.getInputStream();

        PrintStream outputStream = new PrintStream(tc.getOutputStream());

        File file = new File("/Users/guilin1/Documents/test/arp_out.info");
        file.createNewFile();
         writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));

        readUntil(inputStream, "Username:");
        writeCommand(outputStream, "admin");
        readUntil(inputStream, "Password:");
        writeCommand(outputStream, "yhxt@123");
        readUntil(inputStream, "<LNS>");
        writeCommand(outputStream, "display arp");
        readUntil(inputStream, outputStream);
        readUntil(inputStream, "<LNS>");

        System.out.println("------disconnect-----");
        tc.disconnect();
        writer.close();

    }

    public static void writeCommand(PrintStream outputStream, Object obj) {
        outputStream.println(obj);
        outputStream.flush();
    }

    public static void readUntil(InputStream inputStream, PrintStream outputStream) {
        try {
            byte[] buff = new byte[80];
            int ret_read;
            boolean flag = true;
            while (flag) {
                ret_read = inputStream.read(buff, 0, 80);
                if (ret_read > 0) {
                    String temp = new String(buff, 0, ret_read);
                    System.out.print(temp);
                    writer.write(temp.replaceAll("\r","").replaceAll("---- More ----", "").replaceAll("\\[42D", ""));
                    if (temp.contains("[42D")) {
                        flag = false;
                        buff = null;
                    }else{
                        writeCommand(outputStream," ");
                    }
                }else{
                    flag = false;
                }
            }
        } catch (IOException e) {
            System.err.println("Exception while reading socket:" + e.getMessage());
        }
    }

    public static void readUntil(InputStream inputStream, String str) {
        try {
            byte[] buff = new byte[80];
            int ret_read;
            boolean flag = true;
            while (flag) {
                ret_read = inputStream.read(buff, 0, 80);
                if (ret_read > 0) {
                    String temp = new String(buff, 0, ret_read);
                    System.out.print(temp);
                    writer.write(temp.replaceAll("\r","").replaceAll("---- More ----", "").replaceAll("\\[42D", ""));
//                    writer.write(temp);
                    if (temp.trim().endsWith(str)) {
//                        flag = false;
//                        buff = null;
                    }
                }else{
                    flag = false;
                }
            }
        } catch (IOException e) {
            System.err.println("Exception while reading socket:" + e.getMessage());
        }
    }


}
