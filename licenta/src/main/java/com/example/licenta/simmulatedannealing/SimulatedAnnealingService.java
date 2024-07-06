package com.example.licenta.simmulatedannealing;

import com.example.licenta.common.model.Coordinate;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SimulatedAnnealingService {
    ResponseEntity<List<Coordinate>> optimizeRoute(List<Coordinate> coordinates);
}
