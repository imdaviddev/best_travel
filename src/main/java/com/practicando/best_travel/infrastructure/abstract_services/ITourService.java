package com.practicando.best_travel.infrastructure.abstract_services;

import com.practicando.best_travel.api.models.request.TourRequest;
import com.practicando.best_travel.api.models.responses.TourResponse;

import java.util.UUID;

public interface ITourService extends SimpleCrudService<TourRequest, TourResponse, Long>{

    void deleteTicket(Long tourId, UUID ticketId);
    UUID addTicket(Long tourId, Long flyId);
    void removeReservation(Long tourId, UUID reservationId);
    UUID addReservation(Long tourId, Long reservationId);
}
