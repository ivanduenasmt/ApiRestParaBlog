package com.ivanDuenias.ApiRest.controlador;

import com.ivanDuenias.ApiRest.dto.ComentarioDTO;
import com.ivanDuenias.ApiRest.servicio.ComentarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ComentarioControlador {

    @Autowired
    private ComentarioServicio comentarioServicio;

    @GetMapping("/publicaciones/{idPublicacion}/comentarios")
    public List<ComentarioDTO> obtener(@PathVariable("idPublicacion") long idPublicacion) {
        return comentarioServicio.obtenerComentariosPorPublicacionId(idPublicacion);
    }

    @GetMapping("/publicaciones/{idPublicacion}/comentarios/{idComentario}")
    public ResponseEntity<ComentarioDTO> obtenerComentarioPorId
            (@PathVariable("idPublicacion") long idPublicacion, @PathVariable("idComentario") long idComentario) {
        return new ResponseEntity<>(comentarioServicio.obtenerComentarioPorId(idPublicacion, idComentario), HttpStatus.OK);
    }

    @PostMapping("/publicaciones/{idPublicacion}/comentarios")
    public ResponseEntity<ComentarioDTO> crearComentario
            (@PathVariable("idPublicacion") long idPublicacion, @RequestBody ComentarioDTO comentarioDTO) {
        return new ResponseEntity<>(comentarioServicio.crearComentario(idPublicacion, comentarioDTO), HttpStatus.CREATED);
    }

    @PutMapping("/publicaciones/{idPublicacion}/comentarios/{idComentario}")
    public ResponseEntity<ComentarioDTO> actualizarComentario
            (@PathVariable("idPublicacion") long idPublicacion,@PathVariable("idComentario") long idComentario, @RequestBody ComentarioDTO comentarioDTO){
        return new ResponseEntity<>(comentarioServicio.actualizarComentario(idPublicacion, idComentario, comentarioDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/publicaciones/{idPublicacion}/comentarios/{idComentario}")
    public ResponseEntity<String> eliminarComentario
            (@PathVariable("idPublicacion") long idPublicacion, @PathVariable("idComentario") long idComentario){
        comentarioServicio.eliminarComentario(idPublicacion, idComentario);
        return new ResponseEntity<>("Comentario eliminado con exito", HttpStatus.OK);
    }

}
