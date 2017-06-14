package com.guilin.java.ca.pkcs7;

import com.guilin.java.ca.a.DigitalSignTool;
import org.junit.Test;

/**
 * Created by guilin on 2017/5/4.
 */
public class TestPKCS7Tool {

    @Test
    public void test1() throws Exception {
        String keyStorePath = TestPKCS7Tool.class.getResource("/testca/client.pfx").getPath();
        String keyStorePassword = "guilin";
        String keyPassword = "guilin";
        DigitalSignTool tool = DigitalSignTool.getSigner(keyStorePath, keyStorePassword, keyPassword);
        System.out.println(tool.getDigestAlgorithm());
        System.out.println(tool.getSigningAlgorithm());
        String msg = "hello 张三adf adf";//签名明文
        String code = tool.sign(msg.getBytes());//签名密文
        System.out.println(code.length());
        System.out.println(code);
        String rootCertificatePath = TestPKCS7Tool.class.getResource("/testca/ca.crt").getPath();
        tool = DigitalSignTool.getVerifier(rootCertificatePath);
        tool.verify(code, msg.getBytes(), null);
    }

    @Test
    public void test2() throws Exception {
        String keyStorePath = TestPKCS7Tool.class.getResource("/testca/client.pfx").getPath();
        String keyStorePassword = "guilin";
        String keyPassword = "guilin";
        PKCS7Tool tool = PKCS7Tool.getSigner(keyStorePath, keyStorePassword, keyPassword);
        System.out.println(tool.getDigestAlgorithm());
        System.out.println(tool.getSigningAlgorithm());
        String msg = "hello 张三adf adf";//签名明文
        String code = tool.sign(msg.getBytes());//签名密文
        System.out.println(code.length());
        System.out.println(code);
        String rootCertificatePath = TestPKCS7Tool.class.getResource("/testca/ca.crt").getPath();
        tool = PKCS7Tool.getVerifier(rootCertificatePath);
        tool.verify(code, msg.getBytes(), null);
    }

    @Test
    public void test3() throws Exception {
        String keyStorePath = TestPKCS7Tool.class.getResource("/testca/client.pfx").getPath();
        String keyStorePassword = "guilin";
        String keyPassword = "guilin";
        PKCS7Tool tool = PKCS7Tool.getSigner(keyStorePath, keyStorePassword, keyPassword);
        System.out.println(tool.getDigestAlgorithm());
        System.out.println(tool.getSigningAlgorithm());
        String msg = "hello 张三adf adf";//签名明文
        String code = tool.sign(msg.getBytes());//签名密文
        System.out.println(code.length());
        System.out.println(code);
        String rootCertificatePath = TestPKCS7Tool.class.getResource("/testca/ca.crt").getPath();
        tool = PKCS7Tool.getVerifier(rootCertificatePath);
        tool.verify(code, msg.getBytes(), null);
    }

}
