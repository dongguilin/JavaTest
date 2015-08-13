package com.guilin.java6.snmp;

import java.io.IOException;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.NetworkInterfaceAddress;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;

public class SnmpTest {
    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("java.library.path"));

        NetworkInterface[] devices = JpcapCaptor.getDeviceList();
        for (int i = 0; i < devices.length; i++) {
            System.out.println(i + ": " + devices[i].name + "("
                    + devices[i].description + ")");
            System.out.println(" datalink: " + devices[i].datalink_name + "("
                    + devices[i].datalink_description + ")");
            System.out.print(" MAC address:");
            for (byte b : devices[i].mac_address)
                System.out.print(Integer.toHexString(b & 0xff) + ":");
            System.out.println();
            for (NetworkInterfaceAddress a : devices[i].addresses)
                System.out.println(" address:" + a.address + " " + a.subnet
                        + " " + a.broadcast);
        }
        int index = 2;
        JpcapCaptor captor = JpcapCaptor.openDevice(devices[index], 65535,
                false, 20);
        captor.setFilter("dst host 192.168.13.177", true);
        Packet pt = null;
        int i = 1;
        while (i == 1) {
            pt = captor.getPacket();
            if (pt != null) {
                if (pt instanceof TCPPacket) {
                    TCPPacket tcpp = (TCPPacket) pt;
                    System.out.println(tcpp);
                }
            }
        }
        captor.close();
    }
}
