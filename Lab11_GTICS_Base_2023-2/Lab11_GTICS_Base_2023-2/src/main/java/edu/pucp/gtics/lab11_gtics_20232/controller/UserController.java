package edu.pucp.gtics.lab11_gtics_20232.controller;


import org.springframework.web.bind.annotation.GetMapping;

public class UserController {

    @GetMapping("/openLoginWindow")
    public String loginWindow(){
        return "user/signIn";
    }
    @GetMapping("/closeSession")
    public String closeSession(){
        return "user/signUp";
    }

}