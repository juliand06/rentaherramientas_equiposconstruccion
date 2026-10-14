-- =============================================================================
-- schema-drawsql.sql
-- Archivo DDL optimizado para importación directa en drawSQL (PostgreSQL dialect)
-- Proyecto: Plataforma de Renta de Herramientas y Equipos de Construcción
-- 22 Tablas normalizadas en 4FN organizadas por Bounded Contexts
-- =============================================================================

-- =============================================================================
-- BOUNDED CONTEXT: IDENTITY
-- =============================================================================

CREATE TABLE "roles" (
    "id" UUID PRIMARY KEY,
    "name" VARCHAR(30) NOT NULL UNIQUE,
    "description" VARCHAR(255) NOT NULL,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "users" (
    "id" UUID PRIMARY KEY,
    "email" VARCHAR(150) NOT NULL UNIQUE,
    "password_hash" VARCHAR(255) NOT NULL,
    "first_name" VARCHAR(100) NOT NULL,
    "last_name" VARCHAR(100) NOT NULL,
    "active" BOOLEAN NOT NULL DEFAULT TRUE,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "user_roles" (
    "user_id" UUID NOT NULL,
    "role_id" UUID NOT NULL,
    "assigned_at" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY ("user_id", "role_id")
);

CREATE TABLE "client_profiles" (
    "id" UUID PRIMARY KEY,
    "user_id" UUID NOT NULL UNIQUE,
    "identification_type" VARCHAR(20) NOT NULL,
    "identification_number" VARCHAR(30) NOT NULL UNIQUE,
    "legal_name" VARCHAR(150),
    "reputation_score" NUMERIC(3, 2) NOT NULL DEFAULT 5.00,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "provider_profiles" (
    "id" UUID PRIMARY KEY,
    "user_id" UUID NOT NULL UNIQUE,
    "tax_id" VARCHAR(30) NOT NULL UNIQUE,
    "company_name" VARCHAR(150) NOT NULL,
    "commercial_registry" VARCHAR(50) NOT NULL UNIQUE,
    "commission_rate" NUMERIC(5, 2) NOT NULL DEFAULT 10.00,
    "rating_average" NUMERIC(3, 2) NOT NULL DEFAULT 5.00,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "user_addresses" (
    "id" UUID PRIMARY KEY,
    "user_id" UUID NOT NULL,
    "department" VARCHAR(100) NOT NULL,
    "city" VARCHAR(100) NOT NULL,
    "address_line" VARCHAR(255) NOT NULL,
    "postal_code" VARCHAR(20),
    "is_primary" BOOLEAN NOT NULL DEFAULT FALSE,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "user_phones" (
    "id" UUID PRIMARY KEY,
    "user_id" UUID NOT NULL,
    "country_code" VARCHAR(5) NOT NULL DEFAULT '+57',
    "phone_number" VARCHAR(20) NOT NULL,
    "phone_type" VARCHAR(20) NOT NULL DEFAULT 'MOBILE',
    "is_primary" BOOLEAN NOT NULL DEFAULT FALSE,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "refresh_tokens" (
    "id" UUID PRIMARY KEY,
    "user_id" UUID NOT NULL,
    "token_hash" VARCHAR(255) NOT NULL UNIQUE,
    "expires_at" TIMESTAMPTZ NOT NULL,
    "revoked" BOOLEAN NOT NULL DEFAULT FALSE,
    "revoked_at" TIMESTAMPTZ,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- =============================================================================
-- BOUNDED CONTEXT: CATALOG
-- =============================================================================

CREATE TABLE "categories" (
    "id" UUID PRIMARY KEY,
    "name" VARCHAR(80) NOT NULL UNIQUE,
    "description" TEXT,
    "active" BOOLEAN NOT NULL DEFAULT TRUE,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "tools" (
    "id" UUID PRIMARY KEY,
    "provider_id" UUID NOT NULL,
    "category_id" UUID NOT NULL,
    "code" VARCHAR(50) NOT NULL UNIQUE,
    "name" VARCHAR(120) NOT NULL,
    "description" TEXT NOT NULL,
    "brand" VARCHAR(60) NOT NULL,
    "model" VARCHAR(60) NOT NULL,
    "daily_rental_rate" NUMERIC(12, 2) NOT NULL,
    "security_deposit_amount" NUMERIC(12, 2) NOT NULL DEFAULT 0.00,
    "replacement_value" NUMERIC(12, 2) NOT NULL,
    "active" BOOLEAN NOT NULL DEFAULT TRUE,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "tool_specifications" (
    "id" UUID PRIMARY KEY,
    "tool_id" UUID NOT NULL,
    "spec_key" VARCHAR(50) NOT NULL,
    "spec_value" VARCHAR(100) NOT NULL,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "tool_images" (
    "id" UUID PRIMARY KEY,
    "tool_id" UUID NOT NULL,
    "image_url" VARCHAR(500) NOT NULL,
    "is_cover" BOOLEAN NOT NULL DEFAULT FALSE,
    "display_order" INT NOT NULL DEFAULT 0,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "tool_inventory_units" (
    "id" UUID PRIMARY KEY,
    "tool_id" UUID NOT NULL,
    "serial_number" VARCHAR(80) NOT NULL UNIQUE,
    "status" VARCHAR(25) NOT NULL DEFAULT 'AVAILABLE',
    "physical_condition" VARCHAR(30) NOT NULL DEFAULT 'EXCELLENT',
    "operating_hours" NUMERIC(8, 2) NOT NULL DEFAULT 0.00,
    "acquired_at" DATE NOT NULL,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "maintenance_records" (
    "id" UUID PRIMARY KEY,
    "inventory_unit_id" UUID NOT NULL,
    "maintenance_type" VARCHAR(20) NOT NULL,
    "cost" NUMERIC(12, 2) NOT NULL DEFAULT 0.00,
    "performed_by" VARCHAR(120) NOT NULL,
    "description" TEXT NOT NULL,
    "performed_at" TIMESTAMPTZ NOT NULL,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- =============================================================================
-- BOUNDED CONTEXT: RENTAL
-- =============================================================================

CREATE TABLE "reservations" (
    "id" UUID PRIMARY KEY,
    "client_id" UUID NOT NULL,
    "reservation_number" VARCHAR(50) NOT NULL UNIQUE,
    "status" VARCHAR(25) NOT NULL DEFAULT 'PENDING',
    "total_rental_amount" NUMERIC(12, 2) NOT NULL DEFAULT 0.00,
    "total_deposit_amount" NUMERIC(12, 2) NOT NULL DEFAULT 0.00,
    "expires_at" TIMESTAMPTZ NOT NULL,
    "cancelled_reason" VARCHAR(255),
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "reservation_items" (
    "id" UUID PRIMARY KEY,
    "reservation_id" UUID NOT NULL,
    "inventory_unit_id" UUID NOT NULL,
    "start_date" DATE NOT NULL,
    "end_date" DATE NOT NULL,
    "daily_price_applied" NUMERIC(12, 2) NOT NULL,
    "rental_days" INT NOT NULL,
    "subtotal" NUMERIC(12, 2) NOT NULL,
    "deposit_applied" NUMERIC(12, 2) NOT NULL DEFAULT 0.00,
    "is_active" BOOLEAN NOT NULL DEFAULT TRUE,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "deliveries" (
    "id" UUID PRIMARY KEY,
    "reservation_id" UUID NOT NULL UNIQUE,
    "delivered_by_user_id" UUID NOT NULL,
    "delivered_at" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "initial_condition_notes" TEXT NOT NULL,
    "client_signature_ack" BOOLEAN NOT NULL DEFAULT TRUE,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "returns" (
    "id" UUID PRIMARY KEY,
    "reservation_id" UUID NOT NULL UNIQUE,
    "received_by_user_id" UUID NOT NULL,
    "returned_at" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "delay_days" INT NOT NULL DEFAULT 0,
    "penalty_amount" NUMERIC(12, 2) NOT NULL DEFAULT 0.00,
    "return_condition_notes" TEXT NOT NULL,
    "deposit_refunded" BOOLEAN NOT NULL DEFAULT FALSE,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "damage_reports" (
    "id" UUID PRIMARY KEY,
    "reservation_id" UUID NOT NULL,
    "inventory_unit_id" UUID NOT NULL,
    "reported_by_user_id" UUID NOT NULL,
    "severity" VARCHAR(20) NOT NULL,
    "repair_estimated_cost" NUMERIC(12, 2) NOT NULL,
    "charged_to_deposit" NUMERIC(12, 2) NOT NULL DEFAULT 0.00,
    "description" TEXT NOT NULL,
    "resolved" BOOLEAN NOT NULL DEFAULT FALSE,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- =============================================================================
-- BOUNDED CONTEXT: BILLING
-- =============================================================================

CREATE TABLE "payments" (
    "id" UUID PRIMARY KEY,
    "reservation_id" UUID NOT NULL,
    "payment_reference" VARCHAR(60) NOT NULL UNIQUE,
    "payment_method" VARCHAR(30) NOT NULL,
    "amount" NUMERIC(12, 2) NOT NULL,
    "status" VARCHAR(25) NOT NULL DEFAULT 'PENDING',
    "gateway_transaction_id" VARCHAR(100),
    "gateway_response_code" VARCHAR(50),
    "paid_at" TIMESTAMPTZ,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "invoices" (
    "id" UUID PRIMARY KEY,
    "reservation_id" UUID NOT NULL UNIQUE,
    "client_id" UUID NOT NULL,
    "provider_id" UUID NOT NULL,
    "invoice_number" VARCHAR(50) NOT NULL UNIQUE,
    "subtotal" NUMERIC(12, 2) NOT NULL,
    "tax_rate" NUMERIC(5, 2) NOT NULL DEFAULT 19.00,
    "tax_amount" NUMERIC(12, 2) NOT NULL,
    "total_amount" NUMERIC(12, 2) NOT NULL,
    "pdf_path" VARCHAR(300),
    "issued_at" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "invoice_items" (
    "id" UUID PRIMARY KEY,
    "invoice_id" UUID NOT NULL,
    "description" VARCHAR(255) NOT NULL,
    "quantity" INT NOT NULL DEFAULT 1,
    "unit_price" NUMERIC(12, 2) NOT NULL,
    "line_total" NUMERIC(12, 2) NOT NULL,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- =============================================================================
-- CLAVES FORÁNEAS (RELACIONES)
-- =============================================================================

ALTER TABLE "user_roles" ADD CONSTRAINT "fk_user_roles_user" FOREIGN KEY ("user_id") REFERENCES "users"("id") ON DELETE CASCADE;
ALTER TABLE "user_roles" ADD CONSTRAINT "fk_user_roles_role" FOREIGN KEY ("role_id") REFERENCES "roles"("id") ON DELETE RESTRICT;

ALTER TABLE "client_profiles" ADD CONSTRAINT "fk_client_profiles_user" FOREIGN KEY ("user_id") REFERENCES "users"("id") ON DELETE CASCADE;
ALTER TABLE "provider_profiles" ADD CONSTRAINT "fk_provider_profiles_user" FOREIGN KEY ("user_id") REFERENCES "users"("id") ON DELETE CASCADE;

ALTER TABLE "user_addresses" ADD CONSTRAINT "fk_user_addresses_user" FOREIGN KEY ("user_id") REFERENCES "users"("id") ON DELETE CASCADE;
ALTER TABLE "user_phones" ADD CONSTRAINT "fk_user_phones_user" FOREIGN KEY ("user_id") REFERENCES "users"("id") ON DELETE CASCADE;
ALTER TABLE "refresh_tokens" ADD CONSTRAINT "fk_refresh_tokens_user" FOREIGN KEY ("user_id") REFERENCES "users"("id") ON DELETE CASCADE;

ALTER TABLE "tools" ADD CONSTRAINT "fk_tools_provider" FOREIGN KEY ("provider_id") REFERENCES "provider_profiles"("id") ON DELETE RESTRICT;
ALTER TABLE "tools" ADD CONSTRAINT "fk_tools_category" FOREIGN KEY ("category_id") REFERENCES "categories"("id") ON DELETE RESTRICT;

ALTER TABLE "tool_specifications" ADD CONSTRAINT "fk_tool_specs_tool" FOREIGN KEY ("tool_id") REFERENCES "tools"("id") ON DELETE CASCADE;
ALTER TABLE "tool_images" ADD CONSTRAINT "fk_tool_images_tool" FOREIGN KEY ("tool_id") REFERENCES "tools"("id") ON DELETE CASCADE;

ALTER TABLE "tool_inventory_units" ADD CONSTRAINT "fk_tool_units_tool" FOREIGN KEY ("tool_id") REFERENCES "tools"("id") ON DELETE RESTRICT;
ALTER TABLE "maintenance_records" ADD CONSTRAINT "fk_maint_records_unit" FOREIGN KEY ("inventory_unit_id") REFERENCES "tool_inventory_units"("id") ON DELETE RESTRICT;

ALTER TABLE "reservations" ADD CONSTRAINT "fk_reservations_client" FOREIGN KEY ("client_id") REFERENCES "client_profiles"("id") ON DELETE RESTRICT;

ALTER TABLE "reservation_items" ADD CONSTRAINT "fk_res_items_reservation" FOREIGN KEY ("reservation_id") REFERENCES "reservations"("id") ON DELETE CASCADE;
ALTER TABLE "reservation_items" ADD CONSTRAINT "fk_res_items_unit" FOREIGN KEY ("inventory_unit_id") REFERENCES "tool_inventory_units"("id") ON DELETE RESTRICT;

ALTER TABLE "deliveries" ADD CONSTRAINT "fk_deliveries_reservation" FOREIGN KEY ("reservation_id") REFERENCES "reservations"("id") ON DELETE RESTRICT;
ALTER TABLE "deliveries" ADD CONSTRAINT "fk_deliveries_user" FOREIGN KEY ("delivered_by_user_id") REFERENCES "users"("id") ON DELETE RESTRICT;

ALTER TABLE "returns" ADD CONSTRAINT "fk_returns_reservation" FOREIGN KEY ("reservation_id") REFERENCES "reservations"("id") ON DELETE RESTRICT;
ALTER TABLE "returns" ADD CONSTRAINT "fk_returns_user" FOREIGN KEY ("received_by_user_id") REFERENCES "users"("id") ON DELETE RESTRICT;

ALTER TABLE "damage_reports" ADD CONSTRAINT "fk_damage_reports_reservation" FOREIGN KEY ("reservation_id") REFERENCES "reservations"("id") ON DELETE RESTRICT;
ALTER TABLE "damage_reports" ADD CONSTRAINT "fk_damage_reports_unit" FOREIGN KEY ("inventory_unit_id") REFERENCES "tool_inventory_units"("id") ON DELETE RESTRICT;
ALTER TABLE "damage_reports" ADD CONSTRAINT "fk_damage_reports_user" FOREIGN KEY ("reported_by_user_id") REFERENCES "users"("id") ON DELETE RESTRICT;

ALTER TABLE "payments" ADD CONSTRAINT "fk_payments_reservation" FOREIGN KEY ("reservation_id") REFERENCES "reservations"("id") ON DELETE RESTRICT;

ALTER TABLE "invoices" ADD CONSTRAINT "fk_invoices_reservation" FOREIGN KEY ("reservation_id") REFERENCES "reservations"("id") ON DELETE RESTRICT;
ALTER TABLE "invoices" ADD CONSTRAINT "fk_invoices_client" FOREIGN KEY ("client_id") REFERENCES "client_profiles"("id") ON DELETE RESTRICT;
ALTER TABLE "invoices" ADD CONSTRAINT "fk_invoices_provider" FOREIGN KEY ("provider_id") REFERENCES "provider_profiles"("id") ON DELETE RESTRICT;

ALTER TABLE "invoice_items" ADD CONSTRAINT "fk_invoice_items_invoice" FOREIGN KEY ("invoice_id") REFERENCES "invoices"("id") ON DELETE CASCADE;
