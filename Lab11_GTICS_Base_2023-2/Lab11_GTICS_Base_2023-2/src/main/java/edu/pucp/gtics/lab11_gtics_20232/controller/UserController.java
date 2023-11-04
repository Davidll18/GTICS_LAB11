package edu.pucp.gtics.lab11_gtics_20232.controller;


import edu.pucp.gtics.lab11_gtics_20232.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;
//    @GetMapping(value = {"/",""})
//    public String principal(){
//        return "juegos/lista";
//    }
    @GetMapping(value = {"/openLoginWindow","","/"})
    public String loginWindow(){
        System.out.println(userRepository.findAll());
        return "user/signIn";
    }
    @GetMapping("/closeSession")
    public String closeSession(){
        return "user/signUp";
    }

}