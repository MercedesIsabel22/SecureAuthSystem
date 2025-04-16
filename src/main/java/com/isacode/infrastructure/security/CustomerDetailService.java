package com.isacode.infrastructure.security;

import com.isacode.infrastructure.entity.UsuarioEntity;
import com.isacode.infrastructure.repository.IUsuarioJpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
@Service
public class CustomerDetailService implements UserDetailsService {

    private final IUsuarioJpaRepository usuarioJpaRepository;

    private UsuarioEntity usuarioDetail;

    public CustomerDetailService(IUsuarioJpaRepository usuarioJpaRepository) {
        this.usuarioJpaRepository = usuarioJpaRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        usuarioDetail = usuarioJpaRepository.findByNombreUsuario(username);
        if (!Objects.isNull(usuarioDetail)) {
            return new User(usuarioDetail.getNombreUsuario(), usuarioDetail.getContrasenia(), new ArrayList<>());


        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public UsuarioEntity getUserDetail() {
        return usuarioDetail;
    }


}
