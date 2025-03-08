package com.practicando.best_travel.infrastructure.services;

import com.practicando.best_travel.api.models.request.TourRequest;
import com.practicando.best_travel.api.models.responses.TourResponse;
import com.practicando.best_travel.domain.entities.*;
import com.practicando.best_travel.domain.repositories.*;
import com.practicando.best_travel.infrastructure.abstract_services.ITourService;
import com.practicando.best_travel.infrastructure.helpers.TourHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class TourService implements ITourService {

    private final TourRepository tourRepository;
    private final CustomerRepository customerRepository;
    private final TicketRepository ticketRepository;
    private final FlyRepository flyRepository;
    private final HotelRepository hotelRepository;
    private final TourHelper tourHelper;

    @Override
    public TourResponse create(TourRequest request) {
        var customer = customerRepository.findById(request.getCustomerId()).orElseThrow();
        var flights = new HashSet<FlyEntity>();
        request.getFlights().forEach(fly -> flights.add(this.flyRepository.findById(fly.getId()).orElseThrow()));
        var hotels = new HashMap<HotelEntity, Integer>();
        request.getHotels().forEach(hotel -> hotels.put(this.hotelRepository.findById(hotel.getId()).orElseThrow(), hotel.getTotalDays()));
        var tourToSave = TourEntity.builder()
                .tickets(this.tourHelper.createTickets(flights, customer))
                .reservations(this.tourHelper.createReservation(hotels, customer))
                .customer(customer)
                .build();

        var tourSaved = this.tourRepository.save(tourToSave);

        return entityToResponse(tourSaved);
    }

    @Override
    public TourResponse read(Long id) {
        var tourFromDB = this.tourRepository.findById(id).orElseThrow();
        return entityToResponse(tourFromDB);
    }

    @Override
    public void delete(Long id) {
        var tourToDelete = this.tourRepository.findById(id).orElseThrow();
        this.tourRepository.delete(tourToDelete);
    }

    @Override
    public void deleteTicket(Long tourId, UUID ticketId) {
        var tourUpdate = this.tourRepository.findById(tourId).orElseThrow();
        tourUpdate.removeTicket(ticketId);
        this.tourRepository.save(tourUpdate);
    }

    @Override
    public UUID addTicket(Long tourId, Long flyId) {
        var tourUpdate = this.tourRepository.findById(tourId).orElseThrow();
        var fly = this.flyRepository.findById(flyId).orElseThrow();
        var ticket = this.tourHelper.createTicket(fly, tourUpdate.getCustomer());
        tourUpdate.addTicket(ticket);
        this.tourRepository.save(tourUpdate);

        return ticket.getId();
    }

    @Override
    public void removeReservation(Long tourId, UUID reservationId){

    }

    @Override
    public UUID addReservation(Long tourId, Long reservationId) {
        return null;
    }

    private TourResponse entityToResponse(TourEntity entity){
        return TourResponse.builder()
                .reservationsIds(
                        entity.getReservations().stream()
                                .map(ReservationEntity::getId)
                                .collect(Collectors.toSet())
                ).ticketsIds(
                        entity.getTickets().stream()
                                .map(TicketEntity::getId)
                                .collect(Collectors.toSet())
                ).id(entity.getId()).build();
    }
}
