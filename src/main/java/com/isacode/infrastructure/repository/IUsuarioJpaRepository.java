package com.isacode.infrastructure.repository;

import com.isacode.infrastructure.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {
}
