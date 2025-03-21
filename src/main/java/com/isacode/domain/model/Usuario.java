package com.isacode.domain.model;

import com.isacode.infrastructure.entity.PersonaEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario {
    private Long id;
    private String nombreUsuario;
    private String contrasenia;
    private String correoElectronico;
    private Persona persona;

    public Usuario(Long id, String nombreUsuario, String contrasenia, String correoElectronico, Persona persona) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.correoElectronico = correoElectronico;
        this.persona = persona;
    }
}
