package com.practicando.best_travel.api.controllers;

import com.practicando.best_travel.api.models.request.TourRequest;
import com.practicando.best_travel.api.models.responses.TourResponse;
import com.practicando.best_travel.infrastructure.abstract_services.ITourService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/tours")
@AllArgsConstructor
public class TourController {

    private final ITourService tourService;

    @PostMapping
    public ResponseEntity<TourResponse> post(@RequestBody TourRequest request){
        return  ResponseEntity.ok(this.tourService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TourResponse> post(@PathVariable Long id){
        return ResponseEntity.ok(this.tourService.read(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.tourService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{tourId/remove_ticket/{ticketId}}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long tourId, @PathVariable UUID ticketId){
        this.tourService.deleteTicket(tourId, ticketId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{tourId/add_ticket/{flyId}}")
    public ResponseEntity<Map<String,UUID>> addTicket(@PathVariable Long tourId, @PathVariable Long flyId){
        this.tourService.addTicket(tourId, flyId);
        var response = Collections.singletonMap("ticketId", this.tourService.addTicket(tourId, flyId));
        return ResponseEntity.ok(response);
    }
}
