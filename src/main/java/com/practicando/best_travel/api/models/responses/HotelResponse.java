package com.practicando.best_travel.api.models.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelResponse implements Serializable {

    Long id;
    String name;
    String address;
    Integer rating;
    BigDecimal price;
}
