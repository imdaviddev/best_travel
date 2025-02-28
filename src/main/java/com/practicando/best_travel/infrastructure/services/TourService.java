package com.practicando.best_travel.infrastructure.services;

import com.practicando.best_travel.api.models.request.TourRequest;
import com.practicando.best_travel.api.models.responses.TourResponse;
import com.practicando.best_travel.domain.entities.TourEntity;
import com.practicando.best_travel.domain.repositories.CustomerRepository;
import com.practicando.best_travel.domain.repositories.FlyRepository;
import com.practicando.best_travel.domain.repositories.TicketRepository;
import com.practicando.best_travel.domain.repositories.TourRepository;
import com.practicando.best_travel.infrastructure.abstract_services.ITourService;
import com.practicando.best_travel.infrastructure.helpers.TourHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class TourService implements ITourService {

    private final TourRepository tourRepository;
    private final CustomerRepository customerRepository;
    private final TicketRepository ticketRepository;
    private final FlyRepository flyRepository;
    private final TourHelper tourHelper;

    @Override
    public TourResponse create(TourRequest request) {
        return null;
    }

    @Override
    public TourResponse read(Long aLong) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void deleteTicket(UUID ticketId, Long tourId) {

    }

    @Override
    public UUID addTicket(Long flyId, Long tourId) {
        return null;
    }

    @Override
    public void removeReservation(UUID reservationId, Long tourId) {

    }

    @Override
    public UUID addReservation(Long reservationId, Long tourId) {
        return null;
    }

    private TourResponse entityToResponse(TourEntity entity){
        TourResponse response = new TourResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
}
