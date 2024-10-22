package com.example.BookingService.mapper.room;

import com.example.BookingService.DTO.request.room.RoomRequest;
import com.example.BookingService.DTO.response.room.RoomResponse;
import com.example.BookingService.model.entity.Booking;
import com.example.BookingService.model.entity.Room;
import java.util.ArrayList;
import java.util.Date;
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
public class RoomMapperImpl_ implements RoomMapper {

    @Override
    public RoomResponse roomToResponse(Room room) {
        if ( room == null ) {
            return null;
        }

        String name = null;
        String description = null;
        int number = 0;
        Float price = null;
        int personQuantity = 0;
        List<Date> notAvailableDates = null;

        name = room.getName();
        description = room.getDescription();
        number = room.getNumber();
        price = room.getPrice();
        personQuantity = room.getPersonQuantity();
        List<Date> list = room.getNotAvailableDates();
        if ( list != null ) {
            notAvailableDates = new ArrayList<Date>( list );
        }

        Long hotel_id = null;

        RoomResponse roomResponse = new RoomResponse( name, description, number, price, personQuantity, notAvailableDates, hotel_id );

        return roomResponse;
    }

    @Override
    public Room requestToRoom(RoomRequest request) {
        if ( request == null ) {
            return null;
        }

        Room room = new Room();

        room.setId( request.getId() );
        room.setName( request.getName() );
        room.setDescription( request.getDescription() );
        room.setNumber( request.getNumber() );
        room.setPrice( request.getPrice() );
        room.setPersonQuantity( request.getPersonQuantity() );
        List<Date> list = request.getNotAvailableDates();
        if ( list != null ) {
            room.setNotAvailableDates( new ArrayList<Date>( list ) );
        }
        room.setHotel( request.getHotel() );
        List<Booking> list1 = request.getBookings();
        if ( list1 != null ) {
            room.setBookings( new ArrayList<Booking>( list1 ) );
        }

        return room;
    }
}
