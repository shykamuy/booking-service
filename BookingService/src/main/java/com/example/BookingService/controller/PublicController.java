package com.example.BookingService.controller;

import com.example.BookingService.DTO.request.client.ClientRequest;
import com.example.BookingService.mapper.client.ClientMapper;
import com.example.BookingService.model.RoleType;
import com.example.BookingService.model.entity.Client;
import com.example.BookingService.model.entity.Role;
import com.example.BookingService.model.kafka.GenericKafkaEvent;
import com.example.BookingService.model.kafka.UserCreatedEvent;
import com.example.BookingService.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/public")
public class PublicController {

    @Autowired
    private ClientService service;

    @Autowired
    private ClientMapper mapper;

    @Value("${app.kafka.kafkaEventTopic}")
    private String topicName;
    @Autowired
    private KafkaTemplate<String, String> accountCreationEventTemplate;

    @SneakyThrows
    @PostMapping("/account")
    public ResponseEntity<?> createUserAccount(@RequestBody ClientRequest request, @RequestParam RoleType roleType) {



        Client client = service.createNewAccount(mapper.requestToUser(request), Role.from(roleType));

        ObjectMapper objectMapper = new ObjectMapper();
        String event = objectMapper.writeValueAsString(
                        new UserCreatedEvent("UserCreatedEvent", String.valueOf(client.getId()))
        );
        accountCreationEventTemplate.send(topicName, event);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.userToResponse(client));
    }

}
