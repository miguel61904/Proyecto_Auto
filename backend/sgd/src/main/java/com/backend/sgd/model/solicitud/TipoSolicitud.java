package com.backend.sgd.model.solicitud;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoSolicitud {
    NUEVA("Nueva"),
    REEMPLAZO("Reemplazo"),
    REASIGNACION("Reasignación");

    private final String valor;

    TipoSolicitud(String valor){
        this.valor = valor;
    }

    @JsonValue
    public String getValor() {
        return valor;
    }

    public static TipoSolicitud fromValor(String valor) {
        for(TipoSolicitud tipoSolicitud : TipoSolicitud.values()) {
            if (tipoSolicitud.getValor().equals(valor)) {
                return tipoSolicitud;
            }
        }
        throw new IllegalArgumentException("");
    }
}




