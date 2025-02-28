package com.practicando.best_travel.infrastructure.services;

import com.practicando.best_travel.api.models.request.TicketRequest;
import com.practicando.best_travel.api.models.responses.FlyResponse;
import com.practicando.best_travel.api.models.responses.TicketResponse;
import com.practicando.best_travel.domain.entities.FlyEntity;
import com.practicando.best_travel.domain.entities.TicketEntity;
import com.practicando.best_travel.domain.repositories.CustomerRepository;
import com.practicando.best_travel.domain.repositories.FlyRepository;
import com.practicando.best_travel.domain.repositories.TicketRepository;
import com.practicando.best_travel.infrastructure.abstract_services.ITicketService;
import com.practicando.best_travel.util.BestTravelUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class TicketService implements ITicketService {

    private final TicketRepository ticketRepository;
    private final FlyRepository flyRepository;
    private final CustomerRepository customerRepository;

    @Override
    public TicketResponse create(TicketRequest request) {
        var fly = flyRepository.findById(request.getIdFly()).orElseThrow();
        var customer = customerRepository.findById(request.getIdClient()).orElseThrow();
        var ticketToPersist = TicketEntity.builder()
                .id(UUID.randomUUID())
                .fly(fly)
                .customer(customer)
                .price(priceFly(fly))
                .purchaseDate(LocalDate.now())
                .arrivalDate(BestTravelUtil.getRandomSoon())
                .departureDate(BestTravelUtil.getRandomLatter())
                .build();

        var ticketPersisted = this.ticketRepository.save(ticketToPersist);

        log.info("Ticket saved with id: {}", ticketPersisted.getId());

        return this.toResponse(ticketPersisted);
    }

    @Override
    public TicketResponse read(UUID id) {
        var ticketFromDB = this.ticketRepository.findById(id).orElseThrow();
        return this.toResponse(ticketFromDB);
    }

    @Override
    public TicketResponse update(TicketRequest request, UUID id) {
        var ticketToUpdate = ticketRepository.findById(id).orElseThrow();
        var fly = flyRepository.findById(request.getIdFly()).orElseThrow();

        ticketToUpdate.setFly(fly);
        ticketToUpdate.setPrice(priceFly(fly));
        ticketToUpdate.setDepartureDate(BestTravelUtil.getRandomSoon());
        ticketToUpdate.setArrivalDate(BestTravelUtil.getRandomLatter());

        var ticketUpdated = this.ticketRepository.save(ticketToUpdate);

        log.info("Ticket updated with id {}", ticketUpdated.getId());
        return this.toResponse(ticketUpdated);
    }

    @Override
    public void delete(UUID id) {
        var ticketToDelete = ticketRepository.findById(id).orElseThrow();
        this.ticketRepository.delete(ticketToDelete);
    }

    @Override
    public BigDecimal flyPrice(Long idFly) {
        var fly = this.flyRepository.findById(idFly).orElseThrow();
        return priceFly(fly);
    }

    private TicketResponse toResponse(TicketEntity entity){
        var response = new TicketResponse();
        var flyResponse = new FlyResponse();
        BeanUtils.copyProperties(entity, response);
        BeanUtils.copyProperties(entity.getFly(), flyResponse);
        response.setFly(flyResponse);
        return response;
    }

    public static final BigDecimal charger_price_percentage = BigDecimal.valueOf(0.25);
    public BigDecimal priceFly(FlyEntity fly){
        return fly.getPrice().add(fly.getPrice().multiply(charger_price_percentage));
    }
}
