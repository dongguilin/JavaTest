//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.guilin.java.ca.a;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import sun.misc.BASE64Decoder;
import sun.security.pkcs.ContentInfo;
import sun.security.pkcs.PKCS7;
import sun.security.pkcs.PKCS9Attributes;
import sun.security.pkcs.SignerInfo;
import sun.security.util.DerValue;
import sun.security.x509.AlgorithmId;
import sun.security.x509.X500Name;

import javax.security.auth.x500.X500Principal;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

public class DigitalSignTool {
    private static final int SIGNER = 1;
    private static final int VERIFIER = 2;
    private int mode = 0;
    private String digestAlgorithm = "SHA1";
    private String signingAlgorithm = "SHA1withRSA";
    private X509Certificate[] certificates = null;
    private PrivateKey privateKey = null;
    private Certificate rootCertificate = null;

    private DigitalSignTool(int mode) {
        this.mode = mode;
    }

    public static DigitalSignTool getSigner(String keyStorePath, String keyStorePassword, String keyPassword) throws GeneralSecurityException, IOException {
        KeyStore keyStore = null;
        if (keyStorePath.toLowerCase().endsWith(".pfx")) {
            keyStore = KeyStore.getInstance("PKCS12");
        } else {
            keyStore = KeyStore.getInstance("JKS");
        }

        FileInputStream fis = null;

        try {
            fis = new FileInputStream(keyStorePath);
            keyStore.load(fis, keyStorePassword.toCharArray());
        } finally {
            if (fis != null) {
                fis.close();
            }

        }

        Enumeration aliases = keyStore.aliases();
        String keyAlias = null;
        if (aliases != null) {
            while (aliases.hasMoreElements()) {
                keyAlias = (String) aliases.nextElement();
                Certificate[] certs = keyStore.getCertificateChain(keyAlias);
                if (certs != null && certs.length != 0) {
                    X509Certificate cert = (X509Certificate) certs[0];
                    if (matchUsage(cert.getKeyUsage(), 1)) {
                        try {
                            cert.checkValidity();
                        } catch (CertificateException var12) {
                            ;
                        }
                    }
                }
            }
        }

        if (keyAlias == null) {
            throw new GeneralSecurityException("None certificate for sign in this keystore");
        } else {
            X509Certificate[] certificates = (X509Certificate[]) null;
            if (keyStore.isKeyEntry(keyAlias)) {
                Certificate[] certs = keyStore.getCertificateChain(keyAlias);

                int i;
                for (i = 0; i < certs.length; ++i) {
                    if (!(certs[i] instanceof X509Certificate)) {
                        throw new GeneralSecurityException("Certificate[" + i + "] in chain '" + keyAlias + "' is not a X509Certificate.");
                    }
                }

                certificates = new X509Certificate[certs.length];

                for (i = 0; i < certs.length; ++i) {
                    certificates[i] = (X509Certificate) certs[i];
                }
            } else {
                if (!keyStore.isCertificateEntry(keyAlias)) {
                    throw new GeneralSecurityException(keyAlias + " is unknown to this keystore");
                }

                Certificate cert = keyStore.getCertificate(keyAlias);
                if (cert instanceof X509Certificate) {
                    certificates = new X509Certificate[]{(X509Certificate) cert};
                }
            }

            PrivateKey privateKey = (PrivateKey) keyStore.getKey(keyAlias, keyPassword.toCharArray());
            if (privateKey == null) {
                throw new GeneralSecurityException(keyAlias + " could not be accessed");
            } else {
                DigitalSignTool tool = new DigitalSignTool(1);
                tool.certificates = certificates;
                tool.privateKey = privateKey;
                return tool;
            }
        }
    }

    public static DigitalSignTool getVerifier(String rootCertificatePath) throws GeneralSecurityException, IOException {
        FileInputStream fis = null;
        Certificate rootCertificate = null;

        try {
            fis = new FileInputStream(rootCertificatePath);
            CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");

            try {
                rootCertificate = certificatefactory.generateCertificate(fis);
            } catch (Exception var10) {
                BASE64Decoder base64decoder = new BASE64Decoder();
                InputStream is = new ByteArrayInputStream(base64decoder.decodeBuffer(fis));
                rootCertificate = certificatefactory.generateCertificate(is);
            }
        } finally {
            if (fis != null) {
                fis.close();
            }

        }

        DigitalSignTool tool = new DigitalSignTool(2);
        tool.rootCertificate = rootCertificate;
        return tool;
    }

    public String sign(byte[] data) throws GeneralSecurityException, IOException {
        if (this.mode != 1) {
            throw new IllegalStateException("call a PKCS7Tool instance not for signature.");
        } else {
            Signature signer = Signature.getInstance(this.signingAlgorithm);
            signer.initSign(this.privateKey);
            signer.update(data, 0, data.length);
            byte[] signedAttributes = signer.sign();
            ContentInfo contentInfo = null;
            contentInfo = new ContentInfo(ContentInfo.DATA_OID, (DerValue) null);
            X509Certificate x509 = this.certificates[this.certificates.length - 1];
            BigInteger serial = x509.getSerialNumber();
            SignerInfo si = new SignerInfo(new X500Name(x509.getIssuerDN().getName()),
                    serial, AlgorithmId.get(this.digestAlgorithm),
                    (PKCS9Attributes) null,
                    new AlgorithmId(AlgorithmId.RSAEncryption_oid),
                    signedAttributes, (PKCS9Attributes) null);
            SignerInfo[] signerInfos = new SignerInfo[]{si};
            AlgorithmId[] digestAlgorithmIds = new AlgorithmId[]{AlgorithmId.get(this.digestAlgorithm)};
            PKCS7 p7 = new PKCS7(digestAlgorithmIds, contentInfo, this.certificates, signerInfos);
            ByteArrayOutputStream baout = new ByteArrayOutputStream();
            p7.encodeSignedData(baout);
            return Base64.encode(baout.toByteArray());
        }
    }

    public void verify(String signature, byte[] data, String dn) throws IOException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, CertificateException, NoSuchProviderException {
        if (this.mode != 2) {
            throw new IllegalStateException("call a PKCS7Tool instance not for verify.");
        } else {
            PKCS7 p7 = new PKCS7(Base64.decode(signature));
            SignerInfo[] sis = p7.verify(data);
            if (sis == null) {
                throw new SignatureException("Signature failed verification, data has been tampered");
            } else {
                for (int i = 0; i < sis.length; ++i) {
                    SignerInfo si = sis[i];
                    X509Certificate cert = si.getCertificate(p7);
                    cert.checkValidity();
                    cert.verify(this.rootCertificate.getPublicKey());
                    if (i == 0 && dn != null) {
                        X500Principal name = cert.getSubjectX500Principal();
                        if (!dn.equals(name.getName("RFC1779")) && !(new X500Principal(dn)).equals(name)) {
                            throw new SignatureException("Signer dn '" + name.getName("RFC1779") + "' does not matchs '" + dn + "'");
                        }
                    }
                }

            }
        }
    }

    private static boolean matchUsage(boolean[] keyUsage, int usage) {
        if (usage != 0 && keyUsage != null) {
            for (int i = 0; i < Math.min(keyUsage.length, 32); ++i) {
                if ((usage & 1 << i) != 0 && keyUsage[i]) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public final String getDigestAlgorithm() {
        return this.digestAlgorithm;
    }

    public final void setDigestAlgorithm(String digestAlgorithm) {
        this.digestAlgorithm = digestAlgorithm;
    }

    public final String getSigningAlgorithm() {
        return this.signingAlgorithm;
    }

    public final void setSigningAlgorithm(String signingAlgorithm) {
        this.signingAlgorithm = signingAlgorithm;
    }
}
