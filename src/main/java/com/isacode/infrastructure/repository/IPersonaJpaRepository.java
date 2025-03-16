package com.isacode.infrastructure.repository;


import com.isacode.infrastructure.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaJpaRepository extends JpaRepository<PersonaEntity, Long> {
}
