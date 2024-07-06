package com.example.licenta.simmulatedannealing;

import com.example.licenta.common.model.Coordinate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SimulatedAnnealingController {

   @Autowired
   SimulatedAnnealingService simulatedAnnealingService;

    @PostMapping("/optimize-route-simulated-annealing")
    public ResponseEntity<List<Coordinate>> optimizeRoute(@RequestBody List<Coordinate> coordinates) {
       return simulatedAnnealingService.optimizeRoute(coordinates);
    }
}
