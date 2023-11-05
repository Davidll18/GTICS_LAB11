package edu.pucp.gtics.lab11_gtics_20232.controller;

import edu.pucp.gtics.lab11_gtics_20232.entity.*;
import edu.pucp.gtics.lab11_gtics_20232.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/juegos")
public class JuegosController {

    @Autowired
    JuegosRepository juegosRepository;

    @Autowired
    PlataformasRepository plataformasRepository;

    @Autowired
    DistribuidorasRepository distribuidorasRepository;

    @Autowired
    GenerosRepository generosRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = {"/lista","/",""})
    public String listaJuegos (Model model){
        List<Juegos> listajuegos = juegosRepository.findAll(Sort.by("nombre"));
        model.addAttribute("listajuegos", listajuegos);

        return "juegos/lista";
    }

    //@GetMapping(value = {"", "/", "/vista"})              // Así estaba
    @GetMapping(value = {"/vista"})
    public String vistaJuegos (Model model, @RequestParam("id") int id){
        Optional<Juegos> opt = juegosRepository.findById(id);
        //List<Paises> listaPaises = paisesRepository.findAll();
        if (opt.isPresent()){
            Juegos juegos = opt.get();
            model.addAttribute("distribuidora", juegos);
            //model.addAttribute("listaPaises", listaPaises);
            return "juegos/vista";
        }else {
            return "redirect:/juegos/lista";
        }
    }

    @GetMapping("/nuevo")
    public String nuevoJuegos(Model model, @ModelAttribute("juego") Juegos juego){
        List<Plataformas> listaPlataformas = plataformasRepository.findAll();
        List<Distribuidoras> listaDistribuidoras = distribuidorasRepository.findAll();
        List<Generos> listaGeneros = generosRepository.findAll();
        model.addAttribute("listaPlataformas", listaPlataformas);
        model.addAttribute("listaDistribuidoras", listaDistribuidoras);
        model.addAttribute("listaGeneros", listaGeneros);
        return "juegos/editarFrm";
    }

    @GetMapping("/editar")
    public String editarJuegos(@RequestParam("id") int id, Model model){
        Optional<Juegos> opt = juegosRepository.findById(id);
        List<Plataformas> listaPlataformas = plataformasRepository.findAll();
        List<Distribuidoras> listaDistribuidoras = distribuidorasRepository.findAll();
        List<Generos> listaGeneros = generosRepository.findAll();
        if (opt.isPresent()){
            Juegos juego = opt.get();
            model.addAttribute("juego", juego);
            model.addAttribute("listaPlataformas", listaPlataformas);
            model.addAttribute("listaDistribuidoras", listaDistribuidoras);
            model.addAttribute("listaGeneros", listaGeneros);
            return "juegos/editarFrm";
        }else {
            return "redirect:/juegos/lista";
        }
    }

    @PostMapping("/guardar")
    public String guardarJuegos(Model model, RedirectAttributes attr, @ModelAttribute("juego") @Valid Juegos juego, BindingResult bindingResult ){
        if(bindingResult.hasErrors( )){
            List<Plataformas> listaPlataformas = plataformasRepository.findAll();
            List<Distribuidoras> listaDistribuidoras = distribuidorasRepository.findAll();
            List<Generos> listaGeneros = generosRepository.findAll();
            model.addAttribute("juego", juego);
            model.addAttribute("listaPlataformas", listaPlataformas);
            model.addAttribute("listaDistribuidoras", listaDistribuidoras);
            model.addAttribute("listaGeneros", listaGeneros);
            return "juegos/editarFrm";
        } else {
            if (juego.getIdjuego() == 0) {
                attr.addFlashAttribute("msg", "Juego creado exitosamente");
            } else {
                attr.addFlashAttribute("msg", "Juego actualizado exitosamente");
            }
            juegosRepository.save(juego);
            return "redirect:/juegos/lista";
        }


    }

    @GetMapping("/borrar")
    public String borrarDistribuidora(@RequestParam("id") int id, RedirectAttributes attr){
        Optional<Juegos> opt = juegosRepository.findById(id);
        if (opt.isPresent()) {
            Juegos juegos = opt.get();
            juegosRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Juego '" + juegos.getNombre() +"' borrado exitosamente");
        }
        return "redirect:/juegos/lista";
    }

    @GetMapping("/metodopago")
    public String metodopago(){
        return "plataformas/metodopago";
    }
    @GetMapping("/deposito")
    public String deposito(){
        return "plataformas/deposito";
    }
    @GetMapping("/yape")
    public String yape(){
        return "plataformas/yape";
    }
    @GetMapping("/carritoCompras")
    public String carrito(){
        return "carrito/carritoCompras";
    }
}
