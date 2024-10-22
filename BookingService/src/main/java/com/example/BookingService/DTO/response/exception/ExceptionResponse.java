package com.example.BookingService.DTO.response.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {

    private int code;
    private String description;

}
