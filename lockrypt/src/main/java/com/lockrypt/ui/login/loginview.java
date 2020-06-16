package com.lockrypt.ui.login;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("login")
public class loginview extends VerticalLayout implements BeforeEnterObserver{
    private LoginForm login=new LoginForm();

    public loginview(){
        addClassName("login-form");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        login.setAction("login");
        add(new H1("Login"),login);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent before){
        // inform the user about an authentication error
        if(before.getLocation()
        .getQueryParameters()
        .getParameters()
        .containsKey("error")) {
        login.setError(true);
        }
    }
}