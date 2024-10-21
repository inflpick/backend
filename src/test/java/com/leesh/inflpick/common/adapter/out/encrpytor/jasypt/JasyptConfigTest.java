package com.leesh.inflpick.common.adapter.out.encrpytor.jasypt;

import org.assertj.core.api.Assertions;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;

class JasyptConfigTest {

    @Test
    void stringEncryptor() {
        // Given
        JasyptProperties jasyptProperties = new JasyptProperties("dlsvmfvlr!@");
        JasyptConfig jasyptConfig = new JasyptConfig();

        // When
        StringEncryptor encryptor = jasyptConfig.stringEncryptor(jasyptProperties);

        // Then
        String plainText = "AbC123!dEfGhIjKBCSWAPPALLL//lMadw@#dwako!!nOpQrStUvW33A3xYz1234567890+/ABCD";
        String encrypted = encryptor.encrypt(plainText);
        String decrypted = encryptor.decrypt(encrypted);
        Assertions.assertThat(plainText).isEqualTo(decrypted);
    }

}