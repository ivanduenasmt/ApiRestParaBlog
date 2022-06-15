package com.ivanDuenias.ApiRest.servicio;

import com.ivanDuenias.ApiRest.dto.PublicacionDTO;
import com.ivanDuenias.ApiRest.dto.PublicacionRespuesta;

public interface PublicacionServicio {

    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);
    public PublicacionRespuesta obtenerTodasPublicaciones(int numeroPagina, int medidaPagina, String ordenarPor, String sortDir);
    public PublicacionDTO obtenerPublicacionPorId(long id);
    public PublicacionDTO actualizarPublicacionPorId(PublicacionDTO publicacionDTO, long id);
    public void borrarPublicacion(long id);
}
