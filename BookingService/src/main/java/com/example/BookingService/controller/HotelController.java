package com.example.BookingService.controller;


import com.example.BookingService.DTO.request.hotel.HotelRequest;
import com.example.BookingService.DTO.response.exception.ExceptionResponse;
import com.example.BookingService.mapper.hotel.HotelMapper;
import com.example.BookingService.model.entity.Hotel;
import com.example.BookingService.service.HotelService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/hotel")
@Validated
public class HotelController {

    @Autowired
    private HotelService service;

    @Autowired
    private HotelMapper mapper;

    @GetMapping("/filter")
    public ResponseEntity<?> filterBy(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String article,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Float distanceFromCenter,
            @RequestParam(required = false) Float rating,
            @RequestParam(required = false) Integer reviewQuantity,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        Page<Hotel> hotels = service.filterBy(id, name, article, city, address, distanceFromCenter, rating, reviewQuantity, pageable);

        if (hotels.isEmpty()) {
            return new ResponseEntity<>(
                    new ExceptionResponse(HttpStatus.NOT_FOUND.value(), "Hotels not found"),
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(mapper.hotelsToListResponse(hotels.stream().toList()));

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Hotel hotel = service.findById(id);
            return ResponseEntity.ok(mapper.hotelToResponse(hotel));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(new ExceptionResponse(
                    HttpStatus.NOT_FOUND.value(), "Hotel with id: " + id + " not found"
            ),
                    HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> save(@RequestBody HotelRequest hotel) {
        System.out.println(hotel);
        try {
            service.save(mapper.requestToHotel(hotel));
            return ResponseEntity.ok(mapper.hotelToResponse(mapper.requestToHotel(hotel)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ExceptionResponse(
                    HttpStatus.BAD_REQUEST.value(), "Incorrect data"
            ));
        }
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(@RequestBody HotelRequest hotel) {
        try {
            service.update(mapper.requestToHotel(hotel));
            return ResponseEntity.ok(mapper.hotelToResponse(mapper.requestToHotel(hotel)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ExceptionResponse(
                    HttpStatus.BAD_REQUEST.value(), "Incorrect data"
            ));
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponse(HttpStatus.NOT_FOUND.value(), "Hotel with id: " + id + " not found"),
                    HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/rating/{id}")
    public ResponseEntity<?> changeHotelRating(@PathVariable Long id, @RequestParam @Min(1) @Max(5) int newMark) {
        try {
            return ResponseEntity.ok(mapper.hotelToResponse(service.updateRating(id, newMark)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ExceptionResponse(
                    HttpStatus.BAD_REQUEST.value(), "Incorrect data"
            ));
        }
    }


}
