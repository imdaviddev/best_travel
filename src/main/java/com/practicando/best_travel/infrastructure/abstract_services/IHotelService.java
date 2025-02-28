package com.practicando.best_travel.infrastructure.abstract_services;

import com.practicando.best_travel.api.models.responses.HotelResponse;

import java.util.Set;

public interface IHotelService extends ICatalogService<HotelResponse> {

    Set<HotelResponse> readByRating(Integer rating);
}
