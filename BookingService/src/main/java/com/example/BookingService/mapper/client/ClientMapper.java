package com.example.BookingService.mapper.client;


import com.example.BookingService.DTO.request.client.ClientRequest;
import com.example.BookingService.DTO.response.client.ClientResponse;
import com.example.BookingService.model.entity.Client;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@DecoratedWith(ClientMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {

    ClientResponse userToResponse(Client user);

    Client requestToUser(ClientRequest request);
}
