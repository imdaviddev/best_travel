package com.practicando.best_travel.infrastructure.abstract_services;

import com.practicando.best_travel.api.models.request.TicketRequest;
import com.practicando.best_travel.api.models.responses.TicketResponse;

import java.math.BigDecimal;
import java.util.UUID;

public interface ITicketService extends CrudService<TicketRequest, TicketResponse, UUID>{

    BigDecimal flyPrice(Long idFly);
}
