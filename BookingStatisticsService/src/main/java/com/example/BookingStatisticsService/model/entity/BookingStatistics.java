package com.example.BookingStatisticsService.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "bookings")
public class BookingStatistics {

    @Id
    private String id;
    private String userId;
    private Date arrivalAt;
    private Date departureAt;

}
