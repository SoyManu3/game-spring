package com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Reaction;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {

}

