package com.example.licenta.simmulatedannealing;

import com.example.licenta.common.model.Coordinate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SimulatedAnnealingServiceImpl implements SimulatedAnnealingService{

    @Override
    public ResponseEntity<List<Coordinate>> optimizeRoute(List<Coordinate> coordinates) {
        SimulatedAnnealing simulatedAnnealingService = new SimulatedAnnealing(10000, 0.003);
        System.out.println("Received coordinates: " + coordinates); // Log received data
        try {
            List<Coordinate> optimizedRoute = simulatedAnnealingService.optimizeRoute(coordinates);
            System.out.println("Optimized Coordinates: " + optimizedRoute);
            return ResponseEntity.ok(optimizedRoute);
        } catch (Exception e) {
            System.out.println("Error processing the request: " + e.getMessage()); // Log error details
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
