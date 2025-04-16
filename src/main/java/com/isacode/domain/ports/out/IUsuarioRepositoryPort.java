package com.isacode.domain.ports.out;

import com.isacode.domain.model.Persona;
import com.isacode.domain.model.Usuario;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IUsuarioRepositoryPort {
    Usuario save(Usuario usuario);

    Optional<Usuario> findById(Long id);

    Optional<Usuario> update(Long id, Usuario usuario);

    boolean deleteById(Long id);
    String loginWithUser(Map<String, String> requestMap);

}
