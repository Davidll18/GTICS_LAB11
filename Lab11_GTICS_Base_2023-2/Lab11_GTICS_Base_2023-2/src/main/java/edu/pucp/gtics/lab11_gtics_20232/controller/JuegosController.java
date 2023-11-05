package edu.pucp.gtics.lab11_gtics_20232.controller;

import edu.pucp.gtics.lab11_gtics_20232.dao.DistribuidorasDao;
import edu.pucp.gtics.lab11_gtics_20232.dao.JuegosDao;
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
    PlataformasRepository plataformasRepository;

    final
    DistribuidorasDao distribuidorasDao;

    @Autowired
    GenerosRepository generosRepository;

    @Autowired
    UserRepository userRepository;

    final
    JuegosDao juegosDao;

    public JuegosController(JuegosDao juegosDao, DistribuidorasDao distribuidorasDao) {
        this.juegosDao = juegosDao;
        this.distribuidorasDao = distribuidorasDao;
    }


    @GetMapping(value = {"/lista","/",""})
    public String listaJuegos (Model model){
//        List<Juegos> listajuegos = juegosRepository.findAll(Sort.by("nombre"));
//        model.addAttribute("listajuegos", listajuegos);
        model.addAttribute("listajuegos", juegosDao.listar());
        return "juegos/lista";
    }

    //@GetMapping(value = {"", "/", "/vista"})              // Así estaba
//    @GetMapping(value = {"/vista"})
//    public String vistaJuegos (Model model, @RequestParam("id") int id){
//        Optional<Juegos> opt = juegosRepository.findById(id);
//        //List<Paises> listaPaises = paisesRepository.findAll();
//        if (opt.isPresent()){
//            Juegos juegos = opt.get();
//            model.addAttribute("distribuidora", juegos);
//            //model.addAttribute("listaPaises", listaPaises);
//            return "juegos/vista";
//        }else {
//            return "redirect:/juegos/lista";
//        }
//    }

    @GetMapping("/nuevo")
    public String nuevoJuegos(Model model, @ModelAttribute("juego") Juegos juego){
        List<Plataformas> listaPlataformas = plataformasRepository.findAll();
        List<Distribuidoras> listaDistribuidoras = distribuidorasDao.listar();
        List<Generos> listaGeneros = generosRepository.findAll();
        model.addAttribute("listaPlataformas", listaPlataformas);
        model.addAttribute("listaDistribuidoras", listaDistribuidoras);
        model.addAttribute("listaGeneros", listaGeneros);

        // model.addAttribute("listaDistribuidoras", distribuidorasDao.listar()); // Descomentar cuando ya esta el WS

        return "juegos/editarFrm";
    }

    @GetMapping("/editar")
    public String editarJuegos(@ModelAttribute("juego") Juegos juego, @RequestParam("id") int id, Model model){

        Juegos juegoid = juegosDao.buscarPorId(id);

        List<Plataformas> listaPlataformas = plataformasRepository.findAll();
        List<Distribuidoras> listaDistribuidoras = distribuidorasDao.listar();
        List<Generos> listaGeneros = generosRepository.findAll();
        if (juegoid != null ){
            juego = juegoid;
            model.addAttribute("juego", juego);
            model.addAttribute("listaPlataformas", listaPlataformas);
            model.addAttribute("listaDistribuidoras", listaDistribuidoras);
            model.addAttribute("listaGeneros", listaGeneros);
            // model.addAttribute("listaDistribuidoras", distribuidorasDao.listar()); // Descomentar cuando ya esta el WS


            return "juegos/editarFrm";
        }else {
            return "redirect:/juegos/lista";
        }
    }

    @PostMapping("/guardar")
    public String guardarJuegos(@ModelAttribute("juego") @Valid Juegos juego, BindingResult bindingResult,
                                Model model, RedirectAttributes attr ){
        if (bindingResult.hasErrors()) {
            model.addAttribute("listaPlataformas", plataformasRepository.findAll());
            model.addAttribute("listaDistribuidoras", distribuidorasDao.listar());
            model.addAttribute("listaGeneros", generosRepository.findAll());
            return "juegos/editarFrm";
        } else {
            String msg = "Juego " + (juego.getIdjuego() == null ? "creado" : "actualizado") + " exitosamente";
            attr.addFlashAttribute("msg", msg);
            juegosDao.guardar(juego);
            return "redirect:/juegos/lista";
        }
    }

    @GetMapping("/delete")
    public String borrarJuego(Model model, @RequestParam("id") int id, RedirectAttributes attr) {

        juegosDao.borrarJuego(id);
        attr.addFlashAttribute("msg", "Juego borrado exitosamente");
        //}
        return "redirect:/juegos/lista";

    }

    @GetMapping("/borrar")
    public String borrarJuegos(Model model, @RequestParam("id") int id, RedirectAttributes attr) {
        System.out.println("entro a borrar");

        Juegos juegoBuscar = juegosDao.buscarPorId(id);

        if (juegoBuscar != null) {
            juegosDao.borrarJuego(id);
            attr.addFlashAttribute("msg", "Juego borrado exitosamente");
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
