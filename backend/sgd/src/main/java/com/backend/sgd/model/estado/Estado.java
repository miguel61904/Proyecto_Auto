package com.backend.sgd.model.estado;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Estado {
    APLICADO("Aplicado"),
    NO_APLICADO("No Aplicado"),
    APLICADO_SELECCIONADO("Aplicado Seleccionado"),
    APLICADO_PRESELECCIONADO("Aplicado Preseleccionado"),
    APLICADO_NO_SELECCIONADO("Aplicado No Seleccionado"),
    APLICADO_DESISTIDO("Aplicado Desistido"),
    EVALUACION("Evaluacion"),
    ABIERTO("Abierto"),
    ACEPTADO("Aceptado"),
    CANCELADO("Cancelado");

    public final String valor;

    Estado(String valor) {
        this.valor = valor;
    }

    @JsonValue
    public String getValor() {
        return this.valor;
    }

    public static Estado fromString(String valor) {
        for (Estado estado : Estado.values()) {
            if (estado.valor.equalsIgnoreCase(valor)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("No hay estados");
    }

}
