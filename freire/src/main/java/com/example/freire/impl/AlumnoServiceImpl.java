package com.example.freire.impl;

import com.example.freire.model.Alumno;
import com.example.freire.repositories.AlumnoRepo;
import com.example.freire.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService {
    @Autowired
    AlumnoRepo alumnoRepo;

    public AlumnoServiceImpl(AlumnoRepo alumnoRepository) {
        this.alumnoRepo = alumnoRepository;
    }

    @Override
    public void saveAlumno(Alumno alumno) {
        alumnoRepo.save(alumno);

    }

    @Override
    public Alumno getAlumnoByEmail(String email) {
        return alumnoRepo.findAlumnoByEmail(email);
    }



    @Override
    public List<Alumno> getByName(String name) {
        return alumnoRepo.findAlumnoByName(name);
    }

    @Override
    public Alumno getAlumnoById(Long id) {
        return alumnoRepo.findAlumnoById(id);
    }

    @Override
    public Alumno updateAlumno(Alumno alumno) {
        return alumnoRepo.save(alumno);
    }

    @Override
    public List<Alumno> getAllAlumnos() {
        return alumnoRepo.findAll();
    }

    @Override
    public void deleteAlumnoById(Long id) {
        alumnoRepo.deleteById(id);
    }
}
