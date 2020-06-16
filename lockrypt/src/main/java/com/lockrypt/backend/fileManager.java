package com.lockrypt.backend;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class fileManager {
    static File filePath=new File("./pending-uploads/");
    static String lockerPath="./file-locker/";
    static String fileLoc="./downloads/";
    File[] fileList;
    byte[][] fileBytes;
    private int counter;

    public fileManager(){
        this.fileList=filePath.listFiles();
        this.fileBytes=new byte[this.fileList.length][];
        this.counter=0;
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
            File temp=new File(String.format("%s%s",lockerPath, fileName));
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
            File temp=new File(String.format("%s%s",fileLoc,fileName));
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