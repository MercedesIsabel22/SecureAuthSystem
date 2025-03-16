package com.isacode.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Persona {
    private Long id;
    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;
    private String genero;

    public Persona(Long id, String nombre, String apellidos, Date fechaNacimiento, String genero) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }
}
