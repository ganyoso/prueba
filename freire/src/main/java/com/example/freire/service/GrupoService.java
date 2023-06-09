package com.example.freire.service;

import com.example.freire.model.Asignatura;
import com.example.freire.model.Grupo;

import java.util.List;

public interface GrupoService {


    List<Grupo> getAllGrupo();

    List<Grupo> getGrupoByName(String name);

    Grupo saveGrupo(Grupo grupo);

    Grupo getGrupoById(Long id);

    Grupo getGrupoByNameAndLocation(String name, String location);

    void deleteGrupoById(Long id);
}
