package com.example.BookingStatisticsService.service;

import com.example.BookingStatisticsService.model.entity.BookingStatistics;
import com.example.BookingStatisticsService.model.entity.ClientStatistics;
import com.example.BookingStatisticsService.repository.BookingStatisticsRepository;
import com.example.BookingStatisticsService.repository.ClientStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EventService {

    @Autowired
    private ClientStatisticsRepository clientRepository;

    @Autowired
    private BookingStatisticsRepository bookingRepository;

    public void addClientStatistics(String userId, Long timeStamp) {
        clientRepository.save(new ClientStatistics(UUID.randomUUID().toString(), userId, Date.from(Instant.ofEpochMilli(timeStamp))));
    }

    public void addBookingStatistics(String userId, String arrivalDate, String departureDate) {
        bookingRepository.save(new BookingStatistics(
                UUID.randomUUID().toString(),
                userId,
                Date.from(Instant.ofEpochMilli(Long.parseLong(arrivalDate))),
                Date.from(Instant.ofEpochMilli(Long.parseLong(departureDate))
                )));
    }

    public List<ClientStatistics> findAllClients() {
        return clientRepository.findAll();
    }

    public List<BookingStatistics> findAllBookings() {
        return bookingRepository.findAll();
    }

}
