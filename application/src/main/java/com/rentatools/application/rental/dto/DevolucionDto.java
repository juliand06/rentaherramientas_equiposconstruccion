package com.rentatools.application.rental.dto;

/**
 * Alias de compatibilidad para DevolucionDTO.
 */
public class DevolucionDto extends DevolucionDTO {

    public DevolucionDto() {
        super();
    }

    public DevolucionDto(String estado, String descripcionDano, Double costoReparacion) {
        super(estado, descripcionDano, costoReparacion);
    }
}
