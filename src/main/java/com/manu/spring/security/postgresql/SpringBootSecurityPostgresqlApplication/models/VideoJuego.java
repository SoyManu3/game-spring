package com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
@Table( name = "VideoJuego")
public class VideoJuego {

    @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  @NotBlank
  @Size(max = 140)
  private String nombre;

  @NotBlank
  @Size(max = 140)
  private String creador;

  @NotNull(message = "El año de lanzamiento no puede ser nulo")
  private Integer fechaDeLanzamiento;
  
  @NotBlank
  @Size(max = 140)
  private String plataformas;   

  @NotBlank
  @Size(max = 140)
  private String genero;
  


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "posted_by", referencedColumnName = "id")
@JsonIgnore

 private User postedBy;

  public VideoJuego() {
  }
  public VideoJuego(@NotBlank @Size(max = 140) String nombre, @NotBlank @Size(max = 140) String creador,
      @NotNull(message = "La fecha de lanzamiento no puede ser nula") @PastOrPresent(message = "La fecha de lanzamiento no puede ser en el futuro") Integer fechaDeLanzamiento,
      @NotBlank @Size(max = 140) String plataformas, @NotBlank @Size(max = 140) String genero) {
    this.nombre = nombre;
    this.creador = creador;
    this.fechaDeLanzamiento = fechaDeLanzamiento;
    this.plataformas = plataformas;
    this.genero = genero;
  }

  

  // getters and setters

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
  
  public User getPostedBy() {
    return postedBy;
  }

  public void setPostedBy(User postedBy) {
    this.postedBy = postedBy;
  }


}