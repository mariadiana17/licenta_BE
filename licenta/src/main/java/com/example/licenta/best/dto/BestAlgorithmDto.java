package com.example.licenta.best.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class BestAlgorithmDto {
    private String smallestExecutionTimeAlgorithmName;
    private double smallestExecutionTime;
    private String smallestTotalDistanceAlgorithmName;
    private double smallestTotalDistance;
}
