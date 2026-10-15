package com.rentatools.application.reporting.usecase;

import com.rentatools.application.reporting.dto.SystemMetricsReportDto;
import com.rentatools.application.reporting.port.ReportingPort;

public class GetSystemMetricsUseCase {

    private final ReportingPort reportingPort;

    public GetSystemMetricsUseCase(ReportingPort reportingPort) {
        this.reportingPort = reportingPort;
    }

    public SystemMetricsReportDto execute() {
        return reportingPort.getSystemMetrics();
    }
}
