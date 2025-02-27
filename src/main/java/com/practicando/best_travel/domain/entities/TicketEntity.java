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
@Entity(name = "ticket")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketEntity {

    @Id
    UUID id;
    LocalDateTime departureDate;
    LocalDateTime arrivalDate;
    LocalDate purchaseDate;
    BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "fly_id")
    FlyEntity fly;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id", nullable = true)
    TourEntity tour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    CustomerEntity customer;
}
