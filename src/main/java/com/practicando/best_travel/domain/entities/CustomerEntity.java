package com.practicando.best_travel.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "customer")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerEntity {

    @Id
    String dni;
    @Column(length = 50)
    String fullName;
    @Column(length = 20)
    String creditCard;
    @Column(length = 12)
    String phoneNumber;
    Integer totalFlights;
    Integer totalLodgings;
    Integer totalTours;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "customer"
    )
    Set<TicketEntity> tickets;
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "customer"
    )
    Set<ReservationEntity> reservations;
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "customer"
    )
    Set<TourEntity> tours;
}
