package com.guilin.java.designpattern.responsibilitychain.s2;

/**
 * Created by T57 on 2016/9/12 0012.
 * 模式中的有关框架代码
 */
public class Response {

    private String msg;

    public Response(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Response{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
