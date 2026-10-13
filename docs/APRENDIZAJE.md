# APRENDIZAJE.md — Guía de estudio por fase

Por cada fase: conceptos clave aplicados, 3 preguntas de repaso tipo sustentación
con respuesta, y errores comunes de junior que se evitaron.

## Fase 0 — Plan y convenciones

### Conceptos clave
- **Arquitectura hexagonal (puertos y adaptadores):** el dominio define interfaces (puertos);
  la infraestructura las implementa (adaptadores). El negocio no conoce la tecnología.
- **Regla de dependencias:** `infrastructure → application → domain`.
- **ADR:** documentar por qué se tomó una decisión, no solo qué se hizo.

### Preguntas de repaso
1. **¿Por qué `application` no usa anotaciones de Spring?**
   Para que los casos de uso no dependan del framework. Se prueban con fakes en memoria
   y se podría cambiar Spring sin tocar el negocio. Los beans se declaran a mano en `BeansConfig`.
2. **¿Qué es un puerto y qué es un adaptador?**
   El puerto es una interfaz que pertenece al dominio o a la aplicación
   (ej. `ToolRepository`). El adaptador la implementa con una tecnología concreta (ej. JPA).
3. **¿Por qué se documentan las desviaciones respecto a los repos del profesor?**
   Porque un cambio sin explicación parece un error. Con el motivo escrito se puede defender
   en la sustentación.

### Errores comunes de junior evitados
- Poner lógica de negocio en el controlador.
- Dejar contraseñas en el YAML.
- Subir `target/` o `.env` al repositorio.

---
> Las siguientes fases se agregan aquí al cerrarse cada una.
