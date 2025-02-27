package com.practicando.best_travel.infrastructure.abstract_services;

import com.practicando.best_travel.api.models.request.ReservationRequest;
import com.practicando.best_travel.api.models.responses.ReservationResponse;

import java.math.BigDecimal;
import java.util.UUID;

public interface IReservationService extends CrudService<ReservationRequest, ReservationResponse, UUID> {

    BigDecimal findPrice(Long hotelId);
}
