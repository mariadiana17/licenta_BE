package com.example.licenta.best.service;

import com.example.licenta.best.dto.BestAlgorithmDto;

public interface BestAlgorithmService {
    BestAlgorithmDto getBestOnLongDistanceDto();

    BestAlgorithmDto getBestOnMaxCities();

    BestAlgorithmDto getBestOnShortDistanceDto();

    BestAlgorithmDto getBestOnMinCities();
}
