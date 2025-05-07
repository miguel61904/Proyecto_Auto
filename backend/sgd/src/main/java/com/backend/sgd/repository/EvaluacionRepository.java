package com.backend.sgd.repository;

import com.backend.sgd.model.evaluacion.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluacionRepository extends JpaRepository <Evaluacion, Long> {
    Evaluacion findByNombre(String nombre);
}
