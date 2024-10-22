package com.example.BookingService.controller;

import com.example.BookingService.DTO.request.client.ClientRequest;
import com.example.BookingService.DTO.response.exception.ExceptionResponse;
import com.example.BookingService.mapper.client.ClientMapper;
import com.example.BookingService.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    @Autowired
    private ClientService service;

    @Autowired
    private ClientMapper mapper;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ClientRequest request) {
        try {
            if (!service.checkByNameAndEmail(request.getName(), request.getEmail())) {
                return ResponseEntity.ok(mapper.userToResponse(service.save(mapper.requestToUser(request))));
            }
            return ResponseEntity.badRequest().body(new ExceptionResponse(
                    HttpStatus.BAD_REQUEST.value(), "User may be already created"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ExceptionResponse(
                    HttpStatus.BAD_REQUEST.value(), "incorrect data"
            ));
        }
    }

}
