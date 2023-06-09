package com.example.freire.impl;

import com.example.freire.model.Asignatura;
import com.example.freire.repositories.AsignaturaRepo;
import com.example.freire.service.AsignaturaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {
    private AsignaturaRepo asignaturaRepo;


    public AsignaturaServiceImpl(AsignaturaRepo asignaturaRepo) {
        this.asignaturaRepo = asignaturaRepo;
    }

    @Override
    public List<Asignatura> getAllAsignaturas() {
        return asignaturaRepo.findAll();
    }

    @Override
    public List<Asignatura> getAsignaturaByName(String name) {
        return asignaturaRepo.findByName(name);
    }

    @Override
    public Asignatura saveAsignatura(Asignatura asignatura) {
        return asignaturaRepo.save(asignatura);
    }

    @Override
    public Asignatura getAsignaturaById(Long id) {
        return asignaturaRepo.findAsignaturaById(id);
    }

    @Override
    public Asignatura getAsignaturaByNameAndLocation(String name, String location) {
        return asignaturaRepo.findAsignaturaByNameAndLocation(name, location);
    }

    @Override
    public void deleteAsignaturaById(Long id) {
        asignaturaRepo.deleteById(id);
    }
}
