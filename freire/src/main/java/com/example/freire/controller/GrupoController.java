package com.example.freire.controller;

import com.example.freire.model.Grupo;
import com.example.freire.service.GrupoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GrupoController {
    @Autowired
    private GrupoService grupoService;

    @GetMapping({ "/grupo", "/grupo/"})
    public String grupo(Model model){
        model.addAttribute("grupo", grupoService.getAllGrupo());
        return "/grupo/grupo";
    }


    @GetMapping("/grupo/new")
    public String grupoNew(Model model){
        Grupo grupo = new Grupo();
        model.addAttribute("grupo", grupo);
        return "/grupo/new_grupo";
    }

    @PostMapping("/grupo/new")
    public String saveGrupo(@Valid @ModelAttribute("grupo") Grupo grupo, BindingResult result, Model model){
        Grupo existing = grupoService.getGrupoByNameAndLocation(grupo.getName(), grupo.getLocation());
        if (existing != null) {
            result.rejectValue("nombre", null, "Este nombre ya esta registrado ");
            result.rejectValue("localidad", null, "Esta localidad ya esta registrado ");
            return "redirect:/grupo/new?exist";

        }
        if (result.hasErrors()){
            model.addAttribute("grupo", grupo);
            return "redirect:/grupo/new?error";
        }

        grupoService.saveGrupo(grupo);
        return "redirect:/grupo?success";
    }

    @GetMapping("/grupo/edit/{id}")
    public String editGrupoForm(@PathVariable Long id, Model model){
        Grupo dpt = grupoService.getGrupoById(id);
        model.addAttribute("grupo", dpt);
        return "/grupo/edit_grupo";
    }



    @GetMapping("/grupo/delete/{id}")
    public String deleteGrupo(@PathVariable Long id){
        try{
            grupoService.deleteGrupoById(id);
            return "redirect:/grupo";
        }
        catch(Exception e){
            return "redirect:/grupo?error";

        }

    }
}
