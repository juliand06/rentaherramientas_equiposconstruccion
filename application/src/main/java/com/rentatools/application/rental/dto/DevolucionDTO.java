package com.rentatools.application.rental.dto;

/**
 * DTO que recibe la información del formulario de devolución.
 * Campos:
 * - estado: "OK" o "DAÑOS"
 * - descripcionDano: descripción opcional de los daños
 * - costoReparacion: costo estimado opcional de la reparación
 */
public class DevolucionDTO {

    private String estado;
    private String descripcionDano;
    private Double costoReparacion;

    public DevolucionDTO() {
    }

    public DevolucionDTO(String estado, String descripcionDano, Double costoReparacion) {
        this.estado = estado;
        this.descripcionDano = descripcionDano;
        this.costoReparacion = costoReparacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcionDano() {
        return descripcionDano;
    }

    public void setDescripcionDano(String descripcionDano) {
        this.descripcionDano = descripcionDano;
    }

    public Double getCostoReparacion() {
        return costoReparacion;
    }

    public void setCostoReparacion(Double costoReparacion) {
        this.costoReparacion = costoReparacion;
    }
}
