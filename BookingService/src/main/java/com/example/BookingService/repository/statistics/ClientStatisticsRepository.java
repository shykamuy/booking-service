package com.example.BookingService.repository.statistics;

import com.example.BookingService.model.statistics.entity.ClientStatistics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientStatisticsRepository extends MongoRepository<ClientStatistics, String> {
}
