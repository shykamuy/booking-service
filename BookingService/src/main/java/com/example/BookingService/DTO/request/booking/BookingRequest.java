package com.example.BookingService.DTO.request.booking;

import com.example.BookingService.model.entity.Client;
import com.example.BookingService.model.entity.Room;
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
public class BookingRequest {

    private Long id;
    private Room room;
    private List<Date> bookingDates;
    private Client client;

}
