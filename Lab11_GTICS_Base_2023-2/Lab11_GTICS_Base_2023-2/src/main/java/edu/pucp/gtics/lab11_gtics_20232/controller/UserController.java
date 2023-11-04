package edu.pucp.gtics.lab11_gtics_20232.controller;


import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

//    @GetMapping(value = {"/",""})
//    public String principal(){
//        return "juegos/lista";
//    }
    @GetMapping(value = {"/openLoginWindow","","/"})
    public String loginWindow(){
        return "user/signIn";
    }
    @GetMapping("/closeSession")
    public String closeSession(){
        return "user/signUp";
    }

}