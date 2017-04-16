package com.guilin.java.plsql;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.*;

/**
 * Created by guilin on 2017/4/12.
 */
public class TestProcedure {

    @Test
    public void testWebService1() {
        Connection conn = null;
        try {
            Class.forName("sgcc.nds.jdbc.driver.NdsDriver");//oracle穿墙驱动
            conn = DriverManager.getConnection("jdbc:nds://192.168.60.231:18600/v_18600_gfyun?appname=App_gfyun&errorLevel=onLine&errorLogInfo=*",
                    "gfyun", "gfyun");
            CallableStatement cstmt = conn
                    .prepareCall("{ call WSPROXY_SEND_REQUEST_PRO(?,?,?,?,?,?)}");
            cstmt.setString(1, "http://10.158.249.161:8001/web/services/sgpmsService?wsdl");
            cstmt.setString(2, "http://sgcscws.webservice.core.epm");
            cstmt.setString(3, "sgpmsService");
            cstmt.setString(4, "sgpmsGetData");
            cstmt.setString(5, "<ORDER>\n" +
                    "<servicecode>FC_004</servicecode>\n" +
                    "<source>02</source>\n" +
                    "<target>37101</target>\n" +
                    "<data>\n" +
                    "  <channelType>01</channelType>\n" +
                    "  <proCode>37101</proCode>\n" +
                    "  <selectNo>160721191552</selectNo>\n" +
                    "  <beginTime>2015-10-02 22:10:02</beginTime>\n" +
                    "  <endTime>2017-10-02 22:10:02</endTime>\n" +
                    "</data>\n" +
                    "</ORDER>");
            cstmt.registerOutParameter(6, Types.VARCHAR);
            cstmt.executeQuery();
            System.out.println(cstmt.getString(6));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    @Test
    public void testWebService2() throws Exception {
        Class.forName("sgcc.nds.jdbc.driver.NdsDriver");
        Connection conn = DriverManager.getConnection("jdbc:nds://192.168.60.231:18600/v_18600_gfyun?appname=App_gfyun&errorLevel=onLine&errorLogInfo=*",
                "gfyun", "gfyun");
        CallableStatement cstmt = conn
                .prepareCall("{ call WSPROXY_SEND_REQUEST_PRO2(?)}");
        cstmt.registerOutParameter(1, Types.CLOB);
        cstmt.executeQuery();
        Clob clob = cstmt.getClob(1);
        String str = clobToString(clob);
        System.out.println(str.length());
        System.out.println(str);
        conn.close();
    }

    public static String clobToString(Clob clob) {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(clob.getCharacterStream());
            String s = br.readLine();
            while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
                sb.append(s);
                s = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    br = null;
                }
            }
        }
        return sb.toString();
    }
}
