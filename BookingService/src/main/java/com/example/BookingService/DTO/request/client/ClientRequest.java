package com.example.BookingService.DTO.request.client;

import com.example.BookingService.model.entity.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
public class ClientRequest {

    private Long id;
    private String name;
    private String password;
    private String email;
    private List<Booking> bookings;

}
