package com.rentatools.infrastructure.reporting.config;

import com.rentatools.application.reporting.port.ReportingPort;
import com.rentatools.application.reporting.usecase.GetSystemMetricsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReportingBeansConfig {

    @Bean
    public GetSystemMetricsUseCase getSystemMetricsUseCase(ReportingPort reportingPort) {
        return new GetSystemMetricsUseCase(reportingPort);
    }
}
