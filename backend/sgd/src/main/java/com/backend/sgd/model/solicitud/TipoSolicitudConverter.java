package com.backend.sgd.model.solicitud;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

@Convert(attributeName = "")
public class TipoSolicitudConverter implements AttributeConverter<TipoSolicitud, String> {
    @Override
    public String convertToDatabaseColumn(TipoSolicitud tipoSolicitud) {
        return (tipoSolicitud != null ) ? tipoSolicitud.getValor() : null;
    }

    @Override
    public TipoSolicitud convertToEntityAttribute(String valor){
        return (valor != null) ? TipoSolicitud.fromValor(valor): null;
    }
}
