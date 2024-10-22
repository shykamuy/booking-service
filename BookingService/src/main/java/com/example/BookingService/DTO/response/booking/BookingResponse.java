package com.example.BookingService.DTO.response.booking;

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
public class BookingResponse {

    private Long room_id;
    private List<Date> bookedDates;

}
