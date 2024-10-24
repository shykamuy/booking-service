package com.example.BookingService.controller;

import com.example.BookingService.DTO.request.booking.BookingRequest;
import com.example.BookingService.DTO.response.exception.ExceptionResponse;
import com.example.BookingService.mapper.booking.BookingMapper;
import com.example.BookingService.model.entity.Booking;
import com.example.BookingService.model.kafka.RoomBookedEvent;
import com.example.BookingService.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    @Autowired
    private BookingService service;

    @Autowired
    private BookingMapper mapper;

    @Value("${app.kafka.kafkaEventTopic}")
    private String topicName;
    @Autowired
    private KafkaTemplate<String, String> roomBookedEventTemplate;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findAll() {
        try {
            List<Booking> bookings = service.findAll();
            return ResponseEntity.ok(bookings.stream().map(booking -> mapper.bookingToResponse(booking)).collect(Collectors.toList()));
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ExceptionResponse(HttpStatus.NOT_FOUND.value(), "Bookings not found"),
                    HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody BookingRequest request) {
        try {
            if (!service.isDateAvailable(mapper.requestToBooking(request))) {
                ObjectMapper objectMapper = new ObjectMapper();
                String event = objectMapper.writeValueAsString(
                        new RoomBookedEvent("RoomBookedEvent",
                        request.getClient().getId(),
                        request.getBookingDates().get(0),
                        request.getBookingDates().get(request.getBookingDates().size() - 1))
                );
                roomBookedEventTemplate.send(topicName, event);

                return ResponseEntity.ok(mapper.bookingToResponse(service.createBooking(mapper.requestToBooking(request))));
            }
            return ResponseEntity.badRequest().body(new ExceptionResponse(
                    HttpStatus.BAD_REQUEST.value(), "Dates are unavailable"
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ExceptionResponse(
                    HttpStatus.BAD_REQUEST.value(), "Incorrect data"
            ));
        }
    }
}
