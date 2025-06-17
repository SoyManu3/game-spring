package com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.ERole;
import com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}