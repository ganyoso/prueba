package com.example.freire.repositories;


import com.example.freire.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrupoRepo extends JpaRepository<Grupo, Long> {

    @Override
    List<Grupo> findAll();

    Grupo findAsignaturaById(Long id);
    List<Grupo> findByName(String name);
    List<Grupo> findByLocation(String location);


    Grupo findAsignaturaByNameAndLocation(String name, String location);
}
