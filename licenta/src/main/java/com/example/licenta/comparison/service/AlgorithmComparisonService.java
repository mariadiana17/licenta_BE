package com.example.licenta.comparison.service;

import com.example.licenta.common.model.Coordinate;
import com.example.licenta.common.db.entity.OptimizationResult;
import com.example.licenta.common.db.repository.OptimizationResultRepository;
import com.example.licenta.antcolony.algorithm.AntColonyOptimization;
import com.example.licenta.comparison.model.Result;
import com.example.licenta.geneticalgorithm.GeneticAlgorithmServiceImpl;
import com.example.licenta.greedy.GreedyAlgorithm;
import com.example.licenta.simmulatedannealing.SimulatedAnnealing;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlgorithmComparisonService {

    @Autowired
    private OptimizationResultRepository optimizationResultRepository;

    public List<Result> compareAlgorithms(List<Coordinate> coordinates) {
        List<Result> results = new ArrayList<>();
        var optimizationResultEntity = new OptimizationResult();
        optimizationResultEntity.setInputCoordinatesJson(coordinates);

        // Ant Colony Optimization
        AntColonyOptimization aco = new AntColonyOptimization(coordinates.size(), 100, 100);
        aco.setCoordinates(coordinates);
        var acoPerformances = aco.findOptimalRouteWithPerformance();
        results.add(acoPerformances.getResult());
        optimizationResultEntity.setAntColonyResultJson(acoPerformances.getRoute());
        optimizationResultEntity.setAntColonyExecutionTime(acoPerformances.getResult().getExecutionTime());
        optimizationResultEntity.setAntColonyTotalDistance(acoPerformances.getResult().getDistance());

        // Genetic Algorithm
        GeneticAlgorithmServiceImpl ga = new GeneticAlgorithmServiceImpl();
        var gPerformances = ga.optimizeRouteWithPerformances(coordinates);
        results.add(gPerformances.getResult());
        optimizationResultEntity.setGeneticResultJson(gPerformances.getRoute());
        optimizationResultEntity.setGeneticExecutionTime(gPerformances.getResult().getExecutionTime());
        optimizationResultEntity.setGeneticTotalDistance(gPerformances.getResult().getDistance());

        // Simulated Annealing
        SimulatedAnnealing sa = new SimulatedAnnealing(10000, 0.003);
        var saPerformances =  sa.optimizeRouteWithPerformance(coordinates);
        results.add(saPerformances.getResult());
        optimizationResultEntity.setSimulatedAnnealingResultJson(saPerformances.getRoute());
        optimizationResultEntity.setSimulatedAnnealingExecutionTime(saPerformances.getResult().getExecutionTime());
        optimizationResultEntity.setSimulatedAnnealingTotalDistance(saPerformances.getResult().getDistance());

        // Greedy Algorithm
        GreedyAlgorithm greedy = new GreedyAlgorithm(coordinates);
        var gaPerformances =  greedy.findOptimalRouteWithPerformance();
        results.add(gaPerformances.getResult());
        optimizationResultEntity.setGreedyResultJson(gaPerformances.getRoute());
        optimizationResultEntity.setGreedyExecutionTime(gaPerformances.getResult().getExecutionTime());
        optimizationResultEntity.setGreedyTotalDistance(gaPerformances.getResult().getDistance());

        optimizationResultRepository.save(optimizationResultEntity);
        return results;
    }

    private String convertToJson(Object object) {
        try {
            // Assuming you have a JSON library like Jackson
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


