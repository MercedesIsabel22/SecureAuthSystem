package com.isacode.infrastructure.repository;

import com.isacode.domain.model.Persona;
import com.isacode.infrastructure.entity.PersonaEntity;
import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PersonaJpaRepositoryAdapterTest {
    @Mock
    IPersonaJpaRepository personaJpaRepository;
    @InjectMocks
    PersonaJpaRepositoryAdapter personaJpaRepositoryAdapter;

    @BeforeEach
    void SetUp() {
        MockitoAnnotations.openMocks(this);
        personaJpaRepositoryAdapter = new PersonaJpaRepositoryAdapter(personaJpaRepository);
    }

    @Test
    void saveExitosoPersonaEntity() {
        //Lo que enviamos al metodo de la clase que estamos probando
        Persona persona = new Persona(1L, "isabel", "naba", new Date(), "Femenino");
        //Lo que enviamos a BD como simulación
        PersonaEntity personaEntity = new PersonaEntity(1L, "isabel", "naba", new Date(), "Femenino");

        //simulando comportamiento
        when(personaJpaRepository.save(Mockito.any(PersonaEntity.class))).thenReturn(personaEntity);

        Persona personaAdapter = personaJpaRepositoryAdapter.save(persona);
        assertNotNull(personaAdapter);
        assertEquals(persona.getId(), personaAdapter.getId());
        assertEquals(persona.getNombre(), personaAdapter.getNombre());
    }

    @Test
    void testUpdateExitoso() {
        Long id = 1L;
        Persona personaActualizada = new Persona(id, "NuevoNombre", "NuevoApellido", new Date(), "Femenino");
        when(personaJpaRepository.existsById(id)).thenReturn(true);

        PersonaEntity personaEntityActualizada = PersonaEntity.fromDomainModel(personaActualizada);
        when(personaJpaRepository.save(Mockito.any(PersonaEntity.class))).thenReturn(personaEntityActualizada);

        Optional<Persona> pers = personaJpaRepositoryAdapter.update(id, personaActualizada);

        assertTrue(pers.isPresent());

        Persona personaActualizadaResul = pers.get();
        assertEquals(personaActualizada.getId(), personaActualizadaResul.getId());
        assertEquals(personaActualizada.getNombre(), personaActualizadaResul.getNombre());
        assertEquals(personaActualizada.getApellidos(), personaActualizadaResul.getApellidos());
        assertEquals(personaActualizada.getGenero(), personaActualizadaResul.getGenero());

    }

    @Test
    void findById_IsEmpty() {
        Long id = 1L;

        when(personaJpaRepository.findById(id)).thenReturn(Optional.empty());
        Optional<Persona> optionalPersona = personaJpaRepositoryAdapter.findById(id);
        assertTrue(optionalPersona.isEmpty());

    }

    @Test
    void deleteById_Exitoso() {
        // Simulación de un ID existente
        Long id = 1L;

        // Simulación del comportamiento del repositorio
        when(personaJpaRepository.existsById(id)).thenReturn(true);

        // Ejecutar el método
        boolean deleted = personaJpaRepositoryAdapter.deleteById(id);

        // Verificaciones
        assertTrue(deleted); // Debe ser true porque la persona existe y se elimina
    }


}