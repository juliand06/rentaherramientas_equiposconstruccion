package com.rentatools.infrastructure.reporting.adapters.out;

import com.rentatools.application.reporting.dto.SystemMetricsReportDto;
import com.rentatools.application.reporting.port.ReportingPort;
import com.rentatools.infrastructure.catalog.adapters.out.persistence.repositories.ToolJpaRepository;
import com.rentatools.infrastructure.identity.adapters.out.persistence.repositories.UserJpaRepository;
import com.rentatools.infrastructure.rental.adapters.out.persistence.entity.ReservationJpaEntity;
import com.rentatools.infrastructure.rental.adapters.out.persistence.repositories.ReservationJpaRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ReportingAdapter implements ReportingPort {

    private final UserJpaRepository userRepository;
    private final ToolJpaRepository toolRepository;
    private final ReservationJpaRepository reservationRepository;

    public ReportingAdapter(UserJpaRepository userRepository,
                            ToolJpaRepository toolRepository,
                            ReservationJpaRepository reservationRepository) {
        this.userRepository = userRepository;
        this.toolRepository = toolRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public SystemMetricsReportDto getSystemMetrics() {
        long totalUsers = userRepository.count();
        long totalTools = toolRepository.count();
        long totalReservations = reservationRepository.count();
        BigDecimal totalRevenue = calculateTotalRevenue();
        Map<String, Long> toolsByCategory = getToolsCountByCategory();
        Map<String, Long> reservationsByStatus = getReservationsCountByStatus();

        return new SystemMetricsReportDto(
                totalUsers,
                totalTools,
                totalReservations,
                totalRevenue,
                toolsByCategory,
                reservationsByStatus
        );
    }

    @Override
    public BigDecimal calculateTotalRevenue() {
        return reservationRepository.findAll().stream()
                .map(ReservationJpaEntity::getTotalRentalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public Map<String, Long> getToolsCountByCategory() {
        Map<String, Long> countMap = new HashMap<>();
        toolRepository.findAll().forEach(t -> {
            String catId = t.getCategoryId().toString();
            countMap.put(catId, countMap.getOrDefault(catId, 0L) + 1);
        });
        return countMap;
    }

    @Override
    public Map<String, Long> getReservationsCountByStatus() {
        return reservationRepository.findAll().stream()
                .collect(Collectors.groupingBy(ReservationJpaEntity::getStatus, Collectors.counting()));
    }

    @Override
    public long countFrequentClients() {
        return reservationRepository.findAll().stream()
                .collect(Collectors.groupingBy(ReservationJpaEntity::getClientId, Collectors.counting()))
                .values().stream().filter(c -> c > 1).count();
    }
}
