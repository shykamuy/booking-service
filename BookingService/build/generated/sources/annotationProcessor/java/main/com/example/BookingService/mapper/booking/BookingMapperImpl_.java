package com.example.BookingService.mapper.booking;

import com.example.BookingService.DTO.request.booking.BookingRequest;
import com.example.BookingService.DTO.response.booking.BookingResponse;
import com.example.BookingService.model.entity.Booking;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-23T21:50:52+0500",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.2.jar, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
@Qualifier("delegate")
public class BookingMapperImpl_ implements BookingMapper {

    @Override
    public BookingResponse bookingToResponse(Booking booking) {
        if ( booking == null ) {
            return null;
        }

        List<Date> bookedDates = null;

        List<Date> list = booking.getBookedDates();
        if ( list != null ) {
            bookedDates = new ArrayList<Date>( list );
        }

        Long room_id = null;

        BookingResponse bookingResponse = new BookingResponse( room_id, bookedDates );

        return bookingResponse;
    }

    @Override
    public Booking requestToBooking(BookingRequest request) {
        if ( request == null ) {
            return null;
        }

        Booking booking = new Booking();

        booking.setId( request.getId() );
        booking.setClient( request.getClient() );
        booking.setRoom( request.getRoom() );

        return booking;
    }
}
