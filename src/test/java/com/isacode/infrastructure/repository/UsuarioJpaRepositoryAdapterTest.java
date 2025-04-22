package com.isacode.infrastructure.repository;

import com.isacode.domain.model.Persona;
import com.isacode.domain.model.Usuario;
import com.isacode.infrastructure.entity.UsuarioEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;


class UsuarioJpaRepositoryAdapterTest {
    @Mock
    IUsuarioJpaRepository usuarioJpaRepository;

    @InjectMocks
    UsuarioJpaRepositoryAdapter usuarioJpaRepositoryAdapter;

    @BeforeEach
    void SetUp() {
        MockitoAnnotations.openMocks(this);
        usuarioJpaRepositoryAdapter = new UsuarioJpaRepositoryAdapter(usuarioJpaRepository);

    }

    @Test
    void saveExitosoUsuarioEntity() {
        //Arrange
        Usuario usuario = new Usuario( 1L, "nameTest", "mnb2", "test@exam.com",
                new Persona(1L, "isas", "cod", new Date(), "Femenino"));

        UsuarioEntity usuarioEntity = UsuarioEntity.fromDomainModel(usuario);
        when(usuarioJpaRepository.save(Mockito.any(UsuarioEntity.class))).thenReturn(usuarioEntity);

        // Act
        Usuario usuarioAdapter = usuarioJpaRepositoryAdapter.save(usuario);

        // Assert
        assertNotNull(usuarioAdapter);
        assertEquals(usuario.getId(), usuarioAdapter.getId());
        assertEquals(usuario.getNombreUsuario(), usuarioAdapter.getNombreUsuario());
        assertEquals(usuario.getCorreoElectronico(), usuarioAdapter.getCorreoElectronico());
    }


    @Test
    void UpdateExitoso() {
        // Arrange
        Long id = 1L;
        Usuario usuarioActualizado = new Usuario(id, "nuevoUsuario", "ppass123", "xamp@exam.com",
                new Persona(1L, "Ric", "Villa", new Date(), "femenino"));
        when(usuarioJpaRepository.existsById(id)).thenReturn(true);

        UsuarioEntity usuarioEntityActualizado = UsuarioEntity.fromDomainModel(usuarioActualizado);
        when(usuarioJpaRepository.save(Mockito.any(UsuarioEntity.class))).thenReturn(usuarioEntityActualizado);

        // Act
        Optional<Usuario> usuarioActualizadoResul = usuarioJpaRepositoryAdapter.update(id, usuarioActualizado);

        // Assert
        assertTrue(usuarioActualizadoResul.isPresent());
        assertEquals(usuarioActualizado.getId(), usuarioActualizadoResul.get().getId());
        assertEquals(usuarioActualizado.getNombreUsuario(), usuarioActualizadoResul.get().getNombreUsuario());
        assertEquals(usuarioActualizado.getCorreoElectronico(), usuarioActualizadoResul.get().getCorreoElectronico());
    }

    @Test
    void update_shouldReturnEmptyOptionalIfNotExist() {
        // Arrange
        Long id = 1L;
        Usuario usuarioActualizado = new Usuario(id, "usuarioNoExiste", "passNoExiste", "noExiste@example.com", null);
        when(usuarioJpaRepository.existsById(id)).thenReturn(false);

        // Act
        Optional<Usuario> optionalUpdateUsuario = usuarioJpaRepositoryAdapter.update(id, usuarioActualizado);

        // Assert
        assertTrue(optionalUpdateUsuario.isEmpty());
    }

    @Test
    void findById_IsEmpty() {
        // Arrange
        Long id = 1L;
        when(usuarioJpaRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Optional<Usuario> optionalUsuario = usuarioJpaRepositoryAdapter.findById(id);

        // Assert
        assertTrue(optionalUsuario.isEmpty());
    }

    @Test
    void deleteById_shouldDeleteUsuarioIfExist() {
        // Arrange
        Long id = 1L;
        when(usuarioJpaRepository.existsById(id)).thenReturn(true);

        // Act
        boolean delete = usuarioJpaRepositoryAdapter.deleteById(id);

        // Assert
        assertTrue(delete);
    }

    @Test
    void deleteById_shouldReturnFalseIfNotExist() {
        // Arrange
        Long id = 1L;
        when(usuarioJpaRepository.existsById(id)).thenReturn(false);

        // Act
        boolean deleted = usuarioJpaRepositoryAdapter.deleteById(id);

        // Assert
        assertFalse(deleted);
    }
}
