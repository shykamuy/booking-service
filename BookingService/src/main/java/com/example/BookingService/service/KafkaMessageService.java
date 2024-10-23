package com.example.BookingService.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaMessageService {

    private final List<String> events = new ArrayList<>();

    public void add(String event) {
        events.add(event);
    }



}
