package com.lockrypt.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.memory.UserAttribute;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Route
public class MainView extends VerticalLayout {
    public MainView() {
    }

}
