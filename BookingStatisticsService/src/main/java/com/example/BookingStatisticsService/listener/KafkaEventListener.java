package com.example.BookingStatisticsService.listener;


import com.example.BookingStatisticsService.service.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class KafkaEventListener implements Serializable {

    @Autowired
    private EventService eventService;



    @KafkaListener(
            topics = "${app.kafka.kafkaEventTopic}",
            groupId = "${app.kafka.kafkaEventGroupId}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(
            @Payload String message,
            @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
            @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp
    ) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(message, new TypeReference<Map<String, Object>>(){});
        if (map.get("eventType").equals("UserCreatedEvent")) {
            eventService.addClientStatistics(
                    String.valueOf(map.get("id")),
                    timestamp);
        } else if (message.contains("RoomBookedEvent")) {
            eventService.addBookingStatistics(
                    String.valueOf(map.get("userId")),
                    String.valueOf(map.get("arrivalDate")),
                    String.valueOf(map.get("departureDate"))
            );
        }

    }

}
