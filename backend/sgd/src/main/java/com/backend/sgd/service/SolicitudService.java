package com.backend.sgd.service;

import com.backend.sgd.dto.SolicitudEstado;
import com.backend.sgd.model.solicitud.Solicitud;
import com.backend.sgd.repository.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudService {
    @Autowired
    private SolicitudRepository solicitudRepository;

    public Solicitud guardarSolicitud(Solicitud solicitud) {
        return solicitudRepository.save(solicitud);
    }

    public Solicitud actualizarSolicitud(Long id, Solicitud solicitudActualizada) {
        Optional<Solicitud> solicitudExistente = solicitudRepository.findById(id);
        if (solicitudExistente.isPresent()) {
            Solicitud solicitud = solicitudExistente.get();
            solicitud.setRGS(solicitudActualizada.getRGS());
            solicitud.setEstado(solicitudActualizada.getEstado());
            solicitud.setFechaCierre(solicitudActualizada.getFechaCierre());
            solicitud.setObservaciones(solicitudActualizada.getObservaciones());
            solicitud.setFechaPostulado(solicitudActualizada.getFechaPostulado());
            solicitud.setFechaResultado(solicitudActualizada.getFechaResultado());
            solicitud.setFechaPropuesta(solicitudActualizada.getFechaPropuesta());
            solicitud.setFechaReal(solicitudActualizada.getFechaReal());

            return solicitudRepository.save(solicitud);
        } else {
            throw new RuntimeException("Solicitud no encontrada con id: " + id);
        }
    }
    public List<SolicitudEstado> getSolicitudesPorEstado(int mes, int anio) {
        return solicitudRepository.findSolicitudesPorEstado(mes, anio);
    }
}
