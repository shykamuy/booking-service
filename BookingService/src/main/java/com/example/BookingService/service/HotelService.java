package com.example.BookingService.service;

import com.example.BookingService.filter.specification.HotelSpecification;
import com.example.BookingService.model.entity.Hotel;
import com.example.BookingService.repository.HotelRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HotelService {

    @Autowired
    private HotelRepository repository;

    public List<Hotel> findAll() {
        return repository.findAll();
    }

    public Page<Hotel> filterBy(Long id, String name, String article, String city, String address,
                                Float distanceFromCenter, Float rating, Integer reviewQuantity, Pageable pageable) {
        Specification<Hotel> specification = HotelSpecification.filterByCriteria(id, name, article, city,
                address, distanceFromCenter, rating, reviewQuantity);
        return repository.findAll(specification, pageable);
    }

    public Hotel findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Hotel save(Hotel hotel) {
        return repository.save(hotel);
    }

    public Hotel update(Hotel hotel) {
        Hotel existedHotel = findById(hotel.getId());
        BeanUtils.copyProperties(hotel, existedHotel);
        return repository.save(hotel);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Hotel updateRating(Long id, int newMark) {
        Hotel existedHotel = repository.findById(id).orElseThrow();
        float totalRating = existedHotel.getRating() * existedHotel.getReviewQuantity();
        totalRating = totalRating - existedHotel.getRating() + newMark;
        existedHotel.setRating(totalRating / existedHotel.getReviewQuantity());
        existedHotel.setReviewQuantity(existedHotel.getReviewQuantity() + 1);
        return repository.save(existedHotel);
    }


}
