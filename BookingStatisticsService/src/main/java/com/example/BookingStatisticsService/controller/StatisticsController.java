package com.example.BookingStatisticsService.controller;

import com.example.BookingStatisticsService.model.entity.BookingStatistics;
import com.example.BookingStatisticsService.model.entity.ClientStatistics;
import com.example.BookingStatisticsService.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/statistics")
public class StatisticsController {

    @Autowired
    private EventService eventService;

    @GetMapping("/clients")
    public List<ClientStatistics> findAllClients() {
        return eventService.findAllClients();
    }

    @GetMapping("/bookings")
    public List<BookingStatistics> findAllBookings() {
        return eventService.findAllBookings();
    }

}
