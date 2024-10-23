package com.example.BookingService.repository.statistics;


import com.example.BookingService.model.statistics.entity.BookingStatistics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingStatisticsRepository extends MongoRepository<BookingStatistics, String> {
}
