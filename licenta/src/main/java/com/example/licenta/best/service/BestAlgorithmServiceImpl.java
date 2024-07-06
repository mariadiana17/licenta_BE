package com.example.licenta.best.service;

import com.example.licenta.common.db.repository.OptimizationResultRepository;
import com.example.licenta.best.dto.BestAlgorithmDto;
import com.example.licenta.best.service.converter.OptimizationResultConverter;
import com.example.licenta.best.dto.OptimizationResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BestAlgorithmServiceImpl implements BestAlgorithmService {
    @Autowired
    OptimizationResultRepository optimizationResultRepository;

    private final String GENETIC_ALGORITHM = "GENETIC_ALGORITHM";
    private final String GREEDY_ALGORITHM = "GREEDY_ALGORITHM";
    private final String ANT_COLONY_ALGORITHM = "ANT_COLONY_ALGORITHM";
    private final String SIMULATED_ANNEALING_ALGORITHM = "SIMULATED_ANNEALING_ALGORITHM";

    @Override
    public BestAlgorithmDto getBestOnLongDistanceDto() {
        var optimizations = optimizationResultRepository.findAll();
        var distances = optimizations.stream().map(OptimizationResultConverter::convertToOptimizationResultDto
        ).max(Comparator.comparingDouble(OptimizationResultDto::getInputCoordinatesJson));

        HashMap<String, Double> map = new HashMap<>();
        map.put(GENETIC_ALGORITHM, distances.get().getGeneticExecutionTime());
        map.put(GREEDY_ALGORITHM, distances.get().getGreedyExecutionTime());
        map.put(ANT_COLONY_ALGORITHM, distances.get().getAntColonyExecutionTime());
        map.put(SIMULATED_ANNEALING_ALGORITHM, distances.get().getSimulatedAnnealingExecutionTime());

        String keyWithSmallestValue = null;
        double smallestValue = Double.MAX_VALUE;


        for (Map.Entry<String, Double> entry : map.entrySet()) {
            if (entry.getValue() < smallestValue) {
                smallestValue = entry.getValue();
                keyWithSmallestValue = entry.getKey();
            }
        }
        var response = new BestAlgorithmDto();
        response.setSmallestExecutionTimeAlgorithmName(keyWithSmallestValue);
        response.setSmallestExecutionTime(smallestValue);


        //BASED ON TOTAL DISTANCE
        HashMap<String, Double> mapMinDistance = new HashMap<>();
        mapMinDistance.put(GENETIC_ALGORITHM, distances.get().getGeneticTotalDistance());
        mapMinDistance.put(GREEDY_ALGORITHM, distances.get().getGreedyTotalDistance());
        mapMinDistance.put(ANT_COLONY_ALGORITHM, distances.get().getAntColonyTotalDistance());
        mapMinDistance.put(SIMULATED_ANNEALING_ALGORITHM, distances.get().getSimulatedAnnealingTotalDistance());

        String keyWithSmallestValueDistance = null;
        double smallestValueDistance = Double.MAX_VALUE;
        for (Map.Entry<String, Double> entry : mapMinDistance.entrySet()) {
            if (entry.getValue() < smallestValueDistance) {
                smallestValueDistance = entry.getValue();
                keyWithSmallestValueDistance = entry.getKey();
            }
        }

        response.setSmallestTotalDistance(smallestValueDistance);
        response.setSmallestTotalDistanceAlgorithmName(keyWithSmallestValueDistance);
        return response;
    }

    @Override
    public BestAlgorithmDto getBestOnMaxCities() {
        var optimizations = optimizationResultRepository.findAll();
        var distances = optimizations.stream().max(Comparator.comparingDouble(e -> e.getInputCoordinatesJson().size()));

        HashMap<String, Double> map = new HashMap<>();
        map.put(GENETIC_ALGORITHM, distances.get().getGeneticExecutionTime());
        map.put(GREEDY_ALGORITHM, distances.get().getGreedyExecutionTime());
        map.put(ANT_COLONY_ALGORITHM, distances.get().getAntColonyExecutionTime());
        map.put(SIMULATED_ANNEALING_ALGORITHM, distances.get().getSimulatedAnnealingExecutionTime());

        String keyWithSmallestValue = null;
        double smallestValue = Double.MAX_VALUE;


        for (Map.Entry<String, Double> entry : map.entrySet()) {
            if (entry.getValue() < smallestValue) {
                smallestValue = entry.getValue();
                keyWithSmallestValue = entry.getKey();
            }
        }
        var response = new BestAlgorithmDto();
        response.setSmallestExecutionTimeAlgorithmName(keyWithSmallestValue);
        response.setSmallestExecutionTime(smallestValue);


        //BASED ON TOTAL DISTANCE
        HashMap<String, Double> mapMinDistance = new HashMap<>();
        mapMinDistance.put(GENETIC_ALGORITHM, distances.get().getGeneticTotalDistance());
        mapMinDistance.put(GREEDY_ALGORITHM, distances.get().getGreedyTotalDistance());
        mapMinDistance.put(ANT_COLONY_ALGORITHM, distances.get().getAntColonyTotalDistance());
        mapMinDistance.put(SIMULATED_ANNEALING_ALGORITHM, distances.get().getSimulatedAnnealingTotalDistance());

        String keyWithSmallestValueDistance = null;
        double smallestValueDistance = Double.MAX_VALUE;
        for (Map.Entry<String, Double> entry : mapMinDistance.entrySet()) {
            if (entry.getValue() < smallestValueDistance) {
                smallestValueDistance = entry.getValue();
                keyWithSmallestValueDistance = entry.getKey();
            }
        }

        response.setSmallestTotalDistance(smallestValueDistance);
        response.setSmallestTotalDistanceAlgorithmName(keyWithSmallestValueDistance);
        return response;
    }

    @Override
    public BestAlgorithmDto getBestOnShortDistanceDto() {
        var optimizations = optimizationResultRepository.findAll();
        var distances = optimizations.stream().map(OptimizationResultConverter::convertToOptimizationResultDto
        ).min(Comparator.comparingDouble(OptimizationResultDto::getInputCoordinatesJson));

        HashMap<String, Double> map = new HashMap<>();
        map.put(GENETIC_ALGORITHM, distances.get().getGeneticExecutionTime());
        map.put(GREEDY_ALGORITHM, distances.get().getGreedyExecutionTime());
        map.put(ANT_COLONY_ALGORITHM, distances.get().getAntColonyExecutionTime());
        map.put(SIMULATED_ANNEALING_ALGORITHM, distances.get().getSimulatedAnnealingExecutionTime());

        String keyWithSmallestValue = null;
        double smallestValue = Double.MAX_VALUE;


        for (Map.Entry<String, Double> entry : map.entrySet()) {
            if (entry.getValue() < smallestValue) {
                smallestValue = entry.getValue();
                keyWithSmallestValue = entry.getKey();
            }
        }
        var response = new BestAlgorithmDto();
        response.setSmallestExecutionTimeAlgorithmName(keyWithSmallestValue);
        response.setSmallestExecutionTime(smallestValue);


        //BASED ON TOTAL DISTANCE
        HashMap<String, Double> mapMinDistance = new HashMap<>();
        mapMinDistance.put(GENETIC_ALGORITHM, distances.get().getGeneticTotalDistance());
        mapMinDistance.put(GREEDY_ALGORITHM, distances.get().getGreedyTotalDistance());
        mapMinDistance.put(ANT_COLONY_ALGORITHM, distances.get().getAntColonyTotalDistance());
        mapMinDistance.put(SIMULATED_ANNEALING_ALGORITHM, distances.get().getSimulatedAnnealingTotalDistance());

        String keyWithSmallestValueDistance = null;
        double smallestValueDistance = Double.MAX_VALUE;
        for (Map.Entry<String, Double> entry : mapMinDistance.entrySet()) {
            if (entry.getValue() < smallestValueDistance) {
                smallestValueDistance = entry.getValue();
                keyWithSmallestValueDistance = entry.getKey();
            }
        }

        response.setSmallestTotalDistance(smallestValueDistance);
        response.setSmallestTotalDistanceAlgorithmName(keyWithSmallestValueDistance);
        return response;
    }

    @Override
    public BestAlgorithmDto getBestOnMinCities() {
        var optimizations = optimizationResultRepository.findAll();
        var distances = optimizations.stream().min(Comparator.comparingDouble(e -> e.getInputCoordinatesJson().size()));

        HashMap<String, Double> map = new HashMap<>();
        map.put(GENETIC_ALGORITHM, distances.get().getGeneticExecutionTime());
        map.put(GREEDY_ALGORITHM, distances.get().getGreedyExecutionTime());
        map.put(ANT_COLONY_ALGORITHM, distances.get().getAntColonyExecutionTime());
        map.put(SIMULATED_ANNEALING_ALGORITHM, distances.get().getSimulatedAnnealingExecutionTime());

        String keyWithSmallestValue = null;
        double smallestValue = Double.MAX_VALUE;


        for (Map.Entry<String, Double> entry : map.entrySet()) {
            if (entry.getValue() < smallestValue) {
                smallestValue = entry.getValue();
                keyWithSmallestValue = entry.getKey();
            }
        }
        var response = new BestAlgorithmDto();
        response.setSmallestExecutionTimeAlgorithmName(keyWithSmallestValue);
        response.setSmallestExecutionTime(smallestValue);


        //BASED ON TOTAL DISTANCE
        HashMap<String, Double> mapMinDistance = new HashMap<>();
        mapMinDistance.put(GENETIC_ALGORITHM, distances.get().getGeneticTotalDistance());
        mapMinDistance.put(GREEDY_ALGORITHM, distances.get().getGreedyTotalDistance());
        mapMinDistance.put(ANT_COLONY_ALGORITHM, distances.get().getAntColonyTotalDistance());
        mapMinDistance.put(SIMULATED_ANNEALING_ALGORITHM, distances.get().getSimulatedAnnealingTotalDistance());

        String keyWithSmallestValueDistance = null;
        double smallestValueDistance = Double.MAX_VALUE;
        for (Map.Entry<String, Double> entry : mapMinDistance.entrySet()) {
            if (entry.getValue() < smallestValueDistance) {
                smallestValueDistance = entry.getValue();
                keyWithSmallestValueDistance = entry.getKey();
            }
        }

        response.setSmallestTotalDistance(smallestValueDistance);
        response.setSmallestTotalDistanceAlgorithmName(keyWithSmallestValueDistance);
        return response;
    }
}
