-- V3: Flujo de Devolución y Reporte de Daños
-- Agrega soporte para estado_devolucion en reservations y crea la tabla reporte_dano

-- 1. Modificar tabla reservations para incluir estado_devolucion y relajar constraint de status si aplica
ALTER TABLE reservations ADD COLUMN IF NOT EXISTS estado_devolucion VARCHAR(50) DEFAULT 'PENDIENTE_DEVOLUCION';
UPDATE reservations SET estado_devolucion = 'PENDIENTE_DEVOLUCION' WHERE estado_devolucion IS NULL;

-- Relajar o actualizar chk_reservation_status para admitir DEVUELTO_OK y DEVUELTO_CON_DAÑOS
ALTER TABLE reservations DROP CONSTRAINT IF EXISTS chk_reservation_status;
ALTER TABLE reservations ADD CONSTRAINT chk_reservation_status CHECK (
    status IN ('PENDING', 'CONFIRMED', 'ACTIVE', 'COMPLETED', 'DELIVERED', 'RETURNED', 'CANCELLED', 'EXPIRED', 'DEVUELTO_OK', 'DEVUELTO_CON_DAÑOS', 'DEVUELTO_CON_DANOS')
);

-- 2. Crear tabla reporte_dano
CREATE TABLE IF NOT EXISTS reporte_dano (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    reserva_id UUID NOT NULL REFERENCES reservations(id) ON DELETE CASCADE,
    descripcion TEXT,
    costo_reparacion DOUBLE PRECISION DEFAULT 0.00,
    fecha_reporte TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_reporte_dano_reserva ON reporte_dano(reserva_id);

-- Vistas para asegurar compatibilidad total con consultas estilo 'SELECT * FROM ReporteDano' o 'SELECT * FROM reportedano'
CREATE OR REPLACE VIEW "ReporteDano" AS SELECT * FROM reporte_dano;
CREATE OR REPLACE VIEW reportedano AS SELECT * FROM reporte_dano;
