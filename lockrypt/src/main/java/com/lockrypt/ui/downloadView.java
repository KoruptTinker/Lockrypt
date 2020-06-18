package com.lockrypt.ui;

import java.util.ArrayList;
import java.util.List;

import com.lockrypt.backend.AdvancedEncryption;
import com.lockrypt.backend.fileManager;
import com.lockrypt.backend.lockrypt;
import com.lockrypt.backend.blockchain.block_chain;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.Route;

@Route("download")
public class downloadView extends VerticalLayout{
    private fileManager fm=fileManager.getInstance();
    private lockrypt lock=lockrypt.getInstance();
    private AdvancedEncryption AE=AdvancedEncryption.getInstance();
    public downloadView(){
        fm.getLockerContents();
        H2 head=new H2("Welcome, KoruptTinker!");
        H3 head3=new H3("Download your files from the locker!");
        Dialog d=new Dialog();
        d.add("File has been decrypted and sent to your device! Reloading window.");
        d.setCloseOnOutsideClick(true);
        d.addDialogCloseActionListener(eventClosed->{
            d.close();
            UI.getCurrent().getPage().reload();
        });
        PasswordField passwordField = new PasswordField();
        passwordField.setLabel("Encryption key (16 characters only)");
        passwordField.setPlaceholder("Enter key");
        passwordField.setMaxLength(16);
        Button b1=new Button("Download and decrypt files!");
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setLabel("Files");
        String[] array=new String[fm.fileList.length];
        for(int i=0;i<fm.fileList.length;i++){
            array[i]=fm.fileList[i].getName();
        }
        List<String> contentList = new ArrayList<String>();
        for(int i=0;i<array.length;i++){
            contentList.add(array[i]);
        }
        comboBox.setItems(contentList);
        b1.addClickListener(eventClick->{
            String targetFile=comboBox.getValue();
            boolean flag=false;
            int index=0;
            for(int i=0;i<fm.fileList.length;i++){
                String temp=block_chain.blockchain.get(i+1).getFileName();
                System.out.println(temp);
                if(temp.equals(targetFile)){
                    flag=true;
                    index=i+1;
                }
            }
            if(flag){
                boolean checkValid=true;
                try{
                    byte[] decipher=AE.decrypt(block_chain.blockchain.get(index).get_data2(),passwordField.getValue());
                    fm.createFileDecrypted(decipher, targetFile);
                }
                catch(Exception exc1){
                    checkValid=false;
                    Dialog keywrong=new Dialog();
                    keywrong.add("You've entered a wrong key! Try again with a valid key");
                    keywrong.setCloseOnOutsideClick(true);
                    keywrong.open();
                    keywrong.addDialogCloseActionListener(eventClosewrong->{
                        keywrong.close();
                        UI.getCurrent().getPage().reload();
                    });
                }
                if(checkValid){
                    d.open();
                }
            }  
        });
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        HorizontalLayout hori=new HorizontalLayout();
        hori.add(comboBox,passwordField);
        add(head,head3,hori,b1);
    }
}