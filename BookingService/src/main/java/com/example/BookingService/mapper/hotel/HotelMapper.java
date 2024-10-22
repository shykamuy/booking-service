package com.example.BookingService.mapper.hotel;

import com.example.BookingService.DTO.request.hotel.HotelRequest;
import com.example.BookingService.DTO.response.hotel.HotelResponse;
import com.example.BookingService.DTO.response.hotel.HotelResponseFilter;
import com.example.BookingService.model.entity.Hotel;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@DecoratedWith(HotelMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HotelMapper {

    HotelResponse hotelToResponse(Hotel hotel);

    List<HotelResponseFilter> hotelsToListResponse(List<Hotel> hotels);

    Hotel requestToHotel(HotelRequest request);

}
