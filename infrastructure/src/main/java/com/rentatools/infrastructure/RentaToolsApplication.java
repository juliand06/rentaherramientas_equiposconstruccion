package com.rentatools.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Punto de entrada de la aplicación.
 *
 * <p>Concepto (Introducción a Spring Boot): {@code @SpringBootApplication} activa la
 * auto-configuración y el <em>component scanning</em>. Por estar en
 * {@code com.rentatools.infrastructure}, el escaneo se limita a infraestructura.
 * Los casos de uso de {@code application} NO se escanean: se declaran con {@code @Bean}
 * en las clases {@code XxxBeansConfig}, para que esa capa siga libre de Spring.
 *
 * <p>Extiende {@link SpringBootServletInitializer} para habilitar tanto la ejecución
 * como JAR embebido (vía Docker) como el despliegue tradicional como WAR en Apache Tomcat.
 */
@SpringBootApplication
public class RentaToolsApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RentaToolsApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(RentaToolsApplication.class, args);
    }
}

