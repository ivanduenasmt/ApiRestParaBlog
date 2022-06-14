package com.ivanDuenias.ApiRest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicacionDTO {

    private Long id;
    private String titulo;
    private String descripcion;
    private String contenido;

}
