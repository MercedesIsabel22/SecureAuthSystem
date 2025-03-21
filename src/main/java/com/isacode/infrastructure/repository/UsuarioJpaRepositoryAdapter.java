package com.isacode.infrastructure.repository;

import com.isacode.domain.model.Usuario;
import com.isacode.domain.ports.out.IUsuarioRepositoryPort;
import com.isacode.infrastructure.entity.UsuarioEntity;
import org.springframework.stereotype.Component;


import java.util.Optional;

@Component
public class UsuarioJpaRepositoryAdapter implements IUsuarioRepositoryPort {
    private final IUsuarioJpaRepository usuarioJpaRepository;

    public UsuarioJpaRepositoryAdapter(IUsuarioJpaRepository usuarioJpaRepository) {
        this.usuarioJpaRepository = usuarioJpaRepository;
    }

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity usuarioEntity = UsuarioEntity.fromDomainModel(usuario);
        UsuarioEntity saveUsuarioEntity = usuarioJpaRepository.save(usuarioEntity);
        return saveUsuarioEntity.toDomainModel();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioJpaRepository.findById(id)
                .map(UsuarioEntity::toDomainModel);
    }


    @Override
    public Optional<Usuario> update(Long id, Usuario usuario) {
        if (usuarioJpaRepository.existsById(usuario.getId())) {
            UsuarioEntity usuarioEntity = UsuarioEntity.fromDomainModel(usuario);
            UsuarioEntity updateUsuarioEntity = usuarioJpaRepository.save(usuarioEntity);
            return Optional.of(updateUsuarioEntity.toDomainModel());
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        if (usuarioJpaRepository.existsById(id)) {
            usuarioJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
