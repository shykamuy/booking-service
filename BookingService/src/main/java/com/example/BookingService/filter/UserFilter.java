package com.example.BookingService.filter;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserFilter {

    private Integer pageSize = 10;

    private Integer pageNumber = 0;

}
