package com.rentatools.application.reporting.dto;

import java.math.BigDecimal;
import java.util.Map;

public record SystemMetricsReportDto(
        long totalUsers,
        long totalTools,
        long totalReservations,
        BigDecimal totalRevenue,
        Map<String, Long> toolsByCategory,
        Map<String, Long> reservationsByStatus
) {
}
