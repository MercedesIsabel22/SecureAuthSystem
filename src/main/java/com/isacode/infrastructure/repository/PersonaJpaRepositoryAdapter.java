package com.isacode.infrastructure.repository;

import com.isacode.domain.model.Persona;
import com.isacode.domain.ports.out.IPersonaRepositoryPort;
import com.isacode.infrastructure.entity.PersonaEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonaJpaRepositoryAdapter implements IPersonaRepositoryPort {
    private final IPersonaJpaRepository personaJpaRepository;

    public PersonaJpaRepositoryAdapter(IPersonaJpaRepository personaJpaRepository) {
        this.personaJpaRepository = personaJpaRepository;
    }

    @Override
    public Persona save(Persona persona) {
        PersonaEntity personaEntity = PersonaEntity.fromDomainModel(persona);
        PersonaEntity savePersonEntity = personaJpaRepository.save(personaEntity);
        return savePersonEntity.toDomainModel();
    }

    @Override
    public Optional<Persona> findById(Long id) {
        return personaJpaRepository.findById(id)
                .map(PersonaEntity::toDomainModel);
    }

    @Override
    public Optional<Persona> update(Long id, Persona persona) {
        if (personaJpaRepository.existsById(persona.getId())) {
            PersonaEntity personaEntity = PersonaEntity.fromDomainModel(persona);
            PersonaEntity updatePersonEntity = personaJpaRepository.save(personaEntity);
            return Optional.of(updatePersonEntity.toDomainModel());
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        if (personaJpaRepository.existsById(id)) {
            personaJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
