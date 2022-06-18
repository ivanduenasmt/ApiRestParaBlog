package com.ivanDuenias.ApiRest.controlador;

import com.ivanDuenias.ApiRest.dto.PublicacionDTO;
import com.ivanDuenias.ApiRest.dto.PublicacionRespuesta;
import com.ivanDuenias.ApiRest.servicio.PublicacionServicio;
import com.ivanDuenias.ApiRest.utilerias.AppConstantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionControlador {

    @Autowired
    private PublicacionServicio publicacionServicio;

    @GetMapping("/all")
    public PublicacionRespuesta listarPublicaciones
            (@RequestParam(value = "pageNo", defaultValue = AppConstantes.NUMERO_PAGINA_POR_DEFECTO, required = false) int numeroPagina,
             @RequestParam(value = "pageSize", defaultValue = AppConstantes.MEDIDA_PAGINA_POR_DEFECTO, required = false) int medidaPagina,
             @RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO , required = false) String ordenarPor,
             @RequestParam(value = "sortDir",defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir) {
        return publicacionServicio.obtenerTodasPublicaciones(numeroPagina,medidaPagina,ordenarPor,sortDir);
    }

    @GetMapping("{id}")
    public ResponseEntity<PublicacionDTO> obtenerPorId(@PathVariable("id") long id) {
        return new ResponseEntity<>(publicacionServicio.obtenerPublicacionPorId(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<PublicacionDTO> guardarPublicacion(@RequestBody PublicacionDTO publicacionDTO) {
        return new ResponseEntity<>(publicacionServicio.crearPublicacion(publicacionDTO), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<PublicacionDTO> actualizarPorId(@RequestBody PublicacionDTO publicacionDTO, @PathVariable("id") long id) {
        PublicacionDTO publicacionRespuesta = publicacionServicio.actualizarPublicacionPorId(publicacionDTO, id);
        return new ResponseEntity<>(publicacionRespuesta, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<PublicacionDTO> borrarPorId(@PathVariable("id") long id) {
        publicacionServicio.borrarPublicacion(id);
        return new ResponseEntity<PublicacionDTO>(HttpStatus.OK);
    }
}
