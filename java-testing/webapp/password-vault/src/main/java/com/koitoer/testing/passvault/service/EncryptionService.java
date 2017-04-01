package com.koitoer.testing.passvault.service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

/**
 * Encryption Service
 * Created by mmena on 3/31/17.
 * http://stackoverflow.com/questions/1205135/how-to-encrypt-string-in-java
 */
public class EncryptionService {

    private String keyStr;
    private Key aesKey = null;
    private Cipher cipher = null;

    /**
     * Init method
     * @throws Exception
     */
    synchronized private void init() throws Exception {
        if (keyStr == null || keyStr.length() != 16) {
            throw new Exception("bad aes key configured");
        }
        if (aesKey == null) {
            aesKey = new SecretKeySpec(keyStr.getBytes(), "AES");
            cipher = Cipher.getInstance("AES");
        }
    }

    /**
     * Encrypt password method
     * @param password
     * @return
     * @throws Exception
     */
    public char[] encrypt(String password) throws Exception {
        init();
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        return this.toHexString(cipher.doFinal(password.getBytes())).toCharArray();
    }

    /**
     * Decrypt password
     * @param password
     * @return
     * @throws Exception
     */
    public String decrypt(char[] password) throws Exception {
        init();
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        return new String(cipher.doFinal(toByteArray(String.valueOf(password))));
    }


    private String toHexString(byte[] array) {
        return DatatypeConverter.printHexBinary(array);
    }


    private byte[] toByteArray(String s) {
        return DatatypeConverter.parseHexBinary(s);
    }
}
