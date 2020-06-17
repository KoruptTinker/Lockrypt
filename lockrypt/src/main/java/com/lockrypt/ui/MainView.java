package com.lockrypt.ui;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.lockrypt.backend.AdvancedEncryption;
import com.lockrypt.backend.fileManager;
import com.lockrypt.backend.lockrypt;
import com.lockrypt.backend.blockchain.block_chain;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.HorizontalAlign;
import com.vaadin.flow.component.charts.model.Label;
import com.vaadin.flow.component.charts.model.TimeUnit;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.memory.UserAttribute;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Route
public class MainView extends VerticalLayout {
    private fileManager fm=fileManager.getInstance();
    private lockrypt lock=lockrypt.getInstance();
    private AdvancedEncryption AE=AdvancedEncryption.getInstance();
    private byte[][] cipher;
    public MainView(){
        H2 head=new H2("Welcome, KoruptTinker!");
        H3 head3=new H3("Upload your files to the locker!");
        MemoryBuffer memoryBuffer = new MemoryBuffer();
        Upload upload = new Upload(memoryBuffer);
        upload.addFinishedListener(e -> {
        InputStream inputStream = memoryBuffer.getInputStream();
            });
        
        PasswordField passwordField = new PasswordField();
        passwordField.setLabel("Encryption key");
        passwordField.setPlaceholder("Enter key");
        passwordField.setMaxLength(16);
        Dialog d=new Dialog();
        d.add("Files uploaded to cloud successfully! Logging out now!");
        d.setCloseOnOutsideClick(false);
        Button b1=new Button("Encrypt and Upload the files!",
        e-> {
            block_chain.Create_Blockchain();
            fm.getFileList();
            cipher=new byte[fm.fileList.length][];
            for(int i=0;i<fm.fileList.length;i++){
                fm.getFileBytes(fm.fileList[i]);
            }
            for(int i=0;i<fm.fileList.length;i++){
                try{
                cipher[i]=AE.encrypt(fm.fileBytes[i],passwordField.getValue());
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
                try{
                    byte[] deciphered=AE.decrypt(block_chain.blockchain.get(i+1).get_data2(),passwordField.getValue());
                    fm.createFileDecrypted(deciphered,fm.fileList[i].getName());
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
            d.open();
        });
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        add(head,head3,upload,passwordField,b1);
    }

}
