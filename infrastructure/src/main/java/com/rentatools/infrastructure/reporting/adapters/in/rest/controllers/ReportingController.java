package com.rentatools.infrastructure.reporting.adapters.in.rest.controllers;

import com.rentatools.application.reporting.dto.SystemMetricsReportDto;
import com.rentatools.application.reporting.usecase.GetSystemMetricsUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Reportes y Métricas", description = "Métricas del sistema, analítica de negocio y uso de maquinaria")
@RestController
@RequestMapping("/api/reports")
public class ReportingController {

    private final GetSystemMetricsUseCase getSystemMetrics;

    public ReportingController(GetSystemMetricsUseCase getSystemMetrics) {
        this.getSystemMetrics = getSystemMetrics;
    }

    @Operation(summary = "Obtener métricas globales del sistema", description = "Reporte de herramientas activas, total reservado e ingresos", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/metrics")
    public SystemMetricsReportDto getMetrics() {
        return getSystemMetrics.execute();
    }
}
