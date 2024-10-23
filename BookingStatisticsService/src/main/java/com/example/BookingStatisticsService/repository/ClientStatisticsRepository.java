package com.example.BookingStatisticsService.repository;

import com.example.BookingStatisticsService.model.entity.ClientStatistics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientStatisticsRepository extends MongoRepository<ClientStatistics, String> {
}
