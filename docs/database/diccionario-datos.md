# Diccionario de Datos — Plataforma de Renta de Herramientas y Equipos de Construcción

- **Motor:** PostgreSQL 16
- **Total de Tablas:** 22
- **Nivel de Normalización:** Cuarta Forma Normal (4FN)
- **Bounded Contexts:** `identity`, `catalog`, `rental`, `billing`
- **Convenciones:** Nombres de tablas en plural minúscula (`snake_case`), columnas en inglés, identificadores basados en UUID v4.

---

## Índice de Tablas por Bounded Context

| Bounded Context | # | Tabla | Descripción |
|---|---|---|---|
| **Identity** | 1 | `roles` | Catálogo de roles de seguridad del sistema (`ADMIN`, `PROVIDER`, `CLIENT`). |
| | 2 | `users` | Cuentas principales de autenticación y datos de acceso. |
| | 3 | `user_roles` | Relación N:M entre usuarios y roles asignados. |
| | 4 | `client_profiles` | Perfil extendido 1:1 de clientes (scoring, documento). |
| | 5 | `provider_profiles` | Perfil extendido 1:1 de proveedores (NIT, razón social, comisión). |
| | 6 | `user_addresses` | Direcciones físicas 1:N asociadas a los usuarios (4FN). |
| | 7 | `user_phones` | Teléfonos de contacto 1:N asociados a los usuarios (4FN). |
| | 8 | `refresh_tokens` | Sesiones JWT persistidas con rotación y revocación segura. |
| **Catalog** | 9 | `categories` | Categorías taxonómicas de herramientas y maquinaria. |
| | 10 | `tools` | Ficha maestra de herramientas y equipos ofertados en alquiler. |
| | 11 | `tool_specifications` | Atributos y especificaciones técnicas 1:N por herramienta (4FN). |
| | 12 | `tool_images` | Galería multimedia y fotografías 1:N de las herramientas (4FN). |
| | 13 | `tool_inventory_units` | Unidades físicas reales con número de serie individual y estado operativo. |
| | 14 | `maintenance_records` | Bitácora de servicios preventivos, correctivos y calibraciones por unidad. |
| **Rental** | 15 | `reservations` | Órdenes de alquiler y reservas formalizadas por los clientes. |
| | 16 | `reservation_items` | Detalle de herramientas reservadas con restricción anti-solapamiento (`EXCLUDE GIST`). |
| | 17 | `deliveries` | Actas de entrega física y salida de almacén hacia el cliente. |
| | 18 | `returns` | Actas de recepción física, inspección de devolución y cálculo de penalidades. |
| | 19 | `damage_reports` | Reportes de siniestros, averías y cobros contra el depósito de garantía. |
| **Billing** | 20 | `payments` | Transacciones de pago electrónico (pasarela, PSE, tarjetas). |
| | 21 | `invoices` | Facturas comerciales y comprobantes electrónicos emitidos. |
| | 22 | `invoice_items` | Desglose de conceptos facturados por días de alquiler y cargos adicionales. |

---

## Justificación de la Cuarta Forma Normal (4FN)

El diseño relacional satisface estrictamente la **4FN**:
1. **1FN, 2FN y 3FN:** Todas las columnas son atómicas, las tablas poseen Clave Primaria definida (UUID), no existen dependencias parciales en claves compuestas ni dependencias transitivas entre columnas no clave.
2. **4FN (Eliminación de Dependencias Multivaluadas Independientes):**
   - **Usuarios:** Un usuario puede tener múltiples teléfonos y múltiples direcciones. En lugar de almacenarlos en `users` o combinarlos en una tabla única (lo cual generaría anomalías de producto cartesiano), se normalizan en tablas separadas e independientes: `user_addresses` y `user_phones`.
   - **Herramientas:** Una herramienta tiene especificaciones técnicas independientes (potencia, peso, combustible) y una galería de imágenes independiente (fotos en obra, foto frontal, foto de placa). Al separarlas en `tool_specifications` y `tool_images`, se garantiza que no haya dependencias multivaluadas en `tools`.
   - **Facturación:** Los ítems facturados y los pagos se manejan de forma desacoplada para permitir reembolsos parciales o pagos fraccionados sin redundancia.

---

## Detalle de Tablas y Columnas

### 1. `roles`
Catálogo de roles del sistema.
- **`id`** (`UUID`, PK, NOT NULL, DEFAULT `gen_random_uuid()`): Identificador único del rol.
- **`name`** (`VARCHAR(30)`, NOT NULL, UNIQUE, CHECK `IN ('ADMIN', 'PROVIDER', 'CLIENT')`): Código del rol.
- **`description`** (`VARCHAR(255)`, NOT NULL): Descripción de responsabilidades del rol.
- **`created_at`** (`TIMESTAMPTZ`, NOT NULL, DEFAULT `CURRENT_TIMESTAMP`): Fecha de creación.

### 2. `users`
Cuentas de usuario registradas en el sistema.
- **`id`** (`UUID`, PK, NOT NULL, DEFAULT `gen_random_uuid()`): Identificador único del usuario.
- **`email`** (`VARCHAR(150)`, NOT NULL, UNIQUE): Correo electrónico (login). Validado por expresión regular.
- **`password_hash`** (`VARCHAR(255)`, NOT NULL): Hash BCrypt de la contraseña.
- **`first_name`** (`VARCHAR(100)`, NOT NULL): Nombre o nombres del usuario.
- **`last_name`** (`VARCHAR(100)`, NOT NULL): Apellidos del usuario.
- **`active`** (`BOOLEAN`, NOT NULL, DEFAULT `TRUE`): Estado de la cuenta (baja lógica).
- **`created_at`** (`TIMESTAMPTZ`, NOT NULL, DEFAULT `CURRENT_TIMESTAMP`): Fecha de registro.
- **`updated_at`** (`TIMESTAMPTZ`, NOT NULL, DEFAULT `CURRENT_TIMESTAMP`): Fecha de última actualización.

### 3. `user_roles`
Tabla puente de asociación muchos a muchos entre usuarios y roles.
- **`user_id`** (`UUID`, PK, FK `users.id`, ON DELETE CASCADE): Usuario asociado.
- **`role_id`** (`UUID`, PK, FK `roles.id`, ON DELETE RESTRICT): Rol asignado.
- **`assigned_at`** (`TIMESTAMPTZ`, NOT NULL, DEFAULT `CURRENT_TIMESTAMP`): Fecha de asignación.

### 4. `client_profiles`
Perfil especializado para usuarios con rol `CLIENT`.
- **`id`** (`UUID`, PK, NOT NULL, DEFAULT `gen_random_uuid()`): Identificador del perfil.
- **`user_id`** (`UUID`, NOT NULL, UNIQUE, FK `users.id`, ON DELETE CASCADE): Usuario titular.
- **`identification_type`** (`VARCHAR(20)`, NOT NULL, CHECK `IN ('CC', 'NIT', 'CE', 'PASSPORT')`): Tipo de documento.
- **`identification_number`** (`VARCHAR(30)`, NOT NULL, UNIQUE): Número de identificación legal.
- **`legal_name`** (`VARCHAR(150)`, NULL): Razón social si es empresa o null si es persona natural.
- **`reputation_score`** (`NUMERIC(3,2)`, NOT NULL, DEFAULT `5.00`, CHECK `0.00` a `5.00`): Calificación crediticia/reputación.
- **`created_at`** (`TIMESTAMPTZ`, NOT NULL, DEFAULT `CURRENT_TIMESTAMP`): Fecha de alta.
- **`updated_at`** (`TIMESTAMPTZ`, NOT NULL, DEFAULT `CURRENT_TIMESTAMP`): Fecha de modificación.

### 5. `provider_profiles`
Perfil especializado para empresas y proveedores de maquinaria (`PROVIDER`).
- **`id`** (`UUID`, PK, NOT NULL, DEFAULT `gen_random_uuid()`): Identificador del perfil.
- **`user_id`** (`UUID`, NOT NULL, UNIQUE, FK `users.id`, ON DELETE CASCADE): Usuario titular.
- **`tax_id`** (`VARCHAR(30)`, NOT NULL, UNIQUE): NIT o registro tributario.
- **`company_name`** (`VARCHAR(150)`, NOT NULL): Nombre comercial de la empresa proveedora.
- **`commercial_registry`** (`VARCHAR(50)`, NOT NULL, UNIQUE): Matrícula mercantil o de cámara de comercio.
- **`commission_rate`** (`NUMERIC(5,2)`, NOT NULL, DEFAULT `10.00`, CHECK `0.00` a `100.00`): Porcentaje de comisión para la plataforma.
- **`rating_average`** (`NUMERIC(3,2)`, NOT NULL, DEFAULT `5.00`, CHECK `0.00` a `5.00`): Calificación media del proveedor.
- **`created_at`** (`TIMESTAMPTZ`, NOT NULL, DEFAULT `CURRENT_TIMESTAMP`): Fecha de creación.
- **`updated_at`** (`TIMESTAMPTZ`, NOT NULL, DEFAULT `CURRENT_TIMESTAMP`): Fecha de actualización.

### 6. `user_addresses`
Direcciones físicas normalizadas en 4FN asociadas a usuarios.
- **`id`** (`UUID`, PK, NOT NULL, DEFAULT `gen_random_uuid()`): Identificador de la dirección.
- **`user_id`** (`UUID`, NOT NULL, FK `users.id`, ON DELETE CASCADE): Usuario al que pertenece.
- **`department`** (`VARCHAR(100)`, NOT NULL): Departamento o estado.
- **`city`** (`VARCHAR(100)`, NOT NULL): Ciudad o municipio.
- **`address_line`** (`VARCHAR(255)`, NOT NULL): Nomenclatura vial y detalles.
- **`postal_code`** (`VARCHAR(20)`, NULL): Código postal.
- **`is_primary`** (`BOOLEAN`, NOT NULL, DEFAULT `FALSE`): Marca de dirección principal.
- **`created_at`** (`TIMESTAMPTZ`, NOT NULL, DEFAULT `CURRENT_TIMESTAMP`): Fecha de registro.

### 7. `user_phones`
Números de contacto normalizados en 4FN asociados a usuarios.
- **`id`** (`UUID`, PK, NOT NULL, DEFAULT `gen_random_uuid()`): Identificador del teléfono.
- **`user_id`** (`UUID`, NOT NULL, FK `users.id`, ON DELETE CASCADE): Usuario propietario.
- **`country_code`** (`VARCHAR(5)`, NOT NULL, DEFAULT `'+57'`): Indicativo internacional.
- **`phone_number`** (`VARCHAR(20)`, NOT NULL): Número telefónico.
- **`phone_type`** (`VARCHAR(20)`, NOT NULL, DEFAULT `'MOBILE'`, CHECK `IN ('MOBILE', 'LANDLINE', 'WHATSAPP')`): Tipo de línea.
- **`is_primary`** (`BOOLEAN`, NOT NULL, DEFAULT `FALSE`): Indica si es el contacto preferente.
- **`created_at`** (`TIMESTAMPTZ`, NOT NULL, DEFAULT `CURRENT_TIMESTAMP`): Fecha de alta.
- **Restricción:** `UNIQUE (user_id, phone_number)`.

### 8. `refresh_tokens`
Gestión de persistencia de tokens de refresco para Spring Security y JWT.
- **`id`** (`UUID`, PK, NOT NULL, DEFAULT `gen_random_uuid()`): Identificador de la sesión.
- **`user_id`** (`UUID`, NOT NULL, FK `users.id`, ON DELETE CASCADE): Usuario autenticado.
- **`token_hash`** (`VARCHAR(255)`, NOT NULL, UNIQUE): Hash seguro del token emitido.
- **`expires_at`** (`TIMESTAMPTZ`, NOT NULL): Fecha/hora exacta de caducidad.
- **`revoked`** (`BOOLEAN`, NOT NULL, DEFAULT `FALSE`): Estado de revocación (rotación o logout).
- **`revoked_at`** (`TIMESTAMPTZ`, NULL): Momento de revocación.
- **`created_at`** (`TIMESTAMPTZ`, NOT NULL, DEFAULT `CURRENT_TIMESTAMP`): Fecha de emisión.

### 9. `categories`
Clasificación de herramientas y maquinaria.
- **`id`** (`UUID`, PK, NOT NULL, DEFAULT `gen_random_uuid()`): Identificador de la categoría.
- **`name`** (`VARCHAR(80)`, NOT NULL, UNIQUE): Nombre descriptivo.
- **`description`** (`TEXT`, NULL): Detalle del tipo de maquinaria comprendida.
- **`active`** (`BOOLEAN`, NOT NULL, DEFAULT `TRUE`): Estado de visibilidad.
- **`created_at`** (`TIMESTAMPTZ`, NOT NULL, DEFAULT `CURRENT_TIMESTAMP`): Fecha de creación.

### 10. `tools`
Catálogo maestro de modelos de herramientas disponibles para renta.
- **`id`** (`UUID`, PK, NOT NULL, DEFAULT `gen_random_uuid()`): Identificador de la herramienta.
- **`provider_id`** (`UUID`, NOT NULL, FK `provider_profiles.id`, ON DELETE RESTRICT): Proveedor propietario.
- **`category_id`** (`UUID`, NOT NULL, FK `categories.id`, ON DELETE RESTRICT): Categoría asociada.
- **`code`** (`VARCHAR(50)`, NOT NULL, UNIQUE): Código SKU o referencia interna.
- **`name`** (`VARCHAR(120)`, NOT NULL): Nombre comercial de la herramienta.
- **`description`** (`TEXT`, NOT NULL): Descripción de uso y capacidad.
- **`brand`** (`VARCHAR(60)`, NOT NULL): Marca fabricante (Bosch, DeWalt, Caterpillar, Layher, etc.).
- **`model`** (`VARCHAR(60)`, NOT NULL): Modelo específico.
- **`daily_rental_rate`** (`NUMERIC(12,2)`, NOT NULL, CHECK `> 0`): Tarifa de alquiler por día calendario.
- **`security_deposit_amount`** (`NUMERIC(12,2)`, NOT NULL, DEFAULT `0.00`, CHECK `>= 0`): Depósito de garantía exigido.
- **`replacement_value`** (`NUMERIC(12,2)`, NOT NULL, CHECK `> 0`): Valor comercial de reposición en caso de pérdida total.
- **`active`** (`BOOLEAN`, NOT NULL, DEFAULT `TRUE`): Habilitada para reservas públicas.
- **`created_at`** (`TIMESTAMPTZ`, NOT NULL, DEFAULT `CURRENT_TIMESTAMP`): Fecha de registro.
- **`updated_at`** (`TIMESTAMPTZ`, NOT NULL, DEFAULT `CURRENT_TIMESTAMP`): Fecha de última edición.

### 11. `tool_specifications`
Especificaciones técnicas estructuradas 1:N por herramienta (4FN).
- **`id`** (`UUID`, PK, NOT NULL, DEFAULT `gen_random_uuid()`): Identificador de la especificación.
- **`tool_id`** (`UUID`, NOT NULL, FK `tools.id`, ON DELETE CASCADE): Herramienta asociada.
- **`spec_key`** (`VARCHAR(50)`, NOT NULL): Clave o parámetro técnico (`POTENCIA`, `PESO`, `VOLTAJE`, etc.).
- **`spec_value`** (`VARCHAR(100)`, NOT NULL): Valor y unidad de medida (`1750W`, `15 Kg`, `220V`).
- **`created_at`** (`TIMESTAMPTZ`, NOT NULL, DEFAULT `CURRENT_TIMESTAMP`): Fecha de creación.
- **Restricción:** `UNIQUE (tool_id, spec_key)`.

### 12. `tool_images`
Galería fotográfica y multimedia 1:N por herramienta (4FN).
- **`id`** (`UUID`, PK, NOT NULL, DEFAULT `gen_random_uuid()`): Identificador de la imagen.
- **`tool_id`** (`UUID`, NOT NULL, FK `tools.id`, ON DELETE CASCADE): Herramienta asociada.
- **`image_url`** (`VARCHAR(500)`, NOT NULL): Ruta local o URL remota de la imagen.
- **`is_cover`** (`BOOLEAN`, NOT NULL, DEFAULT `FALSE`): Marca si es la fotografía de portada.
- **`display_order`** (`INT`, NOT NULL, DEFAULT `0`): Posición en el carrusel de presentación.
- **`created_at`** (`TIMESTAMPTZ`, NOT NULL, DEFAULT `CURRENT_TIMESTAMP`): Fecha de carga.

### 13. `tool_inventory_units`
Unidades físicas reales correspondientes a un modelo de herramienta.
- **`id`** (`UUID`, PK, NOT NULL, DEFAULT `gen_random_uuid()`): Identificador de la unidad física.
- **`tool_id`** (`UUID`, NOT NULL, FK `tools.id`, ON DELETE RESTRICT): Modelo al que pertenece.
- **`serial_number`** (`VARCHAR(80)`, NOT NULL, UNIQUE): Número de serie o placa de activo fijo.
- **`status`** (`VARCHAR(25)`, NOT NULL, DEFAULT `'AVAILABLE'`, CHECK `IN ('AVAILABLE', 'RESERVED', 'RENTED', 'IN_MAINTENANCE', 'DAMAGED', 'RETIRED')`): Estado de disponibilidad física.
- **`physical_condition`** (`VARCHAR(30)`, NOT NULL, DEFAULT `'EXCELLENT'`, CHECK `IN ('EXCELLENT', 'GOOD', 'FAIR', 'DAMAGED')`): Condición de operatividad.
- **`operating_hours`** (`NUMERIC(8,2)`, NOT NULL, DEFAULT `0.00`, CHECK `>= 0`): Horómetro acumulado de uso.
- **`acquired_at`** (`DATE`, NOT NULL): Fecha de compra o incorporación al inventario.
- **`created_at`** (`TIMESTAMPTZ`, NOT NULL, DEFAULT `CURRENT_TIMESTAMP`): Fecha de alta.
- **`updated_at`** (`TIMESTAMPTZ`, NOT NULL, DEFAULT `CURRENT_TIMESTAMP`): Fecha de modificación.

### 14. `maintenance_records`
Historial de mantenimientos preventivos, correctivos y calibraciones por unidad física.
- **`id`** (`UUID`, PK, NOT NULL, DEFAULT `gen_random_uuid()`): Identificador del registro.
- **`inventory_unit_id`** (`UUID`, NOT NULL, FK `tool_inventory_units.id`, ON DELETE RESTRICT): Unidad intervenida.
- **`maintenance_type`** (`VARCHAR(20)`, NOT NULL, CHECK `IN ('PREVENTIVE', 'CORRECTIVE', 'CALIBRATION')`): Tipo de servicio.
- **`cost`** (`NUMERIC(12,2)`, NOT NULL, DEFAULT `0.00`, CHECK `>= 0`): Costo total de mano de obra y repuestos.
- **`performed_by`** (`VARCHAR(120)`, NOT NULL): Técnico o taller responsable.
- **`description`** (`TEXT`, NOT NULL): Diagnóstico y trabajo ejecutado.
- **`performed_at`** (`TIMESTAMPTZ`, NOT NULL): Fecha/hora de ejecución del mantenimiento.
- **`created_at`** (`TIMESTAMPTZ`, NOT NULL, DEFAULT `CURRENT_TIMESTAMP`): Fecha de registro en sistema.

### 15. `reservations`
Contratos y solicitudes de reserva formalizadas por los clientes.
- **`id`** (`UUID`, PK, NOT NULL, DEFAULT `gen_random_uuid()`): Identificador de la reserva.
- **`client_id`** (`UUID`, NOT NULL, FK `client_profiles.id`, ON DELETE RESTRICT): Cliente solicitante.
- **`reservation_number`** (`VARCHAR(50)`, NOT NULL, UNIQUE): Consecutivo alfanumérico (ej. `RES-2026-10001`).
- **`status`** (`VARCHAR(25)`, NOT NULL, DEFAULT `'PENDING'`, CHECK `IN ('PENDING', 'CONFIRMED', 'DELIVERED', 'RETURNED', 'CANCELLED', 'EXPIRED')`): Estado del flujo de renta.
- **`total_rental_amount`** (`NUMERIC(12,2)`, NOT NULL, DEFAULT `0.00`, CHECK `>= 0`): Monto liquidado por tarifa de alquiler.
- **`total_deposit_amount`** (`NUMERIC(12,2)`, NOT NULL, DEFAULT `0.00`, CHECK `>= 0`): Total de depósitos en garantía exigidos.
- **`expires_at`** (`TIMESTAMPTZ`, NOT NULL): Fecha/hora límite para pago antes de expirar la reserva (ej. 30 minutos).
- **`cancelled_reason`** (`VARCHAR(255)`, NULL): Motivo de cancelación en caso de anulación.
- **`created_at`** (`TIMESTAMPTZ`, NOT NULL, DEFAULT `CURRENT_TIMESTAMP`): Fecha de creación.
- **`updated_at`** (`TIMESTAMPTZ`, NOT NULL, DEFAULT `CURRENT_TIMESTAMP`): Fecha de actualización de estado.

### 16. `reservation_items`
Líneas de detalle de equipos reservados. Implementa exclusión anti-solapamiento.
- **`id`** (`UUID`, PK, NOT NULL, DEFAULT `gen_random_uuid()`): Identificador del ítem.
- **`reservation_id`** (`UUID`, NOT NULL, FK `reservations.id`, ON DELETE CASCADE): Reserva a la que pertenece.
- **`inventory_unit_id`** (`UUID`, NOT NULL, FK `tool_inventory_units.id`, ON DELETE RESTRICT): Unidad física asignada.
- **`start_date`** (`DATE`, NOT NULL): Fecha inicial del periodo de alquiler.
- **`end_date`** (`DATE`, NOT NULL): Fecha final del periodo de alquiler.
- **`daily_price_applied`** (`NUMERIC(12,2)`, NOT NULL, CHECK `> 0`): Tarifa diaria pactada.
- **`rental_days`** (`INT`, NOT NULL, CHECK `> 0`): Total de días calendario alquilados.
- **`subtotal`** (`NUMERIC(12,2)`, NOT NULL, CHECK `> 0`): Subtotal calculado (`daily_price_applied * rental_days`).
- **`deposit_applied`** (`NUMERIC(12,2)`, NOT NULL, DEFAULT `0.00`, CHECK `>= 0`): Depósito de garantía asignado.
- **`is_active`** (`BOOLEAN`, NOT NULL, DEFAULT `TRUE`): Bandera para control de exclusión. Si la reserva se cancela, pasa a `FALSE`.
- **`rental_period`** (`daterange`, GENERATED ALWAYS AS `daterange(start_date, end_date, '[]')` STORED): Rango cerrado de fechas de renta.
- **`created_at`** (`TIMESTAMPTZ`, NOT NULL, DEFAULT `CURRENT_TIMESTAMP`): Fecha de registro.
- **Restricción Anti-Solapamiento:**  
  `EXCLUDE USING gist (inventory_unit_id WITH =, rental_period WITH &&) WHERE (is_active = TRUE)`: Impide a nivel de motor PostgreSQL que una misma unidad física sea alquilada en fechas solapadas mientras la reserva esté activa.

### 17. `deliveries`
Acta formal de despacho y entrega física de la maquinaria al cliente.
- **`id`** (`UUID`, PK, NOT NULL, DEFAULT `gen_random_uuid()`): Identificador del acta de entrega.
- **`reservation_id`** (`UUID`, NOT NULL, UNIQUE, FK `reservations.id`, ON DELETE RESTRICT): Reserva entregada.
- **`delivered_by_user_id`** (`UUID`, NOT NULL, FK `users.id`, ON DELETE RESTRICT): Funcionario o proveedor que entrega.
- **`delivered_at`** (`TIMESTAMPTZ`, NOT NULL, DEFAULT `CURRENT_TIMESTAMP`): Fecha y hora real de entrega.
- **`initial_condition_notes`** (`TEXT`, NOT NULL): Observaciones del estado inicial y checklist de operatividad.
- **`client_signature_ack`** (`BOOLEAN`, NOT NULL, DEFAULT `TRUE`): Constancia de conformidad firmada por el cliente.
- **`created_at`** (`TIMESTAMPTZ`, NOT NULL, DEFAULT `CURRENT_TIMESTAMP`): Fecha de registro.

### 18. `returns`
Acta formal de devolución, inspección técnica y recepción de los equipos.
- **`id`** (`UUID`, PK, NOT NULL, DEFAULT `gen_random_uuid()`): Identificador del acta de devolución.
- **`reservation_id`** (`UUID`, NOT NULL, UNIQUE, FK `reservations.id`, ON DELETE RESTRICT): Reserva recibida.
- **`received_by_user_id`** (`UUID`, NOT NULL, FK `users.id`, ON DELETE RESTRICT): Funcionario o técnico que recibe.
- **`returned_at`** (`TIMESTAMPTZ`, NOT NULL, DEFAULT `CURRENT_TIMESTAMP`): Fecha y hora real de recepción.
- **`delay_days`** (`INT`, NOT NULL, DEFAULT `0`, CHECK `>= 0`): Días de retraso respecto a la fecha final pactada.
- **`penalty_amount`** (`NUMERIC(12,2)`, NOT NULL, DEFAULT `0.00`, CHECK `>= 0`): Recargo cobrado por entrega extemporánea.
- **`return_condition_notes`** (`TEXT`, NOT NULL): Resultados de la prueba de funcionamiento al recibir.
- **`deposit_refunded`** (`BOOLEAN`, NOT NULL, DEFAULT `FALSE`): Indica si el depósito en garantía fue liberado/reembolsado.
- **`created_at`** (`TIMESTAMPTZ`, NOT NULL, DEFAULT `CURRENT_TIMESTAMP`): Fecha de recepción.

### 19. `damage_reports`
Reporte de siniestros, averías o pérdidas detectadas en la devolución o uso.
- **`id`** (`UUID`, PK, NOT NULL, DEFAULT `gen_random_uuid()`): Identificador del informe de avería.
- **`reservation_id`** (`UUID`, NOT NULL, FK `reservations.id`, ON DELETE RESTRICT): Reserva asociada al incidente.
- **`inventory_unit_id`** (`UUID`, NOT NULL, FK `tool_inventory_units.id`, ON DELETE RESTRICT): Unidad física afectada.
- **`reported_by_user_id`** (`UUID`, NOT NULL, FK `users.id`, ON DELETE RESTRICT): Usuario que formula el reporte.
- **`severity`** (`VARCHAR(20)`, NOT NULL, CHECK `IN ('LOW', 'MEDIUM', 'HIGH', 'TOTAL_LOSS')`): Grado de afectación del equipo.
- **`repair_estimated_cost`** (`NUMERIC(12,2)`, NOT NULL, CHECK `>= 0`): Costo proyectado de reparación o reposición.
- **`charged_to_deposit`** (`NUMERIC(12,2)`, NOT NULL, DEFAULT `0.00`, CHECK `>= 0`): Monto deducido del depósito de garantía.
- **`description`** (`TEXT`, NOT NULL): Narrativa de los daños encontrados y causas.
- **`resolved`** (`BOOLEAN`, NOT NULL, DEFAULT `FALSE`): Estado de liquidación o solución del reporte.
- **`created_at`** (`TIMESTAMPTZ`, NOT NULL, DEFAULT `CURRENT_TIMESTAMP`): Fecha de reporte.

### 20. `payments`
Registro de pasarela y conciliación de pagos electrónicos.
- **`id`** (`UUID`, PK, NOT NULL, DEFAULT `gen_random_uuid()`): Identificador de la transacción.
- **`reservation_id`** (`UUID`, NOT NULL, FK `reservations.id`, ON DELETE RESTRICT): Reserva pagada.
- **`payment_reference`** (`VARCHAR(60)`, NOT NULL, UNIQUE): Referencia única de transacción comercial.
- **`payment_method`** (`VARCHAR(30)`, NOT NULL, CHECK `IN ('CREDIT_CARD', 'DEBIT_CARD', 'PSE', 'BANK_TRANSFER', 'SIMULATED_GATEWAY')`): Canal o método utilizado.
- **`amount`** (`NUMERIC(12,2)`, NOT NULL, CHECK `> 0`): Monto monetario cancelado.
- **`status`** (`VARCHAR(25)`, NOT NULL, DEFAULT `'PENDING'`, CHECK `IN ('PENDING', 'COMPLETED', 'FAILED', 'REFUNDED')`): Estado de la pasarela.
- **`gateway_transaction_id`** (`VARCHAR(100)`, NULL): Identificador provisto por la pasarela de pagos.
- **`gateway_response_code`** (`VARCHAR(50)`, NULL): Código de autorización o error de la entidad financiera.
- **`paid_at`** (`TIMESTAMPTZ`, NULL): Fecha/hora de confirmación del pago.
- **`created_at`** (`TIMESTAMPTZ`, NOT NULL, DEFAULT `CURRENT_TIMESTAMP`): Fecha de inicio del intento de pago.

### 21. `invoices`
Facturas electrónicas y comprobantes fiscales generados automáticamente.
- **`id`** (`UUID`, PK, NOT NULL, DEFAULT `gen_random_uuid()`): Identificador de la factura.
- **`reservation_id`** (`UUID`, NOT NULL, UNIQUE, FK `reservations.id`, ON DELETE RESTRICT): Reserva originadora del cobro.
- **`client_id`** (`UUID`, NOT NULL, FK `client_profiles.id`, ON DELETE RESTRICT): Cliente facturado.
- **`provider_id`** (`UUID`, NOT NULL, FK `provider_profiles.id`, ON DELETE RESTRICT): Proveedor emisor del servicio.
- **`invoice_number`** (`VARCHAR(50)`, NOT NULL, UNIQUE): Consecutivo legal de factura (ej. `FAC-2026-1001`).
- **`subtotal`** (`NUMERIC(12,2)`, NOT NULL, CHECK `>= 0`): Base gravable antes de impuestos.
- **`tax_rate`** (`NUMERIC(5,2)`, NOT NULL, DEFAULT `19.00`, CHECK `>= 0`): Porcentaje de IVA legal aplicable.
- **`tax_amount`** (`NUMERIC(12,2)`, NOT NULL, CHECK `>= 0`): Valor liquidado del impuesto IVA.
- **`total_amount`** (`NUMERIC(12,2)`, NOT NULL, CHECK `>= 0`): Valor total facturado (`subtotal + tax_amount`).
- **`pdf_path`** (`VARCHAR(300)`, NULL): Ruta en disco o almacenamiento del archivo PDF generado.
- **`issued_at`** (`TIMESTAMPTZ`, NOT NULL, DEFAULT `CURRENT_TIMESTAMP`): Fecha de emisión fiscal.
- **`created_at`** (`TIMESTAMPTZ`, NOT NULL, DEFAULT `CURRENT_TIMESTAMP`): Fecha de registro en sistema.

### 22. `invoice_items`
Líneas de detalle de cada factura emitida.
- **`id`** (`UUID`, PK, NOT NULL, DEFAULT `gen_random_uuid()`): Identificador de la línea.
- **`invoice_id`** (`UUID`, NOT NULL, FK `invoices.id`, ON DELETE CASCADE): Factura a la que pertenece.
- **`description`** (`VARCHAR(255)`, NOT NULL): Concepto o descripción del servicio prestado.
- **`quantity`** (`INT`, NOT NULL, DEFAULT `1`, CHECK `> 0`): Cantidad de unidades o días cobrados.
- **`unit_price`** (`NUMERIC(12,2)`, NOT NULL, CHECK `>= 0`): Tarifa unitaria aplicada.
- **`line_total`** (`NUMERIC(12,2)`, NOT NULL, CHECK `>= 0`): Total de la línea (`quantity * unit_price`).
- **`created_at`** (`TIMESTAMPTZ`, NOT NULL, DEFAULT `CURRENT_TIMESTAMP`): Fecha de registro.
