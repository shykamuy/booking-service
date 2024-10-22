package com.example.BookingService.mapper.client;

import com.example.BookingService.DTO.request.client.ClientRequest;
import com.example.BookingService.DTO.response.client.ClientResponse;
import com.example.BookingService.model.entity.Client;

import java.util.ArrayList;

public class ClientMapperDelegate implements ClientMapper {
    @Override
    public ClientResponse userToResponse(Client user) {
        return new ClientResponse(
                user.getName(),
                user.getEmail()
        );
    }

    @Override
    public Client requestToUser(ClientRequest request) {
        return new Client(
                request.getId(),
                request.getName(),
                request.getPassword(),
                request.getEmail(),
                new ArrayList<>(),
                request.getBookings()
        );
    }
}
