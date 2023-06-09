package com.example.freire.impl;


import com.example.freire.model.Grupo;
import com.example.freire.repositories.GrupoRepo;
import com.example.freire.service.GrupoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoServiceImpl implements GrupoService {
    private GrupoRepo grupoRepo;


    public GrupoServiceImpl(GrupoRepo grupoRepo) {
        this.grupoRepo = grupoRepo;
    }

    @Override
    public List<Grupo> getAllGrupo() {
        return grupoRepo.findAll();
    }

    @Override
    public List<Grupo> getGrupoByName(String name) {
        return grupoRepo.findByName(name);
    }

    @Override
    public Grupo saveGrupo(Grupo grupo) {
        return grupoRepo.save(grupo);
    }

    @Override
    public Grupo getGrupoById(Long id) {
        return grupoRepo.findAsignaturaById(id);
    }

    @Override
    public Grupo getGrupoByNameAndLocation(String name, String location) {
        return grupoRepo.findAsignaturaByNameAndLocation(name, location);
    }

    @Override
    public void deleteGrupoById(Long id) {
        grupoRepo.deleteById(id);
    }
}
