package com.backend.sgd.service;

import com.backend.sgd.model.estado.Estado;
import com.backend.sgd.model.evaluacion.Evaluacion;
import com.backend.sgd.model.habilidad.Habilidad;
import com.backend.sgd.model.solicitud.Solicitud;
import com.backend.sgd.model.solicitud.TipoSolicitud;
import com.backend.sgd.repository.EvaluacionRepository;
import com.backend.sgd.repository.HabilidadRepository;
import com.backend.sgd.repository.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class CsvService {
    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @Autowired
    private HabilidadRepository habilidadRepository;

    public void importarCsv(MultipartFile file) throws Exception {
        List<Solicitud> solicitudes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                String[] datos = line.split(",");
                if (datos.length <= 10) {
                    System.err.println("Fila con formato incorrecto: " + line);
                    continue;
                }

                try {
                    Solicitud solicitud = new Solicitud();

                    solicitud.setEvento(datos[0].trim().isEmpty() ? "Sin evento" : datos[0].trim());

                    solicitud.setRGS(datos[1].trim().isEmpty() ? "Sin RGS" : datos[1].trim());

                    solicitud.setPerfil(datos[2].trim().isEmpty() ? "Sin perfil" : datos[2].trim());

                    solicitud.setEVC(datos[3].trim().isEmpty() ? "Sin EVC" : datos[3].trim());

                    if (!datos[4].trim().isEmpty()) {
                        String[] habilidades = datos[4].split(";");
                        Set<Habilidad> habilidadesSet = new HashSet<>();
                        for (String habilidad : habilidades) {
                            Habilidad habilidadDetalle = new Habilidad();
                            habilidadDetalle.setNombre(habilidad.trim());
                            habilidadesSet.add(habilidadDetalle);
                        }
                        solicitud.setHabilidades(habilidadesSet);
                        habilidadRepository.saveAll(habilidadesSet);
                    }

                    if (!datos[5].trim().isEmpty()) {
                        String[] evaluaciones = datos[5].split(";");
                        Set<Evaluacion> evaluacionesSet = new HashSet<>();
                        for (String evaluacion : evaluaciones) {
                            Evaluacion evaluacionDetalle = new Evaluacion();
                            evaluacionDetalle.setNombre(evaluacion.trim());
                            evaluacionesSet.add(evaluacionDetalle);
                        }
                        solicitud.setEvaluaciones(evaluacionesSet);
                        evaluacionRepository.saveAll(evaluacionesSet);
                    }

                    String medellinColumn = datos[6].trim();
                    boolean esDeMedellin = "true".equalsIgnoreCase(medellinColumn) || "1".equals(medellinColumn);
                    solicitud.setMedellin(esDeMedellin);

                    String fechaSolicitudColumn = datos[7].trim();
                    if (!fechaSolicitudColumn.isEmpty()) {
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy");
                            LocalDate fechaSolicitud = LocalDate.parse(fechaSolicitudColumn, formatter);
                            solicitud.setFechaSolicitud(fechaSolicitud);
                        } catch (DateTimeParseException e) {
                            System.err.println("Formato de fecha inválido: " + fechaSolicitudColumn);
                            solicitud.setFechaSolicitud(null); // Dejar nulo si no es válida
                        }
                    }

                    if (!datos[8].trim().isEmpty()) {
                        try {
                            solicitud.setTipoSolicitud(TipoSolicitud.valueOf(datos[8].trim()));
                        } catch (IllegalArgumentException e) {
                            System.err.println("TipoSolicitud no válido: " + datos[8].trim());
                        }
                    }

                    if (!datos[9].trim().isEmpty()) {
                        try {
                            solicitud.setEstado(Estado.valueOf(datos[9].trim()));
                        } catch (IllegalArgumentException e) {
                            System.err.println("Estado no válido: " + datos[9].trim());
                        }
                    }

                    solicitud.setObservaciones(datos[10].trim().isEmpty() ? "Sin observaciones" : datos[10].trim());

                    solicitudes.add(solicitud);
                } catch (Exception e) {
                    System.err.println("Error al procesar fila: " + line);
                    System.err.println("Detalle del error: " + e.getMessage());
                }
            }
        }
        solicitudRepository.saveAll(solicitudes);
    }
}
