package com.example.licenta.best.dto;

import com.example.licenta.common.model.Coordinate;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;


import java.util.List;



@Getter
@Builder
@Data
public class OptimizationResultDto {

    private double inputCoordinatesJson;

    private List<Coordinate> antColonyResultJson;

    private List<Coordinate> geneticResultJson;

    private List<Coordinate> simulatedAnnealingResultJson;

    private List<Coordinate> greedyResultJson;


    private double antColonyExecutionTime;


    private double geneticExecutionTime;


    private double simulatedAnnealingExecutionTime;


    private double greedyExecutionTime;


    private double antColonyTotalDistance;


    private double geneticTotalDistance;

    private double simulatedAnnealingTotalDistance;

    private double greedyTotalDistance;

}
