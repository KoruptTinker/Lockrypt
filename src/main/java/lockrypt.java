package main.java;
import main.java.AdvancedEncryption;
import main.java.fileManager;
public class lockrypt {
    public static void main(String[] args) throws Exception{ 
        AdvancedEncryption AE=new AdvancedEncryption();
        fileManager FM=new fileManager();
        byte[][] cipher=new byte[2][];
        byte[][] decipher=new byte[2][];
        for(int i=0;i<FM.fileList.length;i++){
            FM.getFileBytes(FM.fileList[i]);
        }
        for(int i=0;i<FM.fileList.length;i++){
            cipher[i]=AE.encrypt(FM.fileBytes[i]);
            FM.deleteFile(FM.fileList[i]);
            System.out.println(new String(cipher[i]));
        }
        for(int i=0;i<FM.fileList.length;i++){
            decipher[i]=AE.decrypt(cipher[i]);
            System.out.println(new String(decipher[i]));
        }
    }
}