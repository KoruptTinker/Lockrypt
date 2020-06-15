package main.java;
import java.nio.charset.StandardCharsets;

import main.java.AdvancedEncryption;
import main.java.fileManager;
import main.java.blockchain.*;
public class lockrypt {
    public static void main(String[] args) throws Exception{ 
        AdvancedEncryption AE=new AdvancedEncryption();
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
            block_chain.Add_Block(ciphertext,cipher[i]);
            FM.deleteFile(FM.fileList[i]);
        }
        for(int i=0;i<FM.fileList.length;i++){
            byte[] data=block_chain.blockchain.get(i+1).get_data2();
            System.out.println("CIPHER TEXT: "+block_chain.blockchain.get(i+1).get_data());
            System.out.println("Deciphered Text: "+new String(AE.decrypt(data)));
        }
    }
}