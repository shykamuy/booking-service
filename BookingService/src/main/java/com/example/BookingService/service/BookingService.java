package com.example.BookingService.service;

import com.example.BookingService.model.entity.Booking;
import com.example.BookingService.model.entity.Room;
import com.example.BookingService.repository.BookingRepository;
import com.example.BookingService.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository repository;
    @Autowired
    private RoomRepository roomRepository;

    public boolean isDateAvailable(Booking booking) {
        Date[] dateStrings;
        dateStrings = booking.getBookedDates().toArray(new Date[booking.getBookedDates().size()]);
        return repository.existsByBookedDatesInAndRoomId(dateStrings, booking.getRoom().getId());
    }

    public Booking createBooking(Booking booking) {
        Room existedRoom = roomRepository.findById(booking.getRoom().getId()).orElseThrow();
        List<Date> newNotAvailableDates = existedRoom.getNotAvailableDates();
        newNotAvailableDates.addAll(booking.getBookedDates());
        existedRoom.setNotAvailableDates(newNotAvailableDates);
        booking.setRoom(existedRoom);
        return repository.save(booking);
    }

    public List<Booking> findAll() {
        return repository.findAll();
    }

}
