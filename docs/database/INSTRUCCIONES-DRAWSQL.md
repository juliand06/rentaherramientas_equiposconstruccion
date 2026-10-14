# Guía de Importación en drawSQL

Esta guía explica paso a paso cómo importar el esquema de base de datos del proyecto en **drawSQL** para visualizar y exportar el diagrama relacional completo.

---

## 1. Archivo para drawSQL

El archivo optimizado para drawSQL se encuentra en:
📁 [`docs/database/schema-drawsql.sql`](schema-drawsql.sql)

> [!NOTE]
> Este archivo contiene la definición DDL en dialecto **PostgreSQL** estándar con las **22 tablas normalizadas en 4FN** y sus relaciones (`FOREIGN KEY`), estructurado para que el analizador sintáctico de drawSQL lo procese con 0 errores.

---

## 2. Pasos para la importación

1. **Acceder a drawSQL:**
   - Ingresa a [https://drawsql.app](https://drawsql.app) e inicia sesión con tu cuenta.

2. **Crear un nuevo diagrama:**
   - Haz clic en **"Create new diagram"**.
   - Asigna como nombre: `Plataforma Renta Herramientas y Equipos`.
   - En el selector de motor de base de datos (**Database / Dialect**), selecciona **PostgreSQL**.

3. **Importar el script DDL:**
   - En la barra superior o menú lateral del lienzo, selecciona la opción **"Import DDL"** (o ícono de engranaje → *Import schema*).
   - Abre el archivo local [`docs/database/schema-drawsql.sql`](schema-drawsql.sql), copia todo su contenido y pégalo en el cuadro de texto.
   - Haz clic en el botón **"Import"**.

4. **Auto-distribución del lienzo:**
   - drawSQL creará automáticamente las 22 tablas y trazará las líneas de relación (`FK`).
   - Puedes usar la opción **"Auto-layout"** para organizar automáticamente las tablas por Bounded Contexts:
     - **Identity:** `roles`, `users`, `user_roles`, `client_profiles`, `provider_profiles`, `user_addresses`, `user_phones`, `refresh_tokens`.
     - **Catalog:** `categories`, `tools`, `tool_specifications`, `tool_images`, `tool_inventory_units`, `maintenance_records`.
     - **Rental:** `reservations`, `reservation_items`, `deliveries`, `returns`, `damage_reports`.
     - **Billing:** `payments`, `invoices`, `invoice_items`.

5. **Exportación del diagrama:**
   - Haz clic en **Export** en la barra superior.
   - Puedes exportarlo en formato **PNG** o **PDF**, o generar un enlace público compartible para incluirlo en la entrega académica.
