package com.example.licenta.best.service.converter;

import com.example.licenta.common.calculator.HaversineCalculator;
import com.example.licenta.common.db.entity.OptimizationResult;
import com.example.licenta.best.dto.OptimizationResultDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OptimizationResultConverter {
    public OptimizationResultDto convertToOptimizationResultDto(OptimizationResult optimizationResult) {
        return OptimizationResultDto.builder()
                .inputCoordinatesJson(HaversineCalculator.calculateDistance(optimizationResult.getInputCoordinatesJson().get(0),
                        optimizationResult.getInputCoordinatesJson().get(optimizationResult.getInputCoordinatesJson().size()-1)))
                .antColonyExecutionTime(optimizationResult.getAntColonyExecutionTime())
                .geneticExecutionTime(optimizationResult.getGeneticExecutionTime())
                .simulatedAnnealingExecutionTime(optimizationResult.getSimulatedAnnealingExecutionTime())
                .greedyExecutionTime(optimizationResult.getGreedyExecutionTime())
                .antColonyTotalDistance(optimizationResult.getAntColonyTotalDistance())
                .geneticTotalDistance(optimizationResult.getGeneticTotalDistance())
                .simulatedAnnealingTotalDistance(optimizationResult.getSimulatedAnnealingTotalDistance())
                .greedyTotalDistance(optimizationResult.getGreedyTotalDistance())
                .build();

    }
}
