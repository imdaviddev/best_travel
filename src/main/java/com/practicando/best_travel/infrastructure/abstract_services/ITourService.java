package com.practicando.best_travel.infrastructure.abstract_services;

import com.practicando.best_travel.api.models.request.TourRequest;
import com.practicando.best_travel.api.models.responses.TourResponse;

import java.util.UUID;

public interface ITourService extends SimpleCrudService<TourRequest, TourResponse, Long>{

    void deleteTicket(UUID ticketId, Long tourId);
    UUID addTicket(Long flyId, Long tourId);
    void removeReservation(UUID reservationId, Long tourId);
    UUID addReservation(Long reservationId, Long tourId);
}
