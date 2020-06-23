package com.lockrypt.ui;

import java.io.File;
import java.io.InputStream;
import com.lockrypt.backend.AdvancedEncryption;
import com.lockrypt.backend.fileManager;
import com.lockrypt.backend.lockrypt;
import com.lockrypt.backend.blockchain.block_chain;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.Route;

import org.apache.commons.io.FileUtils;

@Route(value="", layout=MainLayout.class)

public class MainView extends VerticalLayout {
	private fileManager fm=fileManager.getInstance();
    private lockrypt lock=lockrypt.getInstance();
    private AdvancedEncryption AE=AdvancedEncryption.getInstance();
    private byte[][] cipher;
    private int counter=0;
    public MainView(){
        H2 head=new H2("Welcome, KoruptTinker!");
        H3 head3=new H3("Upload your files to the locker!");
        MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.addSucceededListener(event -> {
            InputStream inputStream=buffer.getInputStream(event.getFileName());
            try{
                File targetPath=new File(String.format("/home/korupttinker/Projects/Lockrypt/pending-uploads/%s",event.getFileName()));
                FileUtils.copyInputStreamToFile(inputStream, targetPath);
            }
            catch(Exception exc){
                exc.printStackTrace();
            }
        });
        
        PasswordField passwordField = new PasswordField();
        passwordField.setLabel("Encryption key");
        passwordField.setPlaceholder("Enter key");
        passwordField.setMaxLength(16);
        Dialog d=new Dialog();
        d.add("Files uploaded to cloud successfully! Reloading the page!");
        d.setCloseOnOutsideClick(false);
        d.addDialogCloseActionListener(eventClose->{
            d.close();
            UI.getCurrent().getPage().reload();
        });
        Button b1=new Button("Encrypt and Upload the files!",
        e-> {
            block_chain.Create_Blockchain();
            fm.getFileList();
            cipher=new byte[fm.fileList.length][];
            for(int i=0;i<fm.fileList.length;i++){
                fm.getFileBytes(fm.fileList[i]);
                System.out.println("Bytes received");
            }
            for(int i=0;i<fm.fileList.length;i++){
                try{
                cipher[i]=AE.encrypt(fm.fileBytes[i],passwordField.getValue());
                System.out.println("Encrypted");
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
                String ciphertext=new String(cipher[i]);
                block_chain.Add_Block(ciphertext,cipher[i],fm.fileList[i].getName());
                fm.deleteFile(fm.fileList[i]);
            }
            Notification.show("Files encrypted. Uploading to cloud now.");
            for(int i=0;i<fm.fileList.length;i++){
                byte[] data=block_chain.blockchain.get(i+1).get_data2();
                fm.createFileEncrypted(data,fm.fileList[i].getName());
            }
            d.setCloseOnOutsideClick(true);
            d.open();
        });
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        add(head,head3,upload,passwordField,b1);
    }

}
