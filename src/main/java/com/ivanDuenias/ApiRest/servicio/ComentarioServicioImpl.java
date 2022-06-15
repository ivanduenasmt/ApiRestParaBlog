package com.ivanDuenias.ApiRest.servicio;

import com.ivanDuenias.ApiRest.dto.ComentarioDTO;
import com.ivanDuenias.ApiRest.entidad.Comentario;
import com.ivanDuenias.ApiRest.entidad.Publicacion;
import com.ivanDuenias.ApiRest.excepcion.BlogAppException;
import com.ivanDuenias.ApiRest.excepcion.ResourceNotFoundException;
import com.ivanDuenias.ApiRest.repositorio.ComentarioRepositorio;
import com.ivanDuenias.ApiRest.repositorio.PublicacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentarioServicioImpl implements ComentarioServicio{

    @Autowired
    private ComentarioRepositorio comentarioRepositorio;

    @Autowired
    private PublicacionRepositorio publicacionRepositorio;

    @Override
    public ComentarioDTO crearComentario(long id, ComentarioDTO comentarioDTO) {
        //Convertir a entidad

        //1. Crear comentsrio //Simil crear transaccion
        Comentario comentario = convertirAEntidad(comentarioDTO);
        //2. Buscar publicacion //Simil buscar cuenta
        Publicacion publicacion = publicacionRepositorio.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException());

        //3. Establecer comentario en la publicacion //Simil establecer transaccion en la cuenta
        comentario.setPublicacion(publicacion);
        //4. Crear nuevoComentario donde se guardan los cambios y retornarlo
        Comentario nuevoComentario = comentarioRepositorio.save(comentario);
        return convertirADTO(nuevoComentario);

    }

    @Override
    public List<ComentarioDTO> obtenerComentariosPorPublicacionId(long id) {
        List<Comentario> comentarios = comentarioRepositorio.findByPublicacionId(id);
        return comentarios.stream().map(comentario -> convertirADTO(comentario)).collect(Collectors.toList());
    }

    @Override
    public ComentarioDTO obtenerComentarioPorId(long id, long idComentario) {
        Publicacion publicacion = publicacionRepositorio.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException());

        Comentario comentario = comentarioRepositorio.findById(idComentario)
                .orElseThrow(() ->new ResourceNotFoundException());

        if(!comentario.getPublicacion().getId().equals(publicacion.getId())){
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "EL COMENTARIO NO PERTENECE A LA PUBLICACION");
        }

        return convertirADTO(comentario);
    }

    @Override
    public ComentarioDTO actualizarComentario(long id, long idComentario, ComentarioDTO solicitudComentario) {
        Publicacion publicacion = publicacionRepositorio.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException());

        Comentario comentario = comentarioRepositorio.findById(idComentario)
                .orElseThrow(() ->new ResourceNotFoundException());

        if(!comentario.getPublicacion().getId().equals(publicacion.getId())){
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "EL COMENTARIO NO PERTENECE A LA PUBLICACION");
        }

        comentario.setNombre(solicitudComentario.getNombre());
        comentario.setEmail(solicitudComentario.getEmail());
        comentario.setCuerpo(solicitudComentario.getCuerpo());

        Comentario comentarioActualizado = comentarioRepositorio.save(comentario);
        return convertirADTO(comentario);
    }

    @Override
    public void eliminarComentario(long id, long idComentario) {
        Publicacion publicacion = publicacionRepositorio.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException());

        Comentario comentario = comentarioRepositorio.findById(idComentario)
                .orElseThrow(() ->new ResourceNotFoundException());

        if(!comentario.getPublicacion().getId().equals(publicacion.getId())){
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
        }

        comentarioRepositorio.delete(comentario);
    }


    private ComentarioDTO convertirADTO(Comentario comentario){
        ComentarioDTO comentarioDTO = new ComentarioDTO();

        comentarioDTO.setId(comentario.getIdComentario());
        comentarioDTO.setNombre(comentario.getNombre());
        comentarioDTO.setEmail(comentario.getEmail());
        comentarioDTO.setCuerpo(comentario.getCuerpo());

        return comentarioDTO;
    }

    private Comentario convertirAEntidad(ComentarioDTO comentarioDTO){
        Comentario comentario = new Comentario();

        comentario.setIdComentario(comentarioDTO.getId());
        comentario.setNombre(comentarioDTO.getNombre());
        comentario.setEmail(comentarioDTO.getEmail());
        comentario.setCuerpo(comentarioDTO.getCuerpo());

        return comentario;
    }
}
