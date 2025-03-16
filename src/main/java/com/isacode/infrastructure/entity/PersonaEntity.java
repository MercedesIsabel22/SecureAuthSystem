package com.isacode.infrastructure.entity;


import com.isacode.domain.model.Persona;

import lombok.Getter;

import lombok.Setter;


import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "personas")
public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;
    private String genero;

    public PersonaEntity() {
    }

    public PersonaEntity(Long id, String nombre, String apellidos, Date fechaNacimiento, String genero) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }

    public static PersonaEntity fromDomainModel(Persona persona) {
        return new PersonaEntity(persona.getId(), persona.getNombre(), persona.getApellidos(), persona.getFechaNacimiento(), persona.getGenero());
    }

    public Persona toDomainModel() {
        return new Persona(id, nombre, apellidos, fechaNacimiento, genero);
    }

}
