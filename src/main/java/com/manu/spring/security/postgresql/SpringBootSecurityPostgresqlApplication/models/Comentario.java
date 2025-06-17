package com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



@Entity
@Table(
    name = "comentarios_publicacion"
)
public class Comentario {
    @Id   
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotBlank
    @Size(max = 140)
    private String comentario;
    private LocalDateTime fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posted_by", referencedColumnName = "id", nullable = false)
    private User autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "videojuego_id", referencedColumnName = "id", nullable = false)
    private VideoJuego videoJuego;

    public Comentario(@NotBlank @Size(max = 140) String comentario) {
        this.comentario = comentario;
    }
    public Comentario() {
    }


    @PrePersist
    protected void onCreate() {
    this.fecha = LocalDateTime.now();
}


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    public User getAutor() {
        return autor;
    }
    public void setAutor(User user) {
        this.autor = user;
    }
    public VideoJuego getVideoJuego() {
        return videoJuego;
    }
    public void setVideoJuego(VideoJuego videoJuego) {
        this.videoJuego = videoJuego;
    }
}
