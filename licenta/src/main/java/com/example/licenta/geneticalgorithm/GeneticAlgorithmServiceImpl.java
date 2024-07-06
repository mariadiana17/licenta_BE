package com.example.licenta.geneticalgorithm;

import com.example.licenta.common.model.Coordinate;
import com.example.licenta.common.calculator.HaversineCalculator;
import com.example.licenta.geneticalgorithm.model.Population;
import com.example.licenta.geneticalgorithm.model.Route;
import com.example.licenta.common.dto.PerformancesDto;
import com.example.licenta.comparison.model.Result;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class GeneticAlgorithmServiceImpl implements GeneticAlgorithmService {

    @Override
    public List<Coordinate> optimizeRoute(List<Coordinate> coordinates) {
        int populationSize = 50;
        int generations = 100;

        Population pop = new Population(populationSize, coordinates);
        GeneticAlgorithm ga = new GeneticAlgorithm();

        for (int i = 0; i < generations; i++) {
            pop = ga.evolvePopulation(pop);
        }

        Route bestRoute = pop.getRoutes().stream().min(Comparator.comparingDouble(Route::getDistance)).orElse(null);

        if (bestRoute != null) {
            List<Coordinate> cities = bestRoute.getCities();
            // Ensure the route starts and ends with the correct coordinates
            if (!cities.get(0).equals(coordinates.get(0)) || !cities.get(cities.size() - 1).equals(coordinates.get(coordinates.size() - 1))) {
                if (!cities.get(0).equals(coordinates.get(0))) {
                    // If the first city is not correct, reverse the list
                    Collections.reverse(cities);
                }
                // Ensure the last city is correct after potential reversal
                if (!cities.get(cities.size() - 1).equals(coordinates.get(coordinates.size() - 1))) {
                    throw new IllegalStateException("The genetic algorithm did not produce a valid route with the correct start and end points.");
                }
            }
            return cities;
        }
        return null;
    }

    public PerformancesDto optimizeRouteWithPerformances(List<Coordinate> coordinates) {
        long startTime = System.nanoTime();
        List<Coordinate> optimalRoute = optimizeRoute(coordinates);
        long endTime = System.nanoTime();
        double executionTime = (endTime - startTime) / 1e6; // Convert to milliseconds

        double totalDistance = calculateTotalDistance(optimalRoute);
        return getPerformancesDto((new Result(totalDistance,executionTime)), optimalRoute);

    }
    private PerformancesDto getPerformancesDto(Result result, List<Coordinate> route) {
        var performancesDto = new PerformancesDto();
        performancesDto.setResult(result);
        performancesDto.setRoute(route);
        return performancesDto;
    }

    private double calculateTotalDistance(List<Coordinate> route) {
        double totalDistance = 0.0;
        for (int i = 0; i < route.size() - 1; i++) {
            totalDistance += HaversineCalculator.calculateDistance(route.get(i), route.get(i + 1));
        }
        return totalDistance;
    }


}
