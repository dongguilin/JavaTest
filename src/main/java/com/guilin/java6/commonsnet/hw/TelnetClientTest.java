package com.guilin.java6.commonsnet.hw;


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
        File arpFile = new File("/Users/guilin1/Documents/test/arp_out.info");
        File macFile = new File("/Users/guilin1/Documents/test/mac_out.info");
        TelnetClientTest client = new TelnetClientTest("10.0.1.1", 23);
        boolean flag = client.run(arpFile, macFile);
        if (flag) {
            Map<String, Map<String, String>> data = client.getData();
            System.out.println(data.size());
            Iterator<Map<String, String>> iterator = data.values().iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }

    }

    public boolean run(File arpFile, File macFile) {
        boolean flag = false;
        try {
            arpFile.createNewFile();
            BufferedWriter writer1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arpFile)));
            macFile.createNewFile();
            BufferedWriter writer2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(macFile)));


            readUntil(writer1, "Username:");
            //输入用户名
            writeCommand("admin");

            readUntil(writer1, "Password:");
            //输入密码
            writeCommand("yhxt@123");


            //display arp
            readUntil(writer1, "<LNS>");
            writeCommand("display arp");
            nextPage(writer1);
            readUntil(writer1, "<LNS>");
            writer1.close();

            //display mac-address
            writeCommand("display mac-address");
            nextPage(writer2);
            readUntil(writer2, "<LNS>");
            writer2.close();

            read(arpFile, macFile);

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

    private void read(File arpFile, File macFile) throws Exception {
        //arp
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(arpFile)));
        String str;
        String[] arr;
        while ((str = reader.readLine()) != null) {
            arr = str.trim().split(" +");
            if (arr.length == 5 && isIp(arr[0])) {
                String ip = arr[0];
                if (map.containsKey(ip)) {
                    continue;
                }
                String mac = arr[1];
                Map<String, String> temp = new HashMap();
                temp.put("ip", ip);
                temp.put("mac", mac);
                temp.put("interface", arr[4]);
                map.put(mac, temp);
            }
        }
        reader.close();

        //mac
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(macFile)));
        while ((str = reader.readLine()) != null) {
            arr = str.trim().split(" +");
            if (arr.length == 6 && isMac(arr[0], "-")) {
                String mac = arr[0];
                if (map.containsKey(mac)) {
                    map.get(mac).put("port", arr[3]);
                }
            }
        }
        reader.close();
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

    private void nextPage(Writer writer) {
        try {
            byte[] buff = new byte[80];
            int ret_read;
            boolean flag = true;
            while (flag) {
                ret_read = inputStream.read(buff, 0, 80);
                if (ret_read > 0) {
                    String temp = new String(buff, 0, ret_read);
                    System.out.print(temp);
                    writer.write(temp.replaceAll("---- More ----", "").replaceAll("\\[42D", ""));
//                    writer.write(temp);
                    if (temp.contains("[42D")) {
                        flag = false;
                        buff = null;
                    } else {
                        writeCommand(" ");//输入空格，翻到一下页
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readUntil(Writer writer, String str) {
        try {
            byte[] buff = new byte[80];
            int ret_read;
            boolean flag = true;
            while (flag) {
                ret_read = inputStream.read(buff, 0, 80);
                if (ret_read > 0) {
                    String temp = new String(buff, 0, ret_read);
                    System.out.print(temp);
                    writer.write(temp.replaceAll("---- More ----", "").replaceAll("\\[42D", ""));
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
