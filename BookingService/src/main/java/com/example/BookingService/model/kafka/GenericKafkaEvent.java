package com.example.BookingService.model.kafka;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
public record GenericKafkaEvent<T>(@JsonProperty T eventData,@JsonProperty Date timestamp) {}
