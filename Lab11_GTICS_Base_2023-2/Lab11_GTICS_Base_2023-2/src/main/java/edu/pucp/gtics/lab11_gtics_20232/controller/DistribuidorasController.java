package edu.pucp.gtics.lab11_gtics_20232.controller;

import edu.pucp.gtics.lab11_gtics_20232.dao.DistribuidorasDao;
import edu.pucp.gtics.lab11_gtics_20232.entity.Distribuidoras;
import edu.pucp.gtics.lab11_gtics_20232.entity.Paises;
import edu.pucp.gtics.lab11_gtics_20232.repository.DistribuidorasRepository;
import edu.pucp.gtics.lab11_gtics_20232.repository.PaisesRepository;
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
@RequestMapping("/distribuidoras")

public class DistribuidorasController {

    final DistribuidorasDao distribuidorasDao;


    @Autowired
    DistribuidorasRepository distribuidorasRepository;

    @Autowired
    PaisesRepository paisesRepository;

    public DistribuidorasController(DistribuidorasDao distribuidorasDao) {
        this.distribuidorasDao = distribuidorasDao;
    }

    @GetMapping(value = {"/lista","/",""})
    public String listaDistribuidoras (Model model){
        List<Distribuidoras> listadistribuidoras = distribuidorasRepository.findAll(Sort.by("nombre"));
        model.addAttribute("listadistribuidoras", listadistribuidoras);
        //model.addAttribute("listadistribuidoras",distribuidorasDao.listar());// Descomentar cuando ya esta el WS
        return "distribuidoras/lista";
    }

    @GetMapping("/editar")
    public String editarDistribuidoras(@RequestParam("id") int id, Model model){
        Optional<Distribuidoras> opt = distribuidorasRepository.findById(id);
        List<Paises> listaPaises = paisesRepository.findAll();
        if (opt.isPresent()){
            Distribuidoras distribuidora = opt.get();
            model.addAttribute("distribuidora", distribuidora);
            model.addAttribute("listaPaises", listaPaises);
            return "distribuidoras/editarFrm";
        }else {
            return "redirect:/distribuidoras/lista";
        }

    }

    @GetMapping("/nuevo")
    public String nuevaDistribuidora(Model model, @ModelAttribute("distribuidora") Distribuidoras distribuidora){
        List<Paises> listaPaises = paisesRepository.findAll();
        model.addAttribute("listaPaises", listaPaises);


        return "distribuidoras/editarFrm";
    }

    @PostMapping("/guardar")
    public String guardarDistribuidora(Model model, RedirectAttributes attr, @ModelAttribute("distribuidora") @Valid Distribuidoras distribuidora , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<Paises> listaPaises = paisesRepository.findAll();
            model.addAttribute("distribuidora", distribuidora);
            model.addAttribute("listaPaises", listaPaises);
            return "distribuidoras/editarFrm";
        } else {
            if (distribuidora.getIddistribuidora() == 0) {
                attr.addFlashAttribute("msg", "Distribuidora '" + distribuidora.getNombre() + "' creada exitosamente");
            } else {
                attr.addFlashAttribute("msg", "Distribuidora '" + distribuidora.getNombre() + "' actualizada exitosamente");
            }
            distribuidorasRepository.save(distribuidora);
            return "redirect:/distribuidoras/lista";
        }
    }

    @GetMapping("/borrar")
    public String borrarDistribuidora(@RequestParam("id") int id, RedirectAttributes attr){
        Optional<Distribuidoras> opt = distribuidorasRepository.findById(id);
        if (opt.isPresent()) {
            Distribuidoras distribuidoras = opt.get();
            distribuidorasRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Distribuidora '" + distribuidoras.getNombre() +"' borrado exitosamente");

        }
        return "redirect:/distribuidoras/lista";
    }

}
