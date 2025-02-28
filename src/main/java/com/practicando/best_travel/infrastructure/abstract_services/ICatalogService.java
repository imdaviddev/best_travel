package com.practicando.best_travel.infrastructure.abstract_services;

import com.practicando.best_travel.util.SortType;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.Set;

public interface ICatalogService<R> {

    Page<R> readAll(Integer page, Integer size, SortType sortType);
    Set<R> readLessPrice(BigDecimal price);
    Set<R> readBetweenPrices(BigDecimal min, BigDecimal max);

    String FIELD_BY_SORT = "price";
}
