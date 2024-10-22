package com.example.BookingService.mapper.booking;

import com.example.BookingService.DTO.request.booking.BookingRequest;
import com.example.BookingService.DTO.response.booking.BookingResponse;
import com.example.BookingService.model.entity.Booking;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@DecoratedWith(BookingMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingMapper {

    BookingResponse bookingToResponse(Booking booking);

    Booking requestToBooking(BookingRequest request);

}
