-- =============================================================================
-- V1__schema.sql
-- Plataforma de Renta de Herramientas y Equipos de Construcción (PostgreSQL 16)
-- 22 Tablas normalizadas en 4FN (Cuarta Forma Normal)
-- Bounded Contexts: identity, catalog, rental, billing
-- =============================================================================

-- 0. Extensiones requeridas
CREATE EXTENSION IF NOT EXISTS "pgcrypto";
CREATE EXTENSION IF NOT EXISTS "btree_gist";

-- =============================================================================
-- BOUNDED CONTEXT: IDENTITY (8 Tablas)
-- =============================================================================

-- 1. roles
CREATE TABLE roles (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(30) NOT NULL UNIQUE,
    description VARCHAR(255) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_roles_name CHECK (name IN ('ADMIN', 'PROVIDER', 'CLIENT'))
);

-- 2. users
CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email VARCHAR(150) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_users_email CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$')
);

-- 3. user_roles
CREATE TABLE user_roles (
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    role_id UUID NOT NULL REFERENCES roles(id) ON DELETE RESTRICT,
    assigned_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, role_id)
);

-- 4. client_profiles
CREATE TABLE client_profiles (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL UNIQUE REFERENCES users(id) ON DELETE CASCADE,
    identification_type VARCHAR(20) NOT NULL,
    identification_number VARCHAR(30) NOT NULL UNIQUE,
    legal_name VARCHAR(150),
    reputation_score NUMERIC(3, 2) NOT NULL DEFAULT 5.00,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_client_id_type CHECK (identification_type IN ('CC', 'NIT', 'CE', 'PASSPORT')),
    CONSTRAINT chk_client_score CHECK (reputation_score >= 0.00 AND reputation_score <= 5.00)
);

-- 5. provider_profiles
CREATE TABLE provider_profiles (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL UNIQUE REFERENCES users(id) ON DELETE CASCADE,
    tax_id VARCHAR(30) NOT NULL UNIQUE,
    company_name VARCHAR(150) NOT NULL,
    commercial_registry VARCHAR(50) NOT NULL UNIQUE,
    commission_rate NUMERIC(5, 2) NOT NULL DEFAULT 10.00,
    rating_average NUMERIC(3, 2) NOT NULL DEFAULT 5.00,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_provider_commission CHECK (commission_rate >= 0.00 AND commission_rate <= 100.00),
    CONSTRAINT chk_provider_rating CHECK (rating_average >= 0.00 AND rating_average <= 5.00)
);

-- 6. user_addresses (4FN: normalización 1:N independiente de direcciones)
CREATE TABLE user_addresses (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    department VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    address_line VARCHAR(255) NOT NULL,
    postal_code VARCHAR(20),
    is_primary BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 7. user_phones (4FN: normalización 1:N independiente de teléfonos)
CREATE TABLE user_phones (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    country_code VARCHAR(5) NOT NULL DEFAULT '+57',
    phone_number VARCHAR(20) NOT NULL,
    phone_type VARCHAR(20) NOT NULL DEFAULT 'MOBILE',
    is_primary BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_phones_type CHECK (phone_type IN ('MOBILE', 'LANDLINE', 'WHATSAPP')),
    CONSTRAINT uq_user_phones UNIQUE (user_id, phone_number)
);

-- 8. refresh_tokens (Gestión de sesiones JWT y rotación segura)
CREATE TABLE refresh_tokens (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    token_hash VARCHAR(255) NOT NULL UNIQUE,
    expires_at TIMESTAMPTZ NOT NULL,
    revoked BOOLEAN NOT NULL DEFAULT FALSE,
    revoked_at TIMESTAMPTZ,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- =============================================================================
-- BOUNDED CONTEXT: CATALOG (6 Tablas)
-- =============================================================================

-- 9. categories
CREATE TABLE categories (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(80) NOT NULL UNIQUE,
    description TEXT,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 10. tools
CREATE TABLE tools (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    provider_id UUID NOT NULL REFERENCES provider_profiles(id) ON DELETE RESTRICT,
    category_id UUID NOT NULL REFERENCES categories(id) ON DELETE RESTRICT,
    code VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(120) NOT NULL,
    description TEXT NOT NULL,
    brand VARCHAR(60) NOT NULL,
    model VARCHAR(60) NOT NULL,
    daily_rental_rate NUMERIC(12, 2) NOT NULL,
    security_deposit_amount NUMERIC(12, 2) NOT NULL DEFAULT 0.00,
    replacement_value NUMERIC(12, 2) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_tool_daily_rate CHECK (daily_rental_rate > 0),
    CONSTRAINT chk_tool_deposit CHECK (security_deposit_amount >= 0),
    CONSTRAINT chk_tool_replacement CHECK (replacement_value > 0)
);

-- 11. tool_specifications (4FN: normalización 1:N de fichas técnicas)
CREATE TABLE tool_specifications (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tool_id UUID NOT NULL REFERENCES tools(id) ON DELETE CASCADE,
    spec_key VARCHAR(50) NOT NULL,
    spec_value VARCHAR(100) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_tool_spec UNIQUE (tool_id, spec_key)
);

-- 12. tool_images (4FN: normalización 1:N de galería de imágenes)
CREATE TABLE tool_images (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tool_id UUID NOT NULL REFERENCES tools(id) ON DELETE CASCADE,
    image_url VARCHAR(500) NOT NULL,
    is_cover BOOLEAN NOT NULL DEFAULT FALSE,
    display_order INT NOT NULL DEFAULT 0,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 13. tool_inventory_units (Unidades físicas individuales con número de serie)
CREATE TABLE tool_inventory_units (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tool_id UUID NOT NULL REFERENCES tools(id) ON DELETE RESTRICT,
    serial_number VARCHAR(80) NOT NULL UNIQUE,
    status VARCHAR(25) NOT NULL DEFAULT 'AVAILABLE',
    physical_condition VARCHAR(30) NOT NULL DEFAULT 'EXCELLENT',
    operating_hours NUMERIC(8, 2) NOT NULL DEFAULT 0.00,
    acquired_at DATE NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_unit_status CHECK (status IN ('AVAILABLE', 'RESERVED', 'RENTED', 'IN_MAINTENANCE', 'DAMAGED', 'RETIRED')),
    CONSTRAINT chk_unit_condition CHECK (physical_condition IN ('EXCELLENT', 'GOOD', 'FAIR', 'DAMAGED')),
    CONSTRAINT chk_unit_hours CHECK (operating_hours >= 0)
);

-- 14. maintenance_records (Historial de mantenimientos por unidad física)
CREATE TABLE maintenance_records (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    inventory_unit_id UUID NOT NULL REFERENCES tool_inventory_units(id) ON DELETE RESTRICT,
    maintenance_type VARCHAR(20) NOT NULL,
    cost NUMERIC(12, 2) NOT NULL DEFAULT 0.00,
    performed_by VARCHAR(120) NOT NULL,
    description TEXT NOT NULL,
    performed_at TIMESTAMPTZ NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_maint_type CHECK (maintenance_type IN ('PREVENTIVE', 'CORRECTIVE', 'CALIBRATION')),
    CONSTRAINT chk_maint_cost CHECK (cost >= 0)
);

-- =============================================================================
-- BOUNDED CONTEXT: RENTAL (5 Tablas)
-- =============================================================================

-- 15. reservations
CREATE TABLE reservations (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    client_id UUID NOT NULL REFERENCES client_profiles(id) ON DELETE RESTRICT,
    tool_id UUID REFERENCES tools(id) ON DELETE RESTRICT,
    reservation_number VARCHAR(50) NOT NULL UNIQUE,
    status VARCHAR(25) NOT NULL DEFAULT 'PENDING',
    start_date DATE,
    end_date DATE,
    daily_rate NUMERIC(12, 2) DEFAULT 0.00,
    total_rental_amount NUMERIC(12, 2) NOT NULL DEFAULT 0.00,
    total_deposit_amount NUMERIC(12, 2) NOT NULL DEFAULT 0.00,
    expires_at TIMESTAMPTZ NOT NULL,
    cancelled_reason VARCHAR(255),
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_reservation_status CHECK (status IN ('PENDING', 'CONFIRMED', 'DELIVERED', 'RETURNED', 'CANCELLED', 'EXPIRED')),
    CONSTRAINT chk_reservation_total CHECK (total_rental_amount >= 0),
    CONSTRAINT chk_reservation_deposit CHECK (total_deposit_amount >= 0)
);

-- 16. reservation_items (Líneas de reserva con exclusión de solapamiento de fechas)
CREATE TABLE reservation_items (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    reservation_id UUID NOT NULL REFERENCES reservations(id) ON DELETE CASCADE,
    inventory_unit_id UUID NOT NULL REFERENCES tool_inventory_units(id) ON DELETE RESTRICT,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    daily_price_applied NUMERIC(12, 2) NOT NULL,
    rental_days INT NOT NULL,
    subtotal NUMERIC(12, 2) NOT NULL,
    deposit_applied NUMERIC(12, 2) NOT NULL DEFAULT 0.00,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    rental_period daterange GENERATED ALWAYS AS (daterange(start_date, end_date, '[]')) STORED,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_res_items_dates CHECK (end_date >= start_date),
    CONSTRAINT chk_res_items_days CHECK (rental_days > 0),
    CONSTRAINT chk_res_items_price CHECK (daily_price_applied > 0),
    CONSTRAINT chk_res_items_subtotal CHECK (subtotal > 0),
    CONSTRAINT chk_res_items_deposit CHECK (deposit_applied >= 0),
    CONSTRAINT exclude_overlapping_unit_rental EXCLUDE USING gist (inventory_unit_id WITH =, rental_period WITH &&) WHERE (is_active = TRUE)
);

-- 17. deliveries (Acta de entrega física)
CREATE TABLE deliveries (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    reservation_id UUID NOT NULL UNIQUE REFERENCES reservations(id) ON DELETE RESTRICT,
    delivered_by_user_id UUID NOT NULL REFERENCES users(id) ON DELETE RESTRICT,
    delivered_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    initial_condition_notes TEXT NOT NULL,
    client_signature_ack BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 18. returns (Acta de devolución e inspección)
CREATE TABLE returns (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    reservation_id UUID NOT NULL UNIQUE REFERENCES reservations(id) ON DELETE RESTRICT,
    received_by_user_id UUID NOT NULL REFERENCES users(id) ON DELETE RESTRICT,
    returned_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    delay_days INT NOT NULL DEFAULT 0,
    penalty_amount NUMERIC(12, 2) NOT NULL DEFAULT 0.00,
    return_condition_notes TEXT NOT NULL,
    deposit_refunded BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_return_delay CHECK (delay_days >= 0),
    CONSTRAINT chk_return_penalty CHECK (penalty_amount >= 0)
);

-- 19. damage_reports (Reportes de incidencias o daños)
CREATE TABLE damage_reports (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    reservation_id UUID NOT NULL REFERENCES reservations(id) ON DELETE RESTRICT,
    inventory_unit_id UUID NOT NULL REFERENCES tool_inventory_units(id) ON DELETE RESTRICT,
    reported_by_user_id UUID NOT NULL REFERENCES users(id) ON DELETE RESTRICT,
    severity VARCHAR(20) NOT NULL,
    repair_estimated_cost NUMERIC(12, 2) NOT NULL,
    charged_to_deposit NUMERIC(12, 2) NOT NULL DEFAULT 0.00,
    description TEXT NOT NULL,
    resolved BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_damage_severity CHECK (severity IN ('LOW', 'MEDIUM', 'HIGH', 'TOTAL_LOSS')),
    CONSTRAINT chk_damage_cost CHECK (repair_estimated_cost >= 0),
    CONSTRAINT chk_damage_deposit CHECK (charged_to_deposit >= 0)
);

-- =============================================================================
-- BOUNDED CONTEXT: BILLING (3 Tablas)
-- =============================================================================

-- 20. payments (Transacciones de pago)
CREATE TABLE payments (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    reservation_id UUID NOT NULL REFERENCES reservations(id) ON DELETE RESTRICT,
    payment_reference VARCHAR(60) NOT NULL UNIQUE,
    payment_method VARCHAR(30) NOT NULL,
    amount NUMERIC(12, 2) NOT NULL,
    status VARCHAR(25) NOT NULL DEFAULT 'PENDING',
    gateway_transaction_id VARCHAR(100),
    gateway_response_code VARCHAR(50),
    paid_at TIMESTAMPTZ,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_payment_method CHECK (payment_method IN ('CREDIT_CARD', 'DEBIT_CARD', 'PSE', 'BANK_TRANSFER', 'SIMULATED_GATEWAY')),
    CONSTRAINT chk_payment_status CHECK (status IN ('PENDING', 'COMPLETED', 'FAILED', 'REFUNDED')),
    CONSTRAINT chk_payment_amount CHECK (amount > 0)
);

-- 21. invoices (Facturas comerciales y comprobantes legales)
CREATE TABLE invoices (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    reservation_id UUID NOT NULL UNIQUE REFERENCES reservations(id) ON DELETE RESTRICT,
    client_id UUID NOT NULL REFERENCES client_profiles(id) ON DELETE RESTRICT,
    provider_id UUID NOT NULL REFERENCES provider_profiles(id) ON DELETE RESTRICT,
    invoice_number VARCHAR(50) NOT NULL UNIQUE,
    subtotal NUMERIC(12, 2) NOT NULL,
    tax_rate NUMERIC(5, 2) NOT NULL DEFAULT 19.00,
    tax_amount NUMERIC(12, 2) NOT NULL,
    total_amount NUMERIC(12, 2) NOT NULL,
    pdf_path VARCHAR(300),
    issued_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_invoice_subtotal CHECK (subtotal >= 0),
    CONSTRAINT chk_invoice_tax_rate CHECK (tax_rate >= 0),
    CONSTRAINT chk_invoice_tax_amount CHECK (tax_amount >= 0),
    CONSTRAINT chk_invoice_total CHECK (total_amount >= 0)
);

-- 22. invoice_items (Detalle de conceptos facturados)
CREATE TABLE invoice_items (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    invoice_id UUID NOT NULL REFERENCES invoices(id) ON DELETE CASCADE,
    description VARCHAR(255) NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    unit_price NUMERIC(12, 2) NOT NULL,
    line_total NUMERIC(12, 2) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_inv_items_qty CHECK (quantity > 0),
    CONSTRAINT chk_inv_items_price CHECK (unit_price >= 0),
    CONSTRAINT chk_inv_items_total CHECK (line_total >= 0)
);

-- =============================================================================
-- ÍNDICES ESTRATÉGICOS DE RENDIMIENTO
-- =============================================================================

-- Identity
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_user_roles_user ON user_roles(user_id);
CREATE INDEX idx_client_user ON client_profiles(user_id);
CREATE INDEX idx_client_doc ON client_profiles(identification_number);
CREATE INDEX idx_provider_user ON provider_profiles(user_id);
CREATE INDEX idx_provider_tax ON provider_profiles(tax_id);
CREATE INDEX idx_user_addresses_user ON user_addresses(user_id);
CREATE INDEX idx_user_phones_user ON user_phones(user_id);
CREATE INDEX idx_refresh_tokens_user ON refresh_tokens(user_id);
CREATE INDEX idx_refresh_tokens_hash ON refresh_tokens(token_hash);

-- Catalog
CREATE INDEX idx_tools_provider ON tools(provider_id);
CREATE INDEX idx_tools_category ON tools(category_id);
CREATE INDEX idx_tools_code ON tools(code);
CREATE INDEX idx_tools_active ON tools(active);
CREATE INDEX idx_tool_specs_tool ON tool_specifications(tool_id);
CREATE INDEX idx_tool_images_tool ON tool_images(tool_id);
CREATE INDEX idx_inventory_units_tool ON tool_inventory_units(tool_id);
CREATE INDEX idx_inventory_units_status ON tool_inventory_units(status);
CREATE INDEX idx_maint_unit ON maintenance_records(inventory_unit_id);

-- Rental
CREATE INDEX idx_reservations_client ON reservations(client_id);
CREATE INDEX idx_reservations_status ON reservations(status);
CREATE INDEX idx_reservations_number ON reservations(reservation_number);
CREATE INDEX idx_reservation_items_res ON reservation_items(reservation_id);
CREATE INDEX idx_reservation_items_unit ON reservation_items(inventory_unit_id);
CREATE INDEX idx_deliveries_res ON deliveries(reservation_id);
CREATE INDEX idx_returns_res ON returns(reservation_id);
CREATE INDEX idx_damage_reports_res ON damage_reports(reservation_id);
CREATE INDEX idx_damage_reports_unit ON damage_reports(inventory_unit_id);

-- Billing
CREATE INDEX idx_payments_res ON payments(reservation_id);
CREATE INDEX idx_payments_ref ON payments(payment_reference);
CREATE INDEX idx_payments_status ON payments(status);
CREATE INDEX idx_invoices_res ON invoices(reservation_id);
CREATE INDEX idx_invoices_client ON invoices(client_id);
CREATE INDEX idx_invoices_provider ON invoices(provider_id);
CREATE INDEX idx_invoices_number ON invoices(invoice_number);
CREATE INDEX idx_invoice_items_inv ON invoice_items(invoice_id);
