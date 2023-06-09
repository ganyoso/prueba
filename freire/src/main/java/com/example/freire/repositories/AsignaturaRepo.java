package com.example.freire.repositories;

import com.example.freire.model.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsignaturaRepo extends JpaRepository<Asignatura, Long> {

    @Override
    List<Asignatura> findAll();

    Asignatura findAsignaturaById(Long id);
    List<Asignatura> findByName(String name);
    List<Asignatura> findByLocation(String location);


    Asignatura findAsignaturaByNameAndLocation(String name, String location);
}
