package com.example.licenta.antcolony.algorithm;

import com.example.licenta.common.model.Coordinate;
import com.example.licenta.common.dto.PerformancesDto;
import com.example.licenta.comparison.model.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AntColonyOptimization {
    private double[][] distance;
    private double[][] pheromones;
    private List<Coordinate> coordinates;
    private int numberOfCities;
    private double decayFactor = 0.5;
    private double pheromoneIncreaseFactor = 0.1;
    private int antCount;
    private int iterations;

    public AntColonyOptimization(int numberOfCities, int antCount, int iterations) {
        this.numberOfCities = numberOfCities;
        this.antCount = antCount;
        this.iterations = iterations;
        this.distance = new double[numberOfCities][numberOfCities];
        this.pheromones = new double[numberOfCities][numberOfCities];
    }

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
        initializeDistanceMatrix();
        initializePheromones();
    }

    private void initializeDistanceMatrix() {
        for (int i = 0; i < numberOfCities; i++) {
            for (int j = 0; j < numberOfCities; j++) {
                if (i == j) {
                    distance[i][j] = 0;
                } else {
                    distance[i][j] = Math.hypot(
                            coordinates.get(i).getLatitude() - coordinates.get(j).getLatitude(),
                            coordinates.get(i).getLongitude() - coordinates.get(j).getLongitude());
                }
            }
        }
    }

    private void initializePheromones() {
        for (double[] row : pheromones)
            Arrays.fill(row, 1.0);
    }

    public List<Coordinate> findOptimalRoute() {
        int[] bestTour = null;
        double bestLength = Double.MAX_VALUE;

        for (int i = 0; i < iterations; i++) {
            for (int j = 0; j < antCount; j++) {
                int[] tour = buildTour();
                double tourLength = calculateTourLength(tour);
                if (tourLength < bestLength) {
                    bestLength = tourLength;
                    bestTour = tour;
                }
            }
            updatePheromones(bestTour);
            evaporatePheromones();
        }

        return getTourCoordinates(bestTour);
    }

    public PerformancesDto findOptimalRouteWithPerformance() {
        long startTime = System.nanoTime();
        List<Coordinate> optimalRoute = findOptimalRoute();
        long endTime = System.nanoTime();
        double executionTime = (endTime - startTime) / 1e6; // Convert to milliseconds

        double totalDistance = calculateTotalDistance(optimalRoute);

        return getPerformancesDto(new Result(totalDistance,executionTime), optimalRoute);

    }

    private PerformancesDto getPerformancesDto(Result result, List<Coordinate> route) {
        PerformancesDto performancesDto = new PerformancesDto();
        performancesDto.setResult(result);
        performancesDto.setRoute(route);
        return performancesDto;
    }

    private void updatePheromones(int[] bestTour) {
        for (int i = 0; i < bestTour.length - 1; i++) {
            int from = bestTour[i];
            int to = bestTour[i + 1];
            pheromones[from][to] += pheromoneIncreaseFactor / distance[from][to];
            pheromones[to][from] = pheromones[from][to];  // Symmetric
        }
    }

    private void evaporatePheromones() {
        for (int i = 0; i < numberOfCities; i++) {
            for (int j = 0; j < numberOfCities; j++) {
                pheromones[i][j] *= (1 - decayFactor);
            }
        }
    }

    private int[] buildTour() {
        boolean[] visited = new boolean[numberOfCities];
        int[] tour = new int[numberOfCities];
        int city = 0; // Start from the first city
        visited[city] = true;

        for (int i = 1; i < numberOfCities; i++) {  // Starts from 1 because 0 is the start point
            city = nextCity(city, visited);
            if (city == -1) {
                // If no valid next city is found, this could be a problem.
                break;
            }
            tour[i] = city;
            visited[city] = true;
        }

        return tour;
    }

    private int nextCity(int currentCity, boolean[] visited) {
        double[] probs = calculateProbabilities(currentCity, visited);
        return rouletteWheelSelection(probs);
    }

    private double[] calculateProbabilities(int currentCity, boolean[] visited) {
        double[] probs = new double[numberOfCities];
        double sum = 0.0;

        for (int i = 0; i < numberOfCities; i++) {
            if (!visited[i]) {
                probs[i] = pheromones[currentCity][i] * (1.0 / distance[currentCity][i]);
                sum += probs[i];
            }
        }

        for (int i = 0; i < numberOfCities; i++) {
            if (!visited[i]) {
                probs[i] /= sum;
            }
        }
        return probs;
    }

    private int rouletteWheelSelection(double[] probs) {
        double rand = Math.random();
        double sum = 0.0;
        for (int i = 0; i < numberOfCities; i++) {
            sum += probs[i];
            if (sum >= rand) {
                return i;
            }
        }
        return -1;  // Should never happen
    }

    private double calculateTourLength(int[] tour) {
        double length = 0.0;
        for (int i = 0; i < tour.length - 1; i++) {
            length += distance[tour[i]][tour[i + 1]];
        }
        return length;
    }

    private List<Coordinate> getTourCoordinates(int[] bestTour) {
        List<Coordinate> tourCoordinates = new ArrayList<>();
        for (int index : bestTour) {
            tourCoordinates.add(coordinates.get(index));
        }
        return tourCoordinates;
    }

    private double calculateTotalDistance(List<Coordinate> route) {
        double totalDistance = 0.0;
        for (int i = 0; i < route.size() - 1; i++) {
            totalDistance += Math.hypot(
                    route.get(i).getLatitude() - route.get(i + 1).getLatitude(),
                    route.get(i).getLongitude() - route.get(i + 1).getLongitude());
        }
        return totalDistance;
    }
}
