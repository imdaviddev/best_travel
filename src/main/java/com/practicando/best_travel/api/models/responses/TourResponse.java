package com.practicando.best_travel.api.models.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourResponse implements Serializable {

    Long id;
    Set<UUID> ticketsIds;
    Set<UUID> reservationsIds;
}
