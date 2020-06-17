package com.lockrypt.backend;

import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AdvancedEncryption{
    public static AdvancedEncryption instanceAE;
    private byte[] key;
    private static final String ALGORITHM = "AES";

    public AdvancedEncryption()
    {
        this.key=new byte[16];
    }

    public void setKey(String key){
        if(key.length()<16){
            for(int i=key.length();i<16;i++){
                key+="0";
            }
        }
        this.key=key.getBytes(StandardCharsets.UTF_8);
    }

    public byte[] encrypt(byte[] plainText, String keyTemp) throws Exception
    {
        this.setKey(keyTemp);
        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        return cipher.doFinal(plainText);
    }

    public byte[] decrypt(byte[] cipherText, String keyTemp) throws Exception
    {
        this.setKey(keyTemp);
        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        return cipher.doFinal(cipherText);
    }

    public static AdvancedEncryption getInstance(){
        if(instanceAE == null){
            instanceAE=new AdvancedEncryption();
        }
        return instanceAE;
    }
}