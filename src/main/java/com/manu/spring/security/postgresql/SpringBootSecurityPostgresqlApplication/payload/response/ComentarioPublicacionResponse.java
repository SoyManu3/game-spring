package com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.response;

import java.time.LocalDateTime;

public class ComentarioPublicacionResponse {

private String nombreAutor;
private String comentario;
private LocalDateTime fecha;

public ComentarioPublicacionResponse() {
}


public ComentarioPublicacionResponse(String nombreAutor, String comentario, LocalDateTime fecha) {
        this.nombreAutor = nombreAutor;
        this.comentario = comentario;
        this.fecha = fecha;
    }


public String getNombreAutor() {
    return nombreAutor;
}
public void setNombreAutor(String nombreAutor) {
    this.nombreAutor = nombreAutor;
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

}
