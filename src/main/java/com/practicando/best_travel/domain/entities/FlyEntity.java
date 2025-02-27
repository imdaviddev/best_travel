package com.practicando.best_travel.domain.entities;

import com.practicando.best_travel.util.AeroLine;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "fly")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "origin_lat")
    Double originLat;
    @Column(name = "origin_lng")
    Double originLng;
    @Column(name = "destiny_lat")
    Double destinyLat;
    @Column(name = "destiny_lng")
    Double destinyLng;
    @Column(length = 20)
    String originName;
    @Column(length = 20)
    String destinyName;
    BigDecimal price;
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    AeroLine aeroLine;

    @OneToMany(
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY,
        orphanRemoval = true,
        mappedBy = "fly"
    )
    Set<TicketEntity> tickets;
}
