package com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Comentario;

@Repository
public interface ComentarioPublicacionRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByVideoJuego_Id(Long videojuegoId);
}