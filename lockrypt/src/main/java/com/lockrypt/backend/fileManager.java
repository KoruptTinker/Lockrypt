package com.lockrypt.backend;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class fileManager {
    public static fileManager instanceFM;
    public static File filePath=new File("/home/korupttinker/Projects/Lockrypt/pending-uploads");
    public static String lockerPath="/home/korupttinker/Projects/Lockrypt/file-locker/";
    public static String fileLoc="/home/korupttinker/Projects/Lockrypt/downloads/";
    public File fileList[];
    public byte[][] fileBytes;
    private int counter;

    public static fileManager getInstance() {
		if (instanceFM == null) {
			instanceFM = new fileManager();
		}
		return instanceFM;
    }
    

    public void getLockerContents(){
        File locker=new File("/home/korupttinker/Projects/Lockrypt/file-locker");
        this.fileList=locker.listFiles();
    }

    public fileManager(){
        this.fileBytes=new byte[100][];
        this.counter=0;
    }

    public void getFileList(){
        this.fileList=filePath.listFiles();
    }

    public void getFileBytes(File fileName){
        try{
            Path tempPath=Paths.get(fileName.toString());
            this.fileBytes[counter]=Files.readAllBytes(tempPath);
            this.counter++;
        }
        catch(IOException e){
               e.printStackTrace();
        }
    }

    public void deleteFile(File fileName){
        fileName.delete();
    }

    public void createFileEncrypted(byte[] data, String fileName){
        try{
            File temp=new File(String.format("%s/%s",lockerPath, fileName));
            FileOutputStream fileWrite=new FileOutputStream(temp);
            fileWrite.write(data);
            fileWrite.flush();
            fileWrite.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void createFileDecrypted(byte[] data, String fileName){
        try{
            File temp=new File(String.format("%s/%s",fileLoc,fileName));
            FileOutputStream fileWrite=new FileOutputStream(temp);
            fileWrite.write(data);
            fileWrite.flush();
            fileWrite.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}