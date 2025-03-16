package com.isacode.domain.ports.in;

import com.isacode.domain.model.Persona;

import java.util.List;
import java.util.Optional;

public interface IPersonaUseCase {

    Persona crearPersona(Persona persona);

    Optional<Persona> getPersona(Long id);

    Optional<Persona> actualizar(Long id,Persona persona);

    void eliminar(Long id);
}
