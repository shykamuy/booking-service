package com.example.BookingService.DTO.response.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class ClientResponse {

    private String name;
    private String email;

}
