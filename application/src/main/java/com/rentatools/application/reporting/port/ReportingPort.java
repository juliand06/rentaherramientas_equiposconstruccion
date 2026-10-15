package com.rentatools.application.reporting.port;

import com.rentatools.application.reporting.dto.SystemMetricsReportDto;

import java.math.BigDecimal;
import java.util.Map;

public interface ReportingPort {
    SystemMetricsReportDto getSystemMetrics();
    BigDecimal calculateTotalRevenue();
    Map<String, Long> getToolsCountByCategory();
    Map<String, Long> getReservationsCountByStatus();
    long countFrequentClients();
}
