package com.ivanDuenias.ApiRest.dto;

import com.ivanDuenias.ApiRest.entidad.Comentario;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;


public class PublicacionDTO {


    private Long id;

    @NotEmpty
    @Size(min = 2, message = "El titulo de la publicación deberia tener al menos dos carácteres")
    private String titulo;

    @NotEmpty
    @Size(min = 10, message = "El titulo de la publicación deberia tener al menos diez carácteres")
    private String descripcion;

    @NotEmpty
    private String contenido;

    private Set<Comentario> comentarios;

    public PublicacionDTO() {
    }

    public PublicacionDTO(Long id, String titulo, String descripcion, String contenido, Set<Comentario> comentarios) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contenido = contenido;
        this.comentarios = comentarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Set<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}
