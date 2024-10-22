package com.example.BookingService.DTO.response.hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class HotelResponse {

    private String name;
    private String article;
    private Float distanceFormCenter;
    private String city;
    private String address;
    private Float rating;
    private Long reviewQuantity;

}
