package com.example.freire.service;

import com.example.freire.model.Alumno;

import java.util.List;

public interface AlumnoService {

    void saveAlumno(Alumno alumno);

    Alumno getAlumnoByEmail(String email);

    List<Alumno> getByName(String name);

    Alumno getAlumnoById(Long id);


    Alumno updateAlumno(Alumno alumno);

    List<Alumno> getAllAlumnos();

    void deleteAlumnoById(Long id);
}
