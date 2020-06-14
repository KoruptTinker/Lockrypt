package main.java;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AdvancedEncryption{
    private byte[] key;

    public byte[] generateKey(){
        byte[] randomkey=new byte[16];
        new Random().nextBytes(randomkey);
        System.out.println(randomkey[0]+randomkey[2]);
        return key;

    }
    private static final String ALGORITHM = "AES";

    public AdvancedEncryption()
    {
        byte[] randomkey=new byte[16];
        new Random().nextBytes(randomkey);
        System.out.println(randomkey[0]+randomkey[2]);
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

    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        String plaintext=sc.nextLine();
        byte[] plainText = plaintext.getBytes(StandardCharsets.UTF_8);
        AdvancedEncryption advancedEncryption = new AdvancedEncryption();
        byte[] cipherText=advancedEncryption.encrypt(plainText);
        byte[] decipheredText=advancedEncryption.decrypt(cipherText);
        System.out.println(new String(cipherText));
        System.out.println(new String(decipheredText));
    }
}