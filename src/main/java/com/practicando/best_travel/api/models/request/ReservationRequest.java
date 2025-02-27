package com.practicando.best_travel.api.models.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReservationRequest implements Serializable {

    String idClient;
    Long idHotel;
    Integer totalDays;
}
