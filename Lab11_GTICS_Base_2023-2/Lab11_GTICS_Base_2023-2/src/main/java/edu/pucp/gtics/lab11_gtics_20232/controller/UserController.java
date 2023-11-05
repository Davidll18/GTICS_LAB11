package edu.pucp.gtics.lab11_gtics_20232.controller;


import edu.pucp.gtics.lab11_gtics_20232.entity.User;
import edu.pucp.gtics.lab11_gtics_20232.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;

    final PasswordEncoder passwordEncoder;

    public UserController() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


//    @GetMapping(value = {"/",""})
//    public String principal(){
//        return "juegos/lista";
//    }
    @GetMapping(value = {"/openLoginWindow","","/","/login"})
    public String loginWindow(){
        System.out.println(userRepository.findAll());
        return "user/signIn";
    }
    @GetMapping("/closeSession")
    public String closeSession(){
        return "user/signUp";
    }

    @PostMapping(value = "/guardarUser")
    public String guardarUsuario(@ModelAttribute("usuario") @Valid User user, BindingResult bindingResult, RedirectAttributes attr){
        if(bindingResult.hasErrors()){
            return "user/signUp";
        } else {
            user.setEnable(1);
            user.setAutorizacion("USER");
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            attr.addFlashAttribute("msg","Usuario registrado");
            userRepository.save(user);

            return "redirect:/login";
        }
    }

}