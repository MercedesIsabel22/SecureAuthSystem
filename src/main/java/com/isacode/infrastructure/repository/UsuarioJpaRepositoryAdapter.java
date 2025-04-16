package com.isacode.infrastructure.repository;

import com.isacode.domain.model.Usuario;
import com.isacode.domain.ports.out.IUsuarioRepositoryPort;
import com.isacode.infrastructure.entity.UsuarioEntity;
import com.isacode.infrastructure.security.CustomerDetailService;
import com.isacode.infrastructure.security.Jwt.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


import java.util.Map;
import java.util.Optional;

@Component
public class UsuarioJpaRepositoryAdapter implements IUsuarioRepositoryPort {
    private final IUsuarioJpaRepository usuarioJpaRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;
    private final CustomerDetailService customerDetailService;
    public UsuarioJpaRepositoryAdapter(IUsuarioJpaRepository usuarioJpaRepository, AuthenticationManager authenticationManager, JwtUtil jwtUtil, CustomerDetailService customerDetailService) {
        this.usuarioJpaRepository = usuarioJpaRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.customerDetailService = customerDetailService;
    }

    public String loginWithUser(Map<String, String> requestMap){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("nombreUsuario"), requestMap.get("contrasenia")));
            if(authentication.isAuthenticated()){
                String token_generado = jwtUtil.generateToken(
                        customerDetailService.getUserDetail().getNombreUsuario(),
                        customerDetailService.getUserDetail().getContrasenia()
                );
                return token_generado;
            }

        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
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
