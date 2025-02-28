package com.practicando.best_travel.api.models.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourHotelRequest {

    Long id;
    Integer totalDays;
}
