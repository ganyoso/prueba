package com.example.freire.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "alumnos")
public class Alumno {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surename;


    private String job;

    @Column(nullable=false, unique=true)
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateHire;

    private Float sal;
    //Cascade permite borrar las tablas referenciadas
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(
            name="alumnos_asignaturas",
            joinColumns={@JoinColumn(name="ALUMNO_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ASIGNATURA_ID", referencedColumnName="ID")})
    private List<Asignatura> asignaturas = new ArrayList<>();


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "grupo")
    Grupo grupo;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }


    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateHire() {
        return dateHire;
    }

    public void setDateHire(Date dateHire) {
        this.dateHire = dateHire;
    }

    public Float getSal() {
        return sal;
    }

    public void setSal(Float sal) {
        this.sal = sal;
    }

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surename='" + surename + '\'' +
                ", job='" + job + '\'' +
                ", email='" + email + '\'' +
                ", dateHire=" + dateHire +
                ", sal=" + sal +
                '}';
    }

    @PreRemove
    private void preRemove() {
        asignaturas.clear(); // Elimina las referencias a los roles para evitar la restricción de clave externa
    }
}
