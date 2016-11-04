package com.guilin.java6.netty.telnet;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Handles a client-side channel.
 */
@Sharable
public class TelnetClientHandler extends SimpleChannelInboundHandler<String> {

    private boolean flag = false;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

//    	FileUtils.writeStringToFile(new File("/Users/guilin1/Documents/test/mac254.info"), msg+"\n", true);

        System.err.println(msg);
        if (msg.equals("Login authentication")) {
            ctx.writeAndFlush("admin\r\n");
        } else if (msg.equals("Username:")) {
            ctx.writeAndFlush("yhxt@123\r\n");
        } else if (msg.contains("Unauthorized access or use may lead to prosecution")) {
            ctx.writeAndFlush("\r\n");
        } else if (msg.equals("<LNS>")) {
            ctx.writeAndFlush("display arp\r\n");
            flag = true;
        } else if (msg.length() > 0 && flag) {
            ctx.writeAndFlush(" ");
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}