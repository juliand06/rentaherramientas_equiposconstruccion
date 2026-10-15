package com.rentatools.infrastructure.reporting.adapters.in.rest.controllers;

import com.rentatools.application.reporting.dto.SystemMetricsReportDto;
import com.rentatools.application.reporting.usecase.GetSystemMetricsUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
public class ReportingController {

    private final GetSystemMetricsUseCase getSystemMetrics;

    public ReportingController(GetSystemMetricsUseCase getSystemMetrics) {
        this.getSystemMetrics = getSystemMetrics;
    }

    @GetMapping("/metrics")
    public SystemMetricsReportDto getMetrics() {
        return getSystemMetrics.execute();
    }
}
