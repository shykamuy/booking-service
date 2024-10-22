package com.example.BookingService.mapper.client;

import com.example.BookingService.DTO.request.client.ClientRequest;
import com.example.BookingService.DTO.response.client.ClientResponse;
import com.example.BookingService.model.entity.Booking;
import com.example.BookingService.model.entity.Client;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-22T20:14:00+0500",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.2.jar, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
@Qualifier("delegate")
public class ClientMapperImpl_ implements ClientMapper {

    @Override
    public ClientResponse userToResponse(Client user) {
        if ( user == null ) {
            return null;
        }

        String name = null;
        String email = null;

        name = user.getName();
        email = user.getEmail();

        ClientResponse clientResponse = new ClientResponse( name, email );

        return clientResponse;
    }

    @Override
    public Client requestToUser(ClientRequest request) {
        if ( request == null ) {
            return null;
        }

        Client.ClientBuilder client = Client.builder();

        client.id( request.getId() );
        client.name( request.getName() );
        client.password( request.getPassword() );
        client.email( request.getEmail() );
        List<Booking> list = request.getBookings();
        if ( list != null ) {
            client.bookings( new ArrayList<Booking>( list ) );
        }

        return client.build();
    }
}
