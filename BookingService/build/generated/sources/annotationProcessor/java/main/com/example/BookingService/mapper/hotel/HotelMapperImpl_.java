package com.example.BookingService.mapper.hotel;

import com.example.BookingService.DTO.request.hotel.HotelRequest;
import com.example.BookingService.DTO.response.hotel.HotelResponse;
import com.example.BookingService.DTO.response.hotel.HotelResponseFilter;
import com.example.BookingService.model.entity.Hotel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-23T21:50:52+0500",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.2.jar, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
@Qualifier("delegate")
public class HotelMapperImpl_ implements HotelMapper {

    @Override
    public HotelResponse hotelToResponse(Hotel hotel) {
        if ( hotel == null ) {
            return null;
        }

        String name = null;
        String article = null;
        String city = null;
        String address = null;
        Float rating = null;
        Long reviewQuantity = null;

        name = hotel.getName();
        article = hotel.getArticle();
        city = hotel.getCity();
        address = hotel.getAddress();
        rating = hotel.getRating();
        reviewQuantity = hotel.getReviewQuantity();

        Float distanceFormCenter = null;

        HotelResponse hotelResponse = new HotelResponse( name, article, distanceFormCenter, city, address, rating, reviewQuantity );

        return hotelResponse;
    }

    @Override
    public List<HotelResponseFilter> hotelsToListResponse(List<Hotel> hotels) {
        if ( hotels == null ) {
            return null;
        }

        List<HotelResponseFilter> list = new ArrayList<HotelResponseFilter>( hotels.size() );
        for ( Hotel hotel : hotels ) {
            list.add( hotelToHotelResponseFilter( hotel ) );
        }

        return list;
    }

    @Override
    public Hotel requestToHotel(HotelRequest request) {
        if ( request == null ) {
            return null;
        }

        Hotel hotel = new Hotel();

        hotel.setId( request.getId() );
        hotel.setName( request.getName() );
        hotel.setArticle( request.getArticle() );
        hotel.setCity( request.getCity() );
        hotel.setAddress( request.getAddress() );
        hotel.setDistanceFromCenter( request.getDistanceFromCenter() );

        return hotel;
    }

    protected HotelResponseFilter hotelToHotelResponseFilter(Hotel hotel) {
        if ( hotel == null ) {
            return null;
        }

        String name = null;
        String city = null;
        String address = null;
        Float rating = null;

        name = hotel.getName();
        city = hotel.getCity();
        address = hotel.getAddress();
        rating = hotel.getRating();

        Float distanceFormCenter = null;

        HotelResponseFilter hotelResponseFilter = new HotelResponseFilter( name, distanceFormCenter, city, address, rating );

        return hotelResponseFilter;
    }
}
