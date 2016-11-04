package com.guilin.java.designpattern.responsibilitychain.s2;

/**
 * Created by T57 on 2016/9/12 0012.
 * 抽象处理者
 */
public abstract class Handler {
    private Handler nextHandler;

    //设置下一个处理者
    public void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }

    //每个处理者都有一个处理级别
    abstract Level getHandlerLevel();

    //每个处理者都必须实现处理任务
    abstract Response echo(Request request);

    public final Response handleMessage(Request request) {
        Response response = null;
        if (this.getHandlerLevel().equals(request.getRequestLevel())) {
            response = this.echo(request);
        } else {//不属于自己的处理级别
            if (this.nextHandler != null) {
                response = this.nextHandler.handleMessage(request);
            } else {
                //没有适当的处理者，业务自行处理
            }
        }
        return response;
    }

}
