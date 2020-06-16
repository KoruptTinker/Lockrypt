package com.lockrypt.backend;

import java.nio.charset.StandardCharsets;
import com.lockrypt.backend.AdvancedEncryption;
import com.lockrypt.backend.fileManager;
import com.lockrypt.backend.blockchain.*;
import java.util.Scanner;

public class lockrypt {
    public String username;
    private String password;

    public lockrypt(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter your username: ");
        this.username=sc.nextLine();
        System.out.println("Enter your password [16 characters only]: ");
        boolean flag=false;
        while(!flag){
            flag=setPass(sc.nextLine());
            if(!flag){
                System.out.println("Length doesn't match the required length. Try again!");
            }
        }
        sc.close();
    }
    public String getPass(){
        return this.password;
    }

    public boolean setPass(String pass){
        if(pass.length()==16){
            this.password=pass;
            return true;
        }
        return false;
    }
    public static void main(String[] args) throws Exception{ 
        lockrypt user=new lockrypt();
        AdvancedEncryption AE=new AdvancedEncryption(user);
        fileManager FM=new fileManager();
        block_chain.Create_Blockchain();
        byte[][] cipher=new byte[FM.fileList.length][];
        byte[][] decipher=new byte[FM.fileList.length][];
        for(int i=0;i<FM.fileList.length;i++){
            FM.getFileBytes(FM.fileList[i]);
        }
        for(int i=0;i<FM.fileList.length;i++){
            cipher[i]=AE.encrypt(FM.fileBytes[i]);
            String ciphertext=new String(cipher[i]);
            block_chain.Add_Block(ciphertext,cipher[i],FM.fileList[i].getName());
            FM.deleteFile(FM.fileList[i]);
        }
        for(int i=0;i<FM.fileList.length;i++){
            byte[] data=block_chain.blockchain.get(i+1).get_data2();
            FM.createFileEncrypted(data,FM.fileList[i].getName());
            byte[] deciphered=AE.decrypt(block_chain.blockchain.get(i+1).get_data2());
            FM.createFileDecrypted(deciphered,FM.fileList[i].getName());
        }
    }
}