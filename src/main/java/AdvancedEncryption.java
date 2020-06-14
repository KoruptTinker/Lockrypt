package main.java;

import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AdvancedEncryption{
    private byte[] key;
    private static final String ALGORITHM = "AES";

    public AdvancedEncryption()
    {
        byte[] randomkey=new byte[16];
        new Random().nextBytes(randomkey);
        this.key = randomkey;

    }

    public byte[] encrypt(byte[] plainText) throws Exception
    {
        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        return cipher.doFinal(plainText);
    }

    public byte[] decrypt(byte[] cipherText) throws Exception
    {
        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        return cipher.doFinal(cipherText);
    }
}