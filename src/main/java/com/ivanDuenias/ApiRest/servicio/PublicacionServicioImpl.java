package com.ivanDuenias.ApiRest.servicio;

import com.ivanDuenias.ApiRest.dto.PublicacionDTO;
import com.ivanDuenias.ApiRest.dto.PublicacionRespuesta;
import com.ivanDuenias.ApiRest.entidad.Publicacion;
import com.ivanDuenias.ApiRest.excepcion.ResourceNotFoundException;
import com.ivanDuenias.ApiRest.repositorio.PublicacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public PublicacionRespuesta obtenerTodasPublicaciones(int numeroPagina, int medidaPagina, String ordenarPor, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(ordenarPor).ascending():Sort.by(ordenarPor).descending();

        Pageable pageable = PageRequest.of(numeroPagina, medidaPagina, sort);

        Page<Publicacion> publicacions = publicacionRepositorio.findAll(pageable);

        List<Publicacion> publicaciones = publicacions.getContent();
        List<PublicacionDTO> contenido = publicaciones.stream().map(publicacion -> convertirADTO(publicacion)).collect(Collectors.toList());

        PublicacionRespuesta respuesta = new PublicacionRespuesta();
        respuesta.setContenido(contenido);
        respuesta.setNumeroPagina(publicacions.getNumber());
        respuesta.setMedidaPagina(publicacions.getSize());
        respuesta.setTotalElementos(publicacions.getTotalElements());
        respuesta.setTotalPaginas(publicacions.getTotalPages());
        respuesta.setUltima(publicacions.isLast());

        return respuesta;
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
