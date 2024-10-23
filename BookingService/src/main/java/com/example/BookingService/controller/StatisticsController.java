package com.example.BookingService.controller;

import com.example.BookingService.DTO.response.exception.ExceptionResponse;
import com.example.BookingService.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/v1/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/client/create")
    public ResponseEntity<?> createClientCsv() {
        String csvData = statisticsService.generateClientStatisticsCsv();

        String filePath = "client_statistics.csv";
        File file = new File(filePath);

        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(csvData.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ExceptionResponse(
                    HttpStatus.BAD_REQUEST.value(), "Error in creating CSV file"));
        }

        return ResponseEntity.ok("CSV successfully created in: " + filePath);
    }

    @GetMapping("/booking/create")
    public ResponseEntity<?> createBookingCsv() {
        String csvData = statisticsService.generateBookingStatisticsCsv();

        String filePath = "booking_statistics.csv";
        File file = new File(filePath);

        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(csvData.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ExceptionResponse(
                    HttpStatus.BAD_REQUEST.value(), "Error in creating CSV file"));
        }

        return ResponseEntity.ok("CSV successfully created in: " + filePath);
    }

    @GetMapping("/client/download")
    public ResponseEntity<?> downloadClientCsv() {
        String filePath = "client_statistics.csv";
        File file = new File(filePath);

        if (!file.exists()) {
            return new ResponseEntity<>(
                    new ExceptionResponse(HttpStatus.NOT_FOUND.value(), "CSV file not found"),
                    HttpStatus.NOT_FOUND
            );
        }

        FileSystemResource resource = new FileSystemResource(file);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=client_statistics.csv");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .body(resource);
    }
    @GetMapping("/booking/download")
    public ResponseEntity<?> downloadBookingCsv() {
        String filePath = "booking_statistics.csv";
        File file = new File(filePath);

        if (!file.exists()) {
            return new ResponseEntity<>(
                    new ExceptionResponse(HttpStatus.NOT_FOUND.value(), "CSV file not found"),
                    HttpStatus.NOT_FOUND
            );
        }

        FileSystemResource resource = new FileSystemResource(file);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=booking_statistics.csv");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .body(resource);
    }
}

