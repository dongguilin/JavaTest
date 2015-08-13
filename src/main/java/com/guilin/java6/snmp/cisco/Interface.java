package com.guilin.java6.snmp.cisco;

/**
 * Created by guilin1 on 15/8/12.
 * 接口
 */
public class Interface {

    private String id;//记录ID

    private String localHostName;//本端hostname

    private String localIp;//本端IP

    private String localInterface;//本端接口

    private String localType;//本端设备类型（核心、汇聚、接入）

    private String oppositeHostName;//对端hostname

    private String oppositeIp;//对端IP

    private String oppositeInterface;//对端接口

    private String oppositeType;//对端设备类型

    public Interface() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocalHostName() {
        return localHostName;
    }

    public void setLocalHostName(String localHostName) {
        this.localHostName = localHostName;
    }

    public String getLocalIp() {
        return localIp;
    }

    public void setLocalIp(String localIp) {
        this.localIp = localIp;
    }

    public String getLocalInterface() {
        return localInterface;
    }

    public void setLocalInterface(String localInterface) {
        this.localInterface = localInterface;
    }

    public String getLocalType() {
        return localType;
    }

    public void setLocalType(String localType) {
        this.localType = localType;
    }

    public String getOppositeHostName() {
        return oppositeHostName;
    }

    public void setOppositeHostName(String oppositeHostName) {
        this.oppositeHostName = oppositeHostName;
    }

    public String getOppositeIp() {
        return oppositeIp;
    }

    public void setOppositeIp(String oppositeIp) {
        this.oppositeIp = oppositeIp;
    }

    public String getOppositeInterface() {
        return oppositeInterface;
    }

    public void setOppositeInterface(String oppositeInterface) {
        this.oppositeInterface = oppositeInterface;
    }

    public String getOppositeType() {
        return oppositeType;
    }

    public void setOppositeType(String oppositeType) {
        this.oppositeType = oppositeType;
    }
}
