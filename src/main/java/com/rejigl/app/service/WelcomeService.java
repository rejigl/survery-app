package com.rejigl.app.service;

import org.springframework.stereotype.Service;

@Service
public class WelcomeService {

    public String getWelcomeMessage(){
        return "Good Afternoon";
    }
}
