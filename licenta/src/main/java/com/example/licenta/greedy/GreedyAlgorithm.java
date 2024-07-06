package com.example.licenta.greedy;

import com.example.licenta.common.model.Coordinate;
import com.example.licenta.common.calculator.HaversineCalculator;
import com.example.licenta.common.dto.PerformancesDto;
import com.example.licenta.comparison.model.Result;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GreedyAlgorithm {
    private List<Coordinate> coordinates;

    public GreedyAlgorithm(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public List<Coordinate> findOptimalRoute() {
        List<Coordinate> optimalRoute = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        int start = 0; // Start at the first city
        int end = coordinates.size() - 1; // End at the last city
        visited.add(start);

        optimalRoute.add(coordinates.get(start));

        int currentCity = start;
        while (visited.size() < coordinates.size() - 1) {
            double minDistance = Double.POSITIVE_INFINITY;
            int nearestCity = -1;

            for (int i = 1; i < coordinates.size() - 1; i++) {
                if (!visited.contains(i)) {
                    double distance = HaversineCalculator.calculateDistance(coordinates.get(currentCity), coordinates.get(i));
                    if (distance < minDistance) {
                        minDistance = distance;
                        nearestCity = i;
                    }
                }
            }

            if (nearestCity != -1) {
                visited.add(nearestCity);
                optimalRoute.add(coordinates.get(nearestCity));
                currentCity = nearestCity;
            }
        }

        // Finally, add the last city to complete the route
        optimalRoute.add(coordinates.get(end));

        return optimalRoute;
    }

    public PerformancesDto findOptimalRouteWithPerformance() {
        long startTime = System.nanoTime();
        List<Coordinate> optimalRoute = findOptimalRoute();
        long endTime = System.nanoTime();
        double executionTime = (endTime - startTime) / 1e6; // Convert to milliseconds

        double totalDistance = calculateTotalDistance(optimalRoute);

        return getPerformancesDto((new Result(totalDistance,executionTime)), optimalRoute);

    }

    private double calculateTotalDistance(List<Coordinate> route) {
        double totalDistance = 0.0;
        for (int i = 0; i < route.size() - 1; i++) {
            totalDistance += HaversineCalculator.calculateDistance(route.get(i), route.get(i + 1));
        }
        return totalDistance;
    }

    private PerformancesDto getPerformancesDto(Result result, List<Coordinate> route) {
        var performancesDto = new PerformancesDto();
        performancesDto.setResult(result);
        performancesDto.setRoute(route);
        return performancesDto;
    }

}
