package com.lockrypt.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightCondition;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Theme(value = Lumo.class, variant = Lumo.DARK)
public class MainLayout extends AppLayout{

    public MainLayout(){
        createHeader();
        createDrawer();
    }

    private void createDrawer() {
        H3 logo=new H3("Lockrypt | fsociety");
        logo.addClassName("logo");
        HorizontalLayout horizontal=new HorizontalLayout(new DrawerToggle(), logo);
        horizontal.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        horizontal.setWidth("100%");
        horizontal.addClassName("horizontal");
        addToNavbar(horizontal);
        setDrawerOpened(false);
    }

    private void createHeader() {

        RouterLink upload=new RouterLink("Upload",MainView.class);
        upload.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink download=new RouterLink("Download",downloadView.class);
        download.setHighlightCondition(HighlightConditions.sameLocation());
        addToDrawer(new VerticalLayout(upload,download));
    }
}