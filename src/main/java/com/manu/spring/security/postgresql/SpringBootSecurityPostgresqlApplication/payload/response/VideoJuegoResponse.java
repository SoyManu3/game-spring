package com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.response;

import com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.VideoJuego;

public class VideoJuegoResponse {
    private Long id;
    private String nombre;
    private String creador;
    private Integer fechaDeLanzamiento;
    private String plataformas;
    private String genero;
    private String url;
    private String img;
    private String postedBy;

    // Constructor
    public VideoJuegoResponse(VideoJuego videoJuego) {
        this.id = videoJuego.getId();
        this.nombre = videoJuego.getNombre();
        this.creador = videoJuego.getCreador();
        this.fechaDeLanzamiento = videoJuego.getFechaDeLanzamiento();
        this.plataformas = videoJuego.getPlataformas();
        this.genero = videoJuego.getGenero();
        this.url = videoJuego.getUrl();
        this.img = videoJuego.getImg();
        this.postedBy = videoJuego.getPostedBy().getUsername();
         // Asumiendo que User tiene un campo 'username'
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public Integer getFechaDeLanzamiento() {
        return fechaDeLanzamiento;
    }

    public void setFechaDeLanzamiento(Integer fechaDeLanzamiento) {
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    public String getPlataformas() {
        return plataformas;
    }

    public void setPlataformas(String plataformas) {
        this.plataformas = plataformas;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
