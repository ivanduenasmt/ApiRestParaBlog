package com.ivanDuenias.ApiRest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioDTO {

    private long id;
    private String nombre;
    private String email;
    private String cuerpo;

}

