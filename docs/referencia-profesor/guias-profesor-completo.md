Title: Live Content

Description: Fetched live

Source: https://docs.google.com/document/d/1MeoBb9e9S6NxJmtZIV_xaTMhMKmo7nSGPy7ufy9GU4I/export?format=txt

---

﻿1. Se debe implementar arquitectura hexagonal + DDD y proyecto por módulos
2. Documentación:
   * README PRINCIPAL
   * DOCUMENTACION DE COMPILACION
   * DOCUMENTACION DE DESPLIEGUE (DOCKER O TOMCAT NATIVO)
3. Para el frontend se puede usas (REACT, ANGULAR, VUE, JS VANILLA)
4. IMPLEMENTAR NORMALIZACION 4FN
Plataforma de Renta de Herramientas y Equipos de Construcción
Proyecto de Spring Boot
📌 Contexto del Proyecto
El alquiler de herramientas y equipos de construcción es una necesidad frecuente para contratistas, empresas y particulares que requieren maquinaria específica sin necesidad de comprarla. Sin embargo, la gestión de reservas, disponibilidad y pagos suele ser un proceso manual o poco optimizado.
Este proyecto tiene como objetivo desarrollar una Plataforma de Renta de Herramientas y Equipos de Construcción, donde los proveedores puedan registrar su inventario y los clientes puedan alquilar herramientas, programar entregas y gestionar pagos de forma eficiente.
📌 Funcionalidades clave del sistema:
✅ Registro y gestión de herramientas con disponibilidad y costos.
✅ Sistema de reservas con pagos en línea y generación de facturas.
✅ Panel de control para proveedores y clientes con historial de alquileres.
✅ Gestión de devoluciones y estado del equipo alquilado.
Este sistema permitirá automatizar el proceso de alquiler de herramientas, mejorando la organización, el control de inventarios y la experiencia de los usuarios.
📌 Perfiles de Usuarios y Funcionalidades
1️⃣ Administrador de la Plataforma (Rol: Admin)
📌 Acciones Disponibles:
✅ Gestión de usuarios:
* Registrar y administrar proveedores y clientes.
* Verificar el estado de los equipos alquilados.
✅ Control de alquileres y devoluciones:
* Ver historial de alquileres.
* Administrar reportes de daños en herramientas.
✅ Supervisión de pagos:
* Monitorear pagos y facturación.
✅ Reportes y métricas:
* Estadísticas sobre ingresos, equipos más alquilados y rentabilidad.
2️⃣ Proveedor de Equipos (Rol: Proveedor)
📌 Acciones Disponibles:
✅ Gestión de inventario:
* Agregar, editar y eliminar herramientas.
* Definir disponibilidad y costos de alquiler.
✅ Gestión de reservas:
* Aceptar o rechazar solicitudes de alquiler.
* Confirmar devoluciones y reportar daños.
✅ Gestión de pagos y facturación:
* Generar facturas por alquileres realizados.
3️⃣ Cliente (Rol: Cliente)
📌 Acciones Disponibles:
✅ Registro e inicio de sesión con JWT.
✅ Explorar herramientas disponibles y consultar precios.
✅ Realizar reservas:
* Seleccionar fechas y confirmar alquiler.
* ✅ Gestión de pagos:
* Pagar en línea y descargar factura.
* ✅ Historial de alquileres:
* Revisar herramientas alquiladas anteriormente.
📌 Diseño y Requisitos del Frontend
El frontend será la interfaz de usuario con un diseño intuitivo, moderno y responsivo.
📌 Requisitos del Frontend:
✅ Diseño adaptable para móviles, tabletas y escritorio.
✅ Interfaz basada en HTML, CSS y JavaScript puro.
✅ Panel de usuario personalizado según el rol (Administrador, Proveedor, Cliente).
✅ Experiencia de usuario optimizada, con navegación clara y funcionalidad intuitiva.
📌 Interacción del Usuario con el Sistema
1️⃣ Vista del Administrador
📌 Panel de control:
✅ Lista de usuarios registrados (proveedores y clientes).
✅ Resumen de pagos y facturación global.
✅ Historial de alquileres y reportes de estado de equipos.
📌 Módulo de supervisión:
✅ Validación de devoluciones y estado de equipos.
✅ Gestión de incidencias con equipos dañados.
📌 Módulo de reportes:
✅ Generación de reportes de rentabilidad y uso de herramientas.
2️⃣ Vista del Proveedor
📌 Módulo de herramientas:
✅ Lista de herramientas en inventario con estado y disponibilidad.
✅ Carga de nuevas herramientas con imágenes y descripción.
📌 Módulo de reservas:
✅ Gestión de solicitudes de alquiler.
✅ Confirmación de devoluciones y reportes de daños.
📌 Módulo de facturación:
✅ Generación automática de facturas y comprobantes de pago.
3️⃣ Vista del Cliente
📌 Módulo de exploración:
✅ Búsqueda de herramientas por categoría y disponibilidad.
✅ Consulta de precios y detalles del alquiler.
📌 Módulo de reservas:
✅ Selección de fechas de alquiler y confirmación de pago.
✅ Historial de alquileres y estado de herramientas alquiladas.
📌 Módulo de pagos:
✅ Opciones de pago en línea y descarga de facturas.
📌 Tecnologías Utilizadas
✅ Backend:
🔹 Spring Boot (Última versión)
🔹 Java |7 o superior
🔹 Spring Security con JWT
🔹 Spring Data JPA
🔹 MySQL
✅ Frontend:
🔹 HTML, CSS, JavaScript
🔹 Bootstrap o Tailwind CSS (según lo autorice el trainer)
✅ Documentación y Despliegue:
🔹 Swagger
🔹 Apache Tomcat
📌 Entregables Obligatorios
📌 Cada equipo debe entregar:
✅ Repositorio en GitHub con el código fuente.
✅ README.md bien estructurado.
✅ Scripts SQL para la creación y población de la base de datos.
✅ Diagrama relacional de la base de datos.
✅ Documentación Swagger de la API.
✅ Código modular y bien organizado.
Cada estudiante deberá completar el desarrollo de la Plataforma de Renta de Herramientas y Equipos de Construcción siguiendo los requisitos definidos en el enunciado. Para la evaluación y entrega del proyecto, se espera que los estudiantes cumplan con los siguientes entregables y características funcionales.
📌 1. Repositorio en GitHub con Código Fuente
Cada equipo deberá proporcionar un repositorio privado en GitHub, compartido con el instructor, bajo el siguiente formato de nombre:
📌 Proyecto_RentaHerramientas_ApellidoNombre (Ejemplo: Proyecto_RentaHerramientas_GomezCarlos).
El repositorio debe contener:
✅ Código modular y bien estructurado en Spring Boot (última versión).
✅ Separación adecuada de capas (Controllers, Services, Repositories, DTOs, Entities).
✅ Código documentado con comentarios explicativos donde sea necesario.
✅ Commits frecuentes que evidencien el trabajo progresivo del equipo.
📌 2. Implementación Completa del Backend con Spring Boot
El backend debe estar correctamente desarrollado con Spring Boot y Java 21, e incluir:
✅ Autenticación y roles con JWT (Administrador, Proveedor, Cliente).
✅ CRUD completo para clientes, proveedores, herramientas, reservas y pagos.
✅ Gestión de seguridad con Spring Security y restricciones de acceso.
✅ Manejo de errores y excepciones personalizadas.
✅ Gestión de pagos y facturación automática.
✅ Sistema de control de disponibilidad de herramientas.
✅ Configuración de CORS para permitir la comunicación con el frontend.
📌 3. Implementación de Base de Datos en MySQL
La base de datos debe estar correctamente normalizada y configurada en MySQL, incluyendo:
✅ Scripts SQL para la creación de la base de datos y las tablas.
✅ Scripts SQL con al menos 20 registros por cada entidad.
✅ Relaciones bien definidas entre las entidades (Usuarios, Proveedores, Clientes, Herramientas, Reservas, Pagos).
✅ Optimización en las consultas y uso de índices si es necesario.
📌 4. Documentación Completa del Proyecto
📌 README.md bien estructurado en el repositorio con:
✅ Nombre y descripción del proyecto.
✅ Tecnologías utilizadas (Java 17 mínimo, Spring Boot, MySQL, JWT).
✅ Instrucciones para clonar, instalar dependencias y ejecutar el proyecto.
✅ Ejemplo de configuración del application.properties.
✅ Diagrama relacional de la base de datos en formato imagen.
✅ Lista de endpoints de la API documentados con ejemplos de uso en JSON.
✅ Explicación de la autenticación y roles.
✅ Guía para ejecutar pruebas unitarias y de integración.
📌 Swagger para documentación de la API con:
✅ Listado de endpoints accesibles con detalles de peticiones y respuestas.
✅ Ejemplos de solicitudes y respuestas JSON.
✅ Explicación de autenticación y uso de JWT en la API.
📌 5. Implementación del Frontend con HTML, CSS y JavaScript
El frontend debe proporcionar una experiencia de usuario intuitiva, fluida y responsiva, incluyendo:
✅ Diseño adaptable para móviles, tabletas y escritorio.
✅ El trainer tiene la libertad de permitir frameworks de diseño como Bootstrap, Tailwind CSS, Vue.js o React.
✅ Autenticación e interacción con la API usando JWT.
✅ Panel de usuario personalizado según el rol:
* Administrador: Puede gestionar herramientas, proveedores y clientes.
* Proveedor: Puede gestionar sus herramientas y reservas.
* Cliente: Puede realizar alquileres, ver su historial y pagar en línea.
✅ Diseño atractivo y con experiencia de usuario optimizada.
📌 6. Funcionalidades Esperadas en el Sistema
📌 Gestión de Proveedores y Clientes
✅ Registro, edición y eliminación de proveedores y clientes.
✅ Gestión de permisos y acceso según el rol de usuario.
📌 Gestión de Herramientas y Equipos
✅ CRUD de herramientas con estado y disponibilidad.
✅ Carga de imágenes de herramientas y descripción detallada.
📌 Gestión de Reservas y Alquileres
✅ Sistema de reservas con selección de fechas y confirmación automática.
✅ Validación de disponibilidad antes de confirmar una reserva.
✅ Registro de entregas y devoluciones de herramientas.
📌 Gestión de Pagos y Facturación
✅ Registro y validación de pagos realizados por los clientes.
✅ Generación automática de facturas y comprobantes de pago.
✅ Historial de pagos y vencimiento de reservas.
📌 Reportes y Estadísticas
✅ Reporte de ingresos por alquileres.
✅ Estadísticas de herramientas más alquiladas y clientes frecuentes.
✅ Reporte de disponibilidad y estado de herramientas.
📌 7. Pruebas del Sistema
📌 Pruebas de API con Postman:
✅ Peticiones POST, GET, PUT y DELETE correctamente implementadas.
✅ Pruebas de autenticación con JWT y protección de rutas.
✅ Simulación de flujos completos de alquiler y pagos.
📌 Despliegue en Apache Tomcat:
✅ Configuración del servidor embebido para ejecutar el backend.






Documentación de APIs mediante Swagger en Spring Boot
1. Introducción
Cuando se desarrolla una API REST, no es suficiente con implementar correctamente los endpoints. También es necesario que otros desarrolladores puedan comprender cómo utilizar el servicio.
Una API puede contener operaciones como:
GET    /api/products
GET    /api/products/{id}
POST   /api/products
PUT    /api/products/{id}
DELETE /api/products/{id}
Sin documentación adicional, un consumidor de la API tendría que descubrir:
* qué endpoints existen;
* qué parámetros recibe cada operación;
* qué información debe enviarse;
* qué estructura debe tener el JSON;
* qué códigos HTTP puede devolver;
* qué formato posee la respuesta;
* cuáles campos son obligatorios;
* qué autenticación requiere la API.
Para resolver este problema se utiliza el estándar OpenAPI, junto con herramientas como Swagger UI.
En proyectos Spring Boot, una de las bibliotecas más utilizadas para generar esta documentación automáticamente es:
springdoc-openapi
springdoc-openapi examina los controladores, configuraciones y anotaciones de una aplicación Spring Boot y genera automáticamente una especificación OpenAPI y una interfaz Swagger UI.
________________


2. Objetivos de aprendizaje
Al finalizar esta guía, el estudiante estará en capacidad de:
1. Comprender qué es OpenAPI.
2. Diferenciar OpenAPI, Swagger y Swagger UI.
3. Integrar Swagger UI en un proyecto Spring Boot.
4. Generar documentación automática de controladores REST.
5. Documentar endpoints.
6. Documentar parámetros.
7. Documentar cuerpos de petición.
8. Documentar códigos de respuesta HTTP.
9. Documentar modelos y DTO.
10. Personalizar la información general de una API.
11. Probar endpoints directamente desde Swagger UI.
12. Organizar profesionalmente la documentación de una API.
________________


3. ¿Qué es una API REST?
Una API REST permite que diferentes aplicaciones se comuniquen utilizando HTTP.
Por ejemplo:
Cliente web
   ↓
HTTP
   ↓
API Spring Boot
   ↓
Service
   ↓
Repository
   ↓
Base de datos
Una operación típica puede ser:
GET /api/products/10
Respuesta:
{
 "id": 10,
 "name": "Teclado mecánico",
 "price": 250000,
 "stock": 15
}
Para que un desarrollador pueda utilizar correctamente este endpoint necesita conocer su contrato.
________________


4. ¿Qué es OpenAPI?
OpenAPI Specification, generalmente abreviada como OAS, es una especificación utilizada para describir APIs HTTP.
Permite definir formalmente aspectos como:
Endpoints
Métodos HTTP
Parámetros
Request Body
Responses
Códigos HTTP
Modelos
Seguridad
Autenticación
Un documento OpenAPI puede expresarse utilizando:
JSON
o:
YAML
Por ejemplo, una descripción simplificada podría contener:
paths:
 /api/products:
   get:
     summary: Consultar productos
     responses:
       '200':
         description: Lista de productos
Sin embargo, cuando se utiliza Spring Boot con springdoc-openapi, gran parte de este documento se genera automáticamente.
________________


5. ¿Qué es Swagger?
El término Swagger se utiliza actualmente para referirse a un conjunto de herramientas relacionadas con OpenAPI.
Una de las más conocidas es:
Swagger UI
Swagger UI toma una especificación OpenAPI y genera una interfaz web interactiva.
La relación puede representarse así:
Spring Boot
   ↓
springdoc-openapi
   ↓
OpenAPI
   ↓
Swagger UI
   ↓
Documentación interactiva
________________


6. OpenAPI y Swagger no son exactamente lo mismo
Es importante diferenciar los conceptos.
OpenAPI
Es la especificación que describe una API.
Swagger UI
Es una herramienta que interpreta esa especificación y genera una interfaz gráfica.
Por tanto:
OpenAPI
   ↓
describe la API


Swagger UI
   ↓
muestra la documentación
________________


7. ¿Qué es springdoc-openapi?
springdoc-openapi es una biblioteca utilizada con Spring Boot para generar automáticamente documentación OpenAPI.
Analiza elementos como:
@RestController


@RequestMapping


@GetMapping


@PostMapping


@PutMapping


@DeleteMapping


@PathVariable


@RequestParam


@RequestBody
y construye automáticamente la documentación de la API.
Por ejemplo:
@GetMapping("/products")
public List<Product> findAll() {
   return service.findAll();
}
puede aparecer automáticamente en Swagger UI como:
GET /products
________________


8. Compatibilidad con Spring Boot
La versión debe elegirse de acuerdo con la versión de Spring Boot.
Actualmente, la documentación de springdoc-openapi indica que:
Spring Boot 3.x → springdoc-openapi 2.x
Spring Boot 4.x → springdoc-openapi 3.x
Por ejemplo, Spring Boot 4 puede utilizar la rama 3.x de springdoc-openapi.
Esto es importante porque utilizar una versión incompatible puede producir errores durante el arranque de la aplicación.
________________


9. Caso práctico
Se desarrollará la documentación de una API REST para administrar productos.
La aplicación contendrá:
Product
ProductController
ProductService
ProductRepository
Los endpoints serán:
GET    /api/products
GET    /api/products/{id}
POST   /api/products
PUT    /api/products/{id}
DELETE /api/products/{id}
________________


10. Agregar Swagger al proyecto
Para una aplicación Spring MVC que utiliza:
spring-boot-starter-web
se utiliza:
<dependency>
   <groupId>org.springdoc</groupId>
   <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
   <version>3.1.1</version>
</dependency>
Esta versión corresponde a la línea actual compatible con Spring Boot 4 según la documentación oficial de springdoc-openapi.
Si el proyecto utiliza Spring Boot 3, debe emplearse una versión compatible de la rama 2.x.
________________


11. ¿Qué ocurre al agregar la dependencia?
Con la dependencia instalada, springdoc-openapi analiza automáticamente los controladores Spring MVC.
No es obligatorio configurar manualmente Swagger para obtener una documentación básica.
Al iniciar la aplicación se generan recursos como:
/v3/api-docs
y Swagger UI puede accederse habitualmente mediante:
/swagger-ui.html
La especificación también puede obtenerse en YAML mediante:
/v3/api-docs.yaml
Estas rutas son proporcionadas automáticamente por springdoc-openapi.
Por ejemplo, si la aplicación se ejecuta en:
localhost:8080
se puede visitar:
http://localhost:8080/swagger-ui.html
________________


12. Primera prueba
Supóngase el siguiente controlador:
@RestController
@RequestMapping("/api/products")
public class ProductController {


   @GetMapping
   public List<String> findAll() {


       return List.of(
           "Mouse",
           "Teclado",
           "Monitor"
       );
   }
}
Al ejecutar la aplicación, Swagger detectará automáticamente:
GET /api/products
Esto demuestra una característica fundamental:
Swagger puede generar una documentación inicial sin agregar anotaciones específicas de documentación.
________________


13. Sin embargo, la documentación automática es limitada
Swagger puede identificar el endpoint:
GET /api/products
pero posiblemente muestre información genérica.
Por ejemplo:
GET /api/products


product-controller


findAll
Esto no proporciona suficiente contexto para un desarrollador.
Por esa razón se utilizan anotaciones OpenAPI.
________________


14. Anotaciones principales
Las anotaciones más utilizadas se encuentran principalmente en:
io.swagger.v3.oas.annotations
Entre ellas:
@Operation
@Tag
@Parameter
@ApiResponse
@ApiResponses
@RequestBody
@Schema
Cada una cumple una función diferente.
________________


15. Documentar el controlador con @Tag
La anotación:
@Tag
permite agrupar endpoints.
Ejemplo:
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/products")
@Tag(
   name = "Productos",
   description = "Operaciones para administrar productos"
)
public class ProductController {


}
Swagger mostrará una sección denominada:
Productos
en lugar de utilizar únicamente el nombre técnico:
product-controller
________________


16. Documentar una operación con @Operation
Supóngase:
@GetMapping
public List<Product> findAll() {
   return service.findAll();
}
Puede documentarse:
@Operation(
   summary = "Consultar productos",
   description = "Obtiene todos los productos registrados"
)
@GetMapping
public List<Product> findAll() {


   return service.findAll();
}
________________


17. Diferencia entre summary y description
summary debe contener una explicación corta.
Ejemplo:
summary = "Consultar productos"
Mientras que description puede proporcionar información más detallada:
description =
   "Obtiene todos los productos registrados en el sistema"
Swagger puede visualizar:
Consultar productos


Obtiene todos los productos registrados en el sistema
________________


18. Documentar parámetros con @Parameter
Supóngase:
@GetMapping("/{id}")
public Product findById(
       @PathVariable Long id) {


   return service.findById(id);
}
Puede documentarse así:
@GetMapping("/{id}")
@Operation(
   summary = "Consultar producto por ID"
)
public Product findById(


   @Parameter(
       description = "Identificador del producto",
       example = "10"
   )
   @PathVariable Long id
) {


   return service.findById(id);
}
Swagger mostrará información adicional sobre:
id
incluyendo un ejemplo.
________________


19. Documentar códigos HTTP
Una API profesional debe indicar qué respuestas puede generar.
Por ejemplo:
200 OK
404 Not Found
500 Internal Server Error
Puede utilizarse:
@ApiResponse
Ejemplo:
@ApiResponse(
   responseCode = "200",
   description = "Producto encontrado"
)
________________


20. Varias respuestas con @ApiResponses
Ejemplo:
@ApiResponses({


   @ApiResponse(
       responseCode = "200",
       description = "Producto encontrado"
   ),


   @ApiResponse(
       responseCode = "404",
       description = "Producto no encontrado"
   )


})
Aplicado a un endpoint:
@Operation(
   summary = "Consultar producto por ID"
)
@ApiResponses({


   @ApiResponse(
       responseCode = "200",
       description = "Producto encontrado"
   ),


   @ApiResponse(
       responseCode = "404",
       description = "Producto no encontrado"
   )


})
@GetMapping("/{id}")
public ResponseEntity<Product> findById(
       @PathVariable Long id) {


   return service.findById(id)
           .map(ResponseEntity::ok)
           .orElse(ResponseEntity.notFound().build());
}
________________


21. Documentar una entidad
Supóngase:
public class Product {


   private Long id;


   private String name;


   private Double price;


   private Integer stock;
}
Puede utilizarse:
@Schema
Ejemplo:
import io.swagger.v3.oas.annotations.media.Schema;


@Schema(
   description = "Representa un producto del catálogo"
)
public class Product {


}
________________


22. Documentar propiedades
Cada propiedad también puede documentarse.
@Schema(
   description = "Identificador único",
   example = "10"
)
private Long id;
Otro ejemplo:
@Schema(
   description = "Nombre comercial del producto",
   example = "Teclado mecánico"
)
private String name;
________________


23. Entidad completamente documentada
@Schema(
   description = "Producto disponible en el catálogo"
)
public class Product {


   @Schema(
       description = "Identificador del producto",
       example = "10"
   )
   private Long id;




   @Schema(
       description = "Nombre del producto",
       example = "Teclado mecánico"
   )
   private String name;




   @Schema(
       description = "Precio de venta",
       example = "250000"
   )
   private Double price;




   @Schema(
       description = "Cantidad disponible",
       example = "15"
   )
   private Integer stock;
}
Swagger puede utilizar esta información para generar el esquema del objeto.
________________


24. Documentar un POST
Supóngase:
@PostMapping
public Product save(
       @RequestBody Product product) {


   return service.save(product);
}
Puede documentarse así:
@Operation(
   summary = "Registrar producto",
   description =
       "Registra un nuevo producto en el catálogo"
)
@ApiResponses({


   @ApiResponse(
       responseCode = "201",
       description = "Producto creado correctamente"
   ),


   @ApiResponse(
       responseCode = "400",
       description = "Datos inválidos"
   )


})
@PostMapping
public ResponseEntity<Product> save(
       @RequestBody Product product) {


   Product saved = service.save(product);


   return ResponseEntity
           .status(HttpStatus.CREATED)
           .body(saved);
}
________________


25. Swagger genera ejemplos del JSON
A partir del modelo:
Product
Swagger puede mostrar una estructura como:
{
 "id": 10,
 "name": "Teclado mecánico",
 "price": 250000,
 "stock": 15
}
Esto facilita el consumo de la API.
________________


26. Probar un POST desde Swagger UI
Swagger UI permite ejecutar peticiones reales.
Para realizar una prueba se utiliza normalmente:
Try it out
Después se ingresa:
{
 "name": "Mouse inalámbrico",
 "price": 95000,
 "stock": 20
}
Finalmente:
Execute
Swagger enviará una petición:
POST /api/products
________________


27. Swagger no es solamente documentación
Swagger UI también puede utilizarse como cliente HTTP.
Permite probar operaciones como:
GET
POST
PUT
PATCH
DELETE
Por tanto, durante desarrollo puede cumplir parcialmente una función similar a herramientas como:
Postman
Insomnia
Bruno
aunque su objetivo principal sigue siendo visualizar e interactuar con la documentación OpenAPI.
________________


28. Documentar actualización
@Operation(
   summary = "Actualizar producto",
   description =
       "Actualiza la información de un producto existente"
)
@ApiResponses({


   @ApiResponse(
       responseCode = "200",
       description = "Producto actualizado"
   ),


   @ApiResponse(
       responseCode = "404",
       description = "Producto no encontrado"
   )


})
@PutMapping("/{id}")
public ResponseEntity<Product> update(


       @Parameter(
           description = "ID del producto",
           example = "5"
       )
       @PathVariable Long id,


       @RequestBody Product product) {


   // lógica


   return ResponseEntity.ok(product);
}
________________


29. Documentar eliminación
@Operation(
   summary = "Eliminar producto",
   description =
       "Elimina un producto utilizando su identificador"
)
@ApiResponses({


   @ApiResponse(
       responseCode = "204",
       description = "Producto eliminado"
   ),


   @ApiResponse(
       responseCode = "404",
       description = "Producto no encontrado"
   )


})
@DeleteMapping("/{id}")
public ResponseEntity<Void> delete(


       @Parameter(
           description = "Identificador del producto",
           example = "10"
       )
       @PathVariable Long id) {


   service.deleteById(id);


   return ResponseEntity.noContent().build();
}
________________


30. Documentar parámetros de consulta
Supóngase:
GET /api/products/search?name=mouse
Controlador:
@GetMapping("/search")
public List<Product> search(
       @RequestParam String name) {


   return service.search(name);
}
Puede documentarse:
@GetMapping("/search")
@Operation(
   summary = "Buscar productos por nombre"
)
public List<Product> search(


   @Parameter(
       description =
           "Texto que debe contener el nombre del producto",
       example = "mouse"
   )
   @RequestParam String name) {


   return service.search(name);
}
________________


31. Personalizar información general de la API
También puede configurarse información global.
Se crea:
config/OpenApiConfig.java
Ejemplo:
package com.example.store.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {


   @Bean
   public OpenAPI customOpenAPI() {


       return new OpenAPI()
           .info(
               new Info()
                   .title("Store API")
                   .version("1.0")
                   .description(
                       "API REST para administrar productos"
                   )
                   .contact(
                       new Contact()
                           .name("Equipo de desarrollo")
                   )
           );
   }
}
Swagger mostrará entonces:
Store API


API REST para administrar productos


Version 1.0
________________


32. Configuración mediante anotación @OpenAPIDefinition
También puede configurarse la información mediante:
@OpenAPIDefinition
Por ejemplo:
@Configuration
@OpenAPIDefinition(
   info = @Info(
       title = "Store API",
       version = "1.0",
       description =
           "API REST para gestionar productos"
   )
)
public class OpenApiConfig {


}
Ambas estrategias son válidas.
________________


33. Personalizar la ruta Swagger
Puede modificarse la URL mediante:
springdoc.swagger-ui.path=/docs
Entonces podría accederse mediante:
http://localhost:8080/docs
La ruta del documento OpenAPI también puede personalizarse con:
springdoc.api-docs.path=/api-docs
springdoc-openapi soporta estas propiedades de configuración.
________________


34. Ejemplo CRUD documentado
Un controlador completo puede quedar así:
@RestController
@RequestMapping("/api/products")
@Tag(
   name = "Productos",
   description =
       "Operaciones relacionadas con productos"
)
public class ProductController {


   private final ProductService service;


   public ProductController(
           ProductService service) {


       this.service = service;
   }




   @Operation(
       summary = "Consultar productos"
   )
   @ApiResponse(
       responseCode = "200",
       description = "Consulta realizada correctamente"
   )
   @GetMapping
   public List<Product> findAll() {


       return service.findAll();
   }




   @Operation(
       summary = "Consultar producto por ID"
   )
   @ApiResponses({


       @ApiResponse(
           responseCode = "200",
           description = "Producto encontrado"
       ),


       @ApiResponse(
           responseCode = "404",
           description = "Producto no encontrado"
       )
   })
   @GetMapping("/{id}")
   public ResponseEntity<Product> findById(


           @Parameter(
               description =
                   "Identificador del producto",
               example = "1"
           )
           @PathVariable Long id) {


       return service.findById(id)
           .map(ResponseEntity::ok)
           .orElse(
               ResponseEntity
                   .notFound()
                   .build()
           );
   }




   @Operation(
       summary = "Registrar producto"
   )
   @ApiResponses({


       @ApiResponse(
           responseCode = "201",
           description = "Producto creado"
       ),


       @ApiResponse(
           responseCode = "400",
           description = "Datos inválidos"
       )
   })
   @PostMapping
   public ResponseEntity<Product> save(
           @RequestBody Product product) {


       Product saved =
           service.save(product);


       return ResponseEntity
           .status(HttpStatus.CREATED)
           .body(saved);
   }




   @Operation(
       summary = "Eliminar producto"
   )
   @ApiResponses({


       @ApiResponse(
           responseCode = "204",
           description = "Producto eliminado"
       ),


       @ApiResponse(
           responseCode = "404",
           description = "Producto no encontrado"
       )
   })
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(
           @PathVariable Long id) {


       service.deleteById(id);


       return ResponseEntity
           .noContent()
           .build();
   }
}
________________


35. Flujo de generación de documentación
El funcionamiento puede representarse así:
@RestController
      │
      │
@GetMapping
@PostMapping
@Operation
@ApiResponse
@Schema
      │
      ▼
springdoc-openapi
      │
      ▼
OpenAPI
      │
      ├── JSON
      │
      └── YAML
      │
      ▼
Swagger UI
      │
      ▼
Documentación interactiva
________________


36. Documentación automática frente a documentación enriquecida
Sin anotaciones:
@GetMapping
public List<Product> findAll() {
   return service.findAll();
}
Swagger puede descubrir:
GET /api/products
Con anotaciones:
@Operation(
   summary = "Consultar catálogo de productos",
   description =
       "Obtiene todos los productos registrados"
)
se proporciona información semántica adicional.
Por ello, las anotaciones no crean necesariamente el endpoint en Swagger.
El endpoint ya existe debido a Spring MVC.
Las anotaciones enriquecen su documentación.
________________


37. Anotaciones más importantes
Anotación
	Propósito
	@Tag
	Agrupar endpoints
	@Operation
	Documentar una operación
	@Parameter
	Documentar parámetros
	@ApiResponse
	Documentar una respuesta
	@ApiResponses
	Documentar varias respuestas
	@Schema
	Documentar modelos
	@OpenAPIDefinition
	Definir información global
	@SecurityRequirement
	Documentar requisitos de seguridad
	________________


38. Documentar DTO en lugar de entidades
En aplicaciones profesionales suele ser recomendable no exponer directamente entidades JPA.
En lugar de:
Product
puede utilizarse:
ProductRequest
y:
ProductResponse
Ejemplo:
public record ProductRequest(


   @Schema(
       example = "Teclado mecánico"
   )
   String name,


   @Schema(
       example = "250000"
   )
   Double price,


   @Schema(
       example = "10"
   )
   Integer stock


) {
}
________________


39. Endpoint utilizando DTO
@PostMapping
public ResponseEntity<ProductResponse> save(
       @RequestBody ProductRequest request) {


   ProductResponse response =
       service.save(request);


   return ResponseEntity
       .status(HttpStatus.CREATED)
       .body(response);
}
Swagger reconocerá automáticamente:
ProductRequest
como entrada y:
ProductResponse
como salida.
________________


40. Swagger y Bean Validation
Supóngase:
public record ProductRequest(


   @NotBlank
   String name,


   @Positive
   Double price,


   @PositiveOrZero
   Integer stock


) {
}
springdoc-openapi tiene soporte para anotaciones de validación como @NotNull, @Min, @Max y @Size, lo que permite enriquecer automáticamente el esquema generado.
Esto significa que documentación y validación pueden complementarse.
________________


41. Ejemplo práctico con validación
public record ProductRequest(


   @Schema(
       description = "Nombre del producto",
       example = "Monitor 27 pulgadas"
   )
   @NotBlank
   String name,


   @Schema(
       description = "Precio del producto",
       example = "1200000"
   )
   @Positive
   Double price,


   @Schema(
       description = "Unidades disponibles",
       example = "8"
   )
   @PositiveOrZero
   Integer stock


) {
}
________________


42. Separar documentación y lógica
Una mala práctica sería insertar grandes cantidades de documentación dentro de métodos complejos.
El controlador debería continuar siendo legible.
Por ejemplo:
@Operation(
   summary = "Consultar productos activos"
)
@GetMapping("/active")
public List<ProductResponse> findActive() {


   return service.findActive();
}
La documentación complementa el código.
No debe dificultar su comprensión.
________________


43. Organización por etiquetas
Una aplicación puede tener:
Products
Customers
Orders
Payments
Users
Authentication
Cada controlador puede utilizar un @Tag.
Ejemplo:
@Tag(name = "Productos")
@Tag(name = "Clientes")
@Tag(name = "Pedidos")
Swagger organizará visualmente los endpoints.
________________


44. Ventajas de documentar APIs
Una API correctamente documentada:
* facilita el trabajo del frontend;
* reduce errores de integración;
* facilita pruebas;
* mejora el mantenimiento;
* facilita incorporación de nuevos desarrolladores;
* define claramente contratos;
* reduce reuniones y consultas técnicas;
* permite generar clientes automáticamente;
* facilita integraciones externas.
________________


45. Swagger dentro del ciclo de desarrollo
Puede utilizarse desde etapas tempranas.
Diseño
  ↓
Definición de endpoints
  ↓
Implementación
  ↓
Documentación OpenAPI
  ↓
Pruebas con Swagger UI
  ↓
Integración frontend
La documentación debe mantenerse sincronizada con la implementación.
________________


46. Errores frecuentes
Error 1. Utilizar una dependencia antigua
En proyectos modernos no debería utilizarse la antigua biblioteca:
springfox-swagger
como punto de partida para proyectos nuevos.
En Spring Boot moderno es habitual utilizar:
springdoc-openapi
________________


Error 2. Utilizar una versión incompatible
Debe verificarse la compatibilidad entre:
Spring Boot
y:
springdoc-openapi
Por ejemplo:
Boot 3 → springdoc 2.x
Boot 4 → springdoc 3.x
________________


Error 3. Confundir Swagger con OpenAPI
OpenAPI define la especificación.
Swagger UI visualiza esa especificación.
________________


Error 4. Documentar respuestas que el código no devuelve
No debe indicarse:
404 Not Found
si realmente el controlador termina devolviendo:
500 Internal Server Error
La documentación debe reflejar el comportamiento real.
________________


Error 5. Escribir descripciones demasiado genéricas
Evitar:
summary = "Obtiene datos"
Preferir:
summary =
   "Obtiene todos los productos activos"
________________


47. Actividad práctica guiada
Se debe construir una API de estudiantes.
Entidad:
Student
Atributos:
id
name
email
age
active
Endpoints:
GET    /api/students
GET    /api/students/{id}
POST   /api/students
PUT    /api/students/{id}
DELETE /api/students/{id}
________________


48. Paso 1: crear el controlador
@RestController
@RequestMapping("/api/students")
public class StudentController {


}
________________


49. Paso 2: agregar etiqueta
@Tag(
   name = "Estudiantes",
   description =
       "Administración de estudiantes"
)
________________


50. Paso 3: documentar consulta general
Debe implementarse:
@Operation(
   summary = "Consultar estudiantes"
)
@GetMapping
Debe documentarse la respuesta:
200
________________


51. Paso 4: documentar búsqueda por ID
Endpoint:
GET /api/students/{id}
Debe documentarse:
200
404
Además debe utilizarse:
@Parameter
sobre el ID.
________________


52. Paso 5: documentar creación
Endpoint:
POST /api/students
Debe documentarse:
201 Created
400 Bad Request
________________


53. Paso 6: documentar actualización
Endpoint:
PUT /api/students/{id}
Debe documentarse:
200
400
404
________________


54. Paso 7: documentar eliminación
Endpoint:
DELETE /api/students/{id}
Debe documentarse:
204
404
________________


55. Taller práctico
Sistema de biblioteca
Se deberá desarrollar y documentar una API para administrar libros.
Modelo:
Book
Atributos:
id
title
author
isbn
price
available
Endpoints mínimos:
GET    /api/books
GET    /api/books/{id}
POST   /api/books
PUT    /api/books/{id}
DELETE /api/books/{id}
GET    /api/books/search?title=
GET    /api/books/available
Cada endpoint deberá contener:
@Operation
@ApiResponse o @ApiResponses
@Parameter cuando corresponda
El modelo deberá contener:
@Schema
________________


56. Requisitos del taller
La documentación deberá mostrar:
1. Nombre de la API.
2. Versión.
3. Descripción.
4. Agrupación mediante @Tag.
5. Descripción de cada endpoint.
6. Parámetros documentados.
7. Ejemplos de valores.
8. Códigos HTTP.
9. Modelos de entrada.
10. Modelos de salida.
________________


57. Reto adicional
Crear:
BookRequest
BookResponse
en lugar de exponer directamente:
Book
Agregar validaciones:
@NotBlank
@Positive
@Size
y observar cómo cambia el esquema mostrado por Swagger.
________________


58. Preguntas de comprobación
1. ¿Qué problema resuelve OpenAPI?
2. ¿Cuál es la diferencia entre Swagger y OpenAPI?
3. ¿Qué función cumple Swagger UI?
4. ¿Qué hace springdoc-openapi?
5. ¿Qué función tiene @Operation?
6. ¿Qué función tiene @Tag?
7. ¿Para qué se utiliza @Parameter?
8. ¿Cuál es la función de @ApiResponse?
9. ¿Qué diferencia existe entre @ApiResponse y @ApiResponses?
10. ¿Para qué se utiliza @Schema?
11. ¿Qué URL permite obtener normalmente la especificación OpenAPI?
12. ¿Qué significa el endpoint /v3/api-docs?
13. ¿Swagger UI permite ejecutar peticiones HTTP?
14. ¿Por qué deben documentarse los códigos HTTP?
15. ¿Por qué es conveniente documentar DTO en lugar de entidades JPA?
________________


59. Resumen conceptual
La arquitectura puede entenderse así:
Spring Boot
     │
     ▼
@RestController
     │
     ▼
@GetMapping
@PostMapping
@PutMapping
@DeleteMapping
     │
     ▼
springdoc-openapi
     │
     ▼
OpenAPI Specification
     │
     ▼
Swagger UI
     │
     ▼
Documentación interactiva
Las anotaciones permiten enriquecer esta documentación:
@Tag
     ↓
agrupa operaciones


@Operation
     ↓
describe endpoints


@Parameter
     ↓
describe parámetros


@ApiResponse
     ↓
describe respuestas


@Schema
     ↓
describe modelos
________________


60. Conclusión
Swagger/OpenAPI permite convertir una API REST desarrollada con Spring Boot en un servicio mucho más fácil de comprender, probar e integrar.
El principio fundamental puede resumirse de la siguiente manera:
Spring MVC
define los endpoints


springdoc-openapi
analiza los endpoints


OpenAPI
describe el contrato


Swagger UI
presenta y permite probar el contrato
Una documentación profesional no debe limitarse a mostrar las rutas disponibles. También debe comunicar claramente:
qué hace cada operación,
qué información recibe,
qué información devuelve,
qué errores pueden producirse
y cómo debe consumirla otro sistema.
Por esta razón, en proyectos reales Swagger/OpenAPI debe considerarse parte del diseño de la API y no simplemente una herramienta adicional al final del desarrollo.


Guía teórico-práctica
Gestión de Bases de Datos mediante JPA en Spring Boot
1. Introducción
En una aplicación empresarial, la gestión de información constituye uno de los componentes más importantes del sistema. Las aplicaciones requieren almacenar, consultar, modificar y eliminar datos de manera persistente.
En aplicaciones Java tradicionales, estas operaciones pueden realizarse directamente mediante JDBC. Sin embargo, este enfoque exige escribir manualmente sentencias SQL, administrar conexiones, procesar resultados y controlar excepciones relacionadas con la base de datos.
Spring Boot proporciona una alternativa de mayor nivel mediante Spring Data JPA, que permite trabajar con bases de datos relacionales utilizando objetos Java.
JPA permite establecer una correspondencia entre:
Objetos Java
    ⇅
Tablas relacionales
Este mecanismo se denomina:
ORM
Object Relational Mapping
Mediante ORM, una clase Java puede representar una tabla y sus atributos pueden representar columnas.
Por ejemplo:
public class Product {
​
   private Long id;
   private String name;
   private Double price;
}
puede representar:
products
--------------------------------
id       BIGINT
name     VARCHAR
price    NUMERIC
La aplicación puede manipular objetos Product mientras Hibernate se encarga de generar y ejecutar las instrucciones SQL necesarias.
________________


2. Objetivos de aprendizaje
Al finalizar esta guía, el estudiante estará en capacidad de:
1. Comprender el funcionamiento de JPA dentro de una aplicación Spring Boot.
2. Diferenciar JPA, Hibernate y Spring Data JPA.
3. Configurar una conexión entre Spring Boot y una base de datos relacional.
4. Crear entidades persistentes.
5. Administrar operaciones CRUD mediante repositorios.
6. Crear consultas derivadas.
7. Implementar consultas mediante JPQL.
8. Utilizar consultas SQL nativas cuando sea necesario.
9. Implementar paginación y ordenamiento.
10. Gestionar transacciones.
11. Implementar relaciones entre entidades.
12. Aplicar una arquitectura organizada para el acceso a datos.
________________


3. JPA, Hibernate y Spring Data JPA
Uno de los primeros aspectos que debe comprenderse es que estos tres conceptos no representan lo mismo.
3.1 JPA
JPA significa:
Java Persistence API
Actualmente las APIs se encuentran bajo el espacio de nombres:
jakarta.persistence
JPA es una especificación.
Define reglas, interfaces y anotaciones para gestionar persistencia en aplicaciones Java.
Entre sus anotaciones se encuentran:
@Entity
@Table
@Id
@GeneratedValue
@Column
@OneToMany
@ManyToOne
@OneToOne
@ManyToMany
JPA por sí sola no ejecuta SQL.
________________


3.2 Hibernate
Hibernate es una implementación de JPA.
Es responsable de realizar tareas como:
* transformar entidades en registros;
* generar SQL;
* ejecutar consultas;
* detectar cambios en las entidades;
* gestionar relaciones;
* mantener el contexto de persistencia.
La relación puede representarse así:
JPA
Especificación
     ↓
Hibernate
Implementación
________________


3.3 Spring Data JPA
Spring Data JPA simplifica el acceso a datos proporcionando repositorios.
Por ejemplo:
public interface ProductRepository
       extends JpaRepository<Product, Long> {
}
automáticamente proporciona métodos como:
save()
findAll()
findById()
delete()
deleteById()
count()
existsById()
La arquitectura completa puede representarse así:
Aplicación
   ↓
Spring Data JPA
   ↓
JPA
   ↓
Hibernate
   ↓
JDBC
   ↓
Base de datos
________________


4. Caso práctico
Durante la guía se desarrollará un pequeño sistema de gestión de inventario.
El sistema administrará:
Categorías
Productos
Una categoría podrá contener varios productos.
Ejemplo:
Categoría: Computadores


   ├── Portátil ASUS
   ├── MacBook Air
   └── Lenovo ThinkPad
El modelo permitirá estudiar:
* entidades;
* claves primarias;
* relaciones;
* operaciones CRUD;
* consultas;
* paginación;
* transacciones.
________________


5. Creación del proyecto
Puede crearse un proyecto desde Spring Initializr utilizando:
Spring Web
Spring Data JPA
PostgreSQL Driver
Validation
Una estructura inicial podría ser:
src/main/java
└── com.example.inventory
   ├── InventoryApplication.java
   ├── controller
   ├── service
   ├── repository
   └── entity
________________


6. Dependencias Maven
El archivo pom.xml deberá incluir, entre otras:
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-web</artifactId>
</dependency>


<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>


<dependency>
   <groupId>org.postgresql</groupId>
   <artifactId>postgresql</artifactId>
   <scope>runtime</scope>
</dependency>


<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
La dependencia principal es:
spring-boot-starter-data-jpa
________________


7. Crear la base de datos
En PostgreSQL:
CREATE DATABASE inventory_db;
La base de datos debe existir antes de ejecutar la aplicación.
Hibernate puede crear y modificar tablas, pero normalmente no crea la base de datos completa.
________________


8. Configuración de la conexión
Archivo:
src/main/resources/application.properties
Ejemplo:
spring.datasource.url=jdbc:postgresql://localhost:5432/inventory_db
spring.datasource.username=postgres
spring.datasource.password=123456


spring.jpa.hibernate.ddl-auto=update


spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
La propiedad:
spring.jpa.hibernate.ddl-auto=update
indica que Hibernate debe adaptar las tablas existentes a las entidades.
Durante prácticas académicas resulta útil.
En producción es preferible utilizar herramientas de migración como:
Flyway
Liquibase
________________


9. Primera entidad: Category
Se crea:
entity/Category.java
package com.example.inventory.entity;


import jakarta.persistence.*;


@Entity
@Table(name = "categories")
public class Category {


   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;


   @Column(
       nullable = false,
       unique = true,
       length = 100
   )
   private String name;


   @Column(length = 300)
   private String description;


   public Category() {
   }


   public Category(String name, String description) {
       this.name = name;
       this.description = description;
   }


   public Long getId() {
       return id;
   }


   public String getName() {
       return name;
   }


   public void setName(String name) {
       this.name = name;
   }


   public String getDescription() {
       return description;
   }


   public void setDescription(String description) {
       this.description = description;
   }
}
________________


10. Anatomía de una entidad
@Entity
@Entity
indica que la clase será gestionada por JPA.
________________


@Table
@Table(name = "categories")
permite definir explícitamente la tabla asociada.
________________


@Id
@Id
define la clave primaria.
Toda entidad JPA debe contar con un identificador.
________________


@GeneratedValue
@GeneratedValue(strategy = GenerationType.IDENTITY)
indica que la base de datos genera automáticamente el identificador.
________________


@Column
Permite configurar características de la columna.
Ejemplo:
@Column(
   nullable = false,
   unique = true,
   length = 100
)
private String name;
Conceptualmente:
name VARCHAR(100)
NOT NULL
UNIQUE
________________


11. Crear la entidad Product
package com.example.inventory.entity;


import jakarta.persistence.*;


import java.math.BigDecimal;


@Entity
@Table(name = "products")
public class Product {


   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;


   @Column(nullable = false, length = 150)
   private String name;


   @Column(nullable = false)
   private BigDecimal price;


   @Column(nullable = false)
   private Integer stock;


   private Boolean active;


   public Product() {
   }


   public Long getId() {
       return id;
   }


   public String getName() {
       return name;
   }


   public void setName(String name) {
       this.name = name;
   }


   public BigDecimal getPrice() {
       return price;
   }


   public void setPrice(BigDecimal price) {
       this.price = price;
   }


   public Integer getStock() {
       return stock;
   }


   public void setStock(Integer stock) {
       this.stock = stock;
   }


   public Boolean getActive() {
       return active;
   }


   public void setActive(Boolean active) {
       this.active = active;
   }
}
________________


12. Uso de BigDecimal para valores monetarios
En aplicaciones empresariales es preferible representar dinero utilizando:
BigDecimal
en lugar de:
double
Double
Esto evita problemas de precisión asociados con números de punto flotante.
Ejemplo:
private BigDecimal price;
________________


13. Creación de repositorios
Para Product:
package com.example.inventory.repository;


import com.example.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository
       extends JpaRepository<Product, Long> {
}
Para Category:
public interface CategoryRepository
       extends JpaRepository<Category, Long> {
}
________________


14. Operaciones CRUD
CRUD significa:
Create
Read
Update
Delete
Spring Data JPA proporciona estas operaciones automáticamente.
________________


15. Crear información
Se utiliza:
repository.save(entity);
Ejemplo:
Product product = new Product();


product.setName("Monitor 27 pulgadas");
product.setPrice(new BigDecimal("1200000"));
product.setStock(10);
product.setActive(true);


productRepository.save(product);
Hibernate genera aproximadamente:
INSERT INTO products
(name, price, stock, active)
VALUES (?, ?, ?, ?);
________________


16. Consultar todos los registros
List<Product> products =
       productRepository.findAll();
SQL conceptual:
SELECT *
FROM products;
________________


17. Consultar por identificador
Optional<Product> product =
       productRepository.findById(1L);
Optional permite representar:
Producto encontrado
o:
Producto inexistente
Ejemplo:
Product product = productRepository
       .findById(id)
       .orElseThrow(
           () -> new RuntimeException(
               "Producto no encontrado"
           )
       );
________________


18. Actualizar información
Primero se consulta el registro:
Product product = productRepository
       .findById(id)
       .orElseThrow();
Después se modifican sus propiedades:
product.setPrice(
   new BigDecimal("1500000")
);


product.setStock(20);
Finalmente:
productRepository.save(product);
Hibernate genera aproximadamente:
UPDATE products
SET price = ?,
   stock = ?
WHERE id = ?;
________________


19. Eliminar información
Por identificador:
productRepository.deleteById(id);
Conceptualmente:
DELETE
FROM products
WHERE id = ?;
También puede eliminarse una entidad:
productRepository.delete(product);
________________


20. Consultas derivadas
Spring Data JPA puede generar consultas a partir del nombre de un método.
Ejemplo:
List<Product> findByActive(Boolean active);
Spring interpreta:
find
By
Active
aproximadamente como:
SELECT *
FROM products
WHERE active = ?;
________________


21. Buscar por nombre
List<Product> findByName(String name);
________________


22. Buscar texto contenido
List<Product>
findByNameContainingIgnoreCase(String text);
Ejemplo:
findByNameContainingIgnoreCase("monitor");
Puede producir aproximadamente:
WHERE LOWER(name)
LIKE LOWER('%monitor%')
________________


23. Comparaciones
Mayor que:
List<Product>
findByPriceGreaterThan(BigDecimal price);
Menor que:
List<Product>
findByPriceLessThan(BigDecimal price);
Entre dos valores:
List<Product>
findByPriceBetween(
   BigDecimal min,
   BigDecimal max
);
________________


24. Consultas compuestas
List<Product>
findByActiveAndStockGreaterThan(
   Boolean active,
   Integer stock
);
Conceptualmente:
SELECT *
FROM products
WHERE active = true
AND stock > 0;
________________


25. Ordenamiento mediante nombres de métodos
List<Product>
findByActiveOrderByNameAsc(Boolean active);
Conceptualmente:
SELECT *
FROM products
WHERE active = ?
ORDER BY name ASC;
________________


26. Palabras clave utilizadas por Spring Data JPA
Algunas expresiones frecuentes son:
Expresión
	Función
	findBy
	Buscar
	And
	Y
	Or
	O
	GreaterThan
	Mayor que
	LessThan
	Menor que
	Between
	Entre
	Containing
	Contiene
	StartingWith
	Comienza con
	EndingWith
	Finaliza con
	IgnoreCase
	Ignora mayúsculas
	OrderBy
	Ordenamiento
	True
	Verdadero
	False
	Falso
	IsNull
	Es nulo
	IsNotNull
	No es nulo
	________________


27. Consultas JPQL
Cuando las consultas derivadas resultan demasiado extensas, puede utilizarse JPQL.
JPQL significa:
Java Persistence Query Language
JPQL trabaja principalmente con:
Entidades
Atributos
y no directamente con tablas.
Ejemplo:
@Query("""
   SELECT p
   FROM Product p
   WHERE p.active = true
""")
List<Product> findActiveProducts();
Obsérvese:
Product
en lugar de:
products
porque JPQL utiliza el nombre de la entidad.
________________


28. Consulta JPQL con parámetros
@Query("""
   SELECT p
   FROM Product p
   WHERE p.price > :price
""")
List<Product> findExpensiveProducts(
   @Param("price") BigDecimal price
);
Uso:
repository.findExpensiveProducts(
   new BigDecimal("1000000")
);
________________


29. Consulta JPQL con múltiples condiciones
@Query("""
   SELECT p
   FROM Product p
   WHERE p.active = :active
     AND p.stock > :stock
""")
List<Product> searchAvailableProducts(
   @Param("active") Boolean active,
   @Param("stock") Integer stock
);
________________


30. SQL nativo
En determinadas situaciones puede utilizarse SQL directamente.
@Query(
   value = """
       SELECT *
       FROM products
       WHERE stock > 0
   """,
   nativeQuery = true
)
List<Product> findAvailableNative();
En este caso sí se utilizan:
nombre real de tabla
nombre real de columnas
________________


31. JPQL vs SQL nativo
JPQL
Trabaja con:
entidades
atributos
Ejemplo:
SELECT p
FROM Product p
Ventajas:
* más independiente del motor;
* integrado con JPA;
* orientado a objetos.
________________


SQL nativo
Trabaja con:
tablas
columnas
Ejemplo:
SELECT *
FROM products;
Se utiliza cuando se necesitan:
* consultas específicas del motor;
* funciones avanzadas;
* optimizaciones particulares;
* consultas heredadas.
________________


32. Implementar una relación entre entidades
Un producto pertenecerá a una categoría.
La relación será:
Category
   1
   │
   │
   N
Product
Es decir:
Una categoría
puede contener
muchos productos
________________


33. Relación @ManyToOne
En Product:
@ManyToOne
@JoinColumn(
   name = "category_id",
   nullable = false
)
private Category category;
La entidad queda conceptualmente:
@Entity
public class Product {


   @Id
   private Long id;


   private String name;


   private BigDecimal price;


   @ManyToOne
   @JoinColumn(name = "category_id")
   private Category category;
}
Esto crea una columna:
category_id
en:
products
________________


34. Relación inversa @OneToMany
En Category:
@OneToMany(mappedBy = "category")
private List<Product> products;
El modelo queda:
Category
-------------------
id
name


       1
       │
       │
       N


Product
-------------------
id
name
price
category_id
________________


35. mappedBy
La expresión:
mappedBy = "category"
indica que la propiedad:
category
de Product es responsable de mantener la relación.
Por tanto, la clave foránea se encuentra en:
products.category_id
________________


36. Consulta mediante relaciones
Puede crearse:
List<Product>
findByCategoryId(Long categoryId);
Spring Data interpreta la navegación:
Product
↓
category
↓
id
Conceptualmente:
SELECT *
FROM products
WHERE category_id = ?;
________________


37. Buscar por nombre de categoría
También:
List<Product>
findByCategoryName(String name);
Spring Data puede navegar:
Product.category.name
________________


38. Capa de servicio
Es conveniente evitar colocar la lógica de negocio directamente en el controlador.
Se crea:
@Service
public class ProductService {


   private final ProductRepository repository;


   public ProductService(
       ProductRepository repository
   ) {
       this.repository = repository;
   }


   public List<Product> findAll() {
       return repository.findAll();
   }


   public Product findById(Long id) {


       return repository
           .findById(id)
           .orElseThrow(
               () -> new RuntimeException(
                   "Producto no encontrado"
               )
           );
   }


   public Product save(Product product) {
       return repository.save(product);
   }


   public void delete(Long id) {
       repository.deleteById(id);
   }
}
________________


39. Arquitectura recomendada
Controller
   ↓
Service
   ↓
Repository
   ↓
Spring Data JPA
   ↓
Hibernate
   ↓
Base de datos
Responsabilidades:
Controller
Gestiona solicitudes HTTP.
Service
Contiene lógica de negocio.
Repository
Administra persistencia.
Entity
Representa información persistente.
________________


40. Controlador REST
@RestController
@RequestMapping("/api/products")
public class ProductController {


   private final ProductService service;


   public ProductController(
       ProductService service
   ) {
       this.service = service;
   }


   @GetMapping
   public List<Product> findAll() {
       return service.findAll();
   }


   @GetMapping("/{id}")
   public Product findById(
       @PathVariable Long id
   ) {
       return service.findById(id);
   }


   @PostMapping
   public Product save(
       @RequestBody Product product
   ) {
       return service.save(product);
   }


   @DeleteMapping("/{id}")
   public void delete(
       @PathVariable Long id
   ) {
       service.delete(id);
   }
}
________________


41. Paginación
Cuando una tabla contiene miles o millones de registros no debe retornarse toda la información mediante:
findAll()
Para esto se utiliza paginación.
Spring Data proporciona:
Pageable
Page
Ejemplo:
Page<Product> findByActive(
   Boolean active,
   Pageable pageable
);
________________


42. Crear un Pageable
Pageable pageable =
   PageRequest.of(
       0,
       10
   );
Esto significa:
Página: 0
Registros por página: 10
Consulta:
Page<Product> page =
   repository.findAll(pageable);
________________


43. Controlador paginado
@GetMapping("/page")
public Page<Product> findPage(
   @RequestParam(defaultValue = "0")
   int page,


   @RequestParam(defaultValue = "10")
   int size
) {


   Pageable pageable =
       PageRequest.of(page, size);


   return repository.findAll(pageable);
}
Solicitud:
GET /api/products/page?page=0&size=10
________________


44. Ordenamiento
Puede incorporarse:
PageRequest.of(
   page,
   size,
   Sort.by("name").ascending()
);
Ejemplo:
Pageable pageable =
   PageRequest.of(
       0,
       10,
       Sort.by("price")
           .descending()
   );
________________


45. Métodos de conteo
JpaRepository ofrece:
count()
Ejemplo:
long total =
   repository.count();
También pueden crearse:
long countByActive(Boolean active);
Uso:
long activeProducts =
   repository.countByActive(true);
________________


46. Comprobar existencia
Spring Data proporciona:
existsById()
Ejemplo:
boolean exists =
   repository.existsById(10L);
También puede crearse:
boolean existsByName(String name);
Esto resulta útil para validaciones de negocio.
________________


47. Eliminaciones derivadas
También pueden definirse operaciones como:
void deleteByActive(Boolean active);
Sin embargo, estas operaciones deben utilizarse con precaución porque pueden afectar múltiples registros.
________________


48. Transacciones
Una transacción agrupa varias operaciones como una sola unidad lógica.
Ejemplo:
Operación 1
Operación 2
Operación 3
Si todas funcionan:
COMMIT
Si alguna falla:
ROLLBACK
________________


49. @Transactional
Spring permite controlar transacciones mediante:
@Transactional
Ejemplo:
@Transactional
public void decreaseStock(
   Long productId,
   Integer quantity
) {


   Product product =
       repository
           .findById(productId)
           .orElseThrow();


   if (product.getStock() < quantity) {
       throw new RuntimeException(
           "Stock insuficiente"
       );
   }


   product.setStock(
       product.getStock() - quantity
   );
}
La operación ocurre dentro de una transacción.
________________


50. Dirty Checking
Un concepto importante de Hibernate es:
Dirty Checking
Cuando una entidad está administrada dentro de un contexto de persistencia, Hibernate puede detectar modificaciones automáticamente.
Ejemplo:
@Transactional
public void updatePrice(
   Long id,
   BigDecimal price
) {


   Product product =
       repository
           .findById(id)
           .orElseThrow();


   product.setPrice(price);
}
En este escenario puede no ser obligatorio ejecutar:
repository.save(product);
porque Hibernate detectará que el objeto administrado cambió.
Al finalizar la transacción, puede generar:
UPDATE products
SET price = ?
WHERE id = ?;
________________


51. Estados de una entidad
Una entidad JPA puede encontrarse en diferentes estados.
Transient
Objeto creado pero todavía no persistido.
Product product = new Product();
________________


Managed
Objeto administrado por el contexto de persistencia.
Ejemplo:
Product product =
   repository.findById(1L)
       .orElseThrow();
________________


Detached
La entidad fue administrada pero dejó de pertenecer al contexto.
________________


Removed
La entidad ha sido marcada para eliminación.
________________


52. Contexto de persistencia
El contexto de persistencia administra las entidades cargadas durante una transacción.
Conceptualmente:
Base de datos
     ↑
     │
Hibernate
     ↑
     │
Persistence Context
     ↑
     │
Entidades Java
Esto permite funcionalidades como:
Dirty Checking
Identity Map
Caching de primer nivel
________________


53. Validaciones
Una entidad puede incorporar validaciones:
@NotBlank
@Column(nullable = false)
private String name;
Para precio:
@Positive
private BigDecimal price;
Para stock:
@PositiveOrZero
private Integer stock;
________________


54. Validación en el controlador
@PostMapping
public Product save(
   @Valid
   @RequestBody Product product
) {


   return service.save(product);
}
La anotación:
@Valid
solicita que Spring ejecute las validaciones antes de continuar.
________________


55. Actividad práctica 1
Crear una entidad:
Supplier
con:
id
name
email
phone
active
Implementar:
POST    /api/suppliers
GET     /api/suppliers
GET     /api/suppliers/{id}
PUT     /api/suppliers/{id}
DELETE  /api/suppliers/{id}
________________


56. Actividad práctica 2
Crear las siguientes consultas:
findByActive(...)
findByNameContainingIgnoreCase(...)
findByEmail(...)
existsByEmail(...)
El estudiante deberá explicar el SQL aproximado generado por cada una.
________________


57. Actividad práctica 3
Agregar a Product:
minimumStock
Crear una consulta que retorne los productos donde:
stock < minimumStock
Como este tipo de comparación no resulta tan cómoda mediante consultas derivadas, puede utilizarse JPQL:
@Query("""
   SELECT p
   FROM Product p
   WHERE p.stock < p.minimumStock
""")
List<Product> findProductsBelowMinimumStock();
________________


58. Actividad práctica 4
Crear una consulta que permita buscar productos:
activos
con stock mayor que cero
por categoría
ordenados por precio
Una posible consulta JPQL:
@Query("""
   SELECT p
   FROM Product p
   WHERE p.active = true
     AND p.stock > 0
     AND p.category.id = :categoryId
   ORDER BY p.price ASC
""")
List<Product> findAvailableProductsByCategory(
   @Param("categoryId")
   Long categoryId
);
________________


59. Actividad práctica 5: paginación
Crear:
GET /api/products/search
que permita recibir:
page
size
Ejemplo:
/api/products/search?page=0&size=5
La respuesta deberá contener únicamente cinco productos.
________________


60. Taller práctico integrador
Sistema de inventario
Construir un módulo de inventario utilizando:
Category
Product
Supplier
Cada producto deberá contener:
id
name
description
price
stock
minimumStock
active
category
supplier
Se deberán implementar:
1. Registro de categorías.
2. Registro de proveedores.
3. Registro de productos.
4. Consulta de productos.
5. Consulta por identificador.
6. Actualización de productos.
7. Eliminación de productos.
8. Consulta por categoría.
9. Consulta por proveedor.
10. Búsqueda por nombre.
11. Consulta de productos activos.
12. Consulta de productos sin stock.
13. Consulta de productos bajo stock mínimo.
14. Consulta por rango de precios.
15. Paginación.
16. Ordenamiento.
________________


61. Retos adicionales
Reto 1
Crear un método que obtenga productos cuyo precio se encuentre entre dos valores:
findByPriceBetween(...)
________________


Reto 2
Buscar productos pertenecientes a una determinada categoría.
findByCategoryId(...)
________________


Reto 3
Buscar productos cuyo nombre contenga un texto y estén activos.
findByNameContainingIgnoreCaseAndActive(...)
________________


Reto 4
Contar productos de una categoría.
long countByCategoryId(Long categoryId);
________________


Reto 5
Comprobar si existe un producto con determinado nombre.
boolean existsByNameIgnoreCase(
   String name
);
________________


62. Preguntas de análisis
1. ¿Qué diferencia existe entre JPA e Hibernate?
2. ¿Qué función cumple Spring Data JPA?
3. ¿Qué representa una entidad?
4. ¿Para qué se utiliza @Id?
5. ¿Qué función tiene @GeneratedValue?
6. ¿Cuál es la responsabilidad de JpaRepository?
7. ¿Qué diferencia existe entre findAll() y findById()?
8. ¿Por qué findById() retorna Optional?
9. ¿Qué diferencia existe entre JPQL y SQL?
10. ¿Cuándo puede ser conveniente utilizar SQL nativo?
11. ¿Qué significa @ManyToOne?
12. ¿Qué función cumple @JoinColumn?
13. ¿Qué significa mappedBy?
14. ¿Por qué es importante la paginación?
15. ¿Qué diferencia existe entre Page y List?
16. ¿Qué función cumple @Transactional?
17. ¿Qué significa rollback?
18. ¿Qué es Dirty Checking?
19. ¿Qué significa que una entidad esté en estado Managed?
20. ¿Por qué conviene utilizar BigDecimal para representar dinero?
________________


63. Errores frecuentes
Olvidar @Entity
La clase no será reconocida como entidad persistente.
________________


Olvidar @Id
Toda entidad JPA necesita una clave primaria.
________________


Utilizar incorrectamente ddl-auto=create
spring.jpa.hibernate.ddl-auto=create
puede eliminar y recrear tablas durante cada inicio.
________________


Confundir JPQL con SQL
Incorrecto en JPQL:
SELECT *
FROM products
Correcto:
SELECT p
FROM Product p
________________


Usar repositorios directamente desde cualquier clase
Conviene mantener una arquitectura ordenada:
Controller
↓
Service
↓
Repository
________________


Retornar millones de registros mediante findAll()
Debe utilizarse:
paginación
cuando existen grandes volúmenes de información.
________________


64. Buenas prácticas
En proyectos reales resulta recomendable:
* utilizar DTO para comunicación con la API;
* evitar exponer directamente entidades complejas;
* separar lógica de negocio y persistencia;
* utilizar BigDecimal para cantidades monetarias;
* implementar validaciones;
* utilizar paginación;
* utilizar transacciones en operaciones de negocio;
* evitar consultas innecesarias;
* controlar adecuadamente relaciones;
* utilizar migraciones de base de datos;
* manejar excepciones mediante @ControllerAdvice.
________________


65. Evolución hacia una arquitectura empresarial
Una aplicación básica puede utilizar:
Controller
Service
Repository
Entity
Una aplicación más estructurada podría evolucionar hacia:
Controller
  ↓
DTO
  ↓
Application Service
  ↓
Domain
  ↓
Repository
  ↓
JPA
  ↓
Database
En arquitecturas hexagonales:
REST Adapter
    ↓
Application
    ↓
Domain
    ↓
Repository Port
    ↓
JPA Adapter
    ↓
Database
________________


66. Resumen conceptual
La gestión de bases de datos mediante JPA puede entenderse como el siguiente flujo:
Petición HTTP
     ↓
Controller
     ↓
Service
     ↓
Repository
     ↓
Spring Data JPA
     ↓
Hibernate
     ↓
JDBC
     ↓
PostgreSQL
El retorno funciona en sentido inverso:
PostgreSQL
     ↓
Hibernate
     ↓
Entity
     ↓
Repository
     ↓
Service
     ↓
Controller
     ↓
JSON
________________


67. Conclusión
JPA proporciona un modelo orientado a objetos para administrar información almacenada en bases de datos relacionales.
Con Spring Data JPA, muchas operaciones repetitivas pueden reducirse significativamente.
Una declaración como:
public interface ProductRepository
       extends JpaRepository<Product, Long> {
}
proporciona automáticamente las operaciones fundamentales de persistencia.
A partir de esta base pueden incorporarse progresivamente:
Consultas derivadas
JPQL
SQL nativo
Relaciones
Paginación
Ordenamiento
Validaciones
Transacciones
DTO
Proyecciones
Specifications
Auditoría
Optimización de consultas
El objetivo no consiste únicamente en aprender a ejecutar operaciones CRUD, sino en comprender cómo JPA administra el ciclo de vida de las entidades y cómo organizar correctamente la capa de persistencia dentro de una aplicación Spring Boot.
Gestión de Bases de Datos mediante JPA en Spring Boot
1. Introducción
En una aplicación empresarial, gran parte de la información necesita almacenarse de manera permanente.
Ejemplos comunes son:
* usuarios;
* productos;
* clientes;
* pedidos;
* facturas;
* matrículas;
* empleados;
* cursos;
* inventarios.
Esta información normalmente se almacena en una base de datos relacional como:
PostgreSQL
MySQL
MariaDB
Oracle
SQL Server
En Java, una aplicación puede comunicarse directamente con estas bases de datos utilizando JDBC. Sin embargo, trabajar directamente con JDBC implica escribir manualmente gran cantidad de código relacionado con:
Conexiones
PreparedStatement
ResultSet
SQL
Conversión de filas en objetos
Manejo de transacciones
Cierre de recursos
Spring Boot permite simplificar este trabajo mediante JPA, Hibernate y Spring Data JPA.
La idea fundamental es que el desarrollador pueda trabajar principalmente con objetos Java, mientras que el framework se encarga de traducir gran parte de las operaciones hacia SQL.
Por ejemplo:
Product product = new Product();
​
product.setName("Monitor");
product.setPrice(850000.0);
​
repository.save(product);
puede terminar generando internamente una operación equivalente a:
INSERT INTO products(name, price)
VALUES ('Monitor', 850000);
Por tanto, el desarrollador trabaja con:
Objetos Java
mientras que JPA e Hibernate administran la relación con:
Tablas relacionales
2. Objetivos de aprendizaje
Al finalizar esta guía, el estudiante estará en capacidad de:
1. Comprender el propósito de JPA.
2. Diferenciar JPA, Hibernate y Spring Data JPA.
3. Configurar una conexión a base de datos en Spring Boot.
4. Crear entidades persistentes.
5. Mapear clases Java con tablas.
6. Definir claves primarias.
7. Configurar columnas.
8. Crear repositorios.
9. Implementar operaciones CRUD.
10. Construir consultas mediante Query Methods.
11. Comprender el ciclo de persistencia.
12. Utilizar relaciones entre entidades.
13. Comprender el uso básico de transacciones.
14. Aplicar una arquitectura organizada por capas.
15. Gestionar correctamente datos dentro de una aplicación Spring Boot.
3. ¿Qué es JPA?
JPA significa:
Java Persistence API
En versiones modernas de Java empresarial se utiliza el paquete:
jakarta.persistence
Por esta razón también se habla de:
Jakarta Persistence
JPA es una especificación.
Esto significa que establece reglas y contratos para trabajar con persistencia, pero no realiza por sí misma todas las operaciones.
Define elementos como:
@Entity
@Table
@Id
@GeneratedValue
@Column
@OneToMany
@ManyToOne
@OneToOne
@ManyToMany
4. ¿Qué es Hibernate?
Hibernate es una implementación de JPA.
Puede entenderse así:
JPA
↓
Define las reglas


Hibernate
↓
Implementa las reglas
Hibernate se encarga de tareas como:
* convertir objetos Java en registros;
* convertir registros en objetos;
* generar SQL;
* ejecutar consultas;
* gestionar relaciones;
* detectar cambios;
* administrar el contexto de persistencia.
Por ejemplo:
repository.findAll();
puede terminar generando:
SELECT
   p.id,
   p.name,
   p.price,
   p.stock
FROM products p;
5. ¿Qué es Spring Data JPA?
Spring Data JPA agrega una capa adicional de abstracción.
Permite crear repositorios como:
public interface ProductRepository
       extends JpaRepository<Product, Long> {
}
sin tener que implementar manualmente operaciones comunes.
Al extender JpaRepository, se obtienen métodos como:
save()
findAll()
findById()
delete()
deleteById()
existsById()
count()
Por tanto:
Aplicación Spring Boot
       ↓
Spring Data JPA
       ↓
JPA
       ↓
Hibernate
       ↓
JDBC
       ↓
Base de datos
6. ¿Qué es ORM?
ORM significa:
Object Relational Mapping
o:
Mapeo Objeto-Relacional
Permite relacionar objetos Java con estructuras relacionales.
Por ejemplo:
public class Product {


   private Long id;
   private String name;
   private Double price;
}
puede representar una tabla:
products


id
name
price
Visualmente:
Clase Java                Tabla SQL


Product                   products
───────────               ─────────────
id          ───────────►  id
name        ───────────►  name
price       ───────────►  price
7. Caso práctico
Durante la guía se construirá un pequeño sistema de gestión de productos.
Cada producto tendrá:
Campo
	Tipo Java
	Descripción
	id
	Long
	Identificador
	name
	String
	Nombre
	price
	BigDecimal
	Precio
	stock
	Integer
	Existencias
	active
	Boolean
	Estado
	category
	String
	Categoría
	El sistema permitirá:
Registrar productos
Consultar productos
Consultar por ID
Actualizar productos
Eliminar productos
Buscar por nombre
Buscar por categoría
Buscar productos activos
Buscar productos por rango de precios
8. Crear el proyecto Spring Boot
Desde Spring Initializr se pueden agregar:
Spring Web
Spring Data JPA
PostgreSQL Driver
Validation
También puede utilizarse MySQL.
Una estructura inicial puede ser:
src/main/java
└── com.example.store
   ├── StoreApplication.java
   ├── controller
   ├── service
   ├── repository
   └── entity
9. Dependencias Maven
En pom.xml:
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
Para PostgreSQL:
<dependency>
   <groupId>org.postgresql</groupId>
   <artifactId>postgresql</artifactId>
   <scope>runtime</scope>
</dependency>
Y para una API REST:
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-web</artifactId>
</dependency>
10. Crear la base de datos
En PostgreSQL:
CREATE DATABASE store_db;
Posteriormente Spring Boot se conectará a esta base de datos.
11. Configurar la conexión
Archivo:
src/main/resources/application.properties
Ejemplo:
spring.datasource.url=jdbc:postgresql://localhost:5432/store_db
spring.datasource.username=postgres
spring.datasource.password=123456


spring.jpa.hibernate.ddl-auto=update


spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
12. Explicación de la configuración
URL
spring.datasource.url=jdbc:postgresql://localhost:5432/store_db
Indica:
Motor: PostgreSQL
Servidor: localhost
Puerto: 5432
Base de datos: store_db
Usuario
spring.datasource.username=postgres
Contraseña
spring.datasource.password=123456
Estrategia DDL
spring.jpa.hibernate.ddl-auto=update
Controla cómo Hibernate trabaja con la estructura de tablas.
Valores habituales:
none
validate
update
create
create-drop
validate
Comprueba que las tablas correspondan con las entidades.
No modifica el esquema.
update
Intenta adaptar el esquema.
Es útil principalmente durante desarrollo.
create
Crea nuevamente el esquema al iniciar.
Puede eliminar información existente.
create-drop
Crea el esquema al iniciar y lo elimina al finalizar.
Se utiliza principalmente para pruebas.
13. Crear una entidad
Se crea:
entity/Product.java
Ejemplo:
package com.example.store.entity;


import jakarta.persistence.*;


import java.math.BigDecimal;


@Entity
@Table(name = "products")
public class Product {


   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;


   @Column(
       nullable = false,
       length = 120
   )
   private String name;


   @Column(
       nullable = false,
       precision = 12,
       scale = 2
   )
   private BigDecimal price;


   @Column(nullable = false)
   private Integer stock;


   @Column(nullable = false)
   private Boolean active;


   @Column(length = 80)
   private String category;


   public Product() {
   }


   public Long getId() {
       return id;
   }


   public void setId(Long id) {
       this.id = id;
   }


   public String getName() {
       return name;
   }


   public void setName(String name) {
       this.name = name;
   }


   public BigDecimal getPrice() {
       return price;
   }


   public void setPrice(BigDecimal price) {
       this.price = price;
   }


   public Integer getStock() {
       return stock;
   }


   public void setStock(Integer stock) {
       this.stock = stock;
   }


   public Boolean getActive() {
       return active;
   }


   public void setActive(Boolean active) {
       this.active = active;
   }


   public String getCategory() {
       return category;
   }


   public void setCategory(String category) {
       this.category = category;
   }
}
14. Comprender @Entity
@Entity
indica que la clase es administrada por JPA.
Una clase normal:
public class Product {
}
solo existe como objeto Java.
Una clase:
@Entity
public class Product {
}
puede representar información persistente.
15. Comprender @Table
@Table(name = "products")
permite definir explícitamente la tabla asociada.
La relación queda:
Product
  ↓
products
16. Clave primaria con @Id
Toda entidad debe disponer de un identificador.
@Id
private Long id;
equivale conceptualmente a:
PRIMARY KEY
17. Generación automática
@GeneratedValue(
   strategy = GenerationType.IDENTITY
)
indica que el valor del identificador será generado automáticamente.
Por ejemplo:
Producto 1 → id 1
Producto 2 → id 2
Producto 3 → id 3
18. Configurar columnas
La anotación:
@Column
permite especificar propiedades de una columna.
Ejemplo:
@Column(
   nullable = false,
   length = 120
)
private String name;
Conceptualmente:
name VARCHAR(120) NOT NULL
19. Uso de BigDecimal para dinero
Para información monetaria es preferible utilizar:
BigDecimal
en lugar de:
double
o:
Double
Ejemplo:
private BigDecimal price;
Esto evita muchos problemas de precisión decimal.
20. Crear el Repository
Se crea:
repository/ProductRepository.java
package com.example.store.repository;


import com.example.store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository
       extends JpaRepository<Product, Long> {


}
21. Significado de JpaRepository<Product, Long>
El primer tipo:
Product
indica la entidad administrada.
El segundo:
Long
indica el tipo de la clave primaria.
Por tanto:
JpaRepository<Product, Long>
se interpreta como:
Repositorio encargado de administrar entidades Product identificadas mediante un Long.
22. Métodos CRUD heredados
JpaRepository proporciona métodos como:
save()
findAll()
findById()
delete()
deleteById()
count()
existsById()
saveAll()
Ejemplo:
repository.findAll();
equivale conceptualmente a:
SELECT * FROM products;
23. ¿Qué significa CRUD?
CRUD corresponde a:
Create
Read
Update
Delete
En español:
Crear
Consultar
Actualizar
Eliminar
Puede relacionarse con HTTP y SQL:
CRUD
	HTTP
	SQL
	JPA
	Create
	POST
	INSERT
	save
	Read
	GET
	SELECT
	findAll / findById
	Update
	PUT/PATCH
	UPDATE
	save
	Delete
	DELETE
	DELETE
	deleteById
	24. Crear la capa Service
Se crea:
service/ProductService.java
package com.example.store.service;


import com.example.store.entity.Product;
import com.example.store.repository.ProductRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class ProductService {


   private final ProductRepository repository;


   public ProductService(
           ProductRepository repository) {


       this.repository = repository;
   }


   public List<Product> findAll() {
       return repository.findAll();
   }


   public Optional<Product> findById(Long id) {
       return repository.findById(id);
   }


   public Product save(Product product) {
       return repository.save(product);
   }


   public void deleteById(Long id) {
       repository.deleteById(id);
   }
}
25. ¿Por qué utilizar Service?
El Repository administra persistencia.
El Service debería administrar lógica de negocio.
Por ejemplo:
Repository
↓
¿Cómo guardar?


Service
↓
¿Se puede guardar?
¿Qué reglas deben cumplirse?
¿Qué cálculos deben realizarse?
Ejemplo:
public Product save(Product product) {


   if (product.getPrice().signum() <= 0) {
       throw new IllegalArgumentException(
           "El precio debe ser mayor que cero"
       );
   }


   return repository.save(product);
}
26. Arquitectura por capas
Una organización típica es:
Controller
   ↓
Service
   ↓
Repository
   ↓
JPA
   ↓
Hibernate
   ↓
Base de datos
Cada capa posee una responsabilidad.
27. Crear el Controller
@RestController
@RequestMapping("/api/products")
public class ProductController {


   private final ProductService service;


   public ProductController(
           ProductService service) {


       this.service = service;
   }


   @GetMapping
   public List<Product> findAll() {


       return service.findAll();
   }
}
Solicitud:
GET /api/products
Flujo:
Cliente
 ↓
Controller
 ↓
Service
 ↓
Repository
 ↓
Hibernate
 ↓
Base de datos
28. Registrar un producto
@PostMapping
public Product save(
       @RequestBody Product product) {


   return service.save(product);
}
Petición:
POST /api/products
JSON:
{
 "name": "Monitor 27 pulgadas",
 "price": 1200000,
 "stock": 8,
 "active": true,
 "category": "Monitores"
}
29. Flujo de una inserción
La información entra así:
JSON
↓
@RequestBody
↓
Product
↓
Service
↓
Repository.save()
↓
Hibernate
↓
INSERT
↓
PostgreSQL
Hibernate puede generar algo semejante a:
INSERT INTO products
(name, price, stock, active, category)
VALUES
('Monitor 27 pulgadas',
1200000,
8,
true,
'Monitores');
30. Consultar por ID
@GetMapping("/{id}")
public ResponseEntity<Product> findById(
       @PathVariable Long id) {


   return service.findById(id)
       .map(ResponseEntity::ok)
       .orElse(
           ResponseEntity
               .notFound()
               .build()
       );
}
Solicitud:
GET /api/products/5
31. ¿Por qué findById devuelve Optional?
El método:
repository.findById(id)
devuelve:
Optional<Product>
porque existen dos posibilidades:
Producto encontrado
o:
Producto inexistente
El Optional obliga al desarrollador a considerar ambas situaciones.
32. Actualizar información
Ejemplo:
@PutMapping("/{id}")
public ResponseEntity<Product> update(
       @PathVariable Long id,
       @RequestBody Product data) {


   return service.findById(id)
       .map(product -> {


           product.setName(data.getName());
           product.setPrice(data.getPrice());
           product.setStock(data.getStock());
           product.setActive(data.getActive());
           product.setCategory(
               data.getCategory()
           );


           Product updated =
               service.save(product);


           return ResponseEntity.ok(updated);
       })
       .orElse(
           ResponseEntity
               .notFound()
               .build()
       );
}
33. ¿Por qué save() sirve para insertar y actualizar?
Spring Data JPA utiliza:
save()
para ambas operaciones.
Si la entidad es nueva:
INSERT
Si representa información persistente existente:
UPDATE
Conceptualmente:
repository.save(product);
puede significar:
Entidad nueva
↓
INSERT


Entidad existente
↓
UPDATE
34. Eliminar información
@DeleteMapping("/{id}")
public ResponseEntity<Void> delete(
       @PathVariable Long id) {


   if (!service.findById(id).isPresent()) {
       return ResponseEntity
           .notFound()
           .build();
   }


   service.deleteById(id);


   return ResponseEntity
       .noContent()
       .build();
}
Solicitud:
DELETE /api/products/5
35. Query Methods
Una de las características más importantes de Spring Data JPA son las consultas derivadas.
Spring puede interpretar el nombre de un método.
Por ejemplo:
List<Product> findByCategory(
   String category
);
Spring interpreta:
find
By
Category
y genera una consulta equivalente a:
SELECT *
FROM products
WHERE category = ?;
36. Buscar por nombre
List<Product> findByName(
   String name
);
Conceptualmente:
WHERE name = ?
37. Buscar por coincidencia parcial
List<Product>
findByNameContaining(String text);
Conceptualmente:
WHERE name LIKE '%texto%'
38. Ignorar mayúsculas y minúsculas
List<Product>
findByNameContainingIgnoreCase(
   String text
);
Ejemplos:
mouse
Mouse
MOUSE
pueden encontrar el mismo producto.
39. Buscar por estado
List<Product> findByActive(
   Boolean active
);
Uso:
repository.findByActive(true);
40. Buscar por stock
List<Product>
findByStockGreaterThan(
   Integer stock
);
Ejemplo:
repository
   .findByStockGreaterThan(0);
Equivale conceptualmente a:
WHERE stock > 0
41. Buscar por rango de precios
List<Product>
findByPriceBetween(
   BigDecimal min,
   BigDecimal max
);
Conceptualmente:
WHERE price BETWEEN ? AND ?
42. Combinar condiciones
List<Product>
findByActiveAndStockGreaterThan(
   Boolean active,
   Integer stock
);
Conceptualmente:
WHERE active = ?
AND stock > ?
43. Uso de OR
List<Product>
findByCategoryOrName(
   String category,
   String name
);
Conceptualmente:
WHERE category = ?
OR name = ?
44. Ordenamiento
List<Product>
findByActiveOrderByNameAsc(
   Boolean active
);
Conceptualmente:
WHERE active = ?
ORDER BY name ASC
45. Repository ampliado
public interface ProductRepository
       extends JpaRepository<Product, Long> {


   List<Product> findByCategory(
       String category
   );


   List<Product>
   findByNameContainingIgnoreCase(
       String name
   );


   List<Product> findByActive(
       Boolean active
   );


   List<Product>
   findByStockGreaterThan(
       Integer stock
   );


   List<Product>
   findByPriceBetween(
       BigDecimal min,
       BigDecimal max
   );
}
46. Consultas personalizadas con JPQL
Cuando un Query Method se vuelve demasiado complejo, puede utilizarse @Query.
Ejemplo:
@Query("""
   SELECT p
   FROM Product p
   WHERE p.active = true
     AND p.stock > 0
""")
List<Product> findAvailableProducts();
47. JPQL no trabaja directamente con tablas
En JPQL se utilizan:
Entidades
Atributos Java
No:
Tablas
Columnas
Por ejemplo:
SELECT p
FROM Product p
WHERE p.price > :price
No:
SELECT *
FROM products
WHERE price > ...
48. Parámetros en JPQL
@Query("""
   SELECT p
   FROM Product p
   WHERE p.price > :price
""")
List<Product> findExpensiveProducts(
   @Param("price") BigDecimal price
);
49. Consultas SQL nativas
También es posible utilizar SQL directamente.
@Query(
   value = """
       SELECT *
       FROM products
       WHERE stock = 0
   """,
   nativeQuery = true
)
List<Product> findWithoutStock();
Debe utilizarse cuando existe una razón clara para depender del motor SQL.
50. Estados de una entidad en JPA
Una entidad puede encontrarse en diferentes estados.
Los más importantes son:
Transient
Managed
Detached
Removed
51. Entidad Transient
Objeto creado en memoria:
Product product = new Product();
Todavía no está gestionado por JPA.
Java
↓
Objeto nuevo
↓
No existe todavía en BD
52. Entidad Managed
Cuando la entidad está siendo administrada por el contexto de persistencia.
Ejemplo:
Product product =
   repository.findById(1L)
       .orElseThrow();
Durante una transacción, Hibernate puede detectar cambios en este objeto.
53. Dirty Checking
Una característica muy importante de Hibernate es:
Dirty Checking
Supóngase:
@Transactional
public void updatePrice(
       Long id,
       BigDecimal price) {


   Product product =
       repository.findById(id)
           .orElseThrow();


   product.setPrice(price);
}
Dentro de una transacción, Hibernate puede detectar que:
price cambió
y generar automáticamente:
UPDATE products
SET price = ?
WHERE id = ?;
Incluso sin ejecutar explícitamente otro:
save()
sobre una entidad administrada.
54. ¿Qué es @Transactional?
La anotación:
@Transactional
permite ejecutar varias operaciones como una unidad lógica.
Ejemplo:
@Transactional
public void transferStock(
       Long sourceId,
       Long destinationId,
       int quantity) {


   // operación 1
   // operación 2
}
El principio es:
Todo funciona
↓
COMMIT


Algo falla
↓
ROLLBACK
55. Ejemplo de transacción
Supóngase:
@Transactional
public void decreaseStock(
       Long id,
       int quantity) {


   Product product =
       repository.findById(id)
           .orElseThrow();


   if (product.getStock() < quantity) {
       throw new IllegalStateException(
           "Stock insuficiente"
       );
   }


   product.setStock(
       product.getStock() - quantity
   );
}
Si ocurre una excepción antes de finalizar correctamente, la transacción puede revertirse.
56. Relaciones entre entidades
Las bases de datos reales contienen relaciones.
Por ejemplo:
Category
  1
  │
  │
  N
Product
Una categoría puede tener muchos productos.
Un producto pertenece a una categoría.
57. Relación Many-to-One
Una entidad Product puede contener:
@ManyToOne
@JoinColumn(name = "category_id")
private Category category;
Esto significa:
Muchos productos
↓
Una categoría
58. Entidad Category
@Entity
@Table(name = "categories")
public class Category {


   @Id
   @GeneratedValue(
       strategy = GenerationType.IDENTITY
   )
   private Long id;


   @Column(
       nullable = false,
       unique = true
   )
   private String name;


   public Category() {
   }


   // getters y setters
}
59. Product con Category
@Entity
@Table(name = "products")
public class Product {


   @Id
   @GeneratedValue(
       strategy = GenerationType.IDENTITY
   )
   private Long id;


   private String name;


   private BigDecimal price;


   @ManyToOne
   @JoinColumn(
       name = "category_id",
       nullable = false
   )
   private Category category;
}
En la base de datos:
products
─────────────────────────
id
name
price
category_id
60. Relación One-to-Many
Desde Category:
@OneToMany(
   mappedBy = "category"
)
private List<Product> products;
La relación completa queda:
Category
   1
   │
   │
   N
Product
61. mappedBy
mappedBy = "category"
significa que la relación es controlada por el atributo:
Product.category
62. Tipos principales de relaciones JPA
Anotación
	Relación
	@OneToOne
	Uno a uno
	@OneToMany
	Uno a muchos
	@ManyToOne
	Muchos a uno
	@ManyToMany
	Muchos a muchos
	63. Carga LAZY y EAGER
JPA puede cargar relaciones de diferentes formas.
LAZY
Cargar cuando sea necesario
EAGER
Cargar inmediatamente
Conceptualmente:
LAZY
Product
↓
Category solo cuando se solicita


EAGER
Product
↓
Category se trae inmediatamente
64. Cascadas
Las cascadas indican qué operaciones deben propagarse.
Ejemplo:
@OneToMany(
   mappedBy = "category",
   cascade = CascadeType.ALL
)
Algunas opciones:
PERSIST
MERGE
REMOVE
REFRESH
DETACH
ALL
Las cascadas deben utilizarse con cuidado, especialmente:
CascadeType.REMOVE
porque puede eliminar información relacionada.
65. Paginación
Cuando existen miles de registros no es conveniente ejecutar siempre:
findAll()
Spring Data JPA permite paginar.
Ejemplo:
@GetMapping
public Page<Product> findAll(
       Pageable pageable) {


   return repository.findAll(pageable);
}
Solicitud:
GET /api/products?page=0&size=10
66. Ordenamiento
También puede utilizarse:
GET /api/products
?page=0
&size=10
&sort=name,asc
Esto permite:
Página 0
10 registros
Ordenados por nombre ascendente
67. Page, Slice y List
List
Entrega todos los elementos encontrados.
Page
Incluye:
Contenido
Número de página
Total de páginas
Total de elementos
Tamaño
Slice
Indica principalmente si existe una siguiente página.
Puede ser útil cuando no se necesita calcular el total.
68. Validación antes de persistir
Puede utilizarse Bean Validation.
Ejemplo:
public class ProductRequest {


   @NotBlank
   private String name;


   @NotNull
   @Positive
   private BigDecimal price;


   @NotNull
   @PositiveOrZero
   private Integer stock;
}
Controller:
@PostMapping
public Product save(
       @Valid
       @RequestBody ProductRequest request) {


   // ...
}
69. Entidad frente a DTO
En aplicaciones medianas o grandes no es recomendable exponer siempre las entidades directamente.
Puede utilizarse:
Product
↓
Entidad JPA


ProductRequest
↓
Entrada API


ProductResponse
↓
Salida API
Esto desacopla:
Modelo de persistencia
de:
Contrato HTTP
70. Ejemplo de DTO
public record ProductRequest(


   String name,


   BigDecimal price,


   Integer stock,


   Boolean active,


   Long categoryId


) {
}
71. Ejemplo de respuesta
public record ProductResponse(


   Long id,


   String name,


   BigDecimal price,


   Integer stock,


   Boolean active,


   String category


) {
}
72. Arquitectura recomendada
Una estructura más organizada puede ser:
src/main/java/com/example/store
│
├── controller
│   └── ProductController.java
│
├── service
│   └── ProductService.java
│
├── repository
│   └── ProductRepository.java
│
├── entity
│   ├── Product.java
│   └── Category.java
│
├── dto
│   ├── ProductRequest.java
│   └── ProductResponse.java
│
├── exception
│
└── StoreApplication.java
73. Flujo completo de una operación
Supóngase:
POST /api/products
El flujo puede ser:
JSON
↓
ProductRequest
↓
Controller
↓
Service
↓
Product
↓
Repository
↓
JPA
↓
Hibernate
↓
SQL
↓
PostgreSQL
La respuesta:
PostgreSQL
↓
Hibernate
↓
Product
↓
Service
↓
ProductResponse
↓
Controller
↓
JSON
74. Buenas prácticas
74.1 No colocar lógica de negocio en Repository
Evitar que el Repository conozca reglas del negocio.
74.2 No colocar toda la lógica en Controller
El Controller debe encargarse principalmente de HTTP.
74.3 Utilizar BigDecimal para dinero
Preferir:
BigDecimal
74.4 Utilizar DTO
Especialmente cuando el contrato HTTP no coincide exactamente con el modelo de base de datos.
74.5 Controlar transacciones
Las operaciones que modifican múltiples entidades deberían analizarse desde el punto de vista transaccional.
74.6 Evitar ddl-auto=create en producción
Puede destruir información.
74.7 Utilizar migraciones
En proyectos reales es recomendable administrar cambios de esquema con herramientas como:
Flyway
Liquibase
en lugar de depender permanentemente de:
ddl-auto=update
75. Errores frecuentes
Error 1: entidad sin @Id
Toda entidad debe tener identificador.
Error 2: olvidar constructor vacío
JPA requiere poder crear instancias de la entidad.
Es buena práctica disponer de:
public Product() {
}
Error 3: usar Double para dinero
Puede generar problemas de precisión.
Error 4: cargar demasiadas relaciones
Un uso inadecuado de EAGER puede generar consultas muy costosas.
Error 5: serialización infinita
Una relación bidireccional como:
Category → Products
Product → Category
puede generar referencias circulares al convertir entidades directamente a JSON.
Una solución recomendable es utilizar DTO.
Error 6: abuso de CascadeType.ALL
No todas las relaciones deben propagar automáticamente todas las operaciones.
76. Actividad guiada 1
Crear una entidad Customer
Atributos:
id
documentNumber
firstName
lastName
email
phone
active
El estudiante debe:
1. Crear la entidad.
2. Agregar las anotaciones JPA.
3. Definir la clave primaria.
4. Crear CustomerRepository.
5. Crear CustomerService.
6. Crear CustomerController.
77. Actividad guiada 2
Implementar:
GET /api/customers
GET /api/customers/{id}
POST /api/customers
PUT /api/customers/{id}
DELETE /api/customers/{id}
78. Actividad guiada 3
Crear Query Methods para:
Buscar cliente por email
Buscar por número de documento
Buscar clientes activos
Buscar por apellido
Buscar por nombre ignorando mayúsculas
Posibles métodos:
Optional<Customer>
findByEmail(String email);
Optional<Customer>
findByDocumentNumber(
   String documentNumber
);
List<Customer>
findByActive(Boolean active);
List<Customer>
findByLastName(String lastName);
List<Customer>
findByFirstNameContainingIgnoreCase(
   String name
);
79. Actividad guiada 4
Crear una entidad:
Order
con:
id
orderDate
total
status
customer
Relacionarla con:
Customer
mediante:
@ManyToOne
80. Actividad guiada 5
Consultar todos los pedidos de un cliente.
Repositorio:
List<Order>
findByCustomerId(Long customerId);
El estudiante deberá analizar qué consulta SQL representa aproximadamente este método.
81. Taller práctico
Sistema de biblioteca
Se deberán modelar:
Author
Book
Category
Author
id
name
email
Category
id
name
Book
id
isbn
title
price
stock
publishedDate
author
category
Relaciones:
Author
  1
  │
  N
Book


Category
  1
  │
  N
Book
82. Requisitos del taller
El estudiante deberá implementar:
1. Entidades JPA.
2. Repositorios.
3. Servicios.
4. Controladores.
5. CRUD.
6. Relaciones.
7. Consultas derivadas.
8. Paginación.
9. Validaciones.
10. Manejo de errores.
83. Consultas obligatorias del taller
Crear métodos para:
Buscar libros por título
Buscar por autor
Buscar por categoría
Buscar libros con stock
Buscar por rango de precios
Buscar por ISBN
Buscar libros publicados después de determinada fecha
Ejemplos:
List<Book>
findByTitleContainingIgnoreCase(
   String title
);
List<Book>
findByAuthorId(Long authorId);
List<Book>
findByCategoryId(Long categoryId);
List<Book>
findByStockGreaterThan(Integer stock);
List<Book>
findByPriceBetween(
   BigDecimal min,
   BigDecimal max
);
84. Reto avanzado
Crear una consulta JPQL que devuelva libros:
Activos
Con stock mayor que cero
De una categoría específica
Dentro de un rango de precios
Ejemplo conceptual:
@Query("""
   SELECT b
   FROM Book b
   WHERE b.active = true
     AND b.stock > 0
     AND b.category.id = :categoryId
     AND b.price BETWEEN :min AND :max
""")
List<Book> searchAvailableBooks(
   @Param("categoryId")
   Long categoryId,


   @Param("min")
   BigDecimal min,


   @Param("max")
   BigDecimal max
);
85. Ejercicio de análisis
Considérese:
public interface ProductRepository
       extends JpaRepository<Product, Long> {


   List<Product>
   findByCategoryAndActive(
       String category,
       Boolean active
   );
}
Responder:
1. ¿Qué entidad administra?
2. ¿Cuál es el tipo de su clave primaria?
3. ¿Qué información devuelve?
4. ¿Qué significa And?
5. ¿Qué SQL aproximado podría generar?
Respuesta conceptual:
SELECT *
FROM products
WHERE category = ?
AND active = ?;
86. Preguntas de comprobación
1. ¿Qué significa JPA?
2. ¿JPA es una implementación o una especificación?
3. ¿Cuál es la función de Hibernate?
4. ¿Qué función cumple Spring Data JPA?
5. ¿Qué significa ORM?
6. ¿Para qué se utiliza @Entity?
7. ¿Qué función tiene @Table?
8. ¿Para qué se utiliza @Id?
9. ¿Qué hace @GeneratedValue?
10. ¿Qué función cumple @Column?
11. ¿Qué representa JpaRepository<Product, Long>?
12. ¿Qué hace save()?
13. ¿Qué diferencia existe entre Repository y Service?
14. ¿Por qué findById() devuelve Optional?
15. ¿Qué es un Query Method?
16. ¿Qué diferencia existe entre JPQL y SQL?
17. ¿Qué significa @Transactional?
18. ¿Qué es Dirty Checking?
19. ¿Qué diferencia existe entre LAZY y EAGER?
20. ¿Qué función tiene mappedBy?
87. Mapa conceptual
                    SPRING DATA JPA
                          │
           ┌──────────────┼───────────────┐
           │              │               │
        ENTITY         REPOSITORY       SERVICE
           │              │               │
        @Entity       JpaRepository    Lógica negocio
           │              │               │
        @Table           CRUD         @Transactional
           │              │
       @Column       Query Methods
           │              │
         @Id            @Query
           │
           ▼
          JPA
           │
           ▼
       Hibernate
           │
           ▼
          JDBC
           │
           ▼
     Base de datos
88. Resumen pedagógico
Para comprender JPA debe separarse claramente cada responsabilidad.
Entidad
Representa información persistente.
@Entity
public class Product {
}
Repository
Permite acceder a la información.
public interface ProductRepository
       extends JpaRepository<Product, Long> {
}
Service
Gestiona reglas del negocio.
@Service
public class ProductService {
}
Controller
Gestiona solicitudes HTTP.
@RestController
public class ProductController {
}
Hibernate
Transforma las operaciones sobre entidades en acciones sobre la base de datos.
89. Idea fundamental
El estudiante debe evitar pensar que JPA elimina las bases de datos relacionales o SQL.
JPA continúa trabajando sobre:
Tablas
Columnas
Claves
Restricciones
Relaciones
Transacciones
La diferencia es que proporciona una abstracción basada en objetos.
Por ejemplo:
product.getCategory().getName();
puede representar información almacenada mediante:
products.category_id
      ↓
categories.id
Por tanto, conocer SQL y diseño relacional continúa siendo fundamental.
90. Conclusión
La gestión de bases de datos mediante JPA en Spring Boot permite reducir considerablemente el código repetitivo necesario para trabajar con persistencia.
La arquitectura puede resumirse así:
Cliente
  ↓
Controller
  ↓
Service
  ↓
Repository
  ↓
Spring Data JPA
  ↓
Hibernate
  ↓
JDBC
  ↓
Base de datos
Cada tecnología posee una responsabilidad específica:
Spring Boot
configura la aplicación.


Spring Data JPA
simplifica el acceso a datos.


JPA
define el modelo de persistencia.


Hibernate
implementa JPA y genera operaciones SQL.


La base de datos
almacena finalmente la información.
El objetivo de JPA no es ocultar completamente SQL, sino permitir que la aplicación Java trabaje de forma más natural con objetos mientras mantiene la información correctamente relacionada con un modelo relacional.
Una vez dominados estos fundamentos, los siguientes temas recomendados son:
Relaciones avanzadas en JPA
JPQL y @Query
Paginación y ordenamiento
DTO y proyecciones
Specifications
Transacciones
Auditoría
Optimización de consultas
Problema N+1
EntityGraph
Flyway/Liquibase
Bloqueo optimista y pesimista
Estos conceptos permiten evolucionar desde un CRUD básico hacia una capa de persistencia preparada para aplicaciones empresariales.
Introducción a JPA en Spring Boot
1. Propósito de la guía
Esta guía tiene como propósito introducir al estudiante en el uso de JPA dentro de Spring Boot, comprendiendo no solo cómo se escribe el código, sino también qué problema resuelve, cómo funciona y cómo se relaciona con una base de datos relacional.
El objetivo principal no es memorizar anotaciones, sino construir una comprensión progresiva de la persistencia de datos.
Al finalizar, el estudiante podrá:
1. Explicar qué es JPA.
2. Diferenciar JPA, Hibernate y Spring Data JPA.
3. Crear una entidad.
4. Configurar la conexión con una base de datos.
5. Crear un repositorio.
6. Ejecutar operaciones CRUD básicas.
7. Comprender qué sucede internamente cuando se guarda o consulta una entidad.
8. Interpretar el flujo entre controlador, servicio, repositorio y base de datos.
2. Situación de aprendizaje
Supóngase que se desarrolla una aplicación para administrar productos.
Cada producto debe almacenar:
* identificador;
* nombre;
* precio;
* stock;
* estado.
La información debe permanecer almacenada incluso cuando la aplicación se cierre.
Esto significa que no basta con crear objetos en memoria:
Product product = new Product();
Ese objeto desaparecerá cuando la aplicación termine.
Para conservar la información se necesita una base de datos.
Por ejemplo:
Spring Boot
    ↓
PostgreSQL
La pregunta entonces es:
¿Cómo se conecta una clase Java con una tabla de base de datos?
Aquí aparece JPA.
3. ¿Qué es persistencia?
La persistencia es la capacidad de guardar información de manera permanente.
Por ejemplo:
Product product = new Product();
product.setName("Mouse");
Mientras el objeto existe únicamente en memoria:
Memoria RAM
su información es temporal.
Cuando se guarda en una base de datos:
Base de datos
la información se vuelve persistente.
Por tanto:
Objeto temporal
     ↓
Persistencia
     ↓
Dato almacenado
4. ¿Qué es JPA?
JPA significa:
Java Persistence API
Actualmente pertenece al ecosistema Jakarta y se conoce formalmente como:
Jakarta Persistence
JPA es una especificación.
Esto significa que define reglas para trabajar con persistencia en Java.
JPA establece conceptos como:
Entidad
Clave primaria
Relaciones
Consultas
Persistencia
Transacciones
También define anotaciones como:
@Entity
@Id
@GeneratedValue
@Table
@Column
@OneToMany
@ManyToOne
Sin embargo, JPA por sí sola no ejecuta directamente las operaciones.
5. ¿Qué es Hibernate?
Hibernate es una implementación de JPA.
Puede imaginarse así:
JPA
    ↓
define las reglas


Hibernate
    ↓
ejecuta las reglas
Hibernate se encarga de tareas como:
* generar SQL;
* ejecutar consultas;
* insertar registros;
* actualizar registros;
* eliminar registros;
* convertir filas en objetos Java.
Por ejemplo, cuando se ejecuta:
repository.save(product);
Hibernate puede generar internamente una sentencia semejante a:
INSERT INTO products
(name, price)
VALUES ('Mouse', 85000);
6. ¿Qué es Spring Data JPA?
Spring Data JPA facilita todavía más el acceso a datos.
En lugar de escribir manualmente toda la lógica de acceso, puede declararse:
public interface ProductRepository
       extends JpaRepository<Product, Long> {
}
Y automáticamente se obtienen métodos como:
save()
findAll()
findById()
deleteById()
existsById()
count()
La relación completa puede representarse así:
Aplicación Spring Boot
       ↓
Spring Data JPA
       ↓
JPA
       ↓
Hibernate
       ↓
JDBC
       ↓
Base de datos
7. Comparación conceptual
Tecnología
	Función
	JPA
	Define cómo trabajar con persistencia
	Hibernate
	Implementa JPA
	Spring Data JPA
	Simplifica el acceso a datos
	JDBC
	Permite la comunicación con la base de datos
	PostgreSQL/MySQL
	Almacena físicamente los datos
	Una forma sencilla de recordarlo es:
JPA define
Hibernate ejecuta
Spring Data simplifica
8. ¿Qué es ORM?
ORM significa:
Object Relational Mapping
En español:
Mapeo objeto-relacional
ORM permite relacionar:
Objeto Java
con:
Tabla relacional
Por ejemplo:
public class Product {


   private Long id;
   private String name;
   private Double price;
}
puede relacionarse con:
products
-----------------
id
name
price
Por tanto:
Product
  ⇅
products
9. Primera analogía pedagógica
Puede imaginarse JPA como un traductor.
Java habla en términos de:
Objetos
Clases
Atributos
Métodos
La base de datos habla en términos de:
Tablas
Columnas
Filas
SQL
JPA e Hibernate ayudan a traducir entre ambos mundos.
Java
Objeto Product
     ↓
JPA / Hibernate
     ↓
SQL
     ↓
Tabla products
10. Caso práctico de la guía
Se desarrollará una aplicación sencilla denominada:
Store
que permitirá administrar productos.
La entidad tendrá:
Atributo
	Tipo
	id
	Long
	name
	String
	price
	BigDecimal
	stock
	Integer
	active
	Boolean
	11. Estructura recomendada
El proyecto puede organizarse así:
src/main/java
└── com.example.store
   ├── StoreApplication.java
   ├── controller
   │   └── ProductController.java
   ├── service
   │   └── ProductService.java
   ├── repository
   │   └── ProductRepository.java
   └── entity
       └── Product.java
Y:
src/main/resources
└── application.properties
12. Crear el proyecto
Desde Spring Initializr pueden seleccionarse:
Spring Web
Spring Data JPA
PostgreSQL Driver
Si se trabaja con MySQL:
MySQL Driver
13. Dependencias Maven
Para PostgreSQL:
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-web</artifactId>
</dependency>


<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>


<dependency>
   <groupId>org.postgresql</groupId>
   <artifactId>postgresql</artifactId>
   <scope>runtime</scope>
</dependency>
La dependencia fundamental es:
spring-boot-starter-data-jpa
14. Crear la base de datos
En PostgreSQL:
CREATE DATABASE store_db;
Es importante comprender que Hibernate puede crear tablas, pero normalmente la base de datos debe existir previamente.
15. Configurar la conexión
En:
application.properties
puede utilizarse:
spring.datasource.url=jdbc:postgresql://localhost:5432/store_db
spring.datasource.username=postgres
spring.datasource.password=123456


spring.jpa.hibernate.ddl-auto=update


spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
16. Comprender la configuración
URL de conexión
spring.datasource.url=jdbc:postgresql://localhost:5432/store_db
Indica:
Motor: PostgreSQL
Servidor: localhost
Puerto: 5432
Base de datos: store_db
Usuario
spring.datasource.username=postgres
Contraseña
spring.datasource.password=123456
17. ¿Qué significa ddl-auto?
Esta propiedad:
spring.jpa.hibernate.ddl-auto=update
controla cómo Hibernate administra la estructura de la base de datos.
Valores comunes:
Valor
	Comportamiento
	none
	No modifica la estructura
	validate
	Verifica la estructura
	update
	Intenta actualizarla
	create
	Recrea las tablas
	create-drop
	Crea y elimina al finalizar
	Para aprendizaje puede utilizarse:
update
pero en producción normalmente se prefieren herramientas de migración como Flyway o Liquibase.
18. Crear la primera entidad
package com.example.store.entity;


import jakarta.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "products")
public class Product {


   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;


   @Column(nullable = false, length = 100)
   private String name;


   @Column(nullable = false)
   private BigDecimal price;


   private Integer stock;


   private Boolean active;


   public Product() {
   }


   public Product(
           String name,
           BigDecimal price,
           Integer stock,
           Boolean active) {


       this.name = name;
       this.price = price;
       this.stock = stock;
       this.active = active;
   }


   public Long getId() {
       return id;
   }


   public void setId(Long id) {
       this.id = id;
   }


   public String getName() {
       return name;
   }


   public void setName(String name) {
       this.name = name;
   }


   public BigDecimal getPrice() {
       return price;
   }


   public void setPrice(BigDecimal price) {
       this.price = price;
   }


   public Integer getStock() {
       return stock;
   }


   public void setStock(Integer stock) {
       this.stock = stock;
   }


   public Boolean getActive() {
       return active;
   }


   public void setActive(Boolean active) {
       this.active = active;
   }
}
19. ¿Qué hace @Entity?
@Entity
indica que la clase será administrada por JPA.
Sin esta anotación:
public class Product
sería una clase Java común.
Con:
@Entity
se convierte en una entidad persistente.
Product
  ↓
@Entity
  ↓
Entidad JPA
20. ¿Qué hace @Table?
@Table(name = "products")
indica el nombre de la tabla.
La clase se llama:
Product
pero la tabla se llamará:
products
21. ¿Qué hace @Id?
Toda entidad necesita una clave primaria.
@Id
private Long id;
equivale conceptualmente a:
PRIMARY KEY
La clave primaria identifica de forma única cada registro.
22. ¿Qué hace @GeneratedValue?
@GeneratedValue(strategy = GenerationType.IDENTITY)
permite que la base de datos genere automáticamente el ID.
Por ejemplo:
1
2
3
4
5
El programador no debe asignarlo manualmente.
23. ¿Qué hace @Column?
@Column(nullable = false, length = 100)
private String name;
permite definir propiedades de la columna.
En este caso:
name
no puede ser nulo y admite hasta 100 caracteres.
Conceptualmente:
name VARCHAR(100) NOT NULL
24. Relación entre entidad y tabla
La clase:
Product
puede generar una tabla aproximadamente así:
CREATE TABLE products (
   id BIGSERIAL PRIMARY KEY,
   name VARCHAR(100) NOT NULL,
   price NUMERIC NOT NULL,
   stock INTEGER,
   active BOOLEAN
);
Esto permite visualizar el mapeo:
Java                 SQL


Product              products
id                   id
name                 name
price                price
stock                stock
active               active
25. Crear el repositorio
package com.example.store.repository;


import com.example.store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository
       extends JpaRepository<Product, Long> {
}
26. Comprender JpaRepository<Product, Long>
Esta expresión contiene dos tipos:
JpaRepository<Product, Long>
Product significa:
Entidad administrada
Long significa:
Tipo de la clave primaria
Puede leerse como:
Este repositorio administra entidades Product identificadas mediante una clave Long.
27. Métodos heredados automáticamente
Sin programarlos manualmente, ya se dispone de:
save()
findAll()
findById()
delete()
deleteById()
count()
existsById()
Por ejemplo:
repository.findAll();
puede generar una consulta semejante a:
SELECT *
FROM products;
28. Primer ejercicio mental
Si se ejecuta:
repository.findById(5L);
¿qué se espera que haga Hibernate?
Conceptualmente:
SELECT *
FROM products
WHERE id = 5;
Este ejercicio ayuda a relacionar Java con SQL.
29. Crear la capa de servicio
package com.example.store.service;


import com.example.store.entity.Product;
import com.example.store.repository.ProductRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class ProductService {


   private final ProductRepository repository;


   public ProductService(
           ProductRepository repository) {


       this.repository = repository;
   }


   public List<Product> findAll() {
       return repository.findAll();
   }


   public Optional<Product> findById(Long id) {
       return repository.findById(id);
   }


   public Product save(Product product) {
       return repository.save(product);
   }


   public void deleteById(Long id) {
       repository.deleteById(id);
   }
}
30. ¿Por qué utilizar Service?
Podría utilizarse el repositorio directamente desde el controlador.
Pero una arquitectura organizada separa responsabilidades:
Controller
   ↓
Service
   ↓
Repository
Cada capa cumple una función.
Controller
Recibe peticiones HTTP.
Service
Aplica reglas de negocio.
Repository
Accede a la información.
31. Crear el controlador
package com.example.store.controller;


import com.example.store.entity.Product;
import com.example.store.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductController {


   private final ProductService service;


   public ProductController(
           ProductService service) {


       this.service = service;
   }


   @GetMapping
   public List<Product> findAll() {
       return service.findAll();
   }


   @GetMapping("/{id}")
   public ResponseEntity<Product> findById(
           @PathVariable Long id) {


       return service.findById(id)
               .map(ResponseEntity::ok)
               .orElse(
                   ResponseEntity
                       .notFound()
                       .build()
               );
   }


   @PostMapping
   public Product save(
           @RequestBody Product product) {


       return service.save(product);
   }


   @DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(
           @PathVariable Long id) {


       service.deleteById(id);


       return ResponseEntity
               .noContent()
               .build();
   }
}
32. Flujo completo de una petición
Cuando el cliente ejecuta:
GET /api/products
ocurre:
Cliente
  ↓
ProductController
  ↓
ProductService
  ↓
ProductRepository
  ↓
Spring Data JPA
  ↓
Hibernate
  ↓
PostgreSQL
La respuesta regresa en sentido inverso:
PostgreSQL
  ↓
Hibernate
  ↓
Product
  ↓
Repository
  ↓
Service
  ↓
Controller
  ↓
JSON
33. Primera operación: INSERT
Se envía:
POST /api/products
con:
{
 "name": "Teclado mecánico",
 "price": 250000,
 "stock": 10,
 "active": true
}
Spring convierte el JSON en:
Product
Luego:
service.save(product);
y finalmente:
repository.save(product);
Hibernate genera SQL similar a:
INSERT INTO products
(name, price, stock, active)
VALUES
('Teclado mecánico', 250000, 10, true);
34. Actividad de observación
Con:
spring.jpa.show-sql=true
el estudiante debe observar la consola.
Debe identificar el SQL generado por Hibernate.
La finalidad del ejercicio es comprobar que:
repository.save()
termina convirtiéndose en una operación SQL.
35. Consultar productos
Petición:
GET /api/products
Código:
repository.findAll();
SQL aproximado:
SELECT
   *
FROM
   products;
36. Consultar por ID
Petición:
GET /api/products/1
Código:
repository.findById(1L);
El resultado es:
Optional<Product>
Esto es importante.
Puede existir:
Product
o puede no existir.
37. ¿Por qué Optional?
Supóngase:
repository.findById(100L);
pero el producto no existe.
En lugar de devolver directamente un objeto inexistente, Java utiliza:
Optional<Product>
Esto obliga al programador a considerar los dos escenarios:
Encontrado
No encontrado
38. Actualizar una entidad
Supóngase:
Product product = service.findById(id)
       .orElseThrow();


product.setPrice(
   new BigDecimal("300000")
);


service.save(product);
Como la entidad ya existe y posee ID, Hibernate puede generar:
UPDATE products
SET price = 300000
WHERE id = ...;
39. Eliminar una entidad
Código:
repository.deleteById(1L);
SQL conceptual:
DELETE FROM products
WHERE id = 1;
40. CRUD y JPA
Las operaciones principales pueden resumirse así:
CRUD
	Repository
	SQL conceptual
	Create
	save()
	INSERT
	Read
	findAll()
	SELECT
	Read
	findById()
	SELECT WHERE
	Update
	save()
	UPDATE
	Delete
	deleteById()
	DELETE
	41. Consultas derivadas
Spring Data JPA permite construir consultas a partir del nombre de un método.
Ejemplo:
List<Product> findByActive(Boolean active);
Spring interpreta:
find
By
Active
y genera una consulta aproximada:
SELECT *
FROM products
WHERE active = ?;
42. Ejemplo: buscar por nombre
List<Product> findByName(String name);
Puede utilizarse:
repository.findByName("Mouse");
43. Ejemplo: búsqueda parcial
List<Product>
findByNameContainingIgnoreCase(String text);
Uso:
repository
   .findByNameContainingIgnoreCase("mouse");
SQL conceptual:
WHERE LOWER(name)
LIKE LOWER('%mouse%')
44. Ejemplo: productos con precio mayor
List<Product>
findByPriceGreaterThan(BigDecimal price);
Uso:
repository.findByPriceGreaterThan(
   new BigDecimal("100000")
);
45. Ejemplo: combinar condiciones
List<Product>
findByActiveAndStockGreaterThan(
       Boolean active,
       Integer stock);
Uso:
repository
   .findByActiveAndStockGreaterThan(
       true,
       0
   );
Interpretación:
WHERE active = true
AND stock > 0
46. Ejercicio guiado 1
Agregar al repositorio:
List<Product>
findByActive(Boolean active);
Crear en el servicio:
public List<Product> findActive() {
   return repository.findByActive(true);
}
Crear en el controlador:
@GetMapping("/active")
public List<Product> findActive() {
   return service.findActive();
}
Probar:
GET /api/products/active
47. Ejercicio guiado 2
Agregar:
List<Product>
findByNameContainingIgnoreCase(String name);
Crear un endpoint:
GET /api/products/search?name=mouse
El estudiante debe comprobar que Spring Data genera la consulta sin escribir SQL manualmente.
48. Ejercicio guiado 3
Crear un método para buscar productos entre dos precios.
Firma esperada:
List<Product>
findByPriceBetween(
       BigDecimal min,
       BigDecimal max);
Ejemplo:
100000 - 500000
49. Actividad de análisis
Observar los siguientes métodos:
findByName(String name)
findByPriceGreaterThan(BigDecimal price)
findByActiveAndStockGreaterThan(
       Boolean active,
       Integer stock)
El estudiante debe escribir el SQL aproximado que cree que Hibernate ejecutará.
La intención es reforzar la relación entre:
Query Method
y:
SQL
50. Actividad práctica integradora
Catálogo de productos
Se deberá ampliar la entidad agregando:
brand
category
description
createdAt
Después se implementarán consultas para:
Buscar por marca
Buscar por categoría
Buscar activos
Buscar por rango de precios
Buscar por texto en el nombre
Buscar productos con stock disponible
51. Reto pedagógico
Implementar:
List<Product> findByBrand(String brand);
List<Product> findByCategory(String category);
List<Product>
findByPriceBetween(
   BigDecimal min,
   BigDecimal max);
List<Product>
findByStockGreaterThan(Integer stock);
List<Product>
findByActiveOrderByNameAsc(Boolean active);
Luego responder:
1. ¿Qué SQL aproximado representa cada método?
2. ¿Qué palabra del método define la condición?
3. ¿Qué parte define el ordenamiento?
4. ¿Qué ocurriría si el nombre del atributo no existe?
52. Errores frecuentes
Error: olvidar @Entity
Sin:
@Entity
JPA no reconocerá la clase como entidad persistente.
Error: olvidar @Id
Toda entidad debe tener identificador.
Error: base de datos inexistente
Si:
store_db
no existe, la conexión puede fallar.
Error: credenciales incorrectas
Revisar:
spring.datasource.username
spring.datasource.password
Error: nombre de método incorrecto
Por ejemplo:
findByNames(...)
si el atributo real se llama:
name
Spring Data no podrá construir correctamente la consulta.
53. Buenas prácticas iniciales
Utilizar BigDecimal para dinero
Es preferible:
BigDecimal price;
en lugar de:
Double price;
cuando se trabajan valores monetarios.
Mantener separadas las responsabilidades
Preferir:
Controller
   ↓
Service
   ↓
Repository
No exponer siempre entidades directamente
En sistemas reales suelen utilizarse:
DTO
Mapper
Entity
pero este concepto puede introducirse después de dominar JPA básico.
54. Mapa conceptual
JPA
│
├── Entidades
│      └── @Entity
│
├── Identificadores
│      └── @Id
│
├── Mapeo
│      ├── @Table
│      └── @Column
│
├── Persistencia
│      └── Hibernate
│
└── Acceso a datos
      └── Spring Data JPA
             ↓
      JpaRepository
55. Preguntas de comprobación
1. ¿Qué significa JPA?
2. ¿JPA es una implementación o una especificación?
3. ¿Qué función cumple Hibernate?
4. ¿Qué función cumple Spring Data JPA?
5. ¿Qué significa ORM?
6. ¿Qué indica @Entity?
7. ¿Para qué sirve @Id?
8. ¿Qué hace @GeneratedValue?
9. ¿Qué representa JpaRepository<Product, Long>?
10. ¿Qué hace save()?
11. ¿Qué devuelve findById()?
12. ¿Qué diferencia existe entre findAll() y findById()?
13. ¿Qué es una consulta derivada?
14. ¿Qué función cumple la capa Service?
15. ¿Qué SQL aproximado genera findByPriceGreaterThan()?
56. Autoevaluación
El estudiante puede considerarse preparado para continuar si puede explicar sin mirar el código:
¿Qué es una entidad?
¿Qué hace un repositorio?
¿Qué papel cumple Hibernate?
¿Cómo termina un save() convirtiéndose en SQL?
¿Por qué JpaRepository necesita Product y Long?
57. Resumen final
La idea más importante de esta guía puede representarse así:
Objeto Java
   ↓
@Entity
   ↓
JPA
   ↓
Hibernate
   ↓
SQL
   ↓
Base de datos
Spring Data JPA agrega una capa adicional:
Repository
   ↓
JpaRepository
   ↓
Spring Data JPA
   ↓
Hibernate
   ↓
Base de datos
Por tanto:
JPA define las reglas.
Hibernate implementa esas reglas.
Spring Data JPA simplifica el acceso.
El estudiante no necesita escribir manualmente todas las sentencias SQL para realizar operaciones básicas.
En muchos casos puede trabajar con:
save()
findAll()
findById()
deleteById()
y permitir que Spring Data JPA y Hibernate realicen gran parte del trabajo.
58. Ruta de aprendizaje recomendada
Después de dominar esta introducción, el proceso pedagógico recomendado es avanzar en este orden:
1. Entidades y repositorios
       ↓
2. Query Methods
       ↓
3. Relaciones JPA
       ↓
4. JPQL y @Query
       ↓
5. DTO y Mapper
       ↓
6. Paginación
       ↓
7. @Transactional
       ↓
8. FetchType
       ↓
9. Cascade
       ↓
10. Auditoría y migraciones
De esta manera el estudiante construye primero una base conceptual sólida antes de abordar características más avanzadas.
Introducción a Spring Boot
1. Introducción
El desarrollo de aplicaciones empresariales con Java ha evolucionado considerablemente durante los últimos años. Aunque Java proporciona una plataforma robusta, segura y ampliamente utilizada para construir sistemas de software, el desarrollo de aplicaciones web modernas requiere componentes adicionales relacionados con servidores web, persistencia de datos, seguridad, configuración, servicios REST y gestión de dependencias.
Durante muchos años, el framework Spring Framework permitió simplificar gran parte de estas tareas. Sin embargo, los proyectos tradicionales desarrollados con Spring podían requerir una cantidad considerable de configuración.
Para simplificar este proceso surgió Spring Boot, una extensión del ecosistema Spring cuyo objetivo principal consiste en facilitar la creación de aplicaciones Java independientes, configurables y listas para ejecutarse.
Spring Boot permite que el desarrollador pueda concentrarse principalmente en la lógica de negocio, mientras que el framework se encarga automáticamente de buena parte de la configuración técnica requerida por la aplicación.
Con Spring Boot es posible desarrollar, entre otros:
* aplicaciones web;
* APIs REST;
* sistemas empresariales;
* microservicios;
* aplicaciones conectadas con bases de datos;
* servicios distribuidos;
* sistemas de autenticación y autorización;
* aplicaciones basadas en eventos;
* sistemas integrados con servicios externos.
Esta guía introduce los conceptos fundamentales necesarios para comprender la estructura y funcionamiento de una aplicación desarrollada con Spring Boot.
________________


2. Objetivos de aprendizaje
Al finalizar esta guía, el estudiante estará en capacidad de:
1. Explicar qué es Spring Framework y qué función cumple Spring Boot dentro de su ecosistema.
2. Identificar las principales características de Spring Boot.
3. Reconocer la estructura básica de un proyecto Spring Boot.
4. Comprender el concepto de inversión de control.
5. Comprender el concepto de inyección de dependencias.
6. Identificar los principales estereotipos utilizados por Spring.
7. Crear un proyecto utilizando Spring Initializr.
8. Ejecutar una aplicación Spring Boot.
9. Crear un controlador REST básico.
10. Comprender el funcionamiento del servidor web embebido.
11. Utilizar el archivo application.properties.
12. Organizar correctamente los componentes de una aplicación.
13. Implementar un pequeño servicio REST siguiendo una arquitectura básica por capas.
________________


3. Conocimientos previos
Antes de estudiar Spring Boot se recomienda que el estudiante tenga conocimientos básicos sobre:
* Java;
* programación orientada a objetos;
* clases y objetos;
* métodos;
* atributos;
* constructores;
* interfaces;
* encapsulamiento;
* herencia;
* excepciones;
* colecciones;
* Maven o Gradle;
* conceptos básicos de HTTP.
También resulta conveniente conocer los conceptos fundamentales de bases de datos relacionales, aunque estos no son obligatorios para esta primera introducción.
________________


4. ¿Qué es Spring Framework?
Spring Framework es un framework para el desarrollo de aplicaciones empresariales utilizando Java.
Su propósito consiste en proporcionar herramientas, componentes y abstracciones que permitan construir aplicaciones de una manera más organizada, modular y mantenible.
Spring proporciona diferentes módulos para resolver problemas comunes del desarrollo de software.
Entre ellos se encuentran:
* Spring Core;
* Spring MVC;
* Spring Data;
* Spring Security;
* Spring Web;
* Spring Integration;
* Spring Messaging;
* Spring Test.
El núcleo de Spring se basa principalmente en dos conceptos fundamentales:
Inversión de Control
e
Inyección de Dependencias.
Estos conceptos permiten disminuir el acoplamiento entre los componentes de una aplicación.
________________


5. ¿Qué es Spring Boot?
Spring Boot es una herramienta perteneciente al ecosistema Spring que simplifica la creación y configuración de aplicaciones basadas en Spring Framework.
Spring Boot no reemplaza a Spring Framework.
Por el contrario:
Spring Boot utiliza Spring Framework y automatiza gran parte de su configuración.
En una aplicación Spring tradicional, el desarrollador debía realizar manualmente diferentes configuraciones.
Spring Boot introduce mecanismos que permiten detectar automáticamente las dependencias disponibles en el proyecto y configurar componentes apropiados para ellas.
Por ejemplo, cuando se agrega la dependencia correspondiente a aplicaciones web, Spring Boot puede configurar automáticamente:
* Spring MVC;
* un servidor web embebido;
* manejo de peticiones HTTP;
* conversión de objetos Java a JSON;
* configuración básica del contexto web.
Esto permite reducir considerablemente la cantidad de configuración necesaria.
________________


6. Filosofía de Spring Boot
Spring Boot utiliza una filosofía conocida como:
Convention over Configuration.
Esta expresión puede traducirse como:
Convención sobre configuración.
La idea consiste en proporcionar configuraciones predeterminadas razonables.
El desarrollador solamente necesita modificar aquellas configuraciones que sean diferentes de las convenciones utilizadas por el framework.
Por ejemplo, una aplicación web Spring Boot generalmente utiliza el puerto:
8080
No es necesario configurar explícitamente este puerto.
Spring Boot lo utiliza como valor predeterminado.
Si el desarrollador necesita modificarlo, puede hacerlo mediante configuración.
________________


7. Principales características de Spring Boot
Spring Boot proporciona diferentes características que simplifican el desarrollo.
7.1 Auto-configuración
Spring Boot analiza las dependencias presentes en la aplicación e intenta configurar automáticamente los componentes necesarios.
Este mecanismo se denomina:
Auto Configuration
Por ejemplo, si el proyecto contiene:
spring-boot-starter-web
Spring Boot detectará que se pretende desarrollar una aplicación web.
A partir de esa información configurará diferentes componentes automáticamente.
________________


7.2 Dependencias Starter
Los Starters son conjuntos de dependencias previamente agrupadas.
Por ejemplo:
spring-boot-starter-web
incluye dependencias relacionadas con el desarrollo de aplicaciones web.
Otro ejemplo:
spring-boot-starter-data-jpa
incluye las dependencias utilizadas para trabajar con persistencia utilizando JPA.
Esto evita que el desarrollador deba buscar manualmente cada biblioteca.
________________


8. Algunos Starters importantes
Entre los starters más utilizados se encuentran:
Starter
	Propósito
	spring-boot-starter-web
	Desarrollo de aplicaciones web y APIs REST
	spring-boot-starter-data-jpa
	Persistencia utilizando JPA e Hibernate
	spring-boot-starter-security
	Seguridad y autenticación
	spring-boot-starter-validation
	Validación de datos
	spring-boot-starter-test
	Pruebas unitarias e integración
	spring-boot-starter-thymeleaf
	Desarrollo de aplicaciones web con vistas HTML
	spring-boot-starter-actuator
	Monitoreo de aplicaciones
	________________


9. Servidor embebido
Una de las características más importantes de Spring Boot es la posibilidad de ejecutar aplicaciones web utilizando un servidor integrado dentro de la propia aplicación.
Esto se denomina:
Servidor embebido
En proyectos Java web tradicionales era común instalar un servidor como:
Tomcat
y posteriormente desplegar dentro de él un archivo:
WAR
Con Spring Boot el servidor puede incluirse directamente dentro del proyecto.
Por esta razón, una aplicación puede ejecutarse simplemente mediante:
java -jar aplicacion.jar
El servidor se inicia automáticamente.
________________


10. Ventajas de utilizar Spring Boot
Spring Boot ofrece diferentes ventajas.
Entre ellas se encuentran:
* configuración reducida;
* desarrollo rápido;
* integración sencilla con bases de datos;
* servidor web embebido;
* amplia integración con el ecosistema Spring;
* facilidad para desarrollar APIs REST;
* facilidad para implementar microservicios;
* soporte para pruebas;
* gestión simplificada de dependencias;
* configuración mediante propiedades;
* integración con herramientas de monitoreo.
________________


11. Spring Framework frente a Spring Boot
Aunque ambos conceptos están relacionados, no representan exactamente lo mismo.
Spring Framework
	Spring Boot
	Framework principal
	Herramienta construida sobre Spring
	Requiere mayor configuración
	Automatiza configuraciones
	Proporciona IoC y DI
	Utiliza IoC y DI
	Permite construir aplicaciones empresariales
	Facilita la creación y ejecución de esas aplicaciones
	Puede requerir configuración explícita
	Favorece configuración automática
	Puede entenderse la relación mediante la siguiente idea:
Spring Framework
     │
     │
     ▼
Infraestructura principal
     │
     ▼
Spring Boot
     │
     ▼
Simplificación + Auto-configuración
________________


12. Arquitectura básica de una aplicación Spring Boot
Una aplicación básica puede organizarse mediante diferentes capas.
Una estructura sencilla podría ser:
Controller
   ↓
Service
   ↓
Repository
   ↓
Database
Cada capa tiene una responsabilidad específica.
________________


13. Capa Controller
El controlador recibe las solicitudes provenientes de los clientes.
Por ejemplo:
GET /products
El controlador interpreta esta solicitud y delega la operación correspondiente.
Ejemplo:
@RestController
@RequestMapping("/products")
public class ProductController {


}
El controlador no debería contener toda la lógica de negocio.
Su responsabilidad principal consiste en recibir solicitudes y devolver respuestas.
________________


14. Capa Service
La capa de servicio contiene la lógica de negocio.
Ejemplo:
@Service
public class ProductService {


}
En esta capa pueden encontrarse operaciones como:
* calcular precios;
* verificar reglas;
* aplicar descuentos;
* registrar ventas;
* validar condiciones del negocio.
________________


15. Capa Repository
La capa Repository se utiliza normalmente para interactuar con la base de datos.
Ejemplo:
@Repository
public class ProductRepository {


}
Cuando se utiliza Spring Data JPA, muchas operaciones pueden generarse automáticamente.
________________


16. Flujo de una solicitud
Supóngase que un cliente realiza la siguiente solicitud:
GET /products
El flujo podría ser:
Cliente
  ↓
Controller
  ↓
Service
  ↓
Repository
  ↓
Base de datos
Posteriormente la respuesta sigue el camino contrario.
Base de datos
  ↓
Repository
  ↓
Service
  ↓
Controller
  ↓
Cliente
________________


17. Inversión de Control
Uno de los conceptos centrales de Spring es la:
Inversión de Control
También conocida como:
IoC
o:
Inversion of Control
En una aplicación tradicional, las clases suelen crear directamente los objetos que necesitan.
Ejemplo:
public class OrderService {


   private ProductRepository repository =
       new ProductRepository();


}
En este caso:
OrderService
controla directamente la creación de:
ProductRepository
Esto genera acoplamiento.
Con Spring se invierte esta responsabilidad.
La aplicación no crea manualmente las dependencias.
Spring las crea y posteriormente las proporciona a los componentes que las necesitan.
________________


18. Inyección de Dependencias
La inyección de dependencias es el mecanismo mediante el cual Spring proporciona automáticamente a una clase los objetos que necesita.
Por ejemplo:
@Service
public class ProductService {


   private final ProductRepository repository;


   public ProductService(ProductRepository repository) {
       this.repository = repository;
   }


}
Spring detecta que:
ProductService
necesita un objeto:
ProductRepository
Por lo tanto, crea dicho objeto y lo entrega al constructor.
Este mecanismo reduce el acoplamiento.
________________


19. Contenedor IoC
Spring utiliza un componente denominado:
IoC Container
El contenedor administra los objetos de la aplicación.
Entre sus responsabilidades se encuentran:
* crear objetos;
* configurar objetos;
* administrar dependencias;
* conectar componentes;
* administrar su ciclo de vida.
Los objetos administrados por Spring reciben el nombre de:
Beans
________________


20. ¿Qué es un Bean?
Un Bean es un objeto administrado por el contenedor de Spring.
Por ejemplo:
@Service
public class ProductService {


}
Spring detecta la anotación:
@Service
y crea automáticamente una instancia de la clase.
Conceptualmente:
ProductService
       ↓
Spring crea el objeto
       ↓
Spring Container
Posteriormente este objeto puede ser inyectado en otros componentes.
________________


21. Component Scanning
Spring necesita identificar cuáles clases deben convertirse en Beans.
Para ello utiliza un mecanismo denominado:
Component Scanning
Spring examina los paquetes de la aplicación buscando clases marcadas con determinadas anotaciones.
Las principales son:
@Component
@Service
@Repository
@Controller
@RestController
________________


22. @Component
@Component representa un componente genérico administrado por Spring.
Ejemplo:
@Component
public class EmailSender {


}
Spring creará automáticamente una instancia de esta clase.
________________


23. @Service
@Service identifica componentes asociados principalmente con lógica de negocio.
Ejemplo:
@Service
public class CustomerService {


}
Semánticamente indica que la clase pertenece a la capa de servicios.
________________


24. @Repository
@Repository identifica componentes relacionados con acceso a datos.
Ejemplo:
@Repository
public class CustomerRepository {


}
Normalmente esta capa interactúa con:
* bases de datos;
* JPA;
* Hibernate;
* JDBC.
________________


25. @Controller
@Controller se utiliza principalmente en aplicaciones web tradicionales que trabajan con vistas.
Ejemplo:
@Controller
public class HomeController {


}
________________


26. @RestController
@RestController se utiliza para desarrollar servicios REST.
Ejemplo:
@RestController
public class ProductController {


}
Esta anotación combina conceptualmente:
@Controller
y:
@ResponseBody
Esto permite que los métodos puedan devolver datos directamente al cliente.
________________


27. Creación de un proyecto con Spring Initializr
Spring proporciona una herramienta denominada:
Spring Initializr
Esta herramienta permite generar automáticamente la estructura inicial de un proyecto.
Durante la creación normalmente se seleccionan diferentes opciones.
Ejemplo:
Project: Maven
Language: Java
Packaging: Jar
Java: 21
También se definen datos relacionados con el proyecto.
Ejemplo:
Group:
com.example


Artifact:
store


Name:
store
________________


28. Group
El campo:
Group
representa generalmente la organización o dominio lógico responsable del proyecto.
Por ejemplo:
com.breakline
________________


29. Artifact
El campo:
Artifact
representa el identificador del proyecto.
Ejemplo:
store-api
Esto puede producir un archivo:
store-api.jar
________________


30. Dependencia Spring Web
Para desarrollar una API REST se agrega normalmente:
Spring Web
Esto incluye componentes necesarios para trabajar con:
* HTTP;
* REST;
* JSON;
* controladores;
* rutas;
* servidor web.
________________


31. Estructura inicial del proyecto
Después de generar el proyecto se obtiene una estructura similar a:
store
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.example.store
│   │   │       └── StoreApplication.java
│   │   │
│   │   └── resources
│   │       └── application.properties
│   │
│   └── test
│
├── pom.xml
│
└── mvnw
________________


32. Clase principal
La clase principal suele tener una estructura similar a:
package com.example.store;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class StoreApplication {


   public static void main(String[] args) {


       SpringApplication.run(
               StoreApplication.class,
               args
       );
   }
}
Esta clase representa el punto de inicio de la aplicación.
________________


33. @SpringBootApplication
La anotación:
@SpringBootApplication
es una de las anotaciones más importantes de Spring Boot.
Permite habilitar diferentes funcionalidades.
Conceptualmente integra:
@Configuration
@EnableAutoConfiguration
@ComponentScan
________________


34. @Configuration
Indica que una clase puede contener configuraciones de Spring.
________________


35. @EnableAutoConfiguration
Permite que Spring Boot configure automáticamente diferentes componentes de acuerdo con las dependencias disponibles.
________________


36. @ComponentScan
Permite que Spring busque componentes dentro de los paquetes de la aplicación.
Por esta razón resulta importante ubicar correctamente la clase principal.
Una estructura recomendada sería:
com.example.store


├── StoreApplication
├── controller
├── service
├── repository
├── model
└── config
La clase principal se encuentra en el paquete raíz.
Spring puede entonces detectar los componentes ubicados en los subpaquetes.
________________


37. SpringApplication.run()
La instrucción:
SpringApplication.run(
   StoreApplication.class,
   args
);
inicia Spring Boot.
Durante este proceso se ejecutan diferentes operaciones.
Entre ellas:
1. se crea el contexto de Spring;
2. se buscan componentes;
3. se crean Beans;
4. se resuelven dependencias;
5. se aplica auto-configuración;
6. se inicia el servidor web;
7. la aplicación queda disponible para recibir solicitudes.
________________


38. Ejecución de la aplicación
Una aplicación Maven puede ejecutarse mediante:
./mvnw spring-boot:run
En Windows:
mvnw.cmd spring-boot:run
También puede ejecutarse desde el IDE.
Cuando la aplicación inicia correctamente puede observarse información similar a:
Started StoreApplication
El servidor normalmente queda disponible en:
http://localhost:8080
________________


39. Creación del primer endpoint
Se crea una clase:
HelloController
Ejemplo:
package com.example.store.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {


   @GetMapping("/hello")
   public String hello() {


       return "Hello Spring Boot";
   }
}
________________


40. Explicación del controlador
La anotación:
@RestController
le indica a Spring que la clase contiene endpoints REST.
La anotación:
@GetMapping("/hello")
indica que el método responderá solicitudes HTTP:
GET
dirigidas a:
/hello
Cuando un cliente solicita:
http://localhost:8080/hello
Spring ejecuta:
hello()
y devuelve:
Hello Spring Boot
________________


41. ¿Qué es un endpoint?
Un endpoint representa una dirección mediante la cual un cliente puede interactuar con una API.
Por ejemplo:
GET /products
puede representar la consulta de productos.
Otro endpoint:
POST /products
puede representar el registro de un producto.
Un endpoint está compuesto generalmente por:
Método HTTP + URI
Ejemplo:
GET /customers
________________


42. Métodos HTTP
Las APIs REST utilizan diferentes métodos HTTP.
Los principales son:
Método
	Uso habitual
	GET
	Consultar
	POST
	Crear
	PUT
	Actualizar completamente
	PATCH
	Actualizar parcialmente
	DELETE
	Eliminar
	________________


43. Ejemplo práctico: API de productos
Se desarrollará una API sencilla para administrar productos.
Inicialmente no se utilizará base de datos.
Los datos se almacenarán temporalmente en memoria.
La estructura será:
Product
ProductService
ProductController
________________


44. Creación del modelo Product
Se crea el paquete:
model
y posteriormente la clase:
package com.example.store.model;


public class Product {


   private Long id;
   private String name;
   private Double price;


   public Product() {
   }


   public Product(
           Long id,
           String name,
           Double price
   ) {


       this.id = id;
       this.name = name;
       this.price = price;
   }


   public Long getId() {
       return id;
   }


   public void setId(Long id) {
       this.id = id;
   }


   public String getName() {
       return name;
   }


   public void setName(String name) {
       this.name = name;
   }


   public Double getPrice() {
       return price;
   }


   public void setPrice(Double price) {
       this.price = price;
   }
}
La clase representa un producto del sistema.
Contiene:
id
name
price
________________


45. Creación del servicio
Se crea:
package com.example.store.service;


import com.example.store.model.Product;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class ProductService {


   private final List<Product> products =
           new ArrayList<>();


   public ProductService() {


       products.add(
           new Product(
               1L,
               "Laptop",
               3500000.0
           )
       );


       products.add(
           new Product(
               2L,
               "Mouse",
               80000.0
           )
       );
   }


   public List<Product> findAll() {


       return products;
   }
}
La anotación:
@Service
hace que Spring administre esta clase.
________________


46. Creación del controlador
Se crea:
package com.example.store.controller;


import com.example.store.model.Product;
import com.example.store.service.ProductService;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {


   private final ProductService service;


   public ProductController(
           ProductService service
   ) {


       this.service = service;
   }


   @GetMapping
   public List<Product> findAll() {


       return service.findAll();
   }
}
________________


47. Explicación de la inyección
El controlador necesita:
ProductService
Sin Spring posiblemente tendría que escribir:
ProductService service =
   new ProductService();
Sin embargo, Spring crea automáticamente el servicio.
Después lo inyecta mediante el constructor:
public ProductController(
       ProductService service
) {


   this.service = service;
}
Esto representa inyección de dependencias.
________________


48. Probar el endpoint
Se ejecuta la aplicación.
Posteriormente se realiza la petición:
GET
http://localhost:8080/products
El resultado será similar a:
[
 {
   "id": 1,
   "name": "Laptop",
   "price": 3500000.0
 },
 {
   "id": 2,
   "name": "Mouse",
   "price": 80000.0
 }
]
________________


49. Conversión automática a JSON
El controlador devuelve:
List<Product>
Sin embargo, el cliente recibe:
[
 {
   "id": 1,
   "name": "Laptop"
 }
]
Spring Boot realiza automáticamente la serialización.
Esto significa:
Objeto Java
     ↓
Serialización
     ↓
JSON
La biblioteca utilizada normalmente dentro del ecosistema Spring se encarga de realizar esta conversión.
________________


50. RequestMapping
La anotación:
@RequestMapping("/products")
define una ruta base.
Por ejemplo:
@RestController
@RequestMapping("/products")
public class ProductController {
}
Esto significa que todos los endpoints definidos dentro del controlador comenzarán con:
/products
________________


51. PathVariable
Se puede recibir información desde la URL.
Ejemplo:
GET /products/10
El valor:
10
puede capturarse utilizando:
@PathVariable
Ejemplo:
@GetMapping("/{id}")
public String findById(
       @PathVariable Long id
) {


   return "Product: " + id;
}
Una solicitud:
GET /products/5
producirá:
Product: 5
________________


52. RequestParam
También pueden recibirse parámetros.
Ejemplo:
/products/search?name=Laptop
Se puede utilizar:
@GetMapping("/search")
public String search(
       @RequestParam String name
) {


   return "Searching: " + name;
}
________________


53. RequestBody
Cuando un cliente envía información JSON, Spring puede convertirla automáticamente en un objeto Java.
Ejemplo:
@PostMapping
public Product create(
       @RequestBody Product product
) {


   return product;
}
El cliente podría enviar:
{
 "id": 3,
 "name": "Keyboard",
 "price": 150000
}
Spring realiza:
JSON
↓
Deserialización
↓
Objeto Product
________________


54. Agregar productos
Se modifica el servicio:
public Product add(Product product) {


   products.add(product);


   return product;
}
En el controlador:
@PostMapping
public Product create(
       @RequestBody Product product
) {


   return service.add(product);
}
________________


55. API resultante
La API ya dispone de dos operaciones.
Consultar:
GET /products
Crear:
POST /products
Esto representa una primera aproximación a una API REST.
________________


56. application.properties
Spring Boot permite configurar la aplicación mediante el archivo:
application.properties
Este archivo se encuentra normalmente en:
src/main/resources
Ejemplo:
spring.application.name=store
________________


57. Cambiar el puerto
El puerto predeterminado puede modificarse.
Ejemplo:
server.port=8081
La aplicación quedará disponible en:
http://localhost:8081
________________


58. Configuración personalizada
También pueden definirse propiedades propias.
Ejemplo:
store.name=My Store
Posteriormente pueden recuperarse desde Spring.
________________


59. Anatomía general de una aplicación
Un proyecto básico podría organizarse así:
src/main/java
└── com.example.store
   │
   ├── StoreApplication.java
   │
   ├── controller
   │   └── ProductController.java
   │
   ├── service
   │   └── ProductService.java
   │
   ├── repository
   │
   ├── model
   │   └── Product.java
   │
   └── config
Cada paquete tiene una responsabilidad específica.
________________


60. Separación de responsabilidades
Una aplicación correctamente organizada evita concentrar toda la lógica en una sola clase.
Una estructura incorrecta sería:
Controller
├── recibe HTTP
├── valida
├── calcula
├── consulta BD
├── transforma datos
└── responde
Esto genera controladores demasiado grandes.
Una estructura mejor sería:
Controller
  ↓
Service
  ↓
Repository
Cada capa desempeña una función determinada.
________________


61. Ciclo básico de Spring Boot
Cuando inicia una aplicación puede imaginarse el siguiente flujo:
main()
  ↓
SpringApplication.run()
  ↓
Spring Boot inicia
  ↓
Escaneo de componentes
  ↓
Creación de Beans
  ↓
Inyección de dependencias
  ↓
Auto-configuración
  ↓
Servidor web inicia
  ↓
Aplicación disponible
________________


62. Ejercicio guiado 1: crear un proyecto
Se propone desarrollar una aplicación denominada:
student-api
Configuración sugerida:
Project:
Maven


Language:
Java


Packaging:
Jar
Dependencia:
Spring Web
Después de generar el proyecto, se debe ejecutar.
El estudiante debe verificar que el servidor inicia correctamente.
________________


63. Ejercicio guiado 2: crear un controlador
Se debe crear:
StudentController
Ejemplo:
@RestController
@RequestMapping("/students")
public class StudentController {


   @GetMapping
   public String findAll() {


       return "Student list";
   }
}
Posteriormente se debe probar:
GET /students
________________


64. Ejercicio guiado 3: retornar un objeto
Se crea:
public class Student {


   private Long id;
   private String name;
   private String email;


   public Student(
           Long id,
           String name,
           String email
   ) {


       this.id = id;
       this.name = name;
       this.email = email;
   }


   public Long getId() {
       return id;
   }


   public String getName() {
       return name;
   }


   public String getEmail() {
       return email;
   }
}
Posteriormente:
@GetMapping("/example")
public Student example() {


   return new Student(
           1L,
           "Laura",
           "laura@email.com"
   );
}
Spring devolverá automáticamente JSON.
________________


65. Ejercicio guiado 4: trabajar con Service
Se crea:
@Service
public class StudentService {


   public List<Student> findAll() {


       return List.of(


           new Student(
               1L,
               "Laura",
               "laura@email.com"
           ),


           new Student(
               2L,
               "Carlos",
               "carlos@email.com"
           )
       );
   }
}
Posteriormente se inyecta:
@RestController
@RequestMapping("/students")
public class StudentController {


   private final StudentService service;


   public StudentController(
           StudentService service
   ) {


       this.service = service;
   }


   @GetMapping
   public List<Student> findAll() {


       return service.findAll();
   }
}
________________


66. Ejercicio guiado 5: recibir parámetros
Se crea:
@GetMapping("/{id}")
public String findById(
       @PathVariable Long id
) {


   return "Student id: " + id;
}
Se prueba:
GET /students/10
Resultado:
Student id: 10
________________


67. Ejercicio guiado 6: recibir JSON
Se crea:
@PostMapping
public Student create(
       @RequestBody Student student
) {


   return student;
}
Se envía:
{
 "id": 3,
 "name": "Andrea",
 "email": "andrea@email.com"
}
________________


68. Taller práctico
Caso: sistema de gestión de cursos
Se requiere desarrollar una API sencilla para administrar cursos.
Cada curso tendrá:
id
name
duration
price
Se deberá crear:
Course
CourseService
CourseController
La aplicación deberá permitir:
GET /courses
Consultar todos los cursos.
GET /courses/{id}
Consultar un curso.
POST /courses
Crear un curso.
________________


69. Paso 1: crear Course
public class Course {


   private Long id;
   private String name;
   private Integer duration;
   private Double price;


}
El estudiante deberá implementar:
* constructor;
* getters;
* setters.
________________


70. Paso 2: crear CourseService
Se deberá crear una colección:
private final List<Course> courses =
   new ArrayList<>();
Y los métodos:
findAll()
findById()
add()
________________


71. Paso 3: crear CourseController
Se utilizarán las anotaciones:
@RestController
@RequestMapping
@GetMapping
@PostMapping
@PathVariable
@RequestBody
________________


72. Resultado esperado
La aplicación deberá responder:
GET /courses
con:
[
 {
   "id": 1,
   "name": "Java Fundamentals",
   "duration": 40,
   "price": 250000
 }
]
________________


73. Reto práctico
Se propone desarrollar una pequeña API para administrar libros.
Cada libro tendrá:
id
title
author
price
La API deberá implementar:
GET /books
GET /books/{id}
POST /books
DELETE /books/{id}
La aplicación deberá utilizar:
Book
BookService
BookController
Los datos permanecerán temporalmente en memoria.
________________


74. Preguntas de comprensión
El estudiante deberá responder:
1. ¿Qué problema intenta resolver Spring Boot?
2. ¿Cuál es la diferencia entre Spring Framework y Spring Boot?
3. ¿Qué significa inversión de control?
4. ¿Qué significa inyección de dependencias?
5. ¿Qué es un Bean?
6. ¿Qué función cumple el contenedor IoC?
7. ¿Qué función cumple @SpringBootApplication?
8. ¿Qué diferencia existe entre @Controller y @RestController?
9. ¿Qué función cumple @Service?
10. ¿Qué función cumple @Repository?
11. ¿Para qué sirve @GetMapping?
12. ¿Para qué sirve @PostMapping?
13. ¿Cuál es la función de @RequestBody?
14. ¿Qué diferencia existe entre @PathVariable y @RequestParam?
15. ¿Qué es un servidor embebido?
16. ¿Qué función cumple application.properties?
17. ¿Qué significa auto-configuración?
18. ¿Qué es un Starter?
19. ¿Qué función cumple SpringApplication.run()?
20. ¿Por qué se recomienda separar Controller, Service y Repository?
________________


75. Errores frecuentes
Error 1: colocar clases fuera del paquete principal
Una estructura problemática puede ser:
com.example.app
   Application.java


com.other.controller
   ProductController.java
Spring podría no detectar automáticamente el controlador.
Se recomienda:
com.example.app
├── Application.java
├── controller
├── service
└── repository
________________


Error 2: olvidar @RestController
Si se crea:
public class ProductController {
}
Spring no reconocerá automáticamente la clase como controlador.
Debe utilizarse:
@RestController
________________


Error 3: utilizar new para crear servicios
Una práctica poco conveniente sería:
ProductService service =
   new ProductService();
En aplicaciones Spring se recomienda permitir que el contenedor administre estas dependencias.
________________


Error 4: colocar toda la lógica en Controller
El controlador debería actuar principalmente como puerta de entrada HTTP.
Las reglas de negocio deben ubicarse generalmente en servicios.
________________


Error 5: utilizar atributos públicos
Se recomienda mantener encapsulamiento.
En lugar de:
public String name;
se utiliza:
private String name;
junto con los mecanismos apropiados de acceso.
________________


76. Buenas prácticas iniciales
En proyectos Spring Boot se recomienda:
* utilizar nombres claros;
* separar responsabilidades;
* utilizar inyección mediante constructor;
* evitar crear manualmente objetos administrados por Spring;
* mantener controladores pequeños;
* mantener lógica de negocio en servicios;
* mantener acceso a datos en repositorios;
* organizar correctamente los paquetes;
* utilizar DTO cuando la aplicación aumenta de complejidad;
* manejar adecuadamente excepciones;
* validar los datos recibidos;
* documentar endpoints;
* implementar pruebas.
________________


77. Modelo mental de Spring Boot
Una forma sencilla de comprender Spring Boot consiste en imaginarlo como un administrador de objetos.
El desarrollador crea clases.
Ejemplo:
ProductController
ProductService
ProductRepository
Spring analiza estas clases.
Después crea objetos:
productController
productService
productRepository
Posteriormente conecta sus dependencias:
ProductController
      │
      ▼
ProductService
      │
      ▼
ProductRepository
Finalmente la aplicación queda preparada para recibir solicitudes.
________________


78. Resumen conceptual
Los conceptos fundamentales estudiados pueden representarse de la siguiente manera:
Spring Framework
      │
      ▼
Inversión de Control
      │
      ▼
Inyección de Dependencias
      │
      ▼
Beans
      │
      ▼
Spring Container
Spring Boot agrega:
Auto-configuración
Starters
Servidor embebido
Configuración simplificada
Para aplicaciones web se agrega:
Spring Web
      │
      ▼
Controller
      │
      ▼
Endpoints REST
      │
      ▼
HTTP + JSON
________________


79. Actividad integradora
Sistema de productos
El estudiante deberá implementar una aplicación denominada:
product-api
La aplicación administrará:
Product
con los atributos:
id
name
description
price
stock
Se deberán implementar los endpoints:
GET /products
Consulta todos los productos.
GET /products/{id}
Consulta un producto por identificador.
POST /products
Registra un producto.
DELETE /products/{id}
Elimina un producto.
La arquitectura deberá contener:
controller
service
model
En esta etapa no se utilizará base de datos.
Los datos se almacenarán utilizando:
ArrayList
________________


80. Criterios de evaluación
La actividad puede evaluarse mediante los siguientes criterios:
Criterio
	Porcentaje
	Estructura correcta del proyecto
	15 %
	Uso correcto de anotaciones
	20 %
	Implementación de Controller
	20 %
	Implementación de Service
	20 %
	Funcionamiento de endpoints
	15 %
	Calidad y organización del código
	10 %
	Total:
100 %
________________


81. Conclusión
Spring Boot constituye una de las herramientas más utilizadas dentro del ecosistema Java para desarrollar aplicaciones backend modernas.
Su principal objetivo consiste en simplificar el desarrollo de aplicaciones basadas en Spring mediante mecanismos como la auto-configuración, los starters y los servidores embebidos.
Sin embargo, para utilizar Spring Boot correctamente no basta con conocer sus anotaciones.
Resulta fundamental comprender los conceptos que sustentan el framework, especialmente:
Inversión de Control
Inyección de Dependencias
Beans
Contenedor IoC
Component Scanning
Separación de responsabilidades
Estos conceptos permiten comprender por qué Spring puede crear, administrar y conectar automáticamente los componentes de una aplicación.
Una vez comprendidos estos fundamentos, el estudiante estará preparado para avanzar hacia temas como:
Spring Data JPA
Hibernate
DTO
Validation
Exception Handling
Spring Security
JWT
Testing
OpenAPI
Docker
Arquitectura Hexagonal
Microservicios
Spring AI
La comprensión de estos fundamentos representa, por tanto, la base para desarrollar aplicaciones empresariales robustas y escalables utilizando Java y Spring Boot.




Guía teórico-práctica: Introducción a Spring Boot Security y JWT
1. Introducción
La seguridad en una aplicación web no consiste solamente en ocultar determinadas páginas. Una aplicación debe poder responder preguntas como:
* ¿Quién está realizando la petición?
* ¿Está autenticado?
* ¿Qué permisos tiene?
* ¿Puede acceder a este endpoint?
* ¿La petición contiene credenciales válidas?
* ¿El token sigue vigente?
* ¿El usuario puede modificar este recurso?
En Spring Boot, estas responsabilidades pueden gestionarse mediante Spring Security.
Cuando se construye una API REST, una estrategia muy frecuente consiste en utilizar Spring Security junto con JWT, JSON Web Token.
La arquitectura básica será:
Cliente
  ↓
POST /auth/login
  ↓
Spring Security
  ↓
Validación de credenciales
  ↓
JWT generado
  ↓
Cliente guarda token
  ↓
GET /api/products
Authorization: Bearer TOKEN
  ↓
Spring Security valida JWT
  ↓
Acceso permitido o rechazado
________________


2. Objetivos de aprendizaje
Al finalizar esta guía, el estudiante estará en capacidad de:
1. Comprender qué es Spring Security.
2. Diferenciar autenticación y autorización.
3. Comprender qué es JWT.
4. Identificar la estructura de un JWT.
5. Configurar seguridad básica en Spring Boot.
6. Crear usuarios con contraseña cifrada.
7. Utilizar PasswordEncoder.
8. Configurar SecurityFilterChain.
9. Comprender AuthenticationManager.
10. Implementar un endpoint de login.
11. Generar un JWT.
12. Enviar un JWT mediante Authorization.
13. Crear un filtro JWT.
14. Proteger endpoints.
15. Trabajar con roles y autoridades.
16. Comprender el flujo completo de autenticación.
________________


3. ¿Qué es Spring Security?
Spring Security es el framework de seguridad del ecosistema Spring.
Permite implementar:
Autenticación
Autorización
Protección de endpoints
Roles
Permisos
Cifrado de contraseñas
Sesiones
JWT
OAuth2
OpenID Connect
CSRF
CORS
En una API REST, normalmente se utiliza para interceptar cada petición y decidir si puede continuar.
Conceptualmente:
Petición HTTP
    ↓
Spring Security
    ↓
¿Está autenticado?
    ↓
¿Tiene permiso?
    ↓
Controller
________________


4. Autenticación y autorización
Estos dos conceptos deben diferenciarse claramente.
Autenticación
Responde:
¿Quién es el usuario?
Ejemplo:
email: admin@email.com
password: 123456
Si las credenciales son correctas:
Usuario autenticado
________________


Autorización
Responde:
¿Qué puede hacer ese usuario?
Por ejemplo:
ADMIN
→ crear
→ actualizar
→ eliminar
​
USER
→ consultar
Por tanto:
Autenticación
     ↓
¿Quién eres?
​
Autorización
     ↓
¿Qué puedes hacer?
________________


5. Ejemplo cotidiano
Imagine un edificio.
En la entrada:
Documento de identidad
↓
Autenticación
Después:
¿Tiene acceso al piso 10?
↓
Autorización
Una persona puede estar perfectamente identificada, pero no necesariamente autorizada para ingresar a todas las zonas.
________________


6. ¿Qué es JWT?
JWT significa:
JSON Web Token
Es un estándar utilizado para transportar información de forma compacta entre sistemas.
Un JWT normalmente se ve así:
eyJhbGciOiJIUzI1NiJ9.
eyJzdWIiOiJ1c2VyQGVtYWlsLmNvbSJ9.
xxxxxxxxxxxxxxxxxxxxxxxx
Tiene tres partes:
HEADER.PAYLOAD.SIGNATURE
________________


7. Estructura de JWT
Visualmente:
JWT
├── Header
├── Payload
└── Signature
________________


Header
Describe normalmente el algoritmo.
Ejemplo conceptual:
{
 "alg": "HS256",
 "typ": "JWT"
}
________________


Payload
Contiene claims.
Ejemplo:
{
 "sub": "admin@email.com",
 "role": "ADMIN",
 "iat": 1791220000,
 "exp": 1791223600
}
________________


Signature
Permite comprobar que el token no fue modificado.
Conceptualmente:
Header
+
Payload
+
Secret Key
     ↓
Signature
________________


8. JWT no cifra necesariamente la información
Este punto es fundamental.
Un JWT firmado:
NO significa
información secreta
El payload puede ser decodificado.
Por eso nunca debería colocarse dentro del token información como:
Contraseñas
Número completo de tarjeta
Secretos
Datos altamente sensibles
La firma sirve principalmente para detectar modificaciones.
________________


9. ¿Por qué usar JWT?
En una API REST se busca frecuentemente una autenticación sin sesión tradicional del servidor.
En un sistema basado en JWT:
Servidor
no necesita guardar una sesión HTTP tradicional


Cliente
envía token en cada petición
Flujo:
Login
↓
JWT
↓
Cliente
↓
Cada petición envía JWT
________________


10. ¿Dónde se envía el JWT?
Normalmente mediante:
Authorization: Bearer TOKEN
Por ejemplo:
GET /api/products HTTP/1.1
Host: localhost:8080
Authorization: Bearer eyJhbGciOi...
El prefijo:
Bearer
indica que se está enviando un token de acceso.
________________


11. Dependencias
Para comenzar se necesita Spring Security.
Maven:
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-security</artifactId>
</dependency>
También necesitaremos Spring Web:
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-web</artifactId>
</dependency>
Si utilizamos JPA:
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
Para JWT se necesita además una biblioteca específica. La biblioteca elegida puede variar según el proyecto.
________________


12. ¿Qué ocurre al agregar Spring Security?
Si solamente se agrega:
spring-boot-starter-security
Spring Boot protege la aplicación automáticamente.
Por ejemplo:
GET /api/products
ya no estará abierto libremente.
Spring crea una configuración de seguridad por defecto.
Esto permite comprobar que Spring Security realmente está interceptando las solicitudes.
________________


13. Arquitectura conceptual
Podemos dividir la seguridad en estas piezas:
SecurityConfig
     ↓
AuthenticationManager
     ↓
UserDetailsService
     ↓
PasswordEncoder
     ↓
JwtService
     ↓
JwtAuthenticationFilter
Cada componente cumple una responsabilidad distinta.
________________


14. Entidad User
Supongamos:
@Entity
@Table(name = "users")
public class User {


   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   private UUID id;


   @Column(
       nullable = false,
       unique = true
   )
   private String email;


   @Column(nullable = false)
   private String password;


   @Column(nullable = false)
   private String role;


   private boolean enabled;
}
Ejemplo:
email: admin@email.com
password: ...
role: ADMIN
enabled: true
La contraseña almacenada no debería estar en texto plano.
________________


15. Nunca guardar contraseñas directamente
Esto es incorrecto:
password = "123456"
en la base de datos.
Debe almacenarse un hash.
Por ejemplo:
10$...
Spring Security permite hacerlo con:
PasswordEncoder
________________


16. PasswordEncoder
Se puede registrar:
@Bean
public PasswordEncoder passwordEncoder() {
   return new BCryptPasswordEncoder();
}
Entonces:
String encoded =
       passwordEncoder.encode("123456");
produce un hash.
Para verificar:
passwordEncoder.matches(
       "123456",
       encoded
);
________________


17. ¿Por qué no comparar contraseñas directamente?
Nunca debe hacerse:
if (password.equals(user.getPassword())) {
}
si la contraseña está correctamente almacenada como hash.
Se debe utilizar:
passwordEncoder.matches(
       rawPassword,
       encodedPassword
);
________________


18. UserDetails
Spring Security trabaja internamente con:
UserDetails
Esta interfaz representa información necesaria para autenticar un usuario.
Incluye conceptos como:
username
password
authorities
enabled
accountNonLocked
credentialsNonExpired
Puede implementarse en una clase propia.
________________


19. Ejemplo CustomUserDetails
public class CustomUserDetails
       implements UserDetails {


   private final User user;


   public CustomUserDetails(User user) {
       this.user = user;
   }


   @Override
   public Collection<? extends GrantedAuthority>
   getAuthorities() {


       return List.of(
               new SimpleGrantedAuthority(
                       "ROLE_" + user.getRole()
               )
       );
   }


   @Override
   public String getPassword() {
       return user.getPassword();
   }


   @Override
   public String getUsername() {
       return user.getEmail();
   }
}
________________


20. Authorities y Roles
Spring Security utiliza:
GrantedAuthority
para representar permisos.
Por ejemplo:
ROLE_ADMIN
ROLE_USER
PRODUCT_CREATE
PRODUCT_DELETE
Un rol puede verse como una agrupación de permisos.
________________


21. UserDetailsService
Otra pieza fundamental:
UserDetailsService
Su responsabilidad principal es buscar un usuario.
Ejemplo:
@Service
public class CustomUserDetailsService
       implements UserDetailsService {


   private final UserRepository repository;


   public CustomUserDetailsService(
           UserRepository repository
   ) {
       this.repository = repository;
   }


   @Override
   public UserDetails loadUserByUsername(
           String username
   ) throws UsernameNotFoundException {


       User user =
               repository.findByEmail(username)
                       .orElseThrow(() ->
                               new UsernameNotFoundException(
                                       "User not found"
                               )
                       );


       return new CustomUserDetails(user);
   }
}
________________


22. Flujo de búsqueda
email enviado
    ↓
UserDetailsService
    ↓
UserRepository
    ↓
Base de datos
    ↓
User
    ↓
CustomUserDetails
________________


23. Authentication
Spring Security representa al usuario autenticado mediante:
Authentication
Este objeto puede contener:
principal
credentials
authorities
authenticated
Ejemplo conceptual:
Authentication
├── principal → admin@email.com
├── authorities → ROLE_ADMIN
└── authenticated → true
________________


24. SecurityContext
Spring almacena la autenticación actual dentro del:
SecurityContext
Conceptualmente:
Petición
  ↓
Authentication
  ↓
SecurityContext
  ↓
Controller
Esto permite que posteriormente otras partes de la aplicación sepan quién está autenticado.
________________


25. AuthenticationManager
AuthenticationManager es responsable de coordinar la autenticación.
Ejemplo:
Authentication authentication =
       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
                       request.email(),
                       request.password()
               )
       );
Aquí le estamos diciendo:
intenta autenticar estas credenciales.
________________


26. UsernamePasswordAuthenticationToken
A pesar del nombre Token, aquí no estamos hablando necesariamente de JWT.
Esta clase representa credenciales de autenticación:
new UsernamePasswordAuthenticationToken(
       email,
       password
)
Después Spring procesa esas credenciales.
________________


27. Configurar AuthenticationManager
Por ejemplo:
@Bean
public AuthenticationManager authenticationManager(
       AuthenticationConfiguration configuration
) throws Exception {


   return configuration
           .getAuthenticationManager();
}
________________


28. SecurityFilterChain
En Spring Security moderno, una pieza central de configuración es:
SecurityFilterChain
Ejemplo:
@Bean
public SecurityFilterChain securityFilterChain(
       HttpSecurity http
) throws Exception {


   http
       .csrf(csrf -> csrf.disable())


       .authorizeHttpRequests(auth -> auth
           .requestMatchers("/auth/**")
           .permitAll()


           .anyRequest()
           .authenticated()
       );


   return http.build();
}
________________


29. ¿Qué significa esta configuración?
.requestMatchers("/auth/**")
.permitAll()
indica:
/auth/login
/auth/register
pueden estar públicos.
Mientras que:
.anyRequest()
.authenticated()
significa:
cualquier otro endpoint necesita autenticación.
________________


30. ¿Por qué desactivar CSRF en este ejemplo?
En APIs REST stateless basadas en JWT suele desactivarse CSRF:
.csrf(csrf -> csrf.disable())
pero esto depende de la arquitectura y de cómo se transporten las credenciales.
No debe interpretarse como:
CSRF siempre debe desactivarse.
La decisión depende del mecanismo de autenticación.
________________


31. Stateless
Para una API JWT suele configurarse:
.sessionManagement(session ->
       session.sessionCreationPolicy(
               SessionCreationPolicy.STATELESS
       )
)
Esto indica:
No utilizar sesión HTTP tradicional
Cada petición debe traer sus propias credenciales.
________________


32. Configuración básica JWT
Una configuración inicial podría verse así:
@Bean
public SecurityFilterChain securityFilterChain(
       HttpSecurity http,
       JwtAuthenticationFilter jwtFilter
) throws Exception {


   http
       .csrf(csrf -> csrf.disable())


       .sessionManagement(session ->
           session.sessionCreationPolicy(
               SessionCreationPolicy.STATELESS
           )
       )


       .authorizeHttpRequests(auth -> auth


           .requestMatchers("/auth/**")
           .permitAll()


           .anyRequest()
           .authenticated()
       )


       .addFilterBefore(
           jwtFilter,
           UsernamePasswordAuthenticationFilter.class
       );


   return http.build();
}
________________


33. ¿Qué es JwtService?
JwtService será responsable de:
Generar JWT
Extraer username
Extraer claims
Validar token
Comprobar expiración
Por ejemplo:
public class JwtService {


   public String generateToken(
           UserDetails userDetails
   ) {
       // ...
   }


   public String extractUsername(
           String token
   ) {
       // ...
   }


   public boolean isTokenValid(
           String token,
           UserDetails userDetails
   ) {
       // ...
   }
}
________________


34. Claims
Un JWT contiene claims.
Algunos claims estándar son:
sub → subject
iat → issued at
exp → expiration
iss → issuer
aud → audience
Por ejemplo:
{
 "sub": "admin@email.com",
 "iat": 1791220000,
 "exp": 1791223600
}
________________


35. Generar token
Conceptualmente:
String token =
       jwtService.generateToken(
               userDetails
       );
Resultado:
eyJhbGciOiJIUzI1NiJ9...
________________


36. Request de login
Podemos crear:
public record LoginRequest(
       String email,
       String password
) {
}
Y la respuesta:
public record LoginResponse(
       String token
) {
}
________________


37. Endpoint de login
Ejemplo:
@RestController
@RequestMapping("/auth")
public class AuthController {


   private final AuthenticationManager
           authenticationManager;


   private final UserDetailsService
           userDetailsService;


   private final JwtService jwtService;


   public AuthController(
           AuthenticationManager authenticationManager,
           UserDetailsService userDetailsService,
           JwtService jwtService
   ) {
       this.authenticationManager =
               authenticationManager;


       this.userDetailsService =
               userDetailsService;


       this.jwtService =
               jwtService;
   }


   @PostMapping("/login")
   public LoginResponse login(
           @RequestBody LoginRequest request
   ) {


       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
                       request.email(),
                       request.password()
               )
       );


       UserDetails user =
               userDetailsService
                       .loadUserByUsername(
                               request.email()
                       );


       String token =
               jwtService.generateToken(user);


       return new LoginResponse(token);
   }
}
________________


38. Flujo del login
POST /auth/login
    ↓
email + password
    ↓
AuthenticationManager
    ↓
UserDetailsService
    ↓
PasswordEncoder
    ↓
credenciales correctas
    ↓
JwtService
    ↓
JWT
________________


39. Petición de login
POST /auth/login
Content-Type: application/json
Body:
{
 "email": "admin@email.com",
 "password": "123456"
}
Respuesta:
{
 "token": "eyJhbGciOi..."
}
________________


40. Usar el token
Posteriormente:
GET /api/countries
Authorization: Bearer eyJhbGciOi...
El servidor debe validar ese token.
Aquí entra:
JwtAuthenticationFilter
________________


41. ¿Qué es un filtro?
Un filtro intercepta solicitudes antes de llegar al controller.
Conceptualmente:
HTTP Request
    ↓
Security Filters
    ↓
JwtAuthenticationFilter
    ↓
Controller
________________


42. Crear JwtAuthenticationFilter
Una estructura habitual:
@Component
public class JwtAuthenticationFilter
       extends OncePerRequestFilter {


   @Override
   protected void doFilterInternal(
           HttpServletRequest request,
           HttpServletResponse response,
           FilterChain filterChain
   ) throws ServletException, IOException {


   }
}
________________


43. ¿Qué significa OncePerRequestFilter?
Significa que el filtro está diseñado para ejecutarse una vez por petición.
Es muy utilizado para autenticación JWT.
________________


44. Leer el Header
Dentro del filtro:
String authHeader =
       request.getHeader(
               "Authorization"
       );
Puede contener:
Bearer eyJhbGciOi...
________________


45. Comprobar el formato
if (authHeader == null
       || !authHeader.startsWith("Bearer ")) {


   filterChain.doFilter(
           request,
           response
   );


   return;
}
Si no existe token, el filtro deja continuar el flujo y Spring Security decidirá después si ese endpoint requiere autenticación.
________________


46. Extraer el token
String jwt =
       authHeader.substring(7);
¿Por qué 7?
Porque:
"Bearer "
tiene siete caracteres.
________________


47. Extraer username
String username =
       jwtService.extractUsername(jwt);
Por ejemplo:
admin@email.com
________________


48. Verificar si ya existe autenticación
SecurityContextHolder
       .getContext()
       .getAuthentication()
Si devuelve null, significa que todavía no existe autenticación para esa petición.
________________


49. Cargar usuario
UserDetails userDetails =
       userDetailsService
               .loadUserByUsername(
                       username
               );
________________


50. Validar JWT
if (jwtService.isTokenValid(
       jwt,
       userDetails
)) {
   // autenticar
}
Debe verificarse, como mínimo:
Firma válida
Usuario correcto
Token no expirado
________________


51. Crear Authentication autenticado
UsernamePasswordAuthenticationToken
       authenticationToken =
       new UsernamePasswordAuthenticationToken(
               userDetails,
               null,
               userDetails.getAuthorities()
       );
Aquí ya se trata de una autenticación reconocida.
________________


52. Guardarla en SecurityContext
SecurityContextHolder
       .getContext()
       .setAuthentication(
               authenticationToken
       );
A partir de ese momento Spring considera autenticada la petición.
________________


53. Filtro completo conceptualmente
@Component
public class JwtAuthenticationFilter
       extends OncePerRequestFilter {


   private final JwtService jwtService;


   private final UserDetailsService
           userDetailsService;


   public JwtAuthenticationFilter(
           JwtService jwtService,
           UserDetailsService userDetailsService
   ) {
       this.jwtService = jwtService;
       this.userDetailsService =
               userDetailsService;
   }


   @Override
   protected void doFilterInternal(
           HttpServletRequest request,
           HttpServletResponse response,
           FilterChain filterChain
   ) throws ServletException, IOException {


       String authHeader =
               request.getHeader(
                       "Authorization"
               );


       if (authHeader == null
               || !authHeader
                   .startsWith("Bearer ")) {


           filterChain.doFilter(
                   request,
                   response
           );


           return;
       }


       String jwt =
               authHeader.substring(7);


       String username =
               jwtService.extractUsername(jwt);


       if (username != null
               && SecurityContextHolder
                   .getContext()
                   .getAuthentication()
                   == null) {


           UserDetails userDetails =
                   userDetailsService
                           .loadUserByUsername(
                                   username
                           );


           if (jwtService.isTokenValid(
                   jwt,
                   userDetails
           )) {


               var authToken =
                       new UsernamePasswordAuthenticationToken(
                               userDetails,
                               null,
                               userDetails
                                   .getAuthorities()
                       );


               SecurityContextHolder
                       .getContext()
                       .setAuthentication(
                               authToken
                       );
           }
       }


       filterChain.doFilter(
               request,
               response
       );
   }
}
________________


54. Flujo completo del filtro
Request
 ↓
Authorization header
 ↓
¿Existe Bearer token?
 ├── No
 │    ↓
 │ continuar
 │
 └── Sí
      ↓
   extraer JWT
      ↓
   extraer usuario
      ↓
   cargar UserDetails
      ↓
   validar JWT
      ↓
   crear Authentication
      ↓
   SecurityContext
      ↓
   Controller
________________


55. Proteger endpoints por rol
Podemos configurar:
.authorizeHttpRequests(auth -> auth


   .requestMatchers("/auth/**")
   .permitAll()


   .requestMatchers(
       HttpMethod.GET,
       "/api/countries/**"
   )
   .hasAnyRole(
       "USER",
       "ADMIN"
   )


   .requestMatchers(
       HttpMethod.POST,
       "/api/countries/**"
   )
   .hasRole("ADMIN")


   .requestMatchers(
       HttpMethod.DELETE,
       "/api/countries/**"
   )
   .hasRole("ADMIN")


   .anyRequest()
   .authenticated()
)
________________


56. Ejemplo de permisos
USER
├── GET countries
└── GET country by id


ADMIN
├── GET
├── POST
├── PUT
└── DELETE
________________


57. Seguridad mediante anotaciones
También se puede habilitar seguridad a nivel de método:
@EnableMethodSecurity
Después:
@PreAuthorize(
   "hasRole('ADMIN')"
)
@DeleteMapping("/{id}")
public ResponseEntity<Void> delete(...) {
}
________________


58. @PreAuthorize
Permite expresar reglas como:
@PreAuthorize(
   "hasRole('ADMIN')"
)
o:
@PreAuthorize(
   "hasAuthority('COUNTRY_DELETE')"
)
Esto permite trabajar con permisos más finos.
________________


59. Roles vs permisos
Un enfoque simple:
ADMIN
USER
Un enfoque más flexible:
COUNTRY_READ
COUNTRY_CREATE
COUNTRY_UPDATE
COUNTRY_DELETE
Después:
ADMIN
├── COUNTRY_READ
├── COUNTRY_CREATE
├── COUNTRY_UPDATE
└── COUNTRY_DELETE
Esto suele escalar mejor en sistemas grandes.
________________


60. 401 y 403
Es importante distinguirlos.
401 Unauthorized
Aunque el nombre diga Unauthorized, normalmente significa:
no está autenticado correctamente.
Ejemplos:
No envió token
Token inválido
Token expirado
________________


403 Forbidden
Significa:
está autenticado pero no tiene permiso.
Ejemplo:
Usuario ROLE_USER
intenta DELETE
que requiere ROLE_ADMIN
Respuesta:
403 Forbidden
________________


61. Token expirado
Un JWT debe tener expiración.
Ejemplo conceptual:
emitido: 13:00
expira: 14:00
Después de las 14:00:
JWT inválido
Esto reduce el riesgo de usar tokens indefinidamente.
________________


62. Access Token y Refresh Token
En un sistema más avanzado pueden utilizarse:
Access Token
↓
duración corta


Refresh Token
↓
duración mayor
El access token se utiliza para consumir la API.
El refresh token sirve para obtener un nuevo access token.
________________


63. No guardar el password en JWT
Nunca debe hacerse:
{
 "email": "admin@email.com",
 "password": "123456"
}
El JWT debe contener solamente claims necesarios.
________________


64. Secret Key
El JWT firmado necesita una clave.
No debe escribirse directamente en código:
private String secret =
       "mi-clave-super-secreta";
Preferir variables de entorno o configuración externa:
JWT_SECRET
Por ejemplo:
security.jwt.secret=${JWT_SECRET}
________________


65. Configuración mediante properties
Ejemplo:
security.jwt.secret=${JWT_SECRET}
security.jwt.expiration=3600000
Entonces:
3600000 ms
=
1 hora
________________


66. Arquitectura recomendada
Una organización sencilla podría ser:
security
├── config
│   └── SecurityConfig.java
│
├── jwt
│   ├── JwtService.java
│   └── JwtAuthenticationFilter.java
│
├── user
│   ├── CustomUserDetails.java
│   └── CustomUserDetailsService.java
│
└── auth
   ├── AuthController.java
   ├── LoginRequest.java
   └── LoginResponse.java
En arquitectura hexagonal puede distribuirse de otra manera según bounded context y adapters.
________________


67. Flujo completo de autenticación
               LOGIN


POST /auth/login
       ↓
LoginRequest
       ↓
AuthenticationManager
       ↓
UserDetailsService
       ↓
Database
       ↓
PasswordEncoder
       ↓
credenciales válidas
       ↓
JwtService
       ↓
JWT
       ↓
Cliente
________________


68. Flujo de una petición protegida
GET /api/countries
       ↓
Authorization: Bearer JWT
       ↓
JwtAuthenticationFilter
       ↓
JwtService
       ↓
UserDetailsService
       ↓
token válido
       ↓
SecurityContext
       ↓
Authorization
       ↓
CountryController
________________


69. Ejemplo completo de flujo
Primero
POST /auth/login
{
 "email": "admin@email.com",
 "password": "123456"
}
Respuesta:
{
 "token": "eyJhbGciOi..."
}
Después
DELETE /api/countries/{id}
Authorization: Bearer eyJhbGciOi...
Spring Security evalúa:
¿JWT válido?
   ↓ sí


¿Usuario autenticado?
   ↓ sí


¿Tiene ROLE_ADMIN?
   ↓ sí


Controller
________________


70. ¿Dónde encaja JWT en arquitectura hexagonal?
Spring Security y JWT son principalmente preocupaciones de infraestructura.
No deberían contaminar el agregado Country.
Por ejemplo:
domain
└── country
   ├── Country
   ├── CountryId
   └── CountryRepository


application
└── country
   └── usecases


infrastructure
├── country
│   └── rest
│
└── security
   ├── SecurityConfig
   ├── JwtService
   └── JwtAuthenticationFilter
El dominio no debería saber que existe JWT.
________________


71. Country no debería tener seguridad HTTP
Evitar cosas como:
public class Country {


   private String jwt;


}
o anotaciones Spring Security dentro del agregado.
El dominio debe mantenerse enfocado en reglas del negocio.
________________


72. Seguridad en Controller o configuración
Por ejemplo:
@PreAuthorize(
   "hasAuthority('COUNTRY_DELETE')"
)
@DeleteMapping("/{id}")
public ResponseEntity<Void> delete(...) {
}
Esto pertenece al adapter REST.
No al dominio.
________________


73. Manejo de errores de seguridad
Casos frecuentes:
Credenciales inválidas
Token faltante
Token expirado
Token alterado
Usuario deshabilitado
Permiso insuficiente
Deben traducirse correctamente a:
401
403
________________


74. Buenas prácticas
* usar HTTPS en producción;
* nunca almacenar contraseñas en texto plano;
* usar BCrypt o algoritmos apropiados;
* mantener secrets fuera del código;
* usar expiración;
* validar firma y expiración;
* no colocar datos sensibles en payload;
* devolver 401 y 403 correctamente;
* aplicar mínimo privilegio;
* mantener Spring Security fuera del dominio;
* utilizar Refresh Token cuando el caso de uso lo requiera;
* registrar eventos de seguridad importantes.
________________


75. Errores frecuentes
Error 1: guardar contraseña plana
123456
Incorrecto.
________________


Error 2: creer que JWT está cifrado
JWT firmado no implica payload secreto.
________________


Error 3: token sin expiración
Aumenta el riesgo de reutilización.
________________


Error 4: poner la clave secreta en Git
Nunca debe versionarse una secret real.
________________


Error 5: proteger /auth/login
Si login necesita autenticación:
no es posible iniciar sesión
Debe estar normalmente en:
permitAll()
________________


Error 6: devolver 403 para todo
Debe distinguirse:
401
vs
403
________________


76. Práctica guiada 1
Crear:
User
con:
id
email
password
role
enabled
Crear:
UserRepository
con:
Optional<User> findByEmail(
       String email
);
________________


77. Práctica guiada 2
Crear:
@Bean
public PasswordEncoder passwordEncoder() {
   return new BCryptPasswordEncoder();
}
Registrar un usuario usando:
passwordEncoder.encode(
       request.password()
);
________________


78. Práctica guiada 3
Crear:
CustomUserDetailsService
que implemente:
UserDetailsService
y busque al usuario por email.
________________


79. Práctica guiada 4
Configurar:
/auth/**
como público.
Configurar:
/api/**
como autenticado.
________________


80. Práctica guiada 5
Crear:
POST /auth/login
que reciba:
{
 "email": "...",
 "password": "..."
}
y devuelva:
{
 "token": "..."
}
________________


81. Práctica guiada 6
Crear:
JwtAuthenticationFilter
y probar:
GET /api/countries
primero sin token.
Debe producir:
401
Después con token válido:
200
________________


82. Taller práctico
Implementar seguridad para un sistema de productos.
Roles:
ADMIN
SELLER
USER
Reglas:
GET /api/products
→ todos autenticados


POST /api/products
→ ADMIN


PUT /api/products
→ ADMIN


DELETE /api/products
→ ADMIN


POST /api/orders
→ SELLER o ADMIN
________________


83. Reto avanzado
Cambiar de roles a permisos.
Ejemplo:
PRODUCT_READ
PRODUCT_CREATE
PRODUCT_UPDATE
PRODUCT_DELETE
ORDER_CREATE
Crear relación:
User
 ↓
Role
 ↓
Permission
y utilizar:
@PreAuthorize(
   "hasAuthority('PRODUCT_DELETE')"
)
________________


84. Preguntas de comprobación
1. ¿Qué diferencia existe entre autenticación y autorización?
2. ¿Qué significa JWT?
3. ¿Cuáles son las tres partes de JWT?
4. ¿JWT cifra automáticamente el payload?
5. ¿Qué función tiene PasswordEncoder?
6. ¿Para qué sirve UserDetailsService?
7. ¿Qué representa Authentication?
8. ¿Qué es SecurityContext?
9. ¿Qué función cumple AuthenticationManager?
10. ¿Para qué sirve SecurityFilterChain?
11. ¿Qué significa STATELESS?
12. ¿Qué función tiene JwtAuthenticationFilter?
13. ¿Qué significa Bearer?
14. ¿Cuál es la diferencia entre 401 y 403?
15. ¿Qué diferencia existe entre role y authority?
16. ¿Por qué debe expirar un token?
17. ¿Dónde debería almacenarse una secret?
18. ¿Por qué no debe almacenarse el password dentro del JWT?
19. ¿Qué hace @PreAuthorize?
20. ¿Por qué JWT debería mantenerse fuera del dominio?
________________


85. Mapa conceptual
                   SPRING SECURITY
                          │
            ┌─────────────┴─────────────┐
            │                           │
     Autenticación                 Autorización
            │                           │
            ▼                           ▼
  AuthenticationManager             Roles
            │                       Permisos
            ▼                           │
    UserDetailsService                  ▼
            │                     @PreAuthorize
            ▼
     PasswordEncoder
            │
            ▼
          JWT
            │
            ▼
JwtAuthenticationFilter
            │
            ▼
     SecurityContext
            │
            ▼
        Controller
________________


86. Resumen pedagógico
Spring Security responde principalmente a dos preguntas:
¿Quién eres?
↓
Autenticación


¿Qué puedes hacer?
↓
Autorización
JWT permite transportar la identidad autenticada entre cliente y servidor:
Login
 ↓
JWT
 ↓
Authorization: Bearer
 ↓
Filtro
 ↓
SecurityContext
El flujo fundamental que debes conservar es:
Credenciales
   ↓
AuthenticationManager
   ↓
UserDetailsService
   ↓
PasswordEncoder
   ↓
JWT
   ↓
Cliente


Posteriormente:


JWT
↓
JwtAuthenticationFilter
↓
SecurityContext
↓
Authorization
↓
Controller
En una arquitectura limpia o hexagonal, Spring Security y JWT deben tratarse principalmente como mecanismos de infraestructura. El dominio debe expresar reglas del negocio sin depender de HttpSecurity, filtros, tokens ni anotaciones específicas de seguridad.
   




Guía práctica: Introducción a JPA en Spring Boot
1. Introducción
En una aplicación empresarial, una de las tareas más frecuentes consiste en almacenar, consultar, actualizar y eliminar información de una base de datos.
Tradicionalmente, estas operaciones podían realizarse utilizando JDBC y escribiendo manualmente sentencias SQL como:
INSERT INTO products ...
SELECT * FROM products ...
UPDATE products ...
DELETE FROM products ...
Aunque JDBC sigue siendo válido, en proyectos modernos con Spring Boot es habitual utilizar JPA, ya que permite trabajar con datos mediante objetos Java.
JPA significa:
Java Persistence API
Actualmente también se conoce como:
Jakarta Persistence
JPA define un conjunto de reglas, anotaciones e interfaces para relacionar objetos Java con tablas de una base de datos.
Por ejemplo, una clase Java:
public class Product {
   private Long id;
   private String name;
   private Double price;
}
puede representar una tabla:
products
-------------------------
id
name
price
A este proceso se le denomina:
ORM
o:
Object Relational Mapping
Es decir:
Objeto Java
    ⇅
Tabla relacional
________________


2. ¿Qué relación existe entre JPA, Hibernate y Spring Data JPA?
Es importante diferenciar estos conceptos.
JPA
JPA es una especificación.
Define cómo deben realizarse operaciones de persistencia en Java.
Incluye anotaciones como:
@Entity
@Id
@GeneratedValue
@Column
@Table
JPA no realiza directamente el trabajo.
________________


Hibernate
Hibernate es una implementación de JPA.
Es el componente que realmente puede:
* generar SQL;
* ejecutar consultas;
* insertar registros;
* actualizar registros;
* eliminar registros;
* convertir registros de una tabla en objetos Java.
La relación puede representarse así:
JPA
Especificación
   ↓
Hibernate
Implementación
________________


Spring Data JPA
Spring Data JPA agrega una capa adicional que facilita enormemente el acceso a datos.
Permite crear repositorios como:
public interface ProductRepository
       extends JpaRepository<Product, Long> {
}
y obtener automáticamente operaciones como:
save()
findAll()
findById()
deleteById()
count()
existsById()
Por tanto:
Aplicación Spring Boot
       ↓
Spring Data JPA
       ↓
JPA
       ↓
Hibernate
       ↓
JDBC
       ↓
Base de datos
________________


3. Objetivos de aprendizaje
Al finalizar esta guía, el estudiante estará en capacidad de:
1. Comprender el propósito de JPA.
2. Diferenciar JPA, Hibernate y Spring Data JPA.
3. Configurar una aplicación Spring Boot con persistencia.
4. Crear una entidad JPA.
5. Configurar una conexión a una base de datos.
6. Crear un repositorio con JpaRepository.
7. Implementar operaciones CRUD básicas.
8. Consultar la información almacenada.
9. Comprender el ciclo básico de persistencia de una entidad.
________________


4. Caso práctico
Se desarrollará una aplicación sencilla para administrar productos.
Cada producto tendrá:
Campo
	Tipo
	Descripción
	id
	Long
	Identificador
	name
	String
	Nombre
	price
	Double
	Precio
	stock
	Integer
	Cantidad disponible
	active
	Boolean
	Estado del producto
	La aplicación permitirá:
Crear producto
Consultar productos
Consultar producto por ID
Actualizar producto
Eliminar producto
________________


5. Crear el proyecto Spring Boot
Puede utilizarse Spring Initializr.
Dependencias recomendadas:
Spring Web
Spring Data JPA
PostgreSQL Driver
También puede utilizarse MySQL.
Para esta guía se utilizará PostgreSQL.
________________


6. Dependencias Maven
El archivo pom.xml debe incluir dependencias equivalentes a:
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-web</artifactId>
</dependency>


<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>


<dependency>
   <groupId>org.postgresql</groupId>
   <artifactId>postgresql</artifactId>
   <scope>runtime</scope>
</dependency>
La dependencia más importante para este tema es:
spring-boot-starter-data-jpa
Esta incorpora las herramientas necesarias para trabajar con:
Spring Data JPA
JPA
Hibernate
________________


7. Crear la base de datos
En PostgreSQL se puede crear una base de datos denominada:
CREATE DATABASE store_db;
No es obligatorio crear inicialmente la tabla products, porque Hibernate puede hacerlo automáticamente durante el desarrollo.
________________


8. Configurar la conexión
En:
src/main/resources/application.properties
se puede agregar:
spring.datasource.url=jdbc:postgresql://localhost:5432/store_db
spring.datasource.username=postgres
spring.datasource.password=123456


spring.jpa.hibernate.ddl-auto=update


spring.jpa.show-sql=true


spring.jpa.properties.hibernate.format_sql=true
Explicación
Esta propiedad:
spring.datasource.url
define la ubicación de la base de datos.
spring.datasource.username
establece el usuario.
spring.datasource.password
establece la contraseña.
________________


La propiedad:
spring.jpa.hibernate.ddl-auto=update
permite que Hibernate adapte las tablas según las entidades.
Durante aprendizaje puede ser útil.
Valores comunes:
none
validate
update
create
create-drop
update
Actualiza la estructura sin eliminar normalmente los datos existentes.
create
Elimina y vuelve a crear las tablas cada vez que inicia la aplicación.
Debe utilizarse con precaución.
________________


9. Crear la entidad Product
Se crea una clase:
entity/Product.java
Ejemplo:
package com.example.store.entity;


import jakarta.persistence.*;


@Entity
@Table(name = "products")
public class Product {


   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;


   @Column(nullable = false, length = 100)
   private String name;


   @Column(nullable = false)
   private Double price;


   private Integer stock;


   private Boolean active;


   public Product() {
   }


   public Product(
           String name,
           Double price,
           Integer stock,
           Boolean active) {


       this.name = name;
       this.price = price;
       this.stock = stock;
       this.active = active;
   }


   public Long getId() {
       return id;
   }


   public void setId(Long id) {
       this.id = id;
   }


   public String getName() {
       return name;
   }


   public void setName(String name) {
       this.name = name;
   }


   public Double getPrice() {
       return price;
   }


   public void setPrice(Double price) {
       this.price = price;
   }


   public Integer getStock() {
       return stock;
   }


   public void setStock(Integer stock) {
       this.stock = stock;
   }


   public Boolean getActive() {
       return active;
   }


   public void setActive(Boolean active) {
       this.active = active;
   }
}
________________


10. Comprender @Entity
La anotación:
@Entity
indica que la clase representa una entidad persistente.
Hibernate entiende que debe asociarla a una tabla.
Sin:
@Entity
la clase sería simplemente una clase Java convencional.
Conceptualmente:
Product.java
    ↓
@Entity
    ↓
Hibernate
    ↓
products
________________


11. Comprender @Table
@Table(name = "products")
permite especificar el nombre de la tabla.
Sin esta anotación, Hibernate podría utilizar directamente el nombre de la clase.
Por ejemplo:
Product
Con @Table se controla explícitamente:
products
________________


12. Definir la clave primaria
Toda entidad JPA debe tener un identificador.
Para esto se utiliza:
@Id
Ejemplo:
@Id
private Long id;
Esta propiedad representa la clave primaria.
En términos SQL:
PRIMARY KEY
________________


13. Generación automática del identificador
La anotación:
@GeneratedValue(strategy = GenerationType.IDENTITY)
permite delegar la generación del identificador a la base de datos.
Ejemplo:
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
Cuando se guarda:
Product product = new Product();
no es necesario hacer:
product.setId(1L);
La base de datos genera el valor.
________________


14. Personalizar columnas
Puede utilizarse:
@Column
Ejemplo:
@Column(nullable = false, length = 100)
private String name;
Esto indica que:
name no puede ser NULL
longitud máxima aproximada: 100
Conceptualmente se aproxima a:
name VARCHAR(100) NOT NULL
________________


15. Crear el repositorio
Se crea:
repository/ProductRepository.java
package com.example.store.repository;


import com.example.store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository
       extends JpaRepository<Product, Long> {
}
Esta pequeña interfaz proporciona una gran cantidad de funcionalidades.
No es necesario implementar manualmente métodos como:
insert()
update()
delete()
selectAll()
Spring Data JPA los genera.
________________


16. Comprender JpaRepository
La declaración:
JpaRepository<Product, Long>
contiene dos tipos.
Product → entidad administrada
Long    → tipo de la clave primaria
Por tanto:
JpaRepository<Product, Long>
significa:
Este repositorio administra objetos Product cuyo identificador es de tipo Long.
________________


17. Métodos disponibles automáticamente
Al extender JpaRepository, se obtienen métodos como:
save()
findAll()
findById()
delete()
deleteById()
count()
existsById()
Por ejemplo:
repository.findAll();
equivale conceptualmente a:
SELECT * FROM products;
Y:
repository.findById(1L);
se aproxima a:
SELECT *
FROM products
WHERE id = 1;
________________


18. Crear la capa de servicio
Es recomendable evitar acceder directamente al repositorio desde todos los controladores.
Se puede crear:
service/ProductService.java
package com.example.store.service;


import com.example.store.entity.Product;
import com.example.store.repository.ProductRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class ProductService {


   private final ProductRepository repository;


   public ProductService(ProductRepository repository) {
       this.repository = repository;
   }


   public Product save(Product product) {
       return repository.save(product);
   }


   public List<Product> findAll() {
       return repository.findAll();
   }


   public Optional<Product> findById(Long id) {
       return repository.findById(id);
   }


   public void deleteById(Long id) {
       repository.deleteById(id);
   }
}
________________


19. Flujo de capas
La aplicación queda organizada así:
Controller
   ↓
Service
   ↓
Repository
   ↓
JPA / Hibernate
   ↓
PostgreSQL
Cada capa tiene una responsabilidad.
Controller
Atiende solicitudes HTTP.
Service
Contiene lógica de negocio.
Repository
Accede a datos.
Entity
Representa la estructura persistente.
________________


20. Crear el controlador REST
Se crea:
controller/ProductController.java
package com.example.store.controller;


import com.example.store.entity.Product;
import com.example.store.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductController {


   private final ProductService service;


   public ProductController(ProductService service) {
       this.service = service;
   }


   @PostMapping
   public Product save(
           @RequestBody Product product) {


       return service.save(product);
   }


   @GetMapping
   public List<Product> findAll() {
       return service.findAll();
   }


   @GetMapping("/{id}")
   public ResponseEntity<Product> findById(
           @PathVariable Long id) {


       return service.findById(id)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
   }


   @DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(
           @PathVariable Long id) {


       service.deleteById(id);


       return ResponseEntity.noContent().build();
   }
}
________________


21. Primera operación: insertar
La aplicación se ejecuta y se realiza una solicitud:
POST /api/products
JSON:
{
 "name": "Teclado mecánico",
 "price": 250000,
 "stock": 15,
 "active": true
}
Spring Boot recibe el JSON.
JSON
↓
@RequestBody
↓
Product
Luego:
service.save(product);
termina ejecutando:
repository.save(product);
Hibernate genera el SQL correspondiente.
Conceptualmente:
INSERT INTO products
(name, price, stock, active)
VALUES
('Teclado mecánico', 250000, 15, true);
________________


22. Consultar todos los productos
Petición:
GET /api/products
El controlador ejecuta:
service.findAll();
El servicio ejecuta:
repository.findAll();
Hibernate genera aproximadamente:
SELECT *
FROM products;
Resultado:
[
 {
   "id": 1,
   "name": "Teclado mecánico",
   "price": 250000,
   "stock": 15,
   "active": true
 }
]
________________


23. Consultar por ID
Solicitud:
GET /api/products/1
Código:
repository.findById(1L);
El método devuelve:
Optional<Product>
No devuelve directamente:
Product
porque puede ocurrir que el registro no exista.
Ejemplo:
Optional<Product> product =
       repository.findById(id);
Puede representar:
Producto encontrado
o:
Valor vacío
________________


24. Actualizar un producto
Puede agregarse:
@PutMapping("/{id}")
public ResponseEntity<Product> update(
       @PathVariable Long id,
       @RequestBody Product data) {


   return service.findById(id)
           .map(product -> {


               product.setName(data.getName());
               product.setPrice(data.getPrice());
               product.setStock(data.getStock());
               product.setActive(data.getActive());


               Product updated =
                       service.save(product);


               return ResponseEntity.ok(updated);
           })
           .orElse(ResponseEntity.notFound().build());
}
Aquí ocurre algo muy importante.
Primero se consulta:
findById(id)
Después se modifican propiedades.
Finalmente:
save(product)
Como el objeto ya tiene un identificador persistente, Hibernate puede realizar un:
UPDATE
en lugar de un:
INSERT
________________


25. Eliminar un producto
Solicitud:
DELETE /api/products/1
Código:
repository.deleteById(1L);
Conceptualmente:
DELETE
FROM products
WHERE id = 1;
________________


26. ¿Cómo decide save() entre INSERT y UPDATE?
Esta pregunta es fundamental.
Cuando se ejecuta:
repository.save(product);
Spring Data JPA analiza el estado de la entidad.
Un objeto nuevo como:
Product product = new Product();
product.setName("Mouse");
normalmente no tiene ID.
id = null
Entonces se interpreta como nuevo:
INSERT
Cuando el objeto representa un registro existente:
id = 10
JPA puede tratarlo como una entidad existente y realizar:
UPDATE
La lógica interna es más compleja, pero esta simplificación resulta adecuada para comprender el comportamiento inicial.
________________


27. Consultas derivadas
Spring Data JPA permite crear consultas utilizando el nombre de los métodos.
Por ejemplo:
List<Product> findByActive(Boolean active);
Repository:
public interface ProductRepository
       extends JpaRepository<Product, Long> {


   List<Product> findByActive(Boolean active);
}
Puede utilizarse:
repository.findByActive(true);
Spring Data interpreta:
find
By
Active
y genera una consulta equivalente aproximadamente a:
SELECT *
FROM products
WHERE active = true;
________________


28. Consultar por nombre
Puede agregarse:
List<Product> findByName(String name);
Uso:
repository.findByName("Mouse");
________________


29. Consultar utilizando Containing
Ejemplo:
List<Product> findByNameContaining(String text);
Si se ejecuta:
findByNameContaining("tec");
se comporta conceptualmente como:
WHERE name LIKE '%tec%'
________________


30. Consultar por precio
List<Product> findByPriceGreaterThan(Double price);
Ejemplo:
repository.findByPriceGreaterThan(100000.0);
Equivalente aproximadamente a:
SELECT *
FROM products
WHERE price > 100000;
________________


31. Combinar condiciones
También se puede escribir:
List<Product> findByActiveAndStockGreaterThan(
       Boolean active,
       Integer stock);
Uso:
repository.findByActiveAndStockGreaterThan(
       true,
       0
);
Conceptualmente:
SELECT *
FROM products
WHERE active = true
AND stock > 0;
________________


32. Convención de nombres de Spring Data JPA
Algunas palabras frecuentes son:
Palabra
	Significado
	findBy
	Buscar por
	And
	Y
	Or
	O
	GreaterThan
	Mayor que
	LessThan
	Menor que
	Between
	Entre
	Containing
	Contiene
	StartingWith
	Comienza con
	EndingWith
	Termina con
	OrderBy
	Ordenar por
	IgnoreCase
	Ignorar mayúsculas/minúsculas
	Ejemplo:
List<Product>
findByNameContainingIgnoreCase(String name);
________________


33. Estructura final del proyecto
Una estructura organizada podría ser:
src/main/java
└── com.example.store
   │
   ├── StoreApplication.java
   │
   ├── controller
   │   └── ProductController.java
   │
   ├── service
   │   └── ProductService.java
   │
   ├── repository
   │   └── ProductRepository.java
   │
   └── entity
       └── Product.java
En recursos:
src/main/resources
└── application.properties
________________


34. Flujo completo de una petición
Supóngase:
GET /api/products
El flujo será:
Cliente
  │
  │ GET /api/products
  ▼
ProductController
  │
  ▼
ProductService
  │
  ▼
ProductRepository
  │
  ▼
Spring Data JPA
  │
  ▼
Hibernate
  │
  ▼
JDBC
  │
  ▼
PostgreSQL
La respuesta recorre el camino inverso:
PostgreSQL
  ↓
Hibernate
  ↓
Product
  ↓
Repository
  ↓
Service
  ↓
Controller
  ↓
JSON
________________


35. Ejercicio guiado 1: registrar productos
Se deben registrar al menos cinco productos.
Ejemplo:
{
 "name": "Monitor 24 pulgadas",
 "price": 690000,
 "stock": 8,
 "active": true
}
Otros productos sugeridos:
Mouse inalámbrico
Teclado mecánico
SSD 1 TB
Memoria RAM 16 GB
Webcam Full HD
Después se ejecuta:
GET /api/products
y se verifica que todos los registros estén disponibles.
________________


36. Ejercicio guiado 2: consultar productos activos
Se agrega al repositorio:
List<Product> findByActive(Boolean active);
Luego, en el servicio:
public List<Product> findActiveProducts() {
   return repository.findByActive(true);
}
Y en el controlador:
@GetMapping("/active")
public List<Product> findActive() {
   return service.findActiveProducts();
}
Solicitud:
GET /api/products/active
________________


37. Ejercicio guiado 3: búsqueda por nombre
Repositorio:
List<Product> findByNameContainingIgnoreCase(
       String text);
Servicio:
public List<Product> searchByName(String text) {
   return repository
           .findByNameContainingIgnoreCase(text);
}
Controlador:
@GetMapping("/search")
public List<Product> search(
       @RequestParam String name) {


   return service.searchByName(name);
}
Solicitud:
GET /api/products/search?name=mouse
________________


38. Ejercicio guiado 4: consultar productos costosos
Repositorio:
List<Product> findByPriceGreaterThan(
       Double price);
Controlador:
@GetMapping("/price")
public List<Product> findByPrice(
       @RequestParam Double min) {


   return repository
           .findByPriceGreaterThan(min);
}
Como ejercicio pedagógico funciona; sin embargo, en una arquitectura mejor organizada debería utilizarse la capa Service.
________________


39. Actividad práctica
Sistema de catálogo
El estudiante deberá ampliar la entidad Product.
Agregar:
category
brand
description
creationDate
La entidad podría evolucionar hacia:
@Entity
@Table(name = "products")
public class Product {


   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;


   private String name;


   private Double price;


   private Integer stock;


   private Boolean active;


   private String category;


   private String brand;


   private String description;


   private LocalDate creationDate;
}
Después se deberán implementar consultas para:
Buscar por marca
Buscar por categoría
Buscar activos
Buscar productos con stock mayor que cero
Buscar productos cuyo precio sea mayor que determinado valor
Buscar productos cuyo nombre contenga un texto
________________


40. Reto práctico
Crear los siguientes métodos:
List<Product> findByBrand(String brand);
List<Product> findByCategory(String category);
List<Product> findByStockGreaterThan(Integer stock);
List<Product>
findByPriceBetween(Double min, Double max);
List<Product>
findByActiveOrderByNameAsc(Boolean active);
El estudiante deberá identificar qué SQL aproximado podría generar cada método.
________________


41. Preguntas de comprobación
1. ¿Qué significa JPA?
2. ¿Cuál es la diferencia entre JPA e Hibernate?
3. ¿Qué función cumple Spring Data JPA?
4. ¿Qué indica @Entity?
5. ¿Qué indica @Id?
6. ¿Para qué se utiliza @GeneratedValue?
7. ¿Qué representa JpaRepository<Product, Long>?
8. ¿Qué hace findAll()?
9. ¿Qué retorna findById()?
10. ¿Qué diferencia existe entre una entidad y un repositorio?
11. ¿Qué función tiene la capa Service?
12. ¿Qué ocurre cuando se ejecuta save() sobre una entidad nueva?
13. ¿Qué ventaja tienen las consultas derivadas?
14. ¿Qué SQL podría representar findByPriceGreaterThan()?
15. ¿Cuál es la responsabilidad de Hibernate?
________________


42. Buenas prácticas iniciales
En aplicaciones reales conviene evitar colocar toda la lógica dentro de un controlador.
Debe preferirse:
Controller
   ↓
Service
   ↓
Repository
También conviene evitar exponer directamente las entidades en APIs complejas.
Posteriormente se pueden introducir:
DTO
Mapper
Validation
Exception Handler
Pagination
Specifications
JPQL
Relationships
Transactions
________________


43. Errores frecuentes
Error 1: olvidar @Entity
Si no se agrega:
@Entity
Hibernate no administra la clase.
________________


Error 2: olvidar @Id
Una entidad JPA debe disponer de una clave primaria.
________________


Error 3: base de datos inexistente
Hibernate puede crear tablas, pero normalmente no crea automáticamente la base de datos PostgreSQL.
Debe existir:
store_db
________________


Error 4: credenciales incorrectas
Verificar:
spring.datasource.username
spring.datasource.password
________________


Error 5: utilizar create accidentalmente
Esta configuración:
spring.jpa.hibernate.ddl-auto=create
puede eliminar información al reiniciar.
Para prácticas suele ser preferible:
spring.jpa.hibernate.ddl-auto=update
________________


44. Resumen conceptual
JPA permite representar tablas mediante objetos Java.
Por ejemplo:
Tabla products
     ⇅
Clase Product
La entidad:
@Entity
public class Product {
}
describe cómo se representa el dato.
El repositorio:
public interface ProductRepository
       extends JpaRepository<Product, Long> {
}
permite acceder a esos datos.
Spring Data JPA simplifica el código.
Hibernate realiza gran parte del trabajo de persistencia.
Finalmente:
Spring Boot
  ↓
Spring Data JPA
  ↓
JPA
  ↓
Hibernate
  ↓
JDBC
  ↓
PostgreSQL
Conclusión
La introducción a JPA marca un cambio importante frente al trabajo directo con JDBC. En lugar de pensar exclusivamente en registros y sentencias SQL, la aplicación comienza a trabajar con entidades, repositorios y objetos Java persistentes.
La idea principal que el estudiante debe conservar es:
@Entity
representa los datos
​
JpaRepository
permite administrarlos
​
Hibernate
transforma las operaciones Java en SQL






Guía teórico-práctica: Manejo de dependencias y Java Beans en Spring Boot
1. Introducción
El desarrollo de aplicaciones empresariales modernas requiere organizar adecuadamente tanto las bibliotecas externas utilizadas por el proyecto como los objetos que conforman la lógica interna de la aplicación.
Spring Boot simplifica ambos aspectos mediante dos mecanismos fundamentales:
* El manejo de dependencias, normalmente realizado mediante herramientas como Maven o Gradle.
* La gestión de objetos mediante el contenedor de Spring, donde dichos objetos reciben el nombre de Beans.
El manejo adecuado de las dependencias permite incorporar funcionalidades externas sin tener que desarrollar todo desde cero. Por ejemplo, una aplicación puede incorporar Spring Web para construir servicios REST, Spring Data JPA para acceder a bases de datos o Spring Security para implementar mecanismos de autenticación y autorización.
Por otra parte, el sistema de Beans permite que Spring cree, configure, administre e inyecte automáticamente los objetos necesarios dentro de la aplicación.
Estos dos conceptos constituyen elementos esenciales para comprender el funcionamiento interno de Spring Boot.
________________


2. Objetivos de aprendizaje
Al finalizar esta guía, el estudiante estará en capacidad de:
1. Comprender qué es una dependencia dentro de un proyecto Java.
2. Identificar el papel de Maven dentro de un proyecto Spring Boot.
3. Comprender la estructura básica del archivo pom.xml.
4. Agregar y administrar dependencias utilizando Maven.
5. Comprender el concepto de dependencia transitiva.
6. Comprender el propósito de los Spring Boot Starters.
7. Explicar el concepto de Inversión de Control.
8. Explicar el concepto de Inyección de Dependencias.
9. Comprender qué es un Java Bean dentro del ecosistema Spring.
10. Registrar Beans mediante anotaciones.
11. Utilizar las anotaciones @Component, @Service, @Repository y @Controller.
12. Crear Beans manualmente mediante @Bean.
13. Inyectar Beans utilizando el constructor.
14. Comprender el funcionamiento del contenedor IoC de Spring.
15. Analizar el ciclo de vida básico de un Bean.
16. Aplicar estos conceptos en una aplicación Spring Boot sencilla.
________________


3. Dependencias en aplicaciones Java
3.1 ¿Qué es una dependencia?
Una dependencia es una biblioteca, framework o módulo externo que una aplicación necesita para funcionar.
En lugar de desarrollar desde cero funcionalidades complejas, el proyecto puede utilizar componentes ya existentes.
Por ejemplo, una aplicación podría requerir:
* acceso a bases de datos;
* manejo de JSON;
* creación de APIs REST;
* validación de datos;
* seguridad;
* generación de logs;
* pruebas automatizadas.
Cada una de estas funcionalidades puede incorporarse mediante una dependencia.
Por ejemplo:
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-web</artifactId>
</dependency>
Esta dependencia incorpora los componentes necesarios para desarrollar aplicaciones web con Spring Boot.
________________


4. Problema del manejo manual de bibliotecas
Antes de la popularización de herramientas como Maven y Gradle, las bibliotecas Java normalmente se descargaban manualmente.
El desarrollador debía:
1. buscar la biblioteca en Internet;
2. descargar el archivo .jar;
3. copiarlo dentro del proyecto;
4. configurarlo en el classpath;
5. verificar sus versiones;
6. descargar las dependencias adicionales requeridas por dicha biblioteca.
Este proceso podía producir diferentes problemas.
Por ejemplo:
Proyecto
│
├── libreria-a.jar
├── libreria-b.jar
├── libreria-c.jar
├── libreria-d.jar
└── libreria-e.jar
Una biblioteca podía depender de otras bibliotecas.
Esto generaba lo que tradicionalmente se denomina:
Dependency Hell
o
infierno de dependencias.
El problema aparece cuando diferentes bibliotecas requieren versiones incompatibles de una misma dependencia.
________________


5. Herramientas para manejar dependencias
En proyectos Java modernos se utilizan principalmente dos herramientas:
Maven
Gradle
Spring Boot soporta ambas.
En esta guía se utilizará principalmente Maven, debido a que su estructura declarativa facilita la comprensión inicial del manejo de dependencias.
________________


6. ¿Qué es Maven?
Apache Maven es una herramienta para administrar y automatizar proyectos Java.
Maven permite:
* administrar dependencias;
* compilar proyectos;
* ejecutar pruebas;
* empaquetar aplicaciones;
* generar archivos .jar;
* gestionar plugins;
* organizar el ciclo de construcción del proyecto.
Maven utiliza un archivo principal denominado:
pom.xml
La palabra POM significa:
Project Object Model
________________


7. Estructura básica de un proyecto Maven
Un proyecto Spring Boot basado en Maven suele presentar una estructura similar a la siguiente:
mi-proyecto
│
├── src
│   ├── main
│   │   ├── java
│   │   └── resources
│   │
│   └── test
│       └── java
│
├── pom.xml
└── README.md
El archivo:
pom.xml
contiene la configuración principal del proyecto.
________________


8. Estructura básica del archivo pom.xml
Un ejemplo simplificado puede presentar la siguiente estructura:
<?xml version="1.0" encoding="UTF-8"?>
​
<project xmlns="http://maven.apache.org/POM/4.0.0">
​
   <modelVersion>4.0.0</modelVersion>
​
   <groupId>com.ejemplo</groupId>
​
   <artifactId>demo-spring</artifactId>
​
   <version>0.0.1-SNAPSHOT</version>
​
   <dependencies>
​
   </dependencies>
​
</project>
Cada elemento cumple una función determinada.
________________


9. groupId
El elemento:
<groupId>com.ejemplo</groupId>
identifica normalmente la organización, empresa o dominio responsable del proyecto.
Algunos ejemplos podrían ser:
com.empresa
com.academia
co.edu.institucion
com.breaklinestudio
El groupId ayuda a identificar de forma única los artefactos dentro del ecosistema Maven.
________________


10. artifactId
El elemento:
<artifactId>demo-spring</artifactId>
representa el nombre técnico del proyecto.
Por ejemplo:
gestion-clientes
api-productos
sistema-facturacion
lms-backend
________________


11. version
El elemento:
<version>0.0.1-SNAPSHOT</version>
representa la versión del proyecto.
El término:
SNAPSHOT
indica que el proyecto todavía se encuentra en desarrollo.
Una versión estable podría presentarse de la siguiente manera:
<version>1.0.0</version>
________________


12. Estructura de una dependencia Maven
Una dependencia generalmente se declara utilizando:
<dependency>


   <groupId>...</groupId>


   <artifactId>...</artifactId>


   <version>...</version>


</dependency>
Por ejemplo:
<dependency>
   <groupId>org.postgresql</groupId>
   <artifactId>postgresql</artifactId>
</dependency>
Cada dependencia se identifica principalmente mediante tres elementos.
groupId
artifactId
version
Estos tres valores funcionan de manera similar a una coordenada.
________________


13. Maven Central Repository
Maven obtiene las dependencias desde repositorios.
Uno de los repositorios públicos más utilizados es:
Maven Central
Cuando el proyecto necesita una dependencia, Maven busca el artefacto correspondiente y lo descarga.
Por ejemplo:
spring-boot-starter-web
Maven descarga automáticamente los archivos necesarios.
Las dependencias descargadas quedan almacenadas localmente en el equipo.
Normalmente se encuentran en:
~/.m2/repository
________________


14. Dependencias transitivas
Uno de los conceptos más importantes de Maven es el de:
dependencia transitiva.
Supóngase que una aplicación depende de:
Biblioteca A
y que la Biblioteca A necesita:
Biblioteca B
Biblioteca C
Biblioteca D
El desarrollador solamente declara:
Biblioteca A
Maven puede descargar automáticamente:
A
├── B
├── C
└── D
Estas bibliotecas adicionales reciben el nombre de:
dependencias transitivas.
________________


15. Dependencias en Spring Boot
Spring Boot simplifica considerablemente el manejo de dependencias mediante los llamados:
Spring Boot Starters
Un Starter agrupa las dependencias necesarias para desarrollar una determinada funcionalidad.
Por ejemplo:
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-web</artifactId>
</dependency>
El desarrollador no necesita investigar individualmente todas las bibliotecas necesarias para construir una aplicación web.
Spring Boot proporciona un conjunto compatible de dependencias.
________________


16. Principales Spring Boot Starters
Algunos Starters utilizados frecuentemente son:
Starter
	Propósito
	spring-boot-starter-web
	Desarrollo de aplicaciones web y APIs REST
	spring-boot-starter-data-jpa
	Persistencia utilizando JPA e Hibernate
	spring-boot-starter-security
	Seguridad, autenticación y autorización
	spring-boot-starter-validation
	Validación de objetos
	spring-boot-starter-test
	Pruebas automatizadas
	spring-boot-starter-thymeleaf
	Generación de vistas HTML
	spring-boot-starter-actuator
	Monitoreo y métricas de la aplicación
	________________


17. Ejemplo de dependencias de una API REST
Una API básica podría utilizar:
<dependencies>


   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-web</artifactId>
   </dependency>


   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-data-jpa</artifactId>
   </dependency>


   <dependency>
       <groupId>org.postgresql</groupId>
       <artifactId>postgresql</artifactId>
       <scope>runtime</scope>
   </dependency>


   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-test</artifactId>
       <scope>test</scope>
   </dependency>


</dependencies>
________________


18. Scope de las dependencias
Maven permite definir el alcance de una dependencia mediante:
<scope>
Entre los valores más comunes se encuentran:
compile
runtime
test
provided
________________


18.1 compile
Es el alcance utilizado normalmente por defecto.
La dependencia se encuentra disponible durante:
compilación
ejecución
pruebas
________________


18.2 runtime
La dependencia se necesita principalmente durante la ejecución.
Un ejemplo frecuente es el driver de PostgreSQL:
<dependency>
   <groupId>org.postgresql</groupId>
   <artifactId>postgresql</artifactId>
   <scope>runtime</scope>
</dependency>
________________


18.3 test
La dependencia solamente se utiliza para pruebas.
Ejemplo:
<scope>test</scope>
Normalmente se utiliza con:
JUnit
Mockito
Spring Boot Test
________________


18.4 provided
Indica que la dependencia se utiliza para compilar, pero normalmente será proporcionada por el entorno de ejecución.
________________


19. Administración automática de versiones
Spring Boot proporciona mecanismos de Dependency Management.
Esto permite que muchas dependencias no necesiten declarar explícitamente una versión.
Por ejemplo:
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-web</artifactId>
</dependency>
Obsérvese que no aparece:
<version>
Spring Boot administra una combinación de versiones compatibles.
Esto disminuye considerablemente los conflictos entre bibliotecas.
________________


20. Inspeccionar dependencias Maven
El desarrollador puede utilizar:
mvn dependency:tree
para visualizar el árbol completo de dependencias.
Un resultado simplificado podría ser:
spring-boot-starter-web
│
├── spring-web
├── spring-webmvc
├── jackson-databind
├── tomcat
└── spring-core
Esta herramienta resulta especialmente útil para identificar conflictos de versiones.
________________


21. Introducción a los Beans de Spring
Después de comprender las dependencias externas del proyecto, resulta necesario analizar cómo Spring administra los objetos internos de la aplicación.
En programación Java tradicional, los objetos normalmente se crean utilizando:
new
Por ejemplo:
ClienteService clienteService = new ClienteService();
En aplicaciones Spring, este proceso puede delegarse al framework.
Spring puede encargarse de:
* crear el objeto;
* configurarlo;
* almacenar su referencia;
* proporcionar el objeto donde sea necesario;
* administrar parcialmente su ciclo de vida.
Los objetos administrados por Spring reciben el nombre de:
Beans
________________


22. ¿Qué es un Spring Bean?
Un Spring Bean es un objeto Java cuya creación y administración se encuentra bajo responsabilidad del contenedor de Spring.
Conceptualmente:
Objeto Java
    +
Administrado por Spring
    =
Spring Bean
Por ejemplo:
@Service
public class ClienteService {


}
Cuando Spring detecta esta clase, puede crear automáticamente una instancia.
Conceptualmente:
new ClienteService()
Sin embargo, dicha creación es realizada internamente por el framework.
________________


23. El contenedor IoC
Spring utiliza un contenedor denominado:
IoC Container
IoC significa:
Inversion of Control
o:
Inversión de Control
El principio establece que la aplicación deja de controlar directamente la creación de determinados objetos y delega dicha responsabilidad al framework.
________________


24. Ejemplo sin Inversión de Control
Considérese la siguiente clase:
public class PedidoService {


   private EmailService emailService;


   public PedidoService() {
       emailService = new EmailService();
   }
}
En este caso, PedidoService controla directamente la creación de:
EmailService
Existe una dependencia fuerte entre ambas clases.
________________


25. Problemas del acoplamiento directo
La instrucción:
emailService = new EmailService();
produce varios inconvenientes.
Por ejemplo:
* mayor acoplamiento;
* menor flexibilidad;
* dificultad para realizar pruebas;
* dificultad para reemplazar implementaciones;
* mayor dependencia entre clases.
________________


26. Inversión de Control
Con Spring, la clase puede limitarse a declarar lo que necesita.
Por ejemplo:
@Service
public class PedidoService {


   private final EmailService emailService;


   public PedidoService(EmailService emailService) {
       this.emailService = emailService;
   }
}
La clase ya no crea:
new EmailService();
Spring proporciona automáticamente la dependencia.
________________


27. Inyección de Dependencias
Este mecanismo recibe el nombre de:
Dependency Injection
o:
Inyección de Dependencias
La Inyección de Dependencias constituye una técnica mediante la cual las dependencias de un objeto son suministradas externamente.
Spring utiliza esta técnica de forma intensiva.
________________


28. Diferencia entre IoC y Dependency Injection
Aunque ambos conceptos se encuentran relacionados, no significan exactamente lo mismo.
Inversión de Control
Es el principio general.
Spring controla la creación y gestión de los objetos.
Inyección de Dependencias
Es uno de los mecanismos utilizados para implementar la Inversión de Control.
Puede representarse de la siguiente forma:
Inversión de Control
       │
       └── Inyección de Dependencias
________________


29. ApplicationContext
El principal contenedor de Spring se representa normalmente mediante:
ApplicationContext
El ApplicationContext almacena y administra los Beans disponibles dentro de la aplicación.
Conceptualmente:
ApplicationContext
│
├── ClienteService
├── ProductoService
├── PedidoService
├── ClienteRepository
├── EmailService
└── ConfiguracionPago
Cuando alguna clase necesita uno de estos objetos, Spring consulta el contexto y proporciona el Bean correspondiente.
________________


30. ¿Cómo registra Spring los Beans?
Spring puede registrar Beans principalmente mediante:
1. escaneo de componentes;
2. configuración explícita con @Bean.
El mecanismo más común utiliza anotaciones como:
@Component
@Service
@Repository
@Controller
@RestController
________________


31. @Component
La anotación:
@Component
indica que una clase debe ser administrada por Spring.
Ejemplo:
@Component
public class Calculadora {


   public int sumar(int a, int b) {
       return a + b;
   }
}
Spring detectará la clase y creará un Bean.
Conceptualmente:
ApplicationContext
       │
       └── Calculadora
________________


32. Component Scanning
Spring Boot realiza un proceso denominado:
Component Scanning
Este mecanismo busca clases anotadas con estereotipos de Spring.
Por ejemplo:
com.ejemplo
│
├── DemoApplication.java
│
├── controller
│   └── ClienteController.java
│
├── service
│   └── ClienteService.java
│
└── repository
   └── ClienteRepository.java
Si la clase principal se encuentra en:
com.ejemplo
Spring Boot normalmente escaneará los paquetes ubicados debajo de dicho paquete.
________________


33. Clase principal de Spring Boot
La clase principal suele utilizar:
@SpringBootApplication
public class DemoApplication {


   public static void main(String[] args) {


       SpringApplication.run(
               DemoApplication.class,
               args
       );


   }


}
La anotación:
@SpringBootApplication
combina varias configuraciones.
De manera conceptual incluye funcionalidades relacionadas con:
@Configuration
@EnableAutoConfiguration
@ComponentScan
________________


34. Estereotipos de Spring
Además de @Component, Spring ofrece anotaciones especializadas.
Las más utilizadas son:
@Component
@Service
@Repository
@Controller
@RestController
Todas permiten registrar Beans.
Sin embargo, cada una comunica una intención arquitectónica diferente.
________________


35. @Service
La anotación:
@Service
se utiliza normalmente para representar componentes relacionados con la lógica de negocio.
Ejemplo:
@Service
public class ProductoService {


   public double calcularDescuento(double precio) {
       return precio * 0.10;
   }


}
La clase será registrada como Bean.
________________


36. @Repository
La anotación:
@Repository
se utiliza principalmente en componentes relacionados con persistencia y acceso a datos.
Ejemplo:
@Repository
public class ProductoRepository {


   public void guardar() {
       System.out.println("Producto guardado");
   }


}
________________


37. @Controller
La anotación:
@Controller
identifica componentes pertenecientes a la capa web basada en MVC.
Ejemplo:
@Controller
public class HomeController {


}
________________


38. @RestController
La anotación:
@RestController
se utiliza ampliamente para construir APIs REST.
Ejemplo:
@RestController
public class ProductoController {


   @GetMapping("/productos")
   public String listar() {
       return "Listado de productos";
   }


}
________________


39. Relación entre los estereotipos
Conceptualmente puede representarse así:
@Component
│
├── @Service
├── @Repository
├── @Controller
└── @RestController
Aunque técnicamente existen matices internos, desde la perspectiva pedagógica todas estas anotaciones permiten que Spring detecte y administre objetos.
________________


40. Inyección mediante constructor
La forma recomendada para inyectar dependencias consiste generalmente en utilizar el constructor.
Ejemplo:
@Service
public class PedidoService {


   private final ProductoRepository productoRepository;


   public PedidoService(
           ProductoRepository productoRepository
   ) {


       this.productoRepository = productoRepository;


   }


}
Spring buscará un Bean compatible con:
ProductoRepository
y lo proporcionará automáticamente.
________________


41. Ejemplo completo de inyección
Repositorio:
@Repository
public class ProductoRepository {


   public String buscarProducto() {


       return "Laptop";


   }


}
Servicio:
@Service
public class ProductoService {


   private final ProductoRepository repository;


   public ProductoService(
           ProductoRepository repository
   ) {


       this.repository = repository;


   }


   public String obtenerProducto() {


       return repository.buscarProducto();


   }


}
Controlador:
@RestController
public class ProductoController {


   private final ProductoService service;


   public ProductoController(
           ProductoService service
   ) {


       this.service = service;


   }


   @GetMapping("/producto")
   public String obtenerProducto() {


       return service.obtenerProducto();


   }


}
El flujo de dependencias puede representarse como:
ProductoController
       │
       ▼
ProductoService
       │
       ▼
ProductoRepository
Spring construye automáticamente esta estructura de objetos.
________________


42. ¿Qué ocurre internamente?
Cuando la aplicación inicia, Spring ejecuta conceptualmente un proceso similar al siguiente:
1. Inicia la aplicación
       ↓
2. Escanea paquetes
       ↓
3. Detecta componentes
       ↓
4. Registra Beans
       ↓
5. Analiza dependencias
       ↓
6. Crea objetos
       ↓
7. Inyecta dependencias
       ↓
8. Deja la aplicación disponible
________________


43. @Autowired
Spring también ofrece la anotación:
@Autowired
Esta anotación indica que una dependencia debe ser inyectada.
Por ejemplo:
@Service
public class ClienteService {


   private final ClienteRepository repository;


   @Autowired
   public ClienteService(
           ClienteRepository repository
   ) {


       this.repository = repository;


   }


}
Cuando existe un único constructor, normalmente Spring puede realizar la inyección sin escribir explícitamente @Autowired.
Por ello, es frecuente encontrar:
public ClienteService(
       ClienteRepository repository
) {


   this.repository = repository;


}
________________


44. Inyección por atributo
También es posible encontrar código como:
@Autowired
private ClienteRepository repository;
Sin embargo, desde una perspectiva de diseño, generalmente se prefiere la inyección mediante constructor.
La inyección por constructor presenta ventajas como:
* dependencias explícitas;
* objetos más fáciles de probar;
* posibilidad de utilizar atributos final;
* mayor claridad estructural;
* reducción de dependencias ocultas.
________________


45. Inyección por setter
Otra alternativa consiste en utilizar métodos setter.
Ejemplo:
@Service
public class ClienteService {


   private ClienteRepository repository;


   @Autowired
   public void setRepository(
           ClienteRepository repository
   ) {


       this.repository = repository;


   }


}
Esta modalidad puede utilizarse cuando una dependencia es opcional o cuando resulta necesario modificarla después de crear el objeto.
________________


46. Comparación de tipos de inyección
Tipo
	Características
	Constructor
	Recomendado para dependencias obligatorias
	Setter
	Útil para dependencias opcionales
	Campo
	Simple, pero genera mayor acoplamiento con el framework
	________________


47. Crear Beans manualmente con @Bean
No todos los Beans necesitan crearse mediante:
@Component
@Service
@Repository
Spring también permite registrar Beans manualmente mediante:
@Bean
Para ello suele utilizarse una clase de configuración.
Ejemplo:
@Configuration
public class AppConfig {


   @Bean
   public Calculadora calculadora() {


       return new Calculadora();


   }


}
Spring registrará el objeto retornado por el método como Bean.
________________


48. Diferencia entre @Component y @Bean
@Component se aplica directamente sobre una clase.
Ejemplo:
@Component
public class Calculadora {


}
Mientras que @Bean se aplica sobre un método dentro de una clase de configuración.
Ejemplo:
@Bean
public Calculadora calculadora() {


   return new Calculadora();


}
________________


49. ¿Cuándo utilizar @Bean?
@Bean resulta especialmente útil cuando:
* la clase pertenece a una biblioteca externa;
* no se puede modificar el código fuente de la clase;
* se necesita controlar manualmente la creación del objeto;
* el objeto necesita configuración especial.
Ejemplo conceptual:
@Bean
public ObjectMapper objectMapper() {


   ObjectMapper mapper = new ObjectMapper();


   // configuración personalizada


   return mapper;


}
________________


50. Nombre de un Bean
Spring asigna un nombre a cada Bean.
Por ejemplo:
@Component
public class EmailService {


}
El Bean normalmente recibirá un nombre similar a:
emailService
También puede declararse explícitamente:
@Component("servicioCorreo")
public class EmailService {


}
________________


51. Problema de múltiples Beans del mismo tipo
Supóngase la siguiente interfaz:
public interface NotificationService {


   void enviar();


}
Existen dos implementaciones:
@Service
public class EmailNotificationService
       implements NotificationService {


   @Override
   public void enviar() {


       System.out.println("Correo enviado");


   }


}
y:
@Service
public class SmsNotificationService
       implements NotificationService {


   @Override
   public void enviar() {


       System.out.println("SMS enviado");


   }


}
Ahora se presenta:
@Service
public class PedidoService {


   private final NotificationService notificationService;


   public PedidoService(
           NotificationService notificationService
   ) {


       this.notificationService = notificationService;


   }


}
Spring encuentra:
EmailNotificationService
SmsNotificationService
Ambos implementan:
NotificationService
Por lo tanto, Spring no puede determinar automáticamente cuál debe inyectar.
________________


52. @Qualifier
Una solución consiste en utilizar:
@Qualifier
Ejemplo:
public PedidoService(


   @Qualifier("emailNotificationService")
   NotificationService notificationService


) {


   this.notificationService = notificationService;


}
De esta manera se especifica exactamente qué Bean debe inyectarse.
________________


53. @Primary
Otra alternativa consiste en utilizar:
@Primary
Ejemplo:
@Service
@Primary
public class EmailNotificationService
       implements NotificationService {


}
Spring utilizará este Bean como implementación predeterminada.
________________


54. Alcance de los Beans
Los Beans pueden utilizar diferentes scopes.
Los más conocidos son:
singleton
prototype
request
session
________________


55. Scope Singleton
El scope predeterminado en Spring es:
singleton
Esto significa que normalmente se crea una única instancia del Bean dentro del contenedor.
Ejemplo:
ApplicationContext
       │
       └── ProductoService
            una instancia
Todas las clases que necesiten dicho Bean recibirán normalmente la misma instancia administrada.
________________


56. Scope Prototype
Con:
@Scope("prototype")
Spring crea una nueva instancia cada vez que el Bean es solicitado.
Ejemplo:
@Component
@Scope("prototype")
public class Operacion {


}
________________


57. Scope Request
En aplicaciones web puede utilizarse:
request
Esto permite crear un Bean para cada petición HTTP.
________________


58. Scope Session
El scope:
session
permite mantener un Bean asociado a una sesión HTTP.
________________


59. Ciclo de vida básico de un Bean
El ciclo de vida puede simplificarse de la siguiente forma:
Spring detecta la clase
       ↓
Crea la instancia
       ↓
Inyecta dependencias
       ↓
Ejecuta inicialización
       ↓
Bean disponible
       ↓
Aplicación se detiene
       ↓
Destrucción
________________


60. @PostConstruct
La anotación:
@PostConstruct
permite ejecutar código después de crear e inyectar las dependencias del Bean.
Ejemplo:
@Component
public class ConfiguracionInicial {


   @PostConstruct
   public void iniciar() {


       System.out.println(
           "Configuración inicializada"
       );


   }


}
________________


61. @PreDestroy
La anotación:
@PreDestroy
puede utilizarse para ejecutar lógica antes de destruir el Bean.
Ejemplo:
@PreDestroy
public void cerrar() {


   System.out.println(
       "Cerrando recursos"
   );


}
________________


62. Práctica guiada
Sistema de gestión de pedidos
Se desarrollará una aplicación sencilla compuesta por:
Controller
Service
Repository
NotificationService
La estructura será:
controller
   PedidoController


service
   PedidoService


repository
   PedidoRepository


notification
   NotificationService
   EmailNotificationService
________________


63. Paso 1. Crear el proyecto
El proyecto debe incorporar:
Spring Web
En Maven:
<dependency>


   <groupId>org.springframework.boot</groupId>


   <artifactId>
       spring-boot-starter-web
   </artifactId>


</dependency>
________________


64. Paso 2. Crear el repositorio
package com.ejemplo.repository;


import org.springframework.stereotype.Repository;


@Repository
public class PedidoRepository {


   public String guardarPedido() {


       return "Pedido almacenado correctamente";


   }


}
En este caso, PedidoRepository se convierte en un Bean.
________________


65. Paso 3. Crear la interfaz de notificaciones
package com.ejemplo.notification;


public interface NotificationService {


   String enviarNotificacion();


}
________________


66. Paso 4. Crear la implementación
package com.ejemplo.notification;


import org.springframework.stereotype.Service;


@Service
public class EmailNotificationService
       implements NotificationService {


   @Override
   public String enviarNotificacion() {


       return "Correo enviado correctamente";


   }


}
________________


67. Paso 5. Crear el servicio de pedidos
package com.ejemplo.service;


import com.ejemplo.notification.NotificationService;
import com.ejemplo.repository.PedidoRepository;
import org.springframework.stereotype.Service;


@Service
public class PedidoService {


   private final PedidoRepository pedidoRepository;


   private final NotificationService notificationService;


   public PedidoService(
           PedidoRepository pedidoRepository,
           NotificationService notificationService
   ) {


       this.pedidoRepository = pedidoRepository;


       this.notificationService = notificationService;


   }


   public String procesarPedido() {


       String resultadoPedido =
               pedidoRepository.guardarPedido();


       String resultadoNotificacion =
               notificationService.enviarNotificacion();


       return resultadoPedido
               + " - "
               + resultadoNotificacion;


   }


}
En este ejemplo, Spring debe resolver dos dependencias:
PedidoRepository
NotificationService
________________


68. Paso 6. Crear el controlador
package com.ejemplo.controller;


import com.ejemplo.service.PedidoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PedidoController {


   private final PedidoService pedidoService;


   public PedidoController(
           PedidoService pedidoService
   ) {


       this.pedidoService = pedidoService;


   }


   @GetMapping("/pedido")


   public String procesarPedido() {


       return pedidoService.procesarPedido();


   }


}
________________


69. Paso 7. Ejecutar la aplicación
Una vez ejecutada la aplicación, podrá consultarse:
GET /pedido
La respuesta será similar a:
Pedido almacenado correctamente - Correo enviado correctamente
________________


70. Análisis de lo ocurrido
Spring detecta inicialmente:
PedidoRepository
EmailNotificationService
PedidoService
PedidoController
Posteriormente crea los Beans.
Conceptualmente:
ApplicationContext
│
├── pedidoRepository
├── emailNotificationService
├── pedidoService
└── pedidoController
Después resuelve las dependencias.
PedidoController
     │
     ▼
PedidoService
  │       │
  ▼       ▼
Repository NotificationService
Spring descubre que:
EmailNotificationService
implementa:
NotificationService
Por lo tanto, puede utilizar dicha implementación.
________________


71. Ejercicio práctico 1
Sistema de pagos
Se debe construir una aplicación con la siguiente estructura:
PagoController
PagoService
PagoRepository
El flujo debe ser:
Controller
  ↓
Service
  ↓
Repository
El repositorio debe retornar:
Pago almacenado correctamente
El servicio deberá retornar:
Pago procesado correctamente
El controlador deberá exponer:
GET /pago
________________


72. Ejercicio práctico 2
Se debe crear la interfaz:
public interface PaymentGateway {


   String procesarPago();


}
Posteriormente deben implementarse:
PaypalPaymentGateway
StripePaymentGateway
Ambas clases deberán registrarse como Beans.
El estudiante deberá observar qué sucede cuando un servicio intenta inyectar:
PaymentGateway
sin utilizar @Qualifier.
Posteriormente deberá resolver el problema mediante:
@Qualifier
________________


73. Ejercicio práctico 3
Se debe crear una clase:
PasswordEncoderService
sin utilizar:
@Component
Posteriormente se deberá registrar manualmente mediante:
@Bean
dentro de:
@Configuration
Finalmente deberá inyectarse en otro servicio.
________________


74. Ejercicio práctico 4
Se debe crear un Bean denominado:
ApplicationInfo
que contenga:
private String nombre;
private String version;
El Bean deberá crearse utilizando:
@Bean
y contener los siguientes valores:
nombre = Sistema Académico
version = 1.0
Después deberá ser inyectado dentro de un controlador.
________________


75. Ejercicio práctico 5
Se deben crear:
EmailService
SmsService
PushNotificationService
Las tres clases deben implementar:
NotificationService
Posteriormente deberá utilizarse:
@Primary
para establecer una implementación predeterminada.
________________


76. Preguntas de comprensión
1. ¿Qué problema resuelve un administrador de dependencias?
2. ¿Cuál es la función principal de Maven?
3. ¿Qué información contiene el archivo pom.xml?
4. ¿Qué representan groupId y artifactId?
5. ¿Qué es una dependencia transitiva?
6. ¿Qué ventaja proporcionan los Spring Boot Starters?
7. ¿Qué significa Inversión de Control?
8. ¿Qué significa Inyección de Dependencias?
9. ¿Qué es un Spring Bean?
10. ¿Cuál es la función del ApplicationContext?
11. ¿Qué función cumple @Component?
12. ¿Cuál es la diferencia conceptual entre @Service y @Repository?
13. ¿Cuál es la diferencia entre @Component y @Bean?
14. ¿Por qué se recomienda la inyección mediante constructor?
15. ¿Para qué se utiliza @Qualifier?
16. ¿Para qué se utiliza @Primary?
17. ¿Qué significa que un Bean sea Singleton?
18. ¿Cuál es la función de @PostConstruct?
________________


77. Actividad de análisis
Se presenta el siguiente código:
@Service
public class FacturaService {


   private EmailService emailService =
           new EmailService();


}
El estudiante deberá identificar los problemas de diseño existentes.
Posteriormente deberá transformar la implementación utilizando:
Inversión de Control
+
Inyección de Dependencias
Una posible estructura sería:
@Service
public class FacturaService {


   private final EmailService emailService;


   public FacturaService(
           EmailService emailService
   ) {


       this.emailService = emailService;


   }


}
________________


78. Taller integrador
Sistema de tienda
Se deberá desarrollar una aplicación Spring Boot que implemente:
ProductoController
ProductoService
ProductoRepository
NotificationService
EmailNotificationService
PaymentService
PaymentGateway
La arquitectura deberá presentar:
REST Controller
      ↓
    Service
      ↓
  Repository
Adicionalmente:
Service
  ↓
NotificationService
y:
Service
  ↓
PaymentGateway
El sistema deberá utilizar:
* @RestController;
* @Service;
* @Repository;
* inyección mediante constructor;
* una interfaz;
* dos implementaciones;
* @Qualifier;
* un Bean creado mediante @Bean.
________________


79. Resultado arquitectónico esperado
El sistema podría presentar:
ProductoController
       │
       ▼
ProductoService
  │         │
  │         └───────────────┐
  ▼                         ▼
ProductoRepository    NotificationService
                            │
                            ▼
                EmailNotificationService


ProductoService
       │
       ▼
PaymentGateway
       │
       ▼
PaypalPaymentGateway
Este diseño reduce el acoplamiento entre componentes.
________________


80. Buenas prácticas
En proyectos Spring Boot se recomienda generalmente:
1. utilizar inyección mediante constructor;
2. declarar como final las dependencias obligatorias;
3. evitar crear manualmente servicios mediante new;
4. utilizar interfaces cuando existan varias implementaciones posibles;
5. utilizar @Service para lógica de negocio;
6. utilizar @Repository para acceso a datos;
7. utilizar @RestController para APIs REST;
8. utilizar @Bean cuando la construcción del objeto requiera configuración explícita;
9. mantener organizada la estructura de paquetes;
10. evitar dependencias innecesarias dentro del pom.xml.
________________


81. Errores frecuentes
Error 1. Crear manualmente una dependencia
Código poco recomendable:
ProductoService service =
       new ProductoService();
dentro de un componente administrado por Spring.
Esto impide aprovechar completamente el contenedor IoC.
________________


Error 2. Clase fuera del Component Scan
Si la clase principal se encuentra en:
com.empresa.app
pero un servicio se encuentra en:
org.otropaquete.service
Spring podría no detectarlo automáticamente.
________________


Error 3. Olvidar una anotación
Una clase como:
public class ClienteService {


}
no será registrada automáticamente como Bean simplemente por existir.
Podría necesitar:
@Service
________________


Error 4. Varias implementaciones
Cuando existen:
EmailNotificationService
SmsNotificationService
implementando la misma interfaz, Spring puede necesitar:
@Qualifier
o:
@Primary
________________


Error 5. Agregar dependencias incompatibles
Modificar versiones manualmente sin comprender la gestión de dependencias de Spring Boot puede producir conflictos.
Por ello resulta conveniente utilizar la administración de versiones proporcionada por Spring Boot siempre que sea posible.
________________


82. Síntesis conceptual
El manejo de dependencias y los Beans resuelven dos problemas diferentes pero relacionados.
Dependencias Maven
Administran principalmente:
bibliotecas externas
Ejemplo:
Spring Web
PostgreSQL Driver
Hibernate
JUnit
Spring Beans
Administran principalmente:
objetos internos de la aplicación
Ejemplo:
ClienteService
ProductoRepository
PedidoController
EmailService
Esta diferencia resulta fundamental.
Puede resumirse de la siguiente forma:
Maven
  ↓
Gestiona dependencias externas


Spring Container
  ↓
Gestiona objetos internos
________________


83. Modelo mental recomendado
Una forma sencilla de comprender Spring consiste en imaginar un contenedor central:
                SPRING CONTAINER


┌───────────────────────────────────────┐
│                                       │
│ ClienteController                     │
│                                       │
│ ClienteService                        │
│                                       │
│ ClienteRepository                     │
│                                       │
│ EmailService                          │
│                                       │
│ PaymentGateway                        │
│                                       │
└───────────────────────────────────────┘
Cada objeto administrado dentro de este contenedor constituye un Bean.
Las dependencias entre ellos son conectadas automáticamente por Spring.
________________


84. Conclusión
El manejo de dependencias constituye uno de los pilares fundamentales de los proyectos Java modernos. Herramientas como Maven permiten incorporar bibliotecas externas de forma estructurada, reproducible y controlada.
Spring Boot simplifica aún más este proceso mediante los Starters y su sistema de administración de versiones.
Por otro lado, el sistema de Beans representa uno de los componentes centrales de la arquitectura Spring.
Mediante el contenedor IoC, Spring asume la responsabilidad de crear, configurar e interconectar los objetos de la aplicación.
Esto permite aplicar principios importantes de ingeniería de software, entre ellos:
Bajo acoplamiento
Alta cohesión
Separación de responsabilidades
Inversión de dependencias
Facilidad de pruebas
Mantenibilidad
Escalabilidad
Comprender correctamente la relación entre:
Maven
Dependencias
Spring Boot Starters
IoC
Dependency Injection
ApplicationContext
Beans
@Component
@Service
@Repository
@Bean
resulta esencial antes de avanzar hacia temas como:
Spring Data JPA
Spring Security
Arquitectura Hexagonal
Microservicios
Testing
Eventos
Spring Cloud
Spring AI
Estos conceptos constituyen la base sobre la cual se construye gran parte del ecosistema Spring moderno.


















Guía teórico-práctica: Manejo y Gestión de Excepciones en Spring Boot
1. Introducción
En una aplicación real, no todo ocurre como se espera.
Pueden presentarse situaciones como:
Usuario inexistente
Producto no encontrado
Datos inválidos
Correo duplicado
Stock insuficiente
Error de conexión a base de datos
Violación de una restricción
Parámetro mal formado
Petición incompleta
Si estos errores no se gestionan correctamente, el sistema puede responder con mensajes poco claros como:
500 Internal Server Error
o incluso exponer información técnica interna que no debería llegar al cliente.
El objetivo del manejo de excepciones es convertir errores técnicos o de negocio en respuestas claras, controladas y consistentes.
Una arquitectura adecuada debería transformar:
Excepción interna
     ↓
Manejo centralizado
     ↓
Respuesta HTTP coherente
Por ejemplo:
CountryNotFoundException
     ↓
ExceptionHandler
     ↓
404 Not Found
________________


2. Objetivos de aprendizaje
Al finalizar esta guía, el estudiante estará en capacidad de:
1. Comprender qué es una excepción.
2. Diferenciar errores controlados y no controlados.
3. Comprender la jerarquía de excepciones en Java.
4. Crear excepciones personalizadas.
5. Utilizar try-catch.
6. Comprender throw y throws.
7. Diferenciar excepciones de dominio, aplicación e infraestructura.
8. Gestionar excepciones en controladores REST.
9. Utilizar @ExceptionHandler.
10. Utilizar @ControllerAdvice y @RestControllerAdvice.
11. Crear respuestas de error estándar.
12. Manejar errores de validación.
13. Manejar errores de persistencia.
14. Aplicar buenas prácticas de manejo de errores.
________________


3. ¿Qué es una excepción?
Una excepción es un evento que interrumpe el flujo normal de ejecución de una aplicación.
Ejemplo:
int result = 10 / 0;
produce:
ArithmeticException
Otro ejemplo:
String text = null;
​
System.out.println(text.length());
produce:
NullPointerException
Una excepción indica que algo inesperado ocurrió durante la ejecución.
________________


4. Jerarquía básica de excepciones en Java
Java posee una jerarquía basada en:
Throwable
├── Error
└── Exception
   ├── RuntimeException
   └── otras excepciones
Visualmente:
Throwable
  │
  ├── Error
  │
  └── Exception
         │
         ├── Checked Exceptions
         │
         └── RuntimeException
                │
                ├── NullPointerException
                ├── IllegalArgumentException
                ├── IllegalStateException
                └── etc.
________________


5. Checked y Unchecked Exceptions
Checked Exceptions
Son verificadas por el compilador.
Ejemplo:
IOException
SQLException
Generalmente obligan a usar:
try-catch
o:
throws
Ejemplo:
public void readFile() throws IOException {
}
________________


Unchecked Exceptions
Hereden de:
RuntimeException
No es obligatorio capturarlas.
Ejemplos:
NullPointerException
IllegalArgumentException
IllegalStateException
En aplicaciones Spring Boot es común que las excepciones de negocio personalizadas hereden de:
RuntimeException
________________


6. Uso de try-catch
Ejemplo básico:
try {


   int result = 10 / 0;


} catch (ArithmeticException ex) {


   System.out.println(
       "No se puede dividir entre cero"
   );
}
Flujo:
try
↓
se ejecuta código
↓
ocurre excepción
↓
catch
↓
se maneja
________________


7. Uso de throw
throw se utiliza para lanzar una excepción.
Ejemplo:
if (price.signum() <= 0) {


   throw new IllegalArgumentException(
       "El precio debe ser mayor que cero"
   );
}
Esto significa:
Regla incumplida
     ↓
throw
     ↓
Excepción
________________


8. Uso de throws
throws declara que un método puede propagar una excepción.
Ejemplo:
public void readFile()
       throws IOException {


}
No lanza necesariamente la excepción por sí mismo, sino que declara que puede propagarse.
________________


9. Excepciones personalizadas
Una aplicación empresarial debería utilizar excepciones con significado.
Evitar:
throw new RuntimeException(
   "No existe"
);
Preferir:
throw new CountryNotFoundException(id);
Por ejemplo:
public class CountryNotFoundException
       extends RuntimeException {


   public CountryNotFoundException(
           CountryId id
   ) {


       super(
           "Country not found with id: "
           + id.value()
       );
   }
}
Esto mejora:
Legibilidad
Semántica
Mantenimiento
Depuración
Manejo HTTP
________________


10. Excepciones en una arquitectura por capas
En una arquitectura organizada pueden existir distintas excepciones según la capa.
Domain
Application
Infrastructure
Por ejemplo:
domain
└── exception
   └── CountryNotFoundException


application
└── exception
   └── CountryNotFoundApplicationException


infrastructure
└── exceptionhandlers
   └── CountryExceptionHandler
________________


11. Excepción de dominio
Representa una violación o condición propia del negocio.
Ejemplo:
public class CountryInactiveException
       extends RuntimeException {


   public CountryInactiveException() {
       super("Country is inactive");
   }
}
Otro ejemplo:
public class InvalidCountryCodeException
       extends RuntimeException {


   public InvalidCountryCodeException(
           String code
   ) {


       super(
           "Invalid country code: "
           + code
       );
   }
}
________________


12. Excepción de aplicación
Representa un error detectado durante la ejecución de un caso de uso.
Por ejemplo:
public class CountryNotFoundApplicationException
       extends ApplicationException {


   public CountryNotFoundApplicationException(
           String id
   ) {


       super(
           "Country not found with id: "
           + id
       );
   }
}
Esto permite separar:
Error del dominio
de:
Error del caso de uso
________________


13. Excepción en un Use Case
Ejemplo:
public CountryResponse execute(
       CountryId id
) {


   Country country =
           repository.findById(id)
                   .orElseThrow(() ->
                           new CountryNotFoundApplicationException(
                                   id.value()
                                           .toString()
                           )
                   );


   return new CountryResponse(
           country.id().value(),
           country.name(),
           country.code(),
           country.active()
   );
}
Flujo:
findById
  ↓
¿existe?
  ├── sí → retorna Country
  └── no → lanza excepción
________________


14. ¿Qué pasa si no se maneja la excepción?
Si una excepción llega al controlador y no existe un manejo específico, Spring Boot puede responder:
500 Internal Server Error
Eso no siempre es correcto.
Si el recurso no existe, lo adecuado sería:
404 Not Found
________________


15. @ExceptionHandler
Spring permite manejar excepciones mediante:
@ExceptionHandler
Ejemplo:
@ExceptionHandler(
       CountryNotFoundApplicationException.class
)
public ResponseEntity<String> handleNotFound(
       CountryNotFoundApplicationException ex
) {


   return ResponseEntity
           .status(HttpStatus.NOT_FOUND)
           .body(ex.getMessage());
}
Esto transforma:
CountryNotFoundApplicationException
en:
HTTP 404
________________


16. Problema de manejar errores dentro del Controller
Se podría hacer esto:
@RestController
public class CountryController {


   @ExceptionHandler(
       CountryNotFoundApplicationException.class
   )
   public ResponseEntity<String> handle(...) {
   }
}
Pero si tenemos muchos controladores:
CountryController
EmpresaController
CustomerController
ProductController
terminaríamos repitiendo lógica.
Por eso se utiliza:
@RestControllerAdvice
________________


17. @ControllerAdvice
@ControllerAdvice permite definir manejo global.
Ejemplo:
@ControllerAdvice
public class GlobalExceptionHandler {
}
________________


18. @RestControllerAdvice
Para APIs REST normalmente se utiliza:
@RestControllerAdvice
porque combina:
@ControllerAdvice
+
@ResponseBody
Por tanto, las respuestas se serializan automáticamente a JSON.
Ejemplo:
@RestControllerAdvice
public class CountryExceptionHandler {
}
________________


19. Handler específico para Country
@RestControllerAdvice
public class CountryExceptionHandler {


   @ExceptionHandler(
           CountryNotFoundApplicationException.class
   )
   public ResponseEntity<Map<String, String>>
   handleNotFound(
           CountryNotFoundApplicationException ex
   ) {


       return ResponseEntity
               .status(HttpStatus.NOT_FOUND)
               .body(
                       Map.of(
                               "message",
                               ex.getMessage()
                       )
               );
   }
}
Respuesta:
{
 "message": "Country not found with id: ..."
}
________________


20. Crear una respuesta estándar
Una API profesional debería devolver una estructura uniforme.
Por ejemplo:
{
 "timestamp": "2026-10-01T13:30:00",
 "status": 404,
 "error": "Not Found",
 "message": "Country not found",
 "path": "/api/countries/123"
}
Podemos crear:
public record ApiErrorResponse(
       LocalDateTime timestamp,
       int status,
       String error,
       String message,
       String path
) {
}
________________


21. Handler usando ApiErrorResponse
@RestControllerAdvice
public class CountryExceptionHandler {


   @ExceptionHandler(
           CountryNotFoundApplicationException.class
   )
   public ResponseEntity<ApiErrorResponse>
   handleNotFound(
           CountryNotFoundApplicationException ex,
           HttpServletRequest request
   ) {


       ApiErrorResponse response =
               new ApiErrorResponse(
                       LocalDateTime.now(),
                       HttpStatus.NOT_FOUND.value(),
                       HttpStatus.NOT_FOUND
                               .getReasonPhrase(),
                       ex.getMessage(),
                       request.getRequestURI()
               );


       return ResponseEntity
               .status(HttpStatus.NOT_FOUND)
               .body(response);
   }
}
________________


22. Ventaja de una estructura estándar
Todos los errores tienen el mismo formato.
Por ejemplo:
404
400
409
500
todos pueden responder:
{
 "timestamp": "...",
 "status": 400,
 "error": "...",
 "message": "...",
 "path": "..."
}
Esto facilita el trabajo del frontend.
________________


23. Manejo de errores de validación
Supongamos:
public record CreateCountryRequest(


   @NotBlank
   String name,


   @NotBlank
   String code


) {
}
y en el controller:
@PostMapping
public ResponseEntity<CountryResponse> create(
       @Valid
       @RequestBody CreateCountryRequest request
) {
}
Si llega:
{
 "name": "",
 "code": ""
}
Spring lanza una excepción de validación.
________________


24. MethodArgumentNotValidException
Una de las excepciones comunes es:
MethodArgumentNotValidException
Podemos manejarla:
@ExceptionHandler(
       MethodArgumentNotValidException.class
)
public ResponseEntity<?> handleValidation(
       MethodArgumentNotValidException ex
) {
}
________________


25. Extraer errores de campos
Ejemplo:
@ExceptionHandler(
       MethodArgumentNotValidException.class
)
public ResponseEntity<Map<String, String>>
handleValidation(
       MethodArgumentNotValidException ex
) {


   Map<String, String> errors =
           new HashMap<>();


   ex.getBindingResult()
           .getFieldErrors()
           .forEach(error ->
                   errors.put(
                           error.getField(),
                           error.getDefaultMessage()
                   )
           );


   return ResponseEntity
           .badRequest()
           .body(errors);
}
Respuesta:
{
 "name": "name is required",
 "code": "code is required"
}
________________


26. Mejor respuesta de validación
Podemos utilizar:
public record ValidationErrorResponse(
       LocalDateTime timestamp,
       int status,
       String error,
       Map<String, String> errors,
       String path
) {
}
Esto permite:
{
 "timestamp": "2026-10-01T13:40:00",
 "status": 400,
 "error": "Validation Error",
 "errors": {
   "name": "name is required",
   "code": "code is required"
 },
 "path": "/api/countries"
}
________________


27. Error de parámetro mal formado
Supongamos:
GET /api/countries/abc
pero el controller espera:
@PathVariable UUID id
Spring puede lanzar:
MethodArgumentTypeMismatchException
Podemos manejarlo:
@ExceptionHandler(
       MethodArgumentTypeMismatchException.class
)
public ResponseEntity<ApiErrorResponse>
handleTypeMismatch(
       MethodArgumentTypeMismatchException ex,
       HttpServletRequest request
) {


   ApiErrorResponse response =
           new ApiErrorResponse(
                   LocalDateTime.now(),
                   400,
                   "Bad Request",
                   "Invalid parameter: "
                       + ex.getName(),
                   request.getRequestURI()
           );


   return ResponseEntity
           .badRequest()
           .body(response);
}
________________


28. Error de JSON mal formado
Si llega:
{
 "name": "Colombia",
 "code":
}
Spring puede lanzar:
HttpMessageNotReadableException
Se puede manejar:
@ExceptionHandler(
       HttpMessageNotReadableException.class
)
public ResponseEntity<ApiErrorResponse>
handleUnreadable(
       HttpMessageNotReadableException ex,
       HttpServletRequest request
) {
}
Respuesta adecuada:
400 Bad Request
________________


29. Manejo de duplicados
Supongamos que:
code
de Country debe ser único.
Podemos definir una excepción:
public class CountryCodeAlreadyExistsException
       extends RuntimeException {


   public CountryCodeAlreadyExistsException(
           String code
   ) {


       super(
           "Country code already exists: "
           + code
       );
   }
}
________________


30. Validar en el Use Case
public CountryResponse execute(
       RegisterCountryCommand command
) {


   if (repository.existsByCode(
           command.code()
   )) {


       throw new CountryCodeAlreadyExistsException(
               command.code()
       );
   }


   Country country =
           Country.register(
                   command.name(),
                   command.code()
           );


   Country saved =
           repository.save(country);


   return mapper(saved);
}
________________


31. Respuesta HTTP para duplicados
Un conflicto de unicidad suele representarse con:
409 Conflict
Handler:
@ExceptionHandler(
       CountryCodeAlreadyExistsException.class
)
public ResponseEntity<ApiErrorResponse>
handleConflict(
       CountryCodeAlreadyExistsException ex,
       HttpServletRequest request
) {


   ApiErrorResponse response =
           new ApiErrorResponse(
                   LocalDateTime.now(),
                   HttpStatus.CONFLICT.value(),
                   HttpStatus.CONFLICT
                           .getReasonPhrase(),
                   ex.getMessage(),
                   request.getRequestURI()
           );


   return ResponseEntity
           .status(HttpStatus.CONFLICT)
           .body(response);
}
________________


32. Tabla de errores HTTP frecuentes
Situación
	Código
	Datos inválidos
	400
	No autenticado
	401
	Sin permiso
	403
	Recurso inexistente
	404
	Conflicto
	409
	Error interno
	500
	________________


33. Manejo de errores de base de datos
Spring puede lanzar excepciones como:
DataIntegrityViolationException
por ejemplo cuando se viola una restricción.
Handler:
@ExceptionHandler(
       DataIntegrityViolationException.class
)
public ResponseEntity<ApiErrorResponse>
handleDataIntegrity(
       DataIntegrityViolationException ex,
       HttpServletRequest request
) {


   ApiErrorResponse response =
           new ApiErrorResponse(
                   LocalDateTime.now(),
                   409,
                   "Conflict",
                   "Database constraint violation",
                   request.getRequestURI()
           );


   return ResponseEntity
           .status(HttpStatus.CONFLICT)
           .body(response);
}
________________


34. No exponer detalles internos
Evitar respuestas como:
org.postgresql.util.PSQLException:
duplicate key value violates unique constraint...
Eso expone detalles técnicos.
Preferir:
{
 "status": 409,
 "message": "Country code already exists"
}
________________


35. Handler global
Además de handlers específicos, conviene tener uno genérico.
@ExceptionHandler(Exception.class)
public ResponseEntity<ApiErrorResponse>
handleGeneric(
       Exception ex,
       HttpServletRequest request
) {


   ApiErrorResponse response =
           new ApiErrorResponse(
                   LocalDateTime.now(),
                   500,
                   "Internal Server Error",
                   "An unexpected error occurred",
                   request.getRequestURI()
           );


   return ResponseEntity
           .status(
                   HttpStatus.INTERNAL_SERVER_ERROR
           )
           .body(response);
}
________________


36. ¿Por qué no devolver ex.getMessage() siempre?
Porque una excepción inesperada puede contener:
SQL
Paths
Credenciales parciales
Nombres internos
Detalles de infraestructura
Stack traces
Por eso:
Cliente
→ mensaje seguro
y:
Logs
→ detalle técnico
________________


37. Uso de logs
Ejemplo:
private static final Logger log =
       LoggerFactory.getLogger(
               GlobalExceptionHandler.class
       );
En error inesperado:
log.error(
       "Unexpected error",
       ex
);
Y al cliente:
An unexpected error occurred
________________


38. Separar logging de respuesta
Buenas prácticas:
Internamente
↓
log detallado


Externamente
↓
mensaje controlado
________________


39. Arquitectura recomendada
Una estructura podría ser:
application
└── common
   └── exception
       └── ApplicationException.java


domain
└── country
   └── exception
       ├── CountryNotFoundException.java
       └── InvalidCountryCodeException.java


infrastructure
└── country
   └── adapters
       └── in
           └── rest
               └── exceptionhandlers
                   └── CountryExceptionHandler.java


infrastructure
└── common
   └── exception
       ├── ApiErrorResponse.java
       └── GlobalExceptionHandler.java
________________


40. Responsabilidad de cada capa
Domain
Define errores propios del negocio.
Ejemplo:
InvalidCountryCodeException
________________


Application
Define errores de casos de uso.
Ejemplo:
CountryNotFoundApplicationException
________________


Infrastructure
Convierte excepciones a:
HTTP
JSON
Status codes
________________


41. Flujo completo
GET /api/countries/123
       ↓
CountryController
       ↓
GetCountryByIdUseCase
       ↓
CountryRepository
       ↓
no existe
       ↓
CountryNotFoundApplicationException
       ↓
CountryExceptionHandler
       ↓
404 Not Found
________________


42. Práctica guiada 1
Crear:
CountryNotFoundApplicationException
y modificar:
GetCountryByIdUseCase
para lanzar la excepción cuando el país no exista.
________________


43. Práctica guiada 2
Crear:
CountryCodeAlreadyExistsException
y lanzar:
409 Conflict
si el código ya existe.
________________


44. Práctica guiada 3
Crear:
ApiErrorResponse
con:
timestamp
status
error
message
path
________________


45. Práctica guiada 4
Crear un:
CountryExceptionHandler
que maneje:
CountryNotFoundApplicationException
CountryCodeAlreadyExistsException
________________


46. Práctica guiada 5
Agregar validación a:
CreateCountryRequest
Ejemplo:
@NotBlank
String name;


@NotBlank
@Size(min = 2, max = 3)
String code;
________________


47. Taller práctico
Crear manejo completo de excepciones para:
Product
Situaciones:
ProductNotFoundException
InvalidPriceException
InsufficientStockException
ProductCodeAlreadyExistsException
ValidationException
Mapear a:
404
400
409
según corresponda.
________________


48. Ejemplo de InsufficientStockException
public class InsufficientStockException
       extends RuntimeException {


   public InsufficientStockException(
           int requested,
           int available
   ) {


       super(
           "Requested stock: "
           + requested
           + ", available: "
           + available
       );
   }
}
________________


49. Handler correspondiente
@ExceptionHandler(
       InsufficientStockException.class
)
public ResponseEntity<ApiErrorResponse>
handleStock(
       InsufficientStockException ex,
       HttpServletRequest request
) {


   return ResponseEntity
           .badRequest()
           .body(
                   new ApiErrorResponse(
                           LocalDateTime.now(),
                           400,
                           "Bad Request",
                           ex.getMessage(),
                           request.getRequestURI()
                   )
           );
}
________________


50. Errores frecuentes
Error 1
Usar:
RuntimeException
para todo.
________________


Error 2
Capturar todo en el controller con:
try-catch
________________


Error 3
Devolver siempre:
500
________________


Error 4
Exponer stack trace al cliente.
________________


Error 5
Mezclar lógica de negocio dentro del exception handler.
________________


Error 6
Usar mensajes inconsistentes.
________________


51. Buenas prácticas
1. Utilizar excepciones con nombres semánticos.
2. Centralizar manejo HTTP.
3. No exponer detalles internos.
4. Usar códigos HTTP adecuados.
5. Mantener respuestas uniformes.
6. Registrar errores importantes.
7. Diferenciar errores de dominio y técnicos.
8. Validar temprano.
9. Evitar try-catch innecesario.
10. Documentar errores en Swagger/OpenAPI.
________________


52. Integración con Swagger
Ejemplo:
@ApiResponses({


   @ApiResponse(
       responseCode = "200",
       description = "Country found"
   ),


   @ApiResponse(
       responseCode = "404",
       description = "Country not found"
   ),


   @ApiResponse(
       responseCode = "500",
       description = "Internal server error"
   )
})
La documentación debe reflejar los errores reales.
________________


53. Mapa conceptual
               EXCEPCIONES
                   │
       ┌───────────┼───────────┐
       │           │           │
     Domain    Application  Infrastructure
       │           │           │
Reglas negocio   Use Cases    HTTP Mapping
       │           │           │
       └───────────┴───────────┘
                   │
            ExceptionHandler
                   │
                   ▼
               HTTP Response
________________


54. Ejemplo completo resumido
Excepción:
public class CountryNotFoundApplicationException
       extends RuntimeException {


   public CountryNotFoundApplicationException(
           String id
   ) {
       super("Country not found: " + id);
   }
}
Use Case:
Country country =
       repository.findById(id)
               .orElseThrow(() ->
                       new CountryNotFoundApplicationException(
                               id.value().toString()
                       )
               );
Handler:
@ExceptionHandler(
       CountryNotFoundApplicationException.class
)
public ResponseEntity<ApiErrorResponse>
handleCountryNotFound(
       CountryNotFoundApplicationException ex,
       HttpServletRequest request
) {


   return ResponseEntity
           .status(HttpStatus.NOT_FOUND)
           .body(
                   new ApiErrorResponse(
                           LocalDateTime.now(),
                           404,
                           "Not Found",
                           ex.getMessage(),
                           request.getRequestURI()
                   )
           );
}
________________


55. Conclusión
El manejo de excepciones en Spring Boot no consiste simplemente en capturar errores.
Debe diseñarse como parte de la arquitectura.
La idea general es:
Error
↓
Excepción semántica
↓
Propagación
↓
Handler
↓
Código HTTP
↓
Respuesta JSON clara
En una arquitectura bien organizada:
Domain
→ expresa errores de negocio
​
Application
→ expresa errores del caso de uso
​
Infrastructure
→ traduce errores a HTTP

