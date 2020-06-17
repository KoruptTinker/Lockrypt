package com.lockrypt.ui;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.HorizontalAlign;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.Route;

@Route("download")
public class download extends VerticalLayout{
    public download(){
        H2 head=new H2("Welcome, KoruptTinker!");
        H3 head3=new H3("Download your files from the locker!");
        
        PasswordField passwordField = new PasswordField();
        passwordField.setLabel("Encryption key (16 characters only)");
        passwordField.setPlaceholder("Enter key");
        Button b1=new Button("Download and decrypt files!");
        Checkbox checkbox = new Checkbox();
        checkbox.setLabel("Preserve file name");
        checkbox.setValue(true);
        Checkbox checkbox2 = new Checkbox();
        checkbox2.setLabel("Preserve file name");
        checkbox2.setValue(true);
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setLabel("Files");
        String[] array={"test.txt","testimg.png","pytest.py","test1.mp4"};
        List<String> departmentList = new ArrayList<String>();
        for(int i=0;i<array.length;i++){
            departmentList.add(array[i]);
        }
        comboBox.setItems(departmentList);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        HorizontalLayout hori=new HorizontalLayout();
        hori.add(comboBox,passwordField);
        add(head,head3,hori,checkbox,b1);
    }
}