package com.example.BookingStatisticsService.repository;

import com.example.BookingStatisticsService.model.entity.BookingStatistics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingStatisticsRepository extends MongoRepository<BookingStatistics, String> {
}
