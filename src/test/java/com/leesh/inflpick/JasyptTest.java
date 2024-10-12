package com.leesh.inflpick;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;

public class JasyptTest {

    @Test
    public void test() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        encryptor.setPassword("dlsvmfvlr!@");
        String encrypt = encryptor.encrypt("http://localhost:8080/login/oauth2/code/kakao");
        String decrypt = encryptor.decrypt(encrypt);
        System.out.println("encrypt = " + encrypt);
        System.out.println("decrypt = " + decrypt);
    }
}
