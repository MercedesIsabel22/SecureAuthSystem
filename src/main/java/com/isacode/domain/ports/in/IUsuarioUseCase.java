package com.isacode.domain.ports.in;


import com.isacode.domain.model.Usuario;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IUsuarioUseCase {

    Usuario crearUsuario(Usuario usuario);

    Optional<Usuario> getUsuario(Long id);

    Optional<Usuario> updateUsuario(Long id, Usuario usuario);

    boolean deleteUsuario(Long id);

    String loginWithUser(Map<String, String> requestMap);
}
