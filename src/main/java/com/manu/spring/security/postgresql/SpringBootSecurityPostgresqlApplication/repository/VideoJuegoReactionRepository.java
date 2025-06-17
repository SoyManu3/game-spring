package com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Reaction;
import com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.User;
import com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.VideoJuego;
import com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.VideoJuegoReaction;

@Repository
public interface VideoJuegoReactionRepository extends JpaRepository<VideoJuegoReaction, Long> {
@Query("SELECT COUNT(vr) FROM VideoJuegoReaction vr WHERE vr.videoJuego.id = :videoJuegoId AND vr.reaction.id = :reactionId")
    Long countReactionsByVideoJuegoIdAndReactionId(@Param("videoJuegoId") Long videoJuegoId, @Param("reactionId") Long reactionId);

    boolean existsByUserAndVideoJuegoAndReaction(User user, VideoJuego videoJuego, Reaction reaction);

}
