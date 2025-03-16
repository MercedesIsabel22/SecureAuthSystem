package com.isacode.infrastructure.config;

import com.isacode.application.service.PersonaService;
import com.isacode.application.service.UsuarioService;
import com.isacode.application.usecase.PersonaUseCaseImpl;
import com.isacode.application.usecase.UsuarioUseCaseImpl;
import com.isacode.domain.ports.out.IPersonaRepositoryPort;

import com.isacode.domain.ports.out.IUsuarioRepositoryPort;
import com.isacode.infrastructure.repository.PersonaJpaRepositoryAdapter;

import com.isacode.infrastructure.repository.UsuarioJpaRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public PersonaService personaService(IPersonaRepositoryPort personaRepositoryPort) {
        return new PersonaService(new PersonaUseCaseImpl(personaRepositoryPort));
    }

    @Bean
    public IPersonaRepositoryPort personaRepositoryPort(PersonaJpaRepositoryAdapter personaJpaRepositoryAdapter) {
        return personaJpaRepositoryAdapter;
    }
    @Bean
    public UsuarioService usuarioService(IUsuarioRepositoryPort usuarioRepositoryPort){
        return new UsuarioService(new UsuarioUseCaseImpl(usuarioRepositoryPort));

    }
    @Bean
    public IUsuarioRepositoryPort usuarioRepositoryPort(UsuarioJpaRepositoryAdapter usuarioJpaRepositoryAdapter){
        return usuarioJpaRepositoryAdapter;
    }

}
