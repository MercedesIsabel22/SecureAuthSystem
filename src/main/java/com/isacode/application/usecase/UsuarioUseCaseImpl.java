package com.isacode.application.usecase;

import com.isacode.domain.model.Usuario;
import com.isacode.domain.ports.in.IUsuarioUseCase;
import com.isacode.domain.ports.out.IUsuarioRepositoryPort;


import java.util.Map;
import java.util.Optional;

public class UsuarioUseCaseImpl implements IUsuarioUseCase {
    private final IUsuarioRepositoryPort usuarioRepositoryPort;

    public UsuarioUseCaseImpl(IUsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepositoryPort.save(usuario);
    }

    @Override
    public Optional<Usuario> getUsuario(Long id) {
        return usuarioRepositoryPort.findById(id);
    }

    @Override
    public Optional<Usuario> updateUsuario(Long id, Usuario usuario) {
        return usuarioRepositoryPort.update(id, usuario);
    }

    @Override
    public boolean deleteUsuario(Long id) {
      return usuarioRepositoryPort.deleteById(id);
    }

    @Override
    public String loginWithUser(Map<String, String> requestMap) {
        return usuarioRepositoryPort.loginWithUser(requestMap);
    }
}
