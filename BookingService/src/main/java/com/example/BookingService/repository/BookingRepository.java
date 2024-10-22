package com.example.BookingService.repository;

import com.example.BookingService.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {


    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END " +
            "FROM booking b " +
            "WHERE b.room_id = :roomId " +
            "AND EXISTS (SELECT 1 FROM unnest(b.booked_dates) AS date WHERE date = ANY(:bookedDates))",
            nativeQuery = true)
    boolean existsByBookedDatesInAndRoomId(@Param("bookedDates") Date[] bookedDates, @Param("roomId") Long roomId);
}
