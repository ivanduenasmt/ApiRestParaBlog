package com.ivanDuenias.ApiRest.servicio;

import com.ivanDuenias.ApiRest.dto.PublicacionDTO;

import java.util.List;

public interface PublicacionServicio {

    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);
    public List<PublicacionDTO> obtenerTodasPublicaciones();
    public PublicacionDTO obtenerPublicacionPorId(long id);
    public PublicacionDTO actualizarPublicacionPorId(PublicacionDTO publicacionDTO, long id);
    public void borrarPublicacion(long id);
}
