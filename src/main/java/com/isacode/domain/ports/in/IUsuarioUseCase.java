package com.isacode.domain.ports.in;


import com.isacode.domain.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioUseCase {

    Usuario crearUsuario(Usuario usuario);

    Optional<Usuario> getUsuario(Long id);

    Optional<Usuario> actualizar(Long id, Usuario usuario);

    void eliminar(Long id);
}
