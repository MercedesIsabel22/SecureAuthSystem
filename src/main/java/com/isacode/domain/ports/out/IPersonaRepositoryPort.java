package com.isacode.domain.ports.out;

import com.isacode.domain.model.Persona;

import java.util.List;
import java.util.Optional;

public interface IPersonaRepositoryPort {
    Persona save(Persona persona);

    Optional<Persona> findById(Long id);

    Optional<Persona >update(Long id, Persona persona);

    void delete(Long id);
}
