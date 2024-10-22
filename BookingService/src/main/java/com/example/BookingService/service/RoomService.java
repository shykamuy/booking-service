package com.example.BookingService.service;

import com.example.BookingService.filter.specification.RoomSpecification;
import com.example.BookingService.model.entity.Room;
import com.example.BookingService.repository.RoomRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RoomService {

    @Autowired
    private RoomRepository repository;

    public Page<Room> filterBy(Long id, String name, Float minPrice, Float maxPrice,
                               Integer personQuantity, Date arrivalDate, Date departureDate, Long hotelId, Pageable pageable) {
        Specification<Room> specification = RoomSpecification.filterByCriteria(id, name, minPrice, maxPrice,
                personQuantity, arrivalDate, departureDate, hotelId);
        return repository.findAll(specification, pageable);
    }

    public Room findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Room save(Room room) {
        return repository.save(room);
    }

    public Room update(Room room) {
        Room existedRoom = findById(room.getId());
        BeanUtils.copyProperties(room, existedRoom);
        return repository.save(room);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
