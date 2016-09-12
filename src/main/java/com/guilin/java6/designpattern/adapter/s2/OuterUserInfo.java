package com.guilin.java6.designpattern.adapter.s2;

import java.util.Map;

/**
 * Created by T57 on 2016/9/12 0012.
 * 适配器
 */
public class OuterUserInfo implements IUserInfo {

    //源
    private IOuterUserBaseInfo baseInfo;
    private IOuterUserHomeInfo homeInfo;
    private IOuterUserOfficeInfo officeInfo;

    //数据处理
    private Map<String, String> baseMap = null;
    private Map homeMap = null;
    private Map officeMap = null;

    public OuterUserInfo(IOuterUserBaseInfo baseInfo, IOuterUserHomeInfo homeInfo, IOuterUserOfficeInfo officeInfo) {
        this.baseInfo = baseInfo;
        this.homeInfo = homeInfo;
        this.officeInfo = officeInfo;

        baseMap = baseInfo.getUserBaseInfo();
        homeMap = homeInfo.getUserHomeInfo();
        officeMap = officeInfo.getUserOfficeInfo();
    }

    @Override
    public String getUserName() {
        return baseMap.get("name");
    }

    @Override
    public int getUserAge() {
        return Integer.parseInt(baseMap.get("age"));
    }

    @Override
    public String getUserHome() {
        return (String) homeMap.get("home");
    }

    @Override
    public String getUserPosition() {
        return (String) officeMap.get("position");
    }
}
