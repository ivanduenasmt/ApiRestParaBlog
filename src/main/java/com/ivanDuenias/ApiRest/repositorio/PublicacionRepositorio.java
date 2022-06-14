package com.ivanDuenias.ApiRest.repositorio;

import com.ivanDuenias.ApiRest.entidad.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacionRepositorio extends JpaRepository<Publicacion, Long>{
}
