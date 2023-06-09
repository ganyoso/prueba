package com.example.freire.controller;

import com.example.freire.model.Asignatura;
import com.example.freire.service.AsignaturaService;
import jakarta.servlet.http.HttpSession;
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
public class AsignaturaController {
    @Autowired
    private AsignaturaService asignaturaService;

    @GetMapping({ "/asignatura", "/asignatura/"})
    public String asignatura(Model model){
        model.addAttribute("asignatura", asignaturaService.getAllAsignaturas());
        return "/asignatura/asignatura";
    }


    @GetMapping("/asignatura/new")
    public String asignaturaNew(Model model){
        Asignatura asignatura = new Asignatura();
        model.addAttribute("asignatura", asignatura);
        return "/asignatura/new_asignatura";
    }

    @PostMapping("/asignatura/new")
    public String saveAsignatura(@Valid @ModelAttribute("asignatura") Asignatura asignatura, BindingResult result, Model model){
        Asignatura existing = asignaturaService.getAsignaturaByNameAndLocation(asignatura.getName(), asignatura.getLocation());
        if (existing != null) {
            result.rejectValue("nombre", null, "Este nombre ya esta registrado ");
            result.rejectValue("localidad", null, "Esta localidad ya esta registrado ");
            return "redirect:/asignatura/new?exist";

        }
        if (result.hasErrors()){
            model.addAttribute("asignatura", asignatura);
            return "redirect:/asignatura/new?error";
        }

        asignaturaService.saveAsignatura(asignatura);
        return "redirect:/asignatura?success";
    }

    @GetMapping("/asignatura/edit/{id}")
    public String editAsignaturaForm(@PathVariable Long id, Model model){
        Asignatura dpt = asignaturaService.getAsignaturaById(id);
        model.addAttribute("asignatura", dpt);
        return "/asignatura/edit_asignatura";
    }



    @GetMapping("/asignatura/delete/{id}")
    public String deleteAsignatura(@PathVariable Long id){
        asignaturaService.deleteAsignaturaById(id);
        return "redirect:/asignatura";

    }
}
