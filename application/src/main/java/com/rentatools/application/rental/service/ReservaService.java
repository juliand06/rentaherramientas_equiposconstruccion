package com.rentatools.application.rental.service;

import com.rentatools.application.common.port.TimeProvider;
import com.rentatools.domain.rental.port.repository.ReporteDanoRepository;
import com.rentatools.domain.rental.port.repository.ReservationRepository;

/**
 * Alias de servicio ReservaService según enunciado: "Lógica en DevolucionService (o ReservaService)".
 */
public class ReservaService extends DevolucionService {

    public ReservaService(ReservationRepository reservationRepository,
                          ReporteDanoRepository reporteDanoRepository,
                          TimeProvider timeProvider) {
        super(reservationRepository, reporteDanoRepository, timeProvider);
    }
}
