package com.example.BookingService.service;

import com.example.BookingService.model.statistics.entity.BookingStatistics;
import com.example.BookingService.model.statistics.entity.ClientStatistics;
import com.example.BookingService.repository.statistics.BookingStatisticsRepository;
import com.example.BookingService.repository.statistics.ClientStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    @Autowired
    private BookingStatisticsRepository bookingStatisticsRepository;

    @Autowired
    private ClientStatisticsRepository clientStatisticsRepository;

    public String generateClientStatisticsCsv() {
        List<ClientStatistics> clientStatistics = clientStatisticsRepository.findAll();

        String header = "ID,Client ID,Created At";

        String rows = clientStatistics.stream()
                .map(this::convertToCsvRow)
                .collect(Collectors.joining("\n"));

        return header + "\n" + rows;
    }

    private String convertToCsvRow(ClientStatistics stats) {
        return String.join(",",
                stats.getId(),
                stats.getClientId(),
                stats.getCreatedAt().toString()
        );
    }

    public String generateBookingStatisticsCsv() {
        List<BookingStatistics> bookings = bookingStatisticsRepository.findAll();

        String header = "ID,User ID,Arrival At,Departure At";

        String rows = bookings.stream()
                .map(this::convertToCsvRow)
                .collect(Collectors.joining("\n"));

        return header + "\n" + rows;
    }

    private String convertToCsvRow(BookingStatistics booking) {
        return String.join(",",
                booking.getId(),
                booking.getUserId(),
                booking.getArrivalAt().toString(),
                booking.getDepartureAt().toString()
        );
    }

}
