package com.example.BookingService.model.kafka;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCreatedEvent {
    @JsonProperty
    private String eventType;
    @JsonProperty
    private String id;
}
