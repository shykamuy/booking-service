package com.example.BookingService.mapper.booking;

import com.example.BookingService.DTO.request.booking.BookingRequest;
import com.example.BookingService.DTO.response.booking.BookingResponse;
import com.example.BookingService.model.entity.Booking;

public class BookingMapperDelegate implements BookingMapper{
    @Override
    public BookingResponse bookingToResponse(Booking booking) {
        return new BookingResponse(booking.getRoom().getId(), booking.getBookedDates());
    }

    @Override
    public Booking requestToBooking(BookingRequest request) {
        return new Booking(request.getId(), request.getBookingDates(), request.getClient(), request.getRoom());
    }
}
