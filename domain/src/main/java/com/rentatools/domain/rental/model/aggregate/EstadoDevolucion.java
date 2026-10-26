package com.rentatools.domain.rental.model.aggregate;

/**
 * Estados del ciclo de devolución de una herramienta alquilada.
 */
public enum EstadoDevolucion {
    PENDIENTE_DEVOLUCION,
    DEVUELTO_OK,
    DEVUELTO_CON_DAÑOS,
    DEVUELTO_CON_DANOS;

    public static EstadoDevolucion fromString(String val) {
        if (val == null) {
            return PENDIENTE_DEVOLUCION;
        }
        String clean = val.trim().toUpperCase();
        if (clean.equals("OK") || clean.equals("DEVUELTO_OK")) {
            return DEVUELTO_OK;
        }
        if (clean.contains("DAÑO") || clean.contains("DANO") || clean.contains("DAMAGE")) {
            return DEVUELTO_CON_DAÑOS;
        }
        try {
            return EstadoDevolucion.valueOf(clean);
        } catch (IllegalArgumentException e) {
            return PENDIENTE_DEVOLUCION;
        }
    }
}
