package com.isacode.application.usecase;

import com.isacode.domain.model.Persona;
import com.isacode.domain.ports.in.IPersonaUseCase;
import com.isacode.domain.ports.out.IPersonaRepositoryPort;

import java.util.List;
import java.util.Optional;

public class PersonaUseCaseImpl implements IPersonaUseCase {
    private final IPersonaRepositoryPort personaRepositoryPort;

    public PersonaUseCaseImpl(IPersonaRepositoryPort personaRepositoryPort) {
        this.personaRepositoryPort = personaRepositoryPort;
    }

    @Override
    public Persona crearPersona(Persona persona) {
        return personaRepositoryPort.save(persona);
    }

    @Override
    public Optional<Persona> getPersona(Long id) {
        return personaRepositoryPort.findById(id);
    }

    @Override
    public Optional<Persona> actualizar(Long id, Persona persona) {
        return personaRepositoryPort.update(id, persona);
    }

    @Override
    public void eliminar(Long id) {
        personaRepositoryPort.delete(id);
    }
}
