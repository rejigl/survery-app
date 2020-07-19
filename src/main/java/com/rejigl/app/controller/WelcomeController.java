package com.rejigl.app.controller;

import com.rejigl.app.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Autowired
    private WelcomeService service;

    @RequestMapping("/welcome")
    public String welcomeMessage(){
        return service.getWelcomeMessage() + ", Welcome !!! ";
    }
}
