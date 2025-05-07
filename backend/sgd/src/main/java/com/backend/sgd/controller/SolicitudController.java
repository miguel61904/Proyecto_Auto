package com.backend.sgd.controller;

import com.backend.sgd.dto.SolicitudEstado;
import com.backend.sgd.model.estado.Estado;
import com.backend.sgd.model.evaluacion.Evaluacion;
import com.backend.sgd.model.habilidad.Habilidad;
import com.backend.sgd.model.solicitud.Solicitud;
import com.backend.sgd.repository.EvaluacionRepository;
import com.backend.sgd.repository.HabilidadRepository;
import com.backend.sgd.repository.SolicitudRepository;
import com.backend.sgd.service.SolicitudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private SolicitudService solicitudService;

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @Autowired
    private HabilidadRepository habilidadRepository;

    @Schema
    @PostMapping("solicitudes")
    @Operation(summary = "Ingresar solicitud", description = "Revisar las solicitudes y el estado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion ejecutada con exito"),
            @ApiResponse(responseCode = "400", description = "No se puede ejecutar la operacion")
    })
    @Parameters({
            @Parameter(name = "Evento", in = ParameterIn.QUERY, description = "Es el numero de la soicutud que viene en correo"),
            @Parameter(name = "RGS", in = ParameterIn.QUERY, description = "Numero interno asignado"),
            @Parameter(name = "Perfil", in = ParameterIn.QUERY, description = "Perfil solicitado en el requerimiento"),
            @Parameter(name = "EVC", in = ParameterIn.QUERY, description = "Área en la que se solicita el servicio"),
            @Parameter(name = "Hábilidades", in = ParameterIn.QUERY, description = "Habilidades requeridas por el prestador de servicios"),
            @Parameter(name = "Evaluación", in = ParameterIn.QUERY, description = "Lista de evaluaciones en Evalart", array = @ArraySchema(schema = @Schema(type = "string"))),
            @Parameter(name = "Medelliín", in = ParameterIn.QUERY, description = "Indica si el prestador de servicio debe esta ubicado en la ciudad de Medellín, el valor es un boolean"),
            @Parameter(name = "Fecha Solicitud", in = ParameterIn.QUERY, description = "Fecha en la que se recibe la solicitud"),
            @Parameter(name = "Fecha Cierre", in = ParameterIn.QUERY, description = "Segun la fecha de solicitud hay un limite para presentar al candidato"),
            @Parameter(name = "Tipo Solicitud", in = ParameterIn.QUERY, description = "Seleccionar el tipo de solicitud de la lista"),
            @Parameter(name = "Estado", in = ParameterIn.QUERY, description = "Seleccionar el estado de la lista"),
            @Parameter(name = "Observaciones", in = ParameterIn.QUERY, description = "Detalles en información adicional"),
            @Parameter(name = "Fecha Postulado", in = ParameterIn.QUERY, description = "Fecha postulado"),
            @Parameter(name = "Fecha Resultado", in = ParameterIn.QUERY, description = "Fecha resultado"),
            @Parameter(name = "Fecha Propuesta", in = ParameterIn.QUERY, description = "Fecha propuesta"),
            @Parameter(name = "Fecha Real", in = ParameterIn.QUERY, description = "Fecha real"),
    })

    @ResponseBody
    public ResponseEntity<Solicitud> crearSolicitud(@RequestBody Solicitud solicitud) {
        Solicitud nuevaSolicitud = solicitudRepository.save(solicitud);
        return ResponseEntity.ok(nuevaSolicitud);
    }

    @GetMapping("solicitudes")
    public ResponseEntity<List<Solicitud>> cargarTodas() {
        List<Solicitud> solicitudes = solicitudRepository.findAll();
        return ResponseEntity.ok(solicitudes);
    }

    @GetMapping("{id}")
    public ResponseEntity<Solicitud> obtenerSolicitudPorId(@PathVariable Long id) {
        Optional<Solicitud> solicitud = solicitudRepository.findById(id);
        return solicitud.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("estados")
    public List<String> obtenerEstados() {
        return Arrays.stream(Estado.values()).map(Estado::getValor).collect(Collectors.toList());
    }

    @GetMapping("evaluaciones")
    public ResponseEntity<List<Evaluacion>> obtenerEvaluaciones() {
        List<Evaluacion> evaluaciones = evaluacionRepository.findAll();
        return ResponseEntity.ok(evaluaciones);
    }

    @GetMapping("habilidades")
    public ResponseEntity<List<Habilidad>> obtenerHabilidades() {
        List<Habilidad> habilidades = habilidadRepository.findAll();
        return ResponseEntity.ok(habilidades);
    }

    @PutMapping("{id}")
    public ResponseEntity<Solicitud> actualizarSolicitud(@PathVariable Long id, @RequestBody Solicitud solicitud) {
        try {
            Solicitud solicitudActualizada = solicitudService.actualizarSolicitud(id, solicitud);
            return ResponseEntity.ok(solicitudActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("solicitudes-estado")
    public ResponseEntity<List<SolicitudEstado>> obtenerEstadisticas(
            @RequestParam int mes,
            @RequestParam int anio) {
        List<SolicitudEstado> estadisticas = solicitudService.getSolicitudesPorEstado(mes, anio);
        return ResponseEntity.ok(estadisticas);
    }
}
