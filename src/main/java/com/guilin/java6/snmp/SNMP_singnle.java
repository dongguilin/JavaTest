package com.guilin.java6.snmp;

import org.apache.commons.io.FileUtils;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SNMP_singnle {


    private static Map<String, String> tempOidval = new HashMap<String, String>();

    //    private static String address = "udp:10.160.2.254/161";
    private static String community = "anq.tivoli92";


    public void statusJK() {

        String[] ips = {"10.160.2.254"};
        for (String ip : ips) {
            handler(ip, community);
        }

    }

    private void test(String ip){
        TransportMapping transport = null;

        try {

            String community = "udp:%s/161";

            //设置管理进程的IP的端口
            Address targetAddress = GenericAddress.parse(String.format(community, ip));

            transport = new DefaultUdpTransportMapping();
            Snmp snmp = new Snmp(transport);
            transport.listen();// 监听

            //设置target
            CommunityTarget target = new CommunityTarget();
            target.setCommunity(new OctetString(community));
            target.setAddress(targetAddress);// 设置目标Agent地址
            target.setRetries(2);// 重试次数
            target.setTimeout(5000);// 超时设置
            target.setVersion(SnmpConstants.version2c);// 版本

            transport.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (transport != null) {
                try {
                    transport.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void handler(String ip, String community) {
        TransportMapping transport = null;

        try {

            //设置管理进程的IP的端口
            Address targetAddress = GenericAddress.parse(String.format("udp:%s/161", ip));

            transport = new DefaultUdpTransportMapping();
            Snmp snmp = new Snmp(transport);
            transport.listen();// 监听

            //设置target
            CommunityTarget target = new CommunityTarget();
            target.setCommunity(new OctetString(community));
            target.setAddress(targetAddress);// 设置目标Agent地址
            target.setRetries(2);// 重试次数
            target.setTimeout(5000);// 超时设置
            target.setVersion(SnmpConstants.version2c);// 版本

            //hrStorage
//            Map<String, String> hrStorage = query(snmp, target, "1.3.6.1.2.1.25.2.3.1.1", "1.3.6.1.2.1.25.3.2.1.1");
//            {
//                File file = new File("/Users/guilin1/Documents/test/bjyh/memory.log");
//                if (file.exists() && file.length() == 0 || !file.exists()) {
//                    FileUtils.write(file, "物理内存[" + hrStorage.get(Constants.PHYSICAL_MEMORY_SIZE_OID) + "]\r\n", true);
//                }
//                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
//                StringBuffer buffer = new StringBuffer();
//                buffer.append(format.format(new Date())).append(",")
//                        .append(hrStorage.get(Constants.PHYSICAL_MEMORY_USEDSIZE_OID)).append("\r\n");
//                FileUtils.write(file, buffer.toString(), true);
//                System.out.println(buffer.toString());
//            }

            cpu(snmp, target);
            memory(snmp, target);
            temperature(snmp, target);

            transport.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (transport != null) {
                try {
                    transport.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //cpu
    public static void cpu(Snmp snmp, CommunityTarget target) throws Exception {
        File file = new File("/Users/guilin1/Documents/test2/bjyh/cpu.log");

        String[] cpmCPUTotal5sec = query(snmp, target, "1.3.6.1.4.1.9.9.109.1.1.1.1.3.1");

        String[] cpmCPUTotal5min = query(snmp, target, "1.3.6.1.4.1.9.9.109.1.1.1.1.5.1");

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        StringBuffer buffer = new StringBuffer();
        String dateStr = format.format(new Date());
        buffer.append(dateStr)
                .append(",").append(cpmCPUTotal5sec[1])
                .append(",").append(cpmCPUTotal5min[1])
                .append("\r\n");
        FileUtils.write(file, buffer.toString(), true);
    }

    //温度
    public static void temperature(Snmp snmp, CommunityTarget target) throws Exception {
        File file = new File("/Users/guilin1/Documents/test2/bjyh/temperature.log");

        String[] vt1OutletTemperature = query(snmp, target, "1.3.6.1.4.1.9.9.13.1.3.1.3.1");

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        StringBuffer buffer = new StringBuffer();
        String dateStr = format.format(new Date());
        buffer.append(dateStr)
                .append(",").append(vt1OutletTemperature[1])
                .append("\r\n");
        FileUtils.write(file, buffer.toString(), true);
    }


    //内存
    public static void memory(Snmp snmp, CommunityTarget target) throws Exception {
        File file = new File("/Users/guilin1/Documents/test2/bjyh/memory.log");

        String[] ciscoMemoryPoolUsed = query(snmp, target, "1.3.6.1.4.1.9.9.48.1.1.1.5.1");

        String[] ciscoMemoryPoolFree = query(snmp, target, "1.3.6.1.4.1.9.9.48.1.1.1.6.1");

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        StringBuffer buffer = new StringBuffer();
        String dateStr = format.format(new Date());
        buffer.append(dateStr)
                .append(",").append(ciscoMemoryPoolUsed[1])
                .append(",").append(ciscoMemoryPoolFree[1])
                .append("\r\n");
        FileUtils.write(file, buffer.toString(), true);
    }

//    public static void memory2(Snmp snmp, CommunityTarget target) throws Exception {
//        File file = new File("/Users/guilin1/Documents/test/bjyh/memory.log");
//        if (file.exists() && file.length() == 0 || !file.exists()) {
//
//        }
//
//        Map<String, String> entSensorValue = query(snmp, target, "1.3.6.1.4.1.2021.4", "1.3.6.1.4.1.2021.5");
//
//        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
//        Iterator<Map.Entry<String, String>> iterator = entSensorValue.entrySet().iterator();
//        StringBuffer buffer = new StringBuffer();
//        while (iterator.hasNext()) {
//            Map.Entry<String, String> entry = iterator.next();
//            String key = entry.getKey();
//            String value = entry.getValue();
//            buffer.append(format.format(new Date())).append(",")
//                    .append(key).append(",")
//                    .append(value).append("\r\n");
//        }
//        buffer.append("\r\n");
//        FileUtils.write(file, buffer.toString(), true);
//    }

//    @Test
//    public void testTemperature() throws Exception {
//        //设置管理进程的IP的端口
//        Address targetAddress = GenericAddress.parse(address);
//
//        TransportMapping transport = new DefaultUdpTransportMapping();
//        Snmp snmp = new Snmp(transport);
//        transport.listen();// 监听
//
//        //设置target
//        CommunityTarget target = new CommunityTarget();
//        target.setCommunity(new OctetString(community));
//        target.setAddress(targetAddress);// 设置目标Agent地址
//        target.setRetries(2);// 重试次数
//        target.setTimeout(5000);// 超时设置
//        target.setVersion(SnmpConstants.version2c);// 版本
//
//        temperature(snmp, target);
//        transport.close();
//    }


    public static void main(String[] args) throws Exception {
        try {

            //设置管理进程的IP的端口
//            Address targetAddress = GenericAddress.parse("udp:10.0.1.1/161");
            Address targetAddress = GenericAddress.parse("udp:10.0.1.132/161");

            TransportMapping transport = new DefaultUdpTransportMapping();
            Snmp snmp = new Snmp(transport);
            transport.listen();// 监听

            //设置target
            CommunityTarget target = new CommunityTarget();
//            target.setCommunity(new OctetString("aleiye405$)%r"));
            target.setCommunity(new OctetString("public"));
            target.setAddress(targetAddress);// 设置目标Agent地址
            target.setRetries(2);// 重试次数
            target.setTimeout(5000);// 超时设置
            target.setVersion(SnmpConstants.version2c);// 版本
            //端口数目
            int maxRepetitions = totalPortNum(snmp, target);
            //接口
            Map<String, String> ipmap = interfaces(snmp, target, maxRepetitions, "1.3.6.1.2.1.4.20.1.2.", "1.3.6.1.2.1.4.20.1.3.");
            System.out.println(ipmap);

            //接口的物理地址
            Map<String, String> portMac = interfaces(snmp, target, maxRepetitions, "1.3.6.1.2.1.2.2.1.6.", ".1.3.6.1.2.1.2.2.1.7");
            System.out.println(portMac);

            //接口信息描述
            Map<String, String> portDesc = interfaces(snmp, target, maxRepetitions, "1.3.6.1.2.1.2.2.1.2.", ".1.3.6.1.2.1.2.2.1.3");
            System.out.println(portDesc);

            //接口当前带宽[bps]
            Map<String, String> brand = interfaces(snmp, target, maxRepetitions, "1.3.6.1.2.1.2.2.1.5.", ".1.3.6.1.2.1.2.2.1.6");
            System.out.println(brand);

            //ifDescr
            Map<String, String> ifDescr = interfaces(snmp, target, maxRepetitions, "1.3.6.1.2.1.2.2.1.2.", ".1.3.6.1.2.1.2.2.1.3");
            System.out.println(ifDescr);

            //atPhyAddress
            Map<String, String> atPhyAddress = at(snmp, target, "1.3.6.1.2.1.3.1.1.2.", "1.3.6.1.2.1.3.1.1.3");
            System.out.println(atPhyAddress);

            //hrStorage
            Map<String, String> hrStorage = query(snmp, target, "1.3.6.1.2.1.25.2.3.1.1", "1.3.6.1.2.1.25.3.2.1.1");
            {
                System.out.println("物理内存[" + hrStorage.get(Constants.PHYSICAL_MEMORY_SIZE_OID) + ","
                        + hrStorage.get(Constants.PHYSICAL_MEMORY_USEDSIZE_OID) + "]");
            }


            transport.close();

        } catch (IOException e) {
            e.printStackTrace();

        }
        // Thread.sleep(30000);
    }

    private static int totalPortNum(Snmp snmp, CommunityTarget target) throws Exception {
        //.1.3.6.1.2.1.2.1.0
        PDU request = new PDU();
        request.setType(PDU.GET);// 操作类型GET
        request.add(new VariableBinding(new OID(".1.3.6.1.2.1.2.1.0")));
        ResponseEvent respEvt = snmp.send(request, target);
        if (respEvt != null && respEvt.getResponse() != null) {
            Vector<? extends VariableBinding> revBindings = respEvt
                    .getResponse().getVariableBindings();
            return revBindings.get(0).getVariable().toInt();

        }
        return -1;
    }

    private static String[] query(Snmp snmp, CommunityTarget target, String oid) throws Exception {
        PDU request = new PDU();
        request.setType(PDU.GET);
        request.add(new VariableBinding(new OID(oid)));
        ResponseEvent respEvt = snmp.send(request, target);
        if (respEvt != null && respEvt.getResponse() != null) {
            Vector<? extends VariableBinding> revBindings = respEvt
                    .getResponse().getVariableBindings();
            for (int i = 0; i < revBindings.size(); i++) {
                VariableBinding vbs = revBindings.elementAt(i);
                String coid = vbs.getOid().toString();
                return new String[]{coid, vbs.getVariable().toString()};
            }
        }
        return null;
    }

    private static Map<String, String> query(Snmp snmp, CommunityTarget target, String prefixOid, String suffixOid) throws Exception {
        Map<String, String> map = new HashMap<String, String>();

        String oid = prefixOid;

        boolean flag = true;
        while (flag) {
            PDU request = new PDU();
            request.setType(PDU.GETBULK);
            request.setMaxRepetitions(10);
            request.add(new VariableBinding(new OID(oid)));
            ResponseEvent respEvt = snmp.send(request, target);
            if (respEvt != null && respEvt.getResponse() != null) {
                Vector<? extends VariableBinding> revBindings = respEvt
                        .getResponse().getVariableBindings();
                for (int i = 0; i < revBindings.size(); i++) {
                    VariableBinding vbs = revBindings.elementAt(i);
                    String coid = vbs.getOid().toString();
                    if (!coid.contains(prefixOid)) {
                        flag = false;
                        break;
                    }
                    map.put(coid, vbs.getVariable().toString());
                }
                oid = revBindings.get(revBindings.size() - 1).getOid().toString();
            }
        }
        return map;
    }

    private static Map<String, String> at(Snmp snmp, CommunityTarget target, String prefixOid, String suffixOid) throws Exception {
        Map<String, String> map = new HashMap<String, String>();

        String oid = prefixOid;

        boolean flag = true;
        while (flag) {
            PDU request = new PDU();
            request.setType(PDU.GETBULK);
            request.setMaxRepetitions(10);
            request.add(new VariableBinding(new OID(oid)));
            ResponseEvent respEvt = snmp.send(request, target);
            if (respEvt != null && respEvt.getResponse() != null) {
                Vector<? extends VariableBinding> revBindings = respEvt
                        .getResponse().getVariableBindings();
                for (int i = 0; i < revBindings.size(); i++) {
                    VariableBinding vbs = revBindings.elementAt(i);
                    if (vbs.getOid().toString().contains(suffixOid)) {
                        flag = false;
                        break;
                    }

                    String coid = vbs.getOid().toString();
                    oid = coid;
                    if (coid != null) {
                        String sip = coid.replace(prefixOid, "");
                        map.put(sip, vbs.getVariable().toString());
                    }
                }
            }
        }
        return map;
    }

    private static Map<String, String> interfaces(Snmp snmp, CommunityTarget target, int maxRepetitions, String prefixOid, String suffixOid) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        PDU request = new PDU();
        request.setType(PDU.GETBULK);
        request.setMaxRepetitions(maxRepetitions);
        request.add(new VariableBinding(new OID(prefixOid)));
        ResponseEvent respEvt = snmp.send(request, target);
        if (respEvt != null && respEvt.getResponse() != null) {
            Vector<? extends VariableBinding> revBindings = respEvt
                    .getResponse().getVariableBindings();
            for (int i = 0; i < revBindings.size(); i++) {
                VariableBinding vbs = revBindings.elementAt(i);
                if (vbs.getOid().toString().contains(suffixOid))
                    break;
                String coid = vbs.getOid().toString();
                if (coid != null) {
                    String sip = coid.replace(prefixOid, "");
                    map.put(sip, vbs.getVariable().toString());
                }
            }
        }
        return map;
    }

    private static Map<String, Map<String, String>> convert(Map<String, String> ipPortMap, Map<String, String> portMacMap) {
        Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>(ipPortMap.size());
        Iterator<Map.Entry<String, String>> iterator = ipPortMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> mapEntry = iterator.next();
            String ip = mapEntry.getKey();
            String port = mapEntry.getValue();
            if (portMacMap.containsKey(port)) {
                String mac = portMacMap.get(port);
                Map<String, String> m = new HashMap<String, String>(3);
                m.put("ip", ip);
                m.put("port", port);
                m.put("mac", mac);
                map.put(ip, m);
            }
        }
        return map;
    }

}
