package com.example.BookingService.mapper.hotel;

import com.example.BookingService.DTO.request.hotel.HotelRequest;
import com.example.BookingService.DTO.response.hotel.HotelResponse;
import com.example.BookingService.DTO.response.hotel.HotelResponseFilter;
import com.example.BookingService.model.entity.Hotel;

import java.util.ArrayList;
import java.util.List;

public class HotelMapperDelegate implements HotelMapper{
    @Override
    public HotelResponse hotelToResponse(Hotel hotel) {

        HotelResponse response = new HotelResponse(
                hotel.getName(),
                hotel.getArticle(),
                hotel.getDistanceFromCenter(),
                hotel.getCity(),
                hotel.getAddress(),
                hotel.getRating(),
                hotel.getReviewQuantity()
        );

        return response;
    }

    @Override
    public List<HotelResponseFilter> hotelsToListResponse(List<Hotel> hotels) {

        List<HotelResponseFilter> response = new ArrayList<>();

        hotels.forEach(hotel -> {
            response.add(new HotelResponseFilter(
                    hotel.getName(),
                    hotel.getDistanceFromCenter(),
                    hotel.getCity(),
                    hotel.getAddress(),
                    hotel.getRating()
            ));
        });
        return response;
    }


    @Override
    public Hotel requestToHotel(HotelRequest request) {

        Hotel hotel = new Hotel();
        hotel.setId(request.getId());
        hotel.setName(request.getName());
        hotel.setArticle(request.getArticle());
        hotel.setAddress(request.getAddress());
        hotel.setCity(request.getCity());
        hotel.setDistanceFromCenter(request.getDistanceFromCenter());

        return hotel;
    }
}
