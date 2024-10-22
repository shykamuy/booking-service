package com.example.BookingService.controller;

import com.example.BookingService.DTO.request.room.RoomRequest;
import com.example.BookingService.DTO.response.exception.ExceptionResponse;
import com.example.BookingService.mapper.room.RoomMapper;
import com.example.BookingService.model.entity.Room;
import com.example.BookingService.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    @Autowired
    private RoomService service;

    @Autowired
    private RoomMapper mapper;

    @GetMapping("/filter")
    public ResponseEntity<?> filterBy(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Float minPrice,
            @RequestParam(required = false) Float maxPrice,
            @RequestParam(required = false) Integer personQuantity,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date arrivalDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date departureDate,
            @RequestParam(required = false) Long hotelId,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        Page<Room> rooms = service.filterBy(id, name, minPrice, maxPrice, personQuantity, arrivalDate, departureDate, hotelId, pageable);
        if (rooms.isEmpty()) {
            return new ResponseEntity<>(
                    new ExceptionResponse(HttpStatus.NOT_FOUND.value(), "Rooms not found"),
                    HttpStatus.NOT_FOUND
            );
        }
        return ResponseEntity.ok(rooms.stream().map(room -> mapper.roomToResponse(room)).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Room room = service.findById(id);
            return ResponseEntity.ok(mapper.roomToResponse(room));
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponse(
                    HttpStatus.NOT_FOUND.value(), "Room with id: " + id + " not found"
            ),
                    HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> save(@RequestBody RoomRequest request) {
        try {
            service.save(mapper.requestToRoom(request));
            return ResponseEntity.ok(mapper.roomToResponse(mapper.requestToRoom(request)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ExceptionResponse(
                    HttpStatus.BAD_REQUEST.value(), "Incorrect data"
            ));
        }
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(@RequestBody RoomRequest request) {
        try {
            service.update(mapper.requestToRoom(request));
            return ResponseEntity.ok(mapper.roomToResponse(mapper.requestToRoom(request)));
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
            return new ResponseEntity<>(new ExceptionResponse(HttpStatus.NOT_FOUND.value(), "Room with id: " + id + " not found"),
                    HttpStatus.NOT_FOUND);
        }
    }

}
