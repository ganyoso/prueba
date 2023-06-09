package com.example.freire.service;

import com.example.freire.model.Asignatura;

import java.util.List;

public interface AsignaturaService {


    List<Asignatura> getAllAsignaturas();

    List<Asignatura> getAsignaturaByName(String name);

    Asignatura saveAsignatura(Asignatura asignatura);

    Asignatura getAsignaturaById(Long id);

    Asignatura getAsignaturaByNameAndLocation(String name, String location);

    void deleteAsignaturaById(Long id);
}
