package com.example.BookingService.model.kafka;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class RoomBookedEvent {

    @JsonProperty
    private String eventType;
    @JsonProperty
    private Long userId;
    @JsonProperty
    private Date arrivalDate;
    @JsonProperty
    private Date departureDate;

}
