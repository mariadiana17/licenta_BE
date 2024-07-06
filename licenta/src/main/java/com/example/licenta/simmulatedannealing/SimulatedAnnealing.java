package com.example.licenta.simmulatedannealing;

import com.example.licenta.common.model.Coordinate;
import com.example.licenta.common.calculator.HaversineCalculator;
import com.example.licenta.common.dto.PerformancesDto;
import com.example.licenta.comparison.model.Result;

import java.util.ArrayList;
import java.util.List;

public class SimulatedAnnealing {
    private double temperature;
    private double coolingRate;

    public SimulatedAnnealing(double temperature, double coolingRate) {
        this.temperature = temperature;
        this.coolingRate = coolingRate;
    }

    public List<Coordinate> optimizeRoute(List<Coordinate> currentRoute) {
        List<Coordinate> best = new ArrayList<>(currentRoute);
        List<Coordinate> current = new ArrayList<>(currentRoute);

        while (temperature > 1) {
            List<Coordinate> newRoute = generateNeighbor(current);

            double currentEnergy = routeDistance(current);
            double neighborEnergy = routeDistance(newRoute);
            double acceptanceProbability = acceptanceProbability(currentEnergy, neighborEnergy, temperature);

            if (acceptanceProbability > Math.random()) {
                current = new ArrayList<>(newRoute);
            }

            if (routeDistance(current) < routeDistance(best)) {
                best = new ArrayList<>(current);
            }

            temperature *= 1 - coolingRate;
        }

        return best;
    }

    public PerformancesDto optimizeRouteWithPerformance(List<Coordinate> currentRoute) {
        long startTime = System.nanoTime();
        List<Coordinate> optimalRoute = optimizeRoute(currentRoute);
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
    private List<Coordinate> generateNeighbor(List<Coordinate> currentRoute) {
        int swapIndex1 = 1 + (int) ((currentRoute.size() - 2) * Math.random());
        int swapIndex2 = 1 + (int) ((currentRoute.size() - 2) * Math.random());

        Coordinate temp = currentRoute.get(swapIndex1);
        currentRoute.set(swapIndex1, currentRoute.get(swapIndex2));
        currentRoute.set(swapIndex2, temp);

        return new ArrayList<>(currentRoute);
    }

    private double routeDistance(List<Coordinate> route) {
        double totalDistance = 0;
        for (int i = 0; i < route.size() - 1; i++) {
            totalDistance += HaversineCalculator.calculateDistance(route.get(i), route.get(i + 1));
        }
        totalDistance += HaversineCalculator.calculateDistance(route.get(route.size() - 1), route.get(0));
        return totalDistance;
    }

    private double acceptanceProbability(double currentEnergy, double newEnergy, double temperature) {
        if (newEnergy < currentEnergy) {
            return 1.0;
        }
        return Math.exp((currentEnergy - newEnergy) / temperature);
    }
}
