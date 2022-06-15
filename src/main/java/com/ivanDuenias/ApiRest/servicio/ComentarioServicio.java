package com.ivanDuenias.ApiRest.servicio;

import com.ivanDuenias.ApiRest.dto.ComentarioDTO;

import java.util.List;

public interface ComentarioServicio {

    public ComentarioDTO crearComentario(long id, ComentarioDTO comentarioDTO);
    public List<ComentarioDTO> obtenerComentariosPorPublicacionId(long id);
    public ComentarioDTO obtenerComentarioPorId(long id, long idComentario);
    public ComentarioDTO actualizarComentario(long id, long idComentario, ComentarioDTO solicitudComentario);
    public void eliminarComentario(long id, long idComentario);
}
