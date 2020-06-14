package main.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class fileManager {
    static File filePath=new File("./pending-uploads");
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

    public static void main(String[] args) {
        fileManager filem=new fileManager();
        for(int i=0;i<filem.fileList.length;i++){
            filem.getFileBytes(filem.fileList[i]);
        }
        System.out.println(filem.fileBytes[0].length);
        System.out.println(filem.fileBytes[1].length);
    }
}