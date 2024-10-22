package com.example.BookingService.DTO.request.room;

import com.example.BookingService.model.entity.Booking;
import com.example.BookingService.model.entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
public class RoomRequest {

    private Long id;
    private String name;
    private String description;
    private int number;
    private Float price;
    private int personQuantity;
    private List<Date> notAvailableDates;
    private Hotel hotel;
    private List<Booking> bookings;

}
