package com.guilin.java.designpattern.adapter.s2;

/**
 * Created by T57 on 2016/9/12 0012.
 * 对象适配器是对象的合成关系
 */
public class Client {
    public static void main(String[] args) {
        IOuterUserBaseInfo<String, String> baseInfo = new OuterUserBaseInfo();
        IOuterUserHomeInfo homeInfo = new OuterUserHomeInfo();
        IOuterUserOfficeInfo officeInfo = new OuterUserOfficeInfo();

        IUserInfo userInfo = new OuterUserInfo(baseInfo, homeInfo, officeInfo);
        System.out.println(userInfo.getUserName());
        System.out.println(userInfo.getUserAge());
        System.out.println(userInfo.getUserHome());
        System.out.println(userInfo.getUserPosition());
    }
}
