package com.example.BookingService.mapper.room;

import com.example.BookingService.DTO.request.room.RoomRequest;
import com.example.BookingService.DTO.response.room.RoomResponse;
import com.example.BookingService.model.entity.Room;

public class RoomMapperDelegate implements RoomMapper{
    @Override
    public RoomResponse roomToResponse(Room room) {
        return new RoomResponse(
                room.getName(),
                room.getDescription(),
                room.getNumber(),
                room.getPrice(),
                room.getPersonQuantity(),
                room.getNotAvailableDates(),
                room.getHotel().getId()
        );
    }

    @Override
    public Room requestToRoom(RoomRequest request) {
        return new Room(
                request.getId(),
                request.getName(),
                request.getDescription(),
                request.getNumber(),
                request.getPrice(),
                request.getPersonQuantity(),
                request.getNotAvailableDates(),
                request.getHotel(),
                request.getBookings()
        );
    }
}
