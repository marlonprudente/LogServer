/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syslogserver.Utils;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Marlon
 */
public class Cryptograph {
    Cipher cipher;
    SecureRandom random;
    SecretKey secretKey;
    DESKeySpec desKey ;
    private byte[] key;
    private static final String transformation = "DES";
    
    public Cryptograph(String password) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException{
        desKey = new DESKeySpec(password.getBytes());
        random = new SecureRandom();
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        secretKey = keyFactory.generateSecret(desKey);
        this.cipher = Cipher.getInstance(transformation);
    }
    
    
        public String encrypt(String phrase) throws InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {

        // Length is 16 byte
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] phraseByte = phrase.getBytes("UTF8");
        byte[] cipherPhrase = cipher.doFinal(phraseByte);
        String encryptedPhrase = Base64.encodeBase64String(cipherPhrase);
        

        return encryptedPhrase;
    }
        
        public String decrypt(String phrase) throws InvalidKeyException, IOException, ClassNotFoundException, IllegalBlockSizeException, BadPaddingException {
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] byteDecryptedText = cipher.doFinal(Base64.decodeBase64(phrase));
        //String plaintextBack = new String(byteDecryptedText);
        return  new String(byteDecryptedText, "UTF8");
    }
    
}
