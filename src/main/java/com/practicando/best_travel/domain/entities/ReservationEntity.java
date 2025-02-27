package com.practicando.best_travel.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "reservation")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReservationEntity {

    @Id
    UUID id;

    @Column(name = "date_reservation")
    LocalDateTime dateTimeReservation;
    LocalDate dateStart;
    LocalDate dateEnd;
    Integer totalDays;
    BigDecimal price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    HotelEntity hotel;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id", nullable = true)
    TourEntity tour;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    CustomerEntity customer;
}
