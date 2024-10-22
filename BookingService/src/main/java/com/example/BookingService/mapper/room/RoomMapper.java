package com.example.BookingService.mapper.room;

import com.example.BookingService.DTO.request.room.RoomRequest;
import com.example.BookingService.DTO.response.room.RoomResponse;
import com.example.BookingService.model.entity.Room;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@DecoratedWith(RoomMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoomMapper {

    RoomResponse roomToResponse(Room room);

    Room requestToRoom(RoomRequest request);

}
