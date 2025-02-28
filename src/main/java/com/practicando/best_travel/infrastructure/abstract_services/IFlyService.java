package com.practicando.best_travel.infrastructure.abstract_services;

import com.practicando.best_travel.api.models.responses.FlyResponse;
import java.util.Set;

public interface IFlyService extends ICatalogService<FlyResponse> {

    Set<FlyResponse> readByOriginDestiny(String origin, String destiny);

}
