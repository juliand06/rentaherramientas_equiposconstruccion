package com.rentatools.infrastructure;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

/**
 * Verifica automáticamente la regla de dependencias de la arquitectura hexagonal:
 * {@code infrastructure → application → domain}.
 *
 * <p>Por qué existe: un import equivocado no rompe la compilación, pero rompe la arquitectura.
 * Esta prueba lo convierte en un error visible.
 *
 * <p>Se usa el núcleo de ArchUnit con pruebas normales de JUnit Jupiter (y no el módulo
 * {@code archunit-junit5}) porque Spring Boot 4 trae JUnit 6 y ese motor no descubre las reglas.
 * Se usa {@code allowEmptyShould} porque en la Fase 1 aún no hay clases de negocio; las reglas
 * se aplican en cuanto aparezcan.
 */
class ArchitectureTest {

    private static JavaClasses classes;

    @BeforeAll
    static void importarClases() {
        classes = new ClassFileImporter()
                .withImportOption(new ImportOption.DoNotIncludeTests())
                .importPackages("com.rentatools");
    }

    @Test
    void domain_no_depende_de_application_ni_infrastructure() {
        ArchRule regla = noClasses().that().resideInAPackage("com.rentatools.domain..")
                .should().dependOnClassesThat()
                .resideInAnyPackage("com.rentatools.application..", "com.rentatools.infrastructure..")
                .allowEmptyShould(true);
        regla.check(classes);
    }

    @Test
    void domain_no_usa_frameworks() {
        ArchRule regla = noClasses().that().resideInAPackage("com.rentatools.domain..")
                .should().dependOnClassesThat()
                .resideInAnyPackage(
                        "org.springframework..",
                        "jakarta..",
                        "com.fasterxml..",
                        "org.hibernate..",
                        "lombok..")
                .allowEmptyShould(true);
        regla.check(classes);
    }

    @Test
    void application_no_depende_de_infrastructure() {
        ArchRule regla = noClasses().that().resideInAPackage("com.rentatools.application..")
                .should().dependOnClassesThat()
                .resideInAPackage("com.rentatools.infrastructure..")
                .allowEmptyShould(true);
        regla.check(classes);
    }

    @Test
    void application_no_usa_spring() {
        ArchRule regla = noClasses().that().resideInAPackage("com.rentatools.application..")
                .should().dependOnClassesThat()
                .resideInAnyPackage("org.springframework..", "jakarta..", "org.hibernate..", "lombok..")
                .allowEmptyShould(true);
        regla.check(classes);
    }

    /**
     * Prueba de la prueba: confirma que la regla SÍ detecta una violación.
     * Si algún día esta prueba falla, significa que ArchUnit dejó de analizar clases
     * y las demás reglas pasarían en vacío sin avisar.
     */
    @Test
    void las_reglas_realmente_analizan_clases() {
        org.junit.jupiter.api.Assertions.assertFalse(classes.isEmpty(),
                "ArchUnit no importó ninguna clase de com.rentatools");
        org.junit.jupiter.api.Assertions.assertThrows(AssertionError.class, () ->
                noClasses().that().resideInAPackage("com.rentatools.infrastructure..")
                        .should().dependOnClassesThat().resideInAPackage("org.springframework..")
                        .check(classes));
    }
}
