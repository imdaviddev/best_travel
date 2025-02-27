package com.practicando.best_travel.infrastructure.services;

import com.practicando.best_travel.api.models.request.ReservationRequest;
import com.practicando.best_travel.api.models.responses.HotelResponse;
import com.practicando.best_travel.api.models.responses.ReservationResponse;
import com.practicando.best_travel.domain.entities.ReservationEntity;
import com.practicando.best_travel.domain.repositories.CustomerRepository;
import com.practicando.best_travel.domain.repositories.HotelRepository;
import com.practicando.best_travel.domain.repositories.ReservationRepository;
import com.practicando.best_travel.infrastructure.abstract_services.IReservationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class ReservationService implements IReservationService {

    private final CustomerRepository customerRepository;
    private final HotelRepository hotelRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public ReservationResponse create(ReservationRequest request) {
        var customer = customerRepository.findById(request.getIdClient()).orElseThrow();
        var hotel = hotelRepository.findById(request.getIdHotel()).orElseThrow();
        var totalDays = request.getTotalDays();

        var reservationToPersist = ReservationEntity.builder()
                .id(UUID.randomUUID())
                .hotel(hotel)
                .customer(customer)
                .totalDays(totalDays)
                .dateTimeReservation(LocalDateTime.now())
                .dateStart(LocalDate.now())
                .dateEnd(LocalDate.now().plusDays(totalDays))
                .price(hotel.getPrice().add(hotel.getPrice().multiply(charges_price_percentage)))
                .build();

        var reservationPersisted = this.reservationRepository.save(reservationToPersist);

        log.info("Reservation saved with id: {}", reservationPersisted.getId());

        return this.toResponse(reservationPersisted);
    }

    @Override
    public ReservationResponse read(UUID id) {
        var reservationFromDB = this.reservationRepository.findById(id).orElseThrow();
        return this.toResponse(reservationFromDB);
    }

    @Override
    public ReservationResponse update(ReservationRequest request, UUID id) {
        var reservationToUpdate = this.reservationRepository.findById(id).orElseThrow();
        var hotel = this.hotelRepository.findById(request.getIdHotel()).orElseThrow();
        var totalDays = request.getTotalDays();

        reservationToUpdate.setHotel(hotel);
        reservationToUpdate.setTotalDays(request.getTotalDays());
        reservationToUpdate.setPrice(hotel.getPrice().add(hotel.getPrice().multiply(charges_price_percentage)));
        reservationToUpdate.setDateTimeReservation(LocalDateTime.now());
        reservationToUpdate.setDateStart(LocalDate.now());
        reservationToUpdate.setDateEnd(LocalDate.now().plusDays(totalDays));

        var reservationUpdated = this.reservationRepository.save(reservationToUpdate);

        log.info("Reservation updated with id: {}", reservationUpdated.getId());

        return this.toResponse(reservationUpdated);
    }

    @Override
    public void delete(UUID id) {
        var reservationToDelete = this.reservationRepository.findById(id).orElseThrow();
        this.reservationRepository.delete(reservationToDelete);
    }

    @Override
    public BigDecimal findPrice(Long hotelId) {
        var hotel = hotelRepository.findById(hotelId).orElseThrow();
        return hotel.getPrice().add(hotel.getPrice().multiply(charges_price_percentage));
    }

    private ReservationResponse toResponse(ReservationEntity entity){
        var response = new ReservationResponse();
        var hotelResponse = new HotelResponse();
        BeanUtils.copyProperties(entity, response);
        BeanUtils.copyProperties(entity.getHotel(), hotelResponse);
        response.setHotel(hotelResponse);
        return response;
    }

    private static final BigDecimal charges_price_percentage = BigDecimal.valueOf(0.20);
}
