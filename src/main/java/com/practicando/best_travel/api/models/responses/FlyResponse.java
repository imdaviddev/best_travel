package com.practicando.best_travel.api.models.responses;

import com.practicando.best_travel.util.AeroLine;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlyResponse implements Serializable {

    Long id;
    Double originLat;
    Double originLng;
    Double destinyLat;
    Double destinyLng;
    String originName;
    String destinyName;
    BigDecimal price;
    AeroLine aeroLine;
}
