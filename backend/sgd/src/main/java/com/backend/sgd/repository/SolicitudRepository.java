package com.backend.sgd.repository;

import com.backend.sgd.model.solicitud.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.backend.sgd.dto.SolicitudEstado;

import java.util.List;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

    @Query(value = """
        SELECT
            s.estado AS estado,
            COUNT(s.id) AS totalSolicitudes
        FROM
            Solicitud s
        WHERE
            MONTH(s.fechaSolicitud) = :mes
            AND YEAR(s.fechaSolicitud) = :anio
        GROUP BY
            s.estado
    """)
    List<SolicitudEstado> findSolicitudesPorEstado(@Param("mes") int mes, @Param("anio") int anio);
}