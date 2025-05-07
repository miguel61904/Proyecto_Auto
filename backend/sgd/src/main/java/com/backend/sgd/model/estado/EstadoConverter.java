package com.backend.sgd.model.estado;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class EstadoConverter implements AttributeConverter<Estado, String> {
    @Override
    public String convertToDatabaseColumn(Estado estado){
        return estado == null ? null : estado.getValor();
    }

    @Override
    public Estado convertToEntityAttribute(String valor){
        return valor == null ? null : Estado.fromString(valor);
    }
}

