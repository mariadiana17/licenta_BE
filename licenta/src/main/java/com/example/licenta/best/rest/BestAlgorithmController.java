package com.example.licenta.best.rest;

import com.example.licenta.best.service.BestAlgorithmService;
import com.example.licenta.best.dto.BestAlgorithmDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BestAlgorithmController {
    @Autowired
    private BestAlgorithmService bestAlgorithmService;

    @GetMapping("/best/longest-distance")
    public BestAlgorithmDto getBestOnLongDistance() {
        return bestAlgorithmService.getBestOnLongDistanceDto();
    }

    @GetMapping("/best/max-cities")
    public BestAlgorithmDto getBestOnMaxCities() {
        return bestAlgorithmService.getBestOnMaxCities();
    }

    @GetMapping("/best/shortest-distance")
    public BestAlgorithmDto getBestOnShortDistance() {
        return bestAlgorithmService.getBestOnShortDistanceDto();
    }

    @GetMapping("/best/min-cities")
    public BestAlgorithmDto getBestOnMinCities() {
        return bestAlgorithmService.getBestOnMinCities();
    }
}
