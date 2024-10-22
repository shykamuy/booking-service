package com.example.BookingService.controller;

import com.example.BookingService.DTO.request.client.ClientRequest;
import com.example.BookingService.mapper.client.ClientMapper;
import com.example.BookingService.model.RoleType;
import com.example.BookingService.model.entity.Role;
import com.example.BookingService.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public")
public class PublicController {

    @Autowired
    private ClientService service;

    @Autowired
    private ClientMapper mapper;


    @PostMapping("/account")
    public ResponseEntity<?> createUserAccount(@RequestBody ClientRequest request, @RequestParam RoleType roleType) {
        System.out.println(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.userToResponse(service.createNewAccount(mapper.requestToUser(request), Role.from(roleType))));
    }

}
