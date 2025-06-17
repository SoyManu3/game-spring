package com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.request;

public class ComentarioRequest {
    private Long videojuegoId;
    private String comentario;

   
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public Long getVideojuegoId() {
        return videojuegoId;
    }
    public void setVideojuegoId(Long videojuegoId) {
        this.videojuegoId = videojuegoId;
    }
    
}
