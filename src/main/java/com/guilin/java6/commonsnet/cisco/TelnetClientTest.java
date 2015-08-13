package com.guilin.java6.commonsnet.cisco;


import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.net.telnet.TelnetClient;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by guilin1 on 15/7/29.
 */
public class TelnetClientTest {

    private TelnetClient tc = null;

    private InputStream inputStream;

    private PrintStream printStream;

    private Map<String, Map<String, String>> map = new HashedMap();

    public TelnetClientTest(String ip, int port) throws IOException {
        tc = new TelnetClient();
        tc.connect(ip, port);
        inputStream = tc.getInputStream();
        printStream = new PrintStream(tc.getOutputStream());
    }

    public static void main(String[] args) throws Exception {
        File arpFile = new File("/Users/guilin1/Documents/test/arp_out_cisco2.log");
        File macFile = new File("/Users/guilin1/Documents/test/mac_out_cisco2.log");
        File cdpFile = new File("/Users/guilin1/Documents/test/cdp_out_cisco2.log");
        TelnetClientTest client = new TelnetClientTest("10.160.2.251", 23);
        boolean flag = client.run(arpFile, macFile, cdpFile);
        if (flag) {
            Map<String, Map<String, String>> data = client.getData();
            System.out.println(data.size());
            Iterator<Map<String, String>> iterator = data.values().iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }

    }

    public boolean run(File arpFile, File macFile, File cdpFile) {
        boolean flag = false;
        try {
            arpFile.createNewFile();
            BufferedWriter writer1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arpFile)));
            macFile.createNewFile();
            BufferedWriter writer2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(macFile)));
            cdpFile.createNewFile();
            BufferedWriter writer3 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(cdpFile)));


            readUntil(writer1, "Username:");
            //输入用户名
            writeCommand("cisco");

            readUntil(writer1, "Password:");
            //输入密码
            writeCommand("cisco");

            //
            readUntil(writer1, ">");
            writeCommand("en");
            readUntil(writer1, "Password:");
            writeCommand("dc.cisco");

            //show arp
            readUntil(writer1, "#");
            writeCommand("show arp");
            nextPage(writer1);
            readUntil(writer1, "#");
            writer1.close();

            //show mac address-table
            writeCommand("show mac address-table");
            nextPage(writer2);
            readUntil(writer2, "#");
            writer2.close();

            //show cdp nei
            writeCommand("show cdp nei");
            nextPage(writer3);
            readUntil(writer3, "#");
            writer3.close();


            read(arpFile, macFile, cdpFile);

            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                tc.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    private void read(File arpFile, File macFile, File cdpFile) throws Exception {
        //arp
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(arpFile)));
        String str;
        String[] arr;
        while ((str = reader.readLine()) != null) {
            arr = str.trim().split(" +");
            if (arr.length == 5 && isIp(arr[1])) {
                String ip = arr[1];
                if (map.containsKey(ip)) {
                    continue;
                }
                String mac = arr[3];
                Map<String, String> temp = new HashMap();
                temp.put("ip", ip);
                temp.put("mac", mac);
                temp.put("interface", arr[5]);
                map.put(mac, temp);
            }
        }
        reader.close();

        //mac
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(macFile)));
        while ((str = reader.readLine()) != null) {
            arr = str.trim().split(" +");
            if (arr.length == 6 && isMac(arr[1], ",")) {
                String mac = arr[1];
                if (map.containsKey(mac)) {
                    map.get(mac).put("port", arr[3]);
                }
            }
        }
        reader.close();


        //cdp
//        reader = new BufferedReader(new InputStreamReader(new FileInputStream(cdpFile)));
//        while((str=reader.readLine())!=null){
//
//        }

    }

    public Map<String, Map<String, String>> getData() {
        return map;
    }

    /**
     * 向终端写命令
     *
     * @param obj
     */
    private void writeCommand(Object obj) {
        printStream.println(obj);
        printStream.flush();
    }

    private void writeBlank(){
        printStream.println();
        printStream.flush();
    }

    private void nextPage(Writer writer) {
        try {
            byte[] buff = new byte[1024];
            int ret_read;
            boolean flag = true;
            while (flag) {
                ret_read = inputStream.read(buff, 0, 1024);
                if (ret_read > 0) {
                    String temp = new String(buff, 0, ret_read);
                    System.out.print(temp);
                    writer.write(temp.replaceAll("--More--", "").replaceAll("\\[42D", ""));
                    if(temp.endsWith("#")){
                        flag = false;
                        buff = null;
                    }else{
                        writeBlank();
                    }
//                    if (temp.contains("[42D")) {
//                        flag = false;
//                        buff = null;
//                    } else {
//                        writeCommand(" ");//输入空格，翻到一下页
//                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String readUntil(String str) throws IOException {
        StringBuffer buffer = new StringBuffer();
        byte[] buff = new byte[1024];
        int ret_read;
        boolean flag = true;
        while (flag) {
            ret_read = inputStream.read(buff, 0, 1024);
            if (ret_read > 0) {
                String temp = new String(buff, 0, ret_read);
                System.out.print(temp);
                buffer.append(temp);
                if (temp.trim().endsWith(str)) {
                    flag = false;
                    buff = null;
                }
            }
        }
        return buffer.toString();
    }

    private void writeHW(Writer writer, String str) throws IOException {
        writer.write(str.replaceAll("--More--", "").replaceAll("\\[42D", ""));
    }


    private void readUntil(Writer writer, String str) {
        try {
            byte[] buff = new byte[1024];
            int ret_read;
            boolean flag = true;
            while (flag) {
                ret_read = inputStream.read(buff, 0, 1024);
                if (ret_read > 0) {
                    String temp = new String(buff, 0, ret_read);
                    System.out.print(temp);
                    writer.write(temp.replaceAll("--More--", "").replaceAll("\\[42D", ""));
                    if (temp.trim().endsWith(str)) {
                        flag = false;
                        buff = null;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证是否是IP
     *
     * @param text
     * @return
     */
    private boolean isIp(String text) {
        if (text != null && !text.isEmpty()) {
            String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
            if (text.matches(regex)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 验证是否是MAC地址
     *
     * @param text
     * @param seperator 分隔符
     * @return
     */
    private boolean isMac(String text, String seperator) {
        String patternMac = String.format("^[a-f 0-9]{4}(%s[a-f 0-9]{4}){2}$", seperator);
        return Pattern.compile(patternMac).matcher(text).find();
    }

}
