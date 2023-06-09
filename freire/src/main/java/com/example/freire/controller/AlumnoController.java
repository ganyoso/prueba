package com.example.freire.controller;

import com.example.freire.model.Asignatura;
import com.example.freire.model.Alumno;
import com.example.freire.repositories.AsignaturaRepo;
import com.example.freire.repositories.GrupoRepo;
import com.example.freire.service.AlumnoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private AsignaturaRepo asignaturaRepo;

    @Autowired
    private GrupoRepo grupoRepo;


    public AlumnoController(AlumnoService alumnoService, AsignaturaRepo asignaturaRepo) {
        this.alumnoService = alumnoService;
        this.asignaturaRepo = asignaturaRepo;
    }

    @GetMapping({ "/alumno", "/alumno/"})
    public String alumnoList(Model model){
        model.addAttribute("alumno", alumnoService.getAllAlumnos());
        return "/alumno/alumno";
    }

    @GetMapping("/alumno/new")
    public String alumnoNew(Model model){
        Alumno alumno = new Alumno();
        model.addAttribute("alumno", alumno);
        model.addAttribute("asignaturaRepo", asignaturaRepo.findAll());
        model.addAttribute("grupoRepo", grupoRepo.findAll());

        return "/alumno/new_alumno";
    }

    @PostMapping("/alumno/new")
    public String saveEmpleado(@Valid @ModelAttribute("alumno") Alumno alumno, BindingResult result, Model model){
        Alumno existing = alumnoService.getAlumnoByEmail(alumno.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "Cuenta ya registrada");
            model.addAttribute("alumno", alumno);
            model.addAttribute("asignaturaRepo", asignaturaRepo.findAll());
            model.addAttribute("grupoRepo", grupoRepo.findAll());
            return "/alumno/new_alumno";
        }
        if (result.hasErrors()){
            model.addAttribute("alumno", alumno);
            //return "crear_alumno";
            return "redirect:/alumno/new?error";
        }

        alumnoService.saveAlumno(alumno);
        return "redirect:/alumno?success";
    }


    @GetMapping("/alumno/edit/{id}")
    public String editAlumno(@PathVariable Long id, Model model){
        Alumno empl = alumnoService.getAlumnoById(id);
        model.addAttribute("alumno", empl);
        model.addAttribute("asignaturaRepo", asignaturaRepo.findAll());
        model.addAttribute("grupoRepo", grupoRepo.findAll());

        return "/alumno/crear_alumno";
    }

    @PostMapping("/alumno/update/{id}")
    public String updateAlumno(@PathVariable Long id, @ModelAttribute("alumno") Alumno alumno, Model model){
        Alumno existAlumno = alumnoService.getAlumnoById(id);
        existAlumno.setId(id);
        existAlumno.setSurename(alumno.getSurename());
        existAlumno.setName(alumno.getName());
        existAlumno.setAsignaturas(alumno.getAsignaturas());
        existAlumno.setJob(alumno.getJob());
        existAlumno.setEmail(alumno.getEmail());
        existAlumno.setDateHire(alumno.getDateHire());
        existAlumno.setSal(alumno.getSal());

        alumnoService.updateAlumno(existAlumno);
        return "redirect:/alumno?update";
    }

    @GetMapping("/alumno/delete/{id}")
    public String deleteAlumno(@PathVariable Long id){
        alumnoService.deleteAlumnoById(id);
        return "redirect:/alumno";

    }


}
