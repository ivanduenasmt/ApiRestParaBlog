package com.ivanDuenias.ApiRest.servicio;

import com.ivanDuenias.ApiRest.dto.PublicacionDTO;
import com.ivanDuenias.ApiRest.entidad.Publicacion;
import com.ivanDuenias.ApiRest.excepcion.ResourceNotFoundException;
import com.ivanDuenias.ApiRest.repositorio.PublicacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicacionServicioImpl implements PublicacionServicio{

    @Autowired
    private PublicacionRepositorio publicacionRepositorio;

    @Override
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
        //Convertimos de DTO a entidad
        Publicacion publicacion = convertirAEntidad(publicacionDTO);

        //Convertimos de entidad a DTO
        PublicacionDTO publicacionDTO1 = convertirADTO(publicacion);
        return publicacionDTO1;
    }

    @Override
    public List<PublicacionDTO> obtenerTodasPublicaciones() {
        List<Publicacion> publicaciones = publicacionRepositorio.findAll();
        return publicaciones.stream().map(publicacion -> convertirADTO(publicacion)).collect(Collectors.toList());
    }

    @Override
    public PublicacionDTO obtenerPublicacionPorId(long id) {
        Publicacion publicacion = publicacionRepositorio.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException());
        return convertirADTO(publicacion);
    }

    @Override
    public PublicacionDTO actualizarPublicacionPorId(PublicacionDTO publicacionDTO, long id) {
        Publicacion publicacion = publicacionRepositorio.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException());

        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        publicacion.setContenido(publicacionDTO.getContenido());

        Publicacion publicacionActualizada = publicacionRepositorio.save(publicacion);
        return convertirADTO(publicacionActualizada);
    }

    @Override
    public void borrarPublicacion(long id) {
        Publicacion publicacion = publicacionRepositorio.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException());

        publicacionRepositorio.delete(publicacion);
    }

    //Convertimos de DTO a entidad
    public Publicacion convertirAEntidad(PublicacionDTO publicacionDTO){
        Publicacion publicacion = new Publicacion();

        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        publicacion.setContenido(publicacionDTO.getDescripcion());
        return publicacionRepositorio.save(publicacion);
    }

    //Convertimos de entidad a DTO
    public PublicacionDTO convertirADTO(Publicacion publicacion){
        PublicacionDTO publicacionRespuesta = new PublicacionDTO();

        publicacionRespuesta.setTitulo(publicacion.getTitulo());
        publicacionRespuesta.setDescripcion(publicacion.getDescripcion());
        publicacionRespuesta.setContenido(publicacion.getContenido());
        return publicacionRespuesta;
    }
    
}
