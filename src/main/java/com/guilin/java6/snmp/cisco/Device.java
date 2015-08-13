package com.guilin.java6.snmp.cisco;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guilin1 on 15/8/12.
 */
public class Device {

    private String hostname;//hostname

    private String ip;//管理ip

    private String macAddress;//MAC地址

    private String loginIp;//登录ip

    private String loginName;//登录用户名

    private String loginPwd;//登录密码

    private String type;//设备类型

    private List<Interface> interfaces;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Device() {
        interfaces = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Device{" +
                "hostname='" + hostname + '\'' +
                ", ip='" + ip + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", loginIp='" + loginIp + '\'' +
                ", loginPwd='" + loginPwd + '\'' +
                '}';
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public List<Interface> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<Interface> interfaces) {
        this.interfaces = interfaces;
    }
}
