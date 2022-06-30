package com.ivanDuenias.ApiRest.repositorio;

import com.ivanDuenias.ApiRest.entidad.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface RolRepositorio extends JpaRepository<Rol, Long> {
    public Optional<Rol> findByNombre(String nombre);
}
