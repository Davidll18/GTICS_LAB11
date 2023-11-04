package edu.pucp.gtics.lab11_gtics_20232.controller;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@Controller
//@RequestMapping("/carrito")
public class CarritoController {

    // No voy a completar nada - por ahora aquí

    public String listaCarrito (Model model, @RequestParam("id") int id){

        return "carrito/lista";
    }

    public String nuevoCarrito(@RequestParam("id") int id, Model model){

        return "redirect:/vista";
    }

    public String editarCarrito(@RequestParam("id") int id, Model model){

        return "redirect:/juegos/lista";
    }

    public String borrarCarrito(@RequestParam("id") int id, Model model){

        return "redirect:/carrito/lista";
    }


}
