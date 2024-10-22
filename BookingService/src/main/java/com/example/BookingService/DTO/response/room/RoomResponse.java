package com.example.BookingService.DTO.response.room;

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
public class RoomResponse {

    private String name;
    private String description;
    private int number;
    private Float price;
    private int personQuantity;
    private List<Date> notAvailableDates;
    private Long hotel_id;

}
