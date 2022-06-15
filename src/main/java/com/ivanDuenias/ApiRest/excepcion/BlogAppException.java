package com.ivanDuenias.ApiRest.excepcion;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class BlogAppException extends RuntimeException{

    private static final long seriaVersionUID= 1L;

    private HttpStatus estado;
    private String mensaje;

    private BlogAppException(HttpStatus estado, String mensaje, String mensaje1){
        super();
        this.estado = estado;
        this.mensaje = mensaje;
        this.mensaje = mensaje1;
    }
}
