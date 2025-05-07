package com.backend.sgd.repository;

import com.backend.sgd.model.habilidad.Habilidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabilidadRepository extends JpaRepository <Habilidad, Long> {
    Habilidad findByNombre(String nombre);
}
