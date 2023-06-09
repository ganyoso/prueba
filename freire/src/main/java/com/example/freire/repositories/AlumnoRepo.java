package com.example.freire.repositories;


import com.example.freire.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface AlumnoRepo extends JpaRepository<Alumno, Long> {

    @Override
    List<Alumno> findAll();

    Alumno findAlumnoById(Long id);

    Alumno findAlumnoByEmail(String email);

    List<Alumno> findAlumnoByName(String name);
}


