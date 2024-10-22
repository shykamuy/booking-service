package com.example.BookingService.DTO.request.hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class HotelRequest {

    private Long id;
    private String name;
    private String article;
    private String city;
    private String address;
    private Float distanceFromCenter;

}
