package com.isacode.application.service;

import com.isacode.domain.model.Usuario;
import com.isacode.domain.ports.in.IUsuarioUseCase;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UsuarioService implements IUsuarioUseCase {

    private final IUsuarioUseCase usuarioUseCase;

    public UsuarioService(IUsuarioUseCase usuarioUseCase) {
        this.usuarioUseCase = usuarioUseCase;
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioUseCase.crearUsuario(usuario);
    }

    @Override
    public Optional<Usuario> getUsuario(Long id) {
        return usuarioUseCase.getUsuario(id);
    }

    @Override
    public Optional<Usuario> updateUsuario(Long id, Usuario usuario) {
        return usuarioUseCase.updateUsuario(id, usuario);
    }

    @Override
    public boolean deleteUsuario(Long id) {
        return usuarioUseCase.deleteUsuario(id);
    }

    @Override
    public String loginWithUser(Map<String, String> requestMap) {
        return usuarioUseCase.loginWithUser(requestMap);
    }
}
