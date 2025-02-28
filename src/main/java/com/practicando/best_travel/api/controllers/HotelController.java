package com.practicando.best_travel.api.controllers;

import com.practicando.best_travel.api.models.responses.HotelResponse;
import com.practicando.best_travel.infrastructure.services.HotelService;
import com.practicando.best_travel.util.SortType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/hotels")
@AllArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    public ResponseEntity<Page<HotelResponse>> getAll(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestHeader(required = false) SortType sortType
    ){
        if (Objects.isNull(sortType)) sortType = SortType.NONE;
        var response = this.hotelService.readAll(page, size, sortType);
        return response.isEmpty() ? ResponseEntity.noContent().build() :
                ResponseEntity.ok(response);
    }

    @GetMapping(path = "/less_price")
    public ResponseEntity<Set<HotelResponse>> getLessPrice(
            @RequestParam BigDecimal price
    ){
        var response = this.hotelService.readLessPrice(price);
        return response.isEmpty() ? ResponseEntity.noContent().build() :
                ResponseEntity.ok(response);
    }

    @GetMapping(path = "/between_price")
    public ResponseEntity<Set<HotelResponse>> getBetweenByPrice(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max
    ){
        var response = this.hotelService.readBetweenPrices(min, max);
        return response.isEmpty() ? ResponseEntity.noContent().build() :
                ResponseEntity.ok(response);
    }

    @GetMapping(path = "/rating")
    public ResponseEntity<Set<HotelResponse>> getByRating(
            @RequestParam Integer rating
    ){
        rating = rating > 4 ? 4 : rating < 1 ? 1 : rating;
        var response = this.hotelService.readByRating(rating);
        return response.isEmpty() ? ResponseEntity.noContent().build() :
                ResponseEntity.ok(response);
    }
}
