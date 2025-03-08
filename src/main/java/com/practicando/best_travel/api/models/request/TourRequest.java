package com.practicando.best_travel.api.models.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourRequest implements Serializable {

    String customerId;
    Set<TourFlyRequest> flights;
    Set<TourHotelRequest> hotels;
}
