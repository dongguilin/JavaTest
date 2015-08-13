package com.guilin.java6.snmp.cisco;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by guilin1 on 15/8/12.
 */
public class Parse {

    public static void main(String[] args) throws Exception {

    }

    public static Map<String, Device> itsMap = new HashMap<>();
    public static Map<String, Device> map = new HashMap<>();

    public static void parse(Device device) {
        //1.TODO telnet 登录
//            device.getLoginIp()
//            device.getLoginName()
//            device.getLoginPwd()
        //2.write file

        //3.parse file
        File file = null;
        parse(file, device);
    }


    public static void parse(File file, Device device) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String msg;

            boolean flag = true;
            while (flag) {
                msg = reader.readLine();
                //show run
                if (msg.endsWith("interface Vlan1")) {
                    msg = reader.readLine().trim();// ip address 10.10.255.1 255.255.255.0
                    //管理IP
                    String ip = msg.split(" +")[2];
                    device.setIp(ip);
                }

                //show cdp neighbors detail
                if (msg.endsWith("show cdp neighbors detail")) {
                    List<Interface> interfaces = new ArrayList<>();
                    while (flag) {
                        msg = reader.readLine().trim();
                        if (msg.startsWith("-------------------------")) {
                            //Device ID: BJtest
                            String[] temp = reader.readLine().split(":");
                            if (temp[0].equals("Device ID")) {//Device ID: BJtest
                                String oppositeHostName = temp[1].trim();

                                Interface inface = new Interface();
                                inface.setLocalType(device.getType());
                                inface.setLocalHostName(device.getHostname());
                                inface.setLocalIp(device.getIp());
                                inface.setOppositeHostName(oppositeHostName);
                                inface.setOppositeType(itsMap.get(oppositeHostName).getType());

                                reader.readLine();//Entry address(es):
                                temp = reader.readLine().split(":");//  IP address: 10.160.2.236
                                if (temp[0].trim().equals("IP address")) {
                                    inface.setOppositeIp(temp[1].trim());
                                    reader.readLine();//Platform: cisco 2620,  Capabilities: Router
                                    temp = reader.readLine().split(",");//Interface: GigabitEthernet1/0/24,  Port ID (outgoing port): FastEthernet0/0
                                    //localInterface
                                    inface.setLocalInterface(temp[0].split(":")[1].trim());
                                    //oppositeInterface
                                    inface.setOppositeInterface(temp[1].split(":")[1].trim());
                                    interfaces.add(inface);
                                }

                            }
                        } else if (msg.endsWith("#")) {
                            device.setInterfaces(interfaces);
                            flag = false;
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //TODO 入库

        map.put(device.getHostname(),device);

        //遍历对端设备
        for(Interface inface:device.getInterfaces()){
            if(!map.containsKey(inface.getOppositeHostName())){
                Device next = new Device();
                String hostName = inface.getOppositeHostName();
                next.setHostname(hostName);
                Device d = itsMap.get(hostName);
                next.setLoginIp(d.getLoginIp());
                next.setLoginName(d.getLoginName());
                next.setLoginPwd(d.getLoginPwd());
                next.setMacAddress(d.getMacAddress());
                next.setType(d.getType());
                parse(next);
            }
        }


    }


    /**
     * 验证是否是IP
     *
     * @param text
     * @return
     */
    private static boolean isIp(String text) {
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
    private static boolean isMac(String text, String seperator) {
        String patternMac = String.format("^[a-f 0-9]{4}(%s[a-f 0-9]{4}){2}$", seperator);
        return Pattern.compile(patternMac).matcher(text).find();
    }

}
