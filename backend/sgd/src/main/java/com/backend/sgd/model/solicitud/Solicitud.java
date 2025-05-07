package com.backend.sgd.model.solicitud;

import com.backend.sgd.model.estado.Estado;
import com.backend.sgd.model.estado.EstadoConverter;
import com.backend.sgd.model.evaluacion.Evaluacion;
import com.backend.sgd.model.habilidad.Habilidad;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "solicitud")
public class Solicitud {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String evento;

    @Column
    private String RGS;

    @Column
    private String perfil;

    @Column
    private String EVC;

    @ManyToMany
    @JoinTable(
            name = "solicitud_habilidad",
            joinColumns = @JoinColumn(name = "solicitud_id"),
            inverseJoinColumns = @JoinColumn(name = "habilidad_id")
    )
    private Set<Habilidad> habilidades = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "solicitud_evaluacion",
            joinColumns = @JoinColumn(name = "solicitud_id"),
            inverseJoinColumns = @JoinColumn(name = "evaluacion_id")
    )
    private Set<Evaluacion> evaluaciones = new HashSet<>();

    @Column
    private boolean medellin;

    @Column
    private LocalDate fechaSolicitud;

    @Column
    private LocalDate fechaCierre;

    @Convert(converter = TipoSolicitudConverter.class)
    @Column
    private TipoSolicitud tipoSolicitud;

    @Convert(converter = EstadoConverter.class)
    @Column
    private Estado estado;

    @Column
    private String observaciones;

    @Column
    private LocalDate fechaPostulado;

    @Column
    private LocalDate fechaResultado;

    @Column
    private LocalDate fechaPropuesta;

    @Column
    private LocalDate fechaReal;

    // Constructores
    public Solicitud() {
    }

    public Solicitud(String evento, String RGS, String perfil, String EVC, Set<Habilidad> habilidades, Set<Evaluacion> evaluaciones, boolean medellin, LocalDate fechaSolicitud, LocalDate fechaCierre, TipoSolicitud tipoSolicitud, Estado estado) {
        this.evento = evento;
        this.RGS = RGS;
        this.perfil = perfil;
        this.EVC = EVC;
        this.habilidades = habilidades;
        this.evaluaciones = evaluaciones;
        this.medellin = medellin;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaCierre = fechaCierre;
        this.tipoSolicitud = tipoSolicitud;
        this.estado = estado;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getRGS() {
        return RGS;
    }

    public void setRGS(String RGS) {
        this.RGS = RGS;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getEVC() {
        return EVC;
    }

    public void setEVC(String EVC) {
        this.EVC = EVC;
    }

    public Set<Habilidad> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(Set<Habilidad> habilidades) {
        this.habilidades = habilidades;
    }

    public Set<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(Set<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public boolean isMedellin() {
        return medellin;
    }

    public void setMedellin(boolean medellin) {
        this.medellin = medellin;
    }

    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDate fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public LocalDate getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDate fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public TipoSolicitud getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(TipoSolicitud tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public LocalDate getFechaPostulado() {
        return fechaPostulado;
    }

    public void setFechaPostulado(LocalDate fechaPostulado) {
        this.fechaPostulado = fechaPostulado;
    }

    public LocalDate getFechaResultado() {
        return fechaResultado;
    }

    public void setFechaResultado(LocalDate fechaResultado) {
        this.fechaResultado = fechaResultado;
    }

    public LocalDate getFechaPropuesta() {
        return fechaPropuesta;
    }

    public void setFechaPropuesta(LocalDate fechaPropuesta) {
        this.fechaPropuesta = fechaPropuesta;
    }

    public LocalDate getFechaReal() {
        return fechaReal;
    }

    public void setFechaReal(LocalDate fechaReal) {
        this.fechaReal = fechaReal;
    }
}
