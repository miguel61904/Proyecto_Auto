package com.backend.sgd.init;

import com.backend.sgd.model.*;
import com.backend.sgd.model.estado.Estado;
import com.backend.sgd.model.evaluacion.Evaluacion;
import com.backend.sgd.model.habilidad.Habilidad;
import com.backend.sgd.model.solicitud.Solicitud;
import com.backend.sgd.model.solicitud.TipoSolicitud;
import com.backend.sgd.repository.EvaluacionRepository;
import com.backend.sgd.repository.HabilidadRepository;
import com.backend.sgd.repository.SolicitudRepository;
import com.backend.sgd.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @Autowired
    private HabilidadRepository habilidadRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        inicializarUsuarios();
//        inicializarSolicitudes();
    }

    private void inicializarUsuarios() {
        if (usuarioRepository.count() == 0) {
            String hashedPassword = passwordEncoder.encode("12345");
            Usuario usuario = new Usuario("usuario@ejemplo.com", hashedPassword, "Usuario", "Principal", "ADMIN");
            usuarioRepository.save(usuario);
        }
    }}

//    private void inicializarSolicitudes() {
//        if (solicitudRepository.count() == 0) {
//            Evaluacion evaluacion1 = obtenerEvaluacion("Evaluación de habilidades en Java");
//            Evaluacion evaluacion2 = obtenerEvaluacion("Evaluación de conocimientos en bases de datos");
//            Evaluacion evaluacion3 = obtenerEvaluacion("Prueba de algoritmos y estructuras de datos");
//            Evaluacion evaluacion4 = obtenerEvaluacion("Evaluación de habilidades en desarrollo frontend");
//            Evaluacion evaluacion5 = obtenerEvaluacion("Evaluación de diseño de APIs RESTful");
//            Evaluacion evaluacion6 = obtenerEvaluacion("Prueba de buenas prácticas de programación");
//            Evaluacion evaluacion7 = obtenerEvaluacion("Evaluación de trabajo con frameworks JavaScript (Angular, React)");
//            Evaluacion evaluacion8 = obtenerEvaluacion("Evaluación de pruebas unitarias con JUnit");
//            Evaluacion evaluacion9 = obtenerEvaluacion("Evaluación de integración continua y despliegue (CI/CD)");
//            Evaluacion evaluacion10 = obtenerEvaluacion("Evaluación de patrones de diseño");
//            Evaluacion evaluacion11 = obtenerEvaluacion("Evaluación de habilidades en Python");
//            Evaluacion evaluacion12 = obtenerEvaluacion("Evaluación de seguridad en aplicaciones web");
//            Evaluacion evaluacion13 = obtenerEvaluacion("Evaluación de desempeño en resolución de problemas");
//            Evaluacion evaluacion14 = obtenerEvaluacion("Evaluación de uso de herramientas de monitoreo");
//            Evaluacion evaluacion15 = obtenerEvaluacion("Evaluación de comunicación técnica en equipo");
//            Evaluacion evaluacion16 = obtenerEvaluacion("Evaluación de conocimientos en contenedores Docker");
//            Evaluacion evaluacion17 = obtenerEvaluacion("Evaluación de conocimientos en metodologías ágiles (Scrum)");
//
//            Habilidad habilidad1 = obtenerHabilidad("Java");
//            Habilidad habilidad2 = obtenerHabilidad("JavaScript");
//            Habilidad habilidad3 = obtenerHabilidad("Angular");
//            Habilidad habilidad4 = obtenerHabilidad("Manejo de APIs REST");
//            Habilidad habilidad5 = obtenerHabilidad("Pruebas unitarias con JUnit");
//            Habilidad habilidad6 = obtenerHabilidad("Control de versiones con Git");
//            Habilidad habilidad7 = obtenerHabilidad("Metodologías ágiles (Scrum)");
//            Habilidad habilidad8 = obtenerHabilidad("Integración continua y despliegue (CI/CD)");
//            Habilidad habilidad9 = obtenerHabilidad("Python");
//            Habilidad habilidad10 = obtenerHabilidad("Optimización de rendimiento");
//            Habilidad habilidad11 = obtenerHabilidad("Seguridad en aplicaciones web");
//            Habilidad habilidad12 = obtenerHabilidad("Docker");
//            Habilidad habilidad13 = obtenerHabilidad("Selenium");
//            Habilidad habilidad14 = obtenerHabilidad("Resolución de problemas");
//            Habilidad habilidad15 = obtenerHabilidad("Conocimientos de patrones de diseño");
//            Habilidad habilidad16 = obtenerHabilidad("Uso de herramientas de monitoreo");
//            Habilidad habilidad17 = obtenerHabilidad("Comunicación efectiva en equipo");
//            Habilidad habilidad18 = obtenerHabilidad("Documentación técnica");
//
//            Solicitud solicitud = new Solicitud(
//                    "3324",
//                    "9728341",
//                    "ANÁLISIS DE PERFORMANCE SEMI-SENIOR",
//                    "Depositos y cuentas",
//                    Set.of(habilidad1, habilidad2),
//                    Set.of(evaluacion1, evaluacion2),
//                    false,
//                    LocalDate.of(2024, 10, 18),
//                    LocalDate.of(2024, 10, 18),
//                    TipoSolicitud.REEMPLAZO,
//                    Estado.ACEPTADO
//            );
//
//            Solicitud solicitud1 = new Solicitud(
//                    "3324",
//                    "9728341",
//                    "ANÁLISIS DE PERFORMANCE SEMI-SENIOR",
//                    "Depositos y cuentas",
//                    Set.of(habilidad1, habilidad7),
//                    Set.of(evaluacion3, evaluacion11),
//                    false,
//                    LocalDate.of(2024, 10, 18),
//                    LocalDate.of(2024, 10, 18),
//                    TipoSolicitud.REEMPLAZO,
//                    Estado.ACEPTADO
//            );
//
//            Solicitud solicitud2 = new Solicitud(
//                    "3325",
//                    "9728342",
//                    "DESARROLLADOR FRONTEND JUNIOR",
//                    "Desarrollo web",
//                    Set.of(habilidad2, habilidad8),
//                    Set.of(evaluacion4, evaluacion12),
//                    true,
//                    LocalDate.of(2024, 10, 19),
//                    LocalDate.of(2024, 10, 19),
//                    TipoSolicitud.REEMPLAZO,
//                    Estado.ACEPTADO
//            );
//
//            Solicitud solicitud3 = new Solicitud(
//                    "3326",
//                    "9728343",
//                    "DESARROLLADOR FULL-STACK",
//                    "Sistemas integrados",
//                    Set.of(habilidad13, habilidad6),
//                    Set.of(evaluacion5, evaluacion13),
//                    false,
//                    LocalDate.of(2024, 10, 20),
//                    LocalDate.of(2024, 10, 20),
//                    TipoSolicitud.REEMPLAZO,
//                    Estado.ACEPTADO
//            );
//
//            Solicitud solicitud4 = new Solicitud(
//                    "3327",
//                    "9728344",
//                    "INGENIERO DE BASES DE DATOS",
//                    "Optimización de consultas",
//                    Set.of(habilidad14, habilidad10),
//                    Set.of(evaluacion6, evaluacion14),
//                    true,
//                    LocalDate.of(2024, 10, 21),
//                    LocalDate.of(2024, 10, 21),
//                    TipoSolicitud.REEMPLAZO,
//                    Estado.ABIERTO
//            );
//
//            Solicitud solicitud5 = new Solicitud(
//                    "3328",
//                    "9728345",
//                    "DESARROLLADOR DE SOFTWARE MOBILE",
//                    "Desarrollo de apps",
//                    Set.of(habilidad15, habilidad11),
//                    Set.of(evaluacion7, evaluacion15),
//                    false,
//                    LocalDate.of(2024, 10, 22),
//                    LocalDate.of(2024, 10, 22),
//                    TipoSolicitud.REEMPLAZO,
//                    Estado.ACEPTADO
//            );
//
//            Solicitud solicitud6 = new Solicitud(
//                    "3329",
//                    "9728346",
//                    "ARQUITECTO DE SOLUCIONES",
//                    "Diseño de arquitecturas",
//                    Set.of(habilidad16, habilidad12),
//                    Set.of(evaluacion8, evaluacion16),
//                    true,
//                    LocalDate.of(2024, 10, 23),
//                    LocalDate.of(2024, 10, 23),
//                    TipoSolicitud.NUEVA,
//                    Estado.APLICADO_PRESELECCIONADO
//            );
//
//            Solicitud solicitud7 = new Solicitud(
//                    "3330",
//                    "9728347",
//                    "DESARROLLADOR BACKEND",
//                    "Desarrollo API",
//                    Set.of(habilidad1, habilidad9),
//                    Set.of(evaluacion9, evaluacion17),
//                    false,
//                    LocalDate.of(2024, 10, 24),
//                    LocalDate.of(2024, 10, 24),
//                    TipoSolicitud.REEMPLAZO,
//                    Estado.ACEPTADO
//            );
//
//            Solicitud solicitud8 = new Solicitud(
//                    "3331",
//                    "9728348",
//                    "TESTER AUTOMATIZADO",
//                    "Pruebas de software",
//                    Set.of(habilidad2, habilidad7),
//                    Set.of(evaluacion10, evaluacion13),
//                    true,
//                    LocalDate.of(2024, 10, 25),
//                    LocalDate.of(2024, 10, 25),
//                    TipoSolicitud.NUEVA,
//                    Estado.APLICADO_DESISTIDO
//            );
//
//            Solicitud solicitud9 = new Solicitud(
//                    "3332",
//                    "9728349",
//                    "DESARROLLADOR DE VIDEOJUEGOS",
//                    "Desarrollo de videojuegos",
//                    Set.of(habilidad18, habilidad8),
//                    Set.of(evaluacion1, evaluacion14),
//                    false,
//                    LocalDate.of(2024, 10, 26),
//                    LocalDate.of(2024, 10, 26),
//                    TipoSolicitud.REEMPLAZO,
//                    Estado.ACEPTADO
//            );
//
//            Solicitud solicitud10 = new Solicitud(
//                    "3333",
//                    "9728350",
//                    "DESARROLLADOR DE SOFTWARE INGENIERÍA",
//                    "Integración de sistemas",
//                    Set.of(habilidad4, habilidad9),
//                    Set.of(evaluacion2, evaluacion12),
//                    true,
//                    LocalDate.of(2024, 10, 27),
//                    LocalDate.of(2024, 10, 27),
//                    TipoSolicitud.NUEVA,
//                    Estado.CANCELADO
//            );
//
//            Solicitud solicitud11 = new Solicitud(
//                    "3334",
//                    "9728351",
//                    "DESARROLLADOR DE SOFTWARE SR",
//                    "Desarrollo de aplicaciones",
//                    Set.of(habilidad5, habilidad10),
//                    Set.of(evaluacion3, evaluacion12),
//                    false,
//                    LocalDate.of(2024, 10, 28),
//                    LocalDate.of(2024, 10, 28),
//                    TipoSolicitud.REEMPLAZO,
//                    Estado.ACEPTADO
//            );
//
//            Solicitud solicitud12 = new Solicitud(
//                    "3335",
//                    "9728352",
//                    "LÍDER DE PROYECTOS DE SOFTWARE",
//                    "Gestión de proyectos",
//                    Set.of(habilidad17, habilidad11),
//                    Set.of(evaluacion4, evaluacion14),
//                    true,
//                    LocalDate.of(2024, 10, 29),
//                    LocalDate.of(2024, 10, 29),
//                    TipoSolicitud.NUEVA,
//                    Estado.APLICADO_DESISTIDO
//            );
//
//            Solicitud solicitud13 = new Solicitud(
//                    "3336",
//                    "9728353",
//                    "INGENIERO DE SOFTWARE JUNIOR",
//                    "Soporte y mantenimiento de software",
//                    Set.of(habilidad17, habilidad12),
//                    Set.of(evaluacion5, evaluacion15),
//                    false,
//                    LocalDate.of(2024, 10, 30),
//                    LocalDate.of(2024, 10, 30),
//                    TipoSolicitud.REEMPLAZO,
//                    Estado.ACEPTADO
//            );
//
//            Solicitud solicitud14 = new Solicitud(
//                    "3337",
//                    "9728354",
//                    "DESARROLLADOR FRONTEND",
//                    "Desarrollo web y mobile",
//                    Set.of(habilidad18, habilidad1),
//                    Set.of(evaluacion6, evaluacion14),
//                    true,
//                    LocalDate.of(2024, 10, 31),
//                    LocalDate.of(2024, 10, 31),
//                    TipoSolicitud.NUEVA,
//                    Estado.APLICADO_NO_SELECCIONADO
//            );
//
//            Solicitud solicitud15 = new Solicitud(
//                    "3338",
//                    "9728355",
//                    "DESARROLLADOR BACKEND SR",
//                    "API y microservicios",
//                    Set.of(habilidad9, habilidad2),
//                    Set.of(evaluacion7, evaluacion15),
//                    false,
//                    LocalDate.of(2024, 11, 1),
//                    LocalDate.of(2024, 11, 1),
//                    TipoSolicitud.REEMPLAZO,
//                    Estado.ACEPTADO
//            );
//
//            Solicitud solicitud16 = new Solicitud(
//                    "3339",
//                    "9728356",
//                    "DESARROLLADOR FULL-STACK SR",
//                    "Desarrollo completo de aplicaciones",
//                    Set.of(habilidad10, habilidad3),
//                    Set.of(evaluacion8, evaluacion16),
//                    true,
//                    LocalDate.of(2024, 11, 2),
//                    LocalDate.of(2024, 11, 2),
//                    TipoSolicitud.NUEVA,
//                    Estado.APLICADO_DESISTIDO
//            );
//
//            Solicitud solicitud17 = new Solicitud(
//                    "3340",
//                    "9728357",
//                    "ARQUITECTO DE SOFTWARE",
//                    "Diseño e implementación de arquitecturas",
//                    Set.of(habilidad11, habilidad4),
//                    Set.of(evaluacion9, evaluacion17),
//                    false,
//                    LocalDate.of(2024, 11, 3),
//                    LocalDate.of(2024, 11, 3),
//                    TipoSolicitud.REEMPLAZO,
//                    Estado.ACEPTADO
//            );
//
//            Solicitud solicitud18 = new Solicitud(
//                    "3341",
//                    "9728358",
//                    "ESPECIALISTA EN BASES DE DATOS",
//                    "Manejo y optimización de bases de datos",
//                    Set.of(habilidad12, habilidad5),
//                    Set.of(evaluacion10, evaluacion8),
//                    true,
//                    LocalDate.of(2024, 11, 4),
//                    LocalDate.of(2024, 11, 4),
//                    TipoSolicitud.NUEVA,
//                    Estado.CANCELADO
//            );
//
//            List<Solicitud> solicitudes = List.of(solicitud17, solicitud, solicitud13, solicitud1, solicitud2, solicitud4, solicitud5, solicitud6, solicitud7, solicitud8, solicitud3, solicitud10, solicitud9, solicitud11, solicitud12, solicitud14, solicitud15, solicitud16, solicitud18);
//
//            // Guardar la solicitud
//            solicitudRepository.saveAll(solicitudes);
//        }
//    }
//
//    private Habilidad obtenerHabilidad(String nombre) {
//        Habilidad habilidad = habilidadRepository.findByNombre(nombre);
//        if (habilidad == null) {
//            habilidad = new Habilidad(nombre);
//            habilidadRepository.save(habilidad);
//        }
//        return habilidad;
//    }
//
//    private Evaluacion obtenerEvaluacion(String nombre) {
//        Evaluacion evaluacion = evaluacionRepository.findByNombre(nombre);
//        if (evaluacion == null) {
//            evaluacion = new Evaluacion(nombre);
//            evaluacionRepository.save(evaluacion);
//        }
//        return evaluacion;
//    }

