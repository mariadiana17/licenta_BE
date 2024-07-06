package com.example.licenta.geneticalgorithm;

import com.example.licenta.common.model.Coordinate;
import com.example.licenta.geneticalgorithm.model.Population;
import com.example.licenta.geneticalgorithm.model.Route;
import com.example.licenta.comparison.model.Result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;
    private static final Random random = new Random();

    public Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.getRoutes().size(), pop.getRoutes().get(0).getCities());

        int elitismOffset = 0;
        if (elitism) {
            newPopulation.getRoutes().set(0, pop.getRoutes().stream().min(Comparator.comparingDouble(Route::getDistance)).orElse(null));
            elitismOffset = 1;
        }

        for (int i = elitismOffset; i < newPopulation.getRoutes().size(); i++) {
            Route parent1 = tournamentSelection(pop);
            Route parent2 = tournamentSelection(pop);
            Route child = crossover(parent1, parent2);
            newPopulation.getRoutes().set(i, child);
        }

        for (int i = elitismOffset; i < newPopulation.getRoutes().size(); i++) {
            mutate(newPopulation.getRoutes().get(i));
        }

        return newPopulation;
    }

    public Result evolvePopulationWithPerformance(Population pop, int generations) {
        long startTime = System.nanoTime();
        for (int i = 0; i < generations; i++) {
            pop = evolvePopulation(pop);
        }
        long endTime = System.nanoTime();
        double executionTime = (endTime - startTime) / 1e6; // Convert to milliseconds

        Route bestRoute = pop.getRoutes().stream().min(Comparator.comparingDouble(Route::getDistance)).orElse(null);
        double bestDistance = bestRoute != null ? bestRoute.getDistance() : Double.MAX_VALUE;

        return new Result(bestDistance, executionTime);
    }

    private Route crossover(Route parent1, Route parent2) {
        List<Coordinate> childCities = new ArrayList<>(Collections.nCopies(parent1.getCities().size(), null));

        // Ensure the start and end points remain unchanged
        childCities.set(0, parent1.getCities().get(0));
        childCities.set(childCities.size() - 1, parent1.getCities().get(parent1.getCities().size() - 1));

        int startPos = 1 + random.nextInt(parent1.getCities().size() - 2);
        int endPos = 1 + random.nextInt(parent1.getCities().size() - 2);

        if (startPos > endPos) {
            int temp = startPos;
            startPos = endPos;
            endPos = temp;
        }

        for (int i = startPos; i <= endPos; i++) {
            childCities.set(i, parent1.getCities().get(i));
        }

        for (Coordinate city : parent2.getCities()) {
            if (!childCities.contains(city)) {
                for (int i = 1; i < childCities.size() - 1; i++) {
                    if (childCities.get(i) == null) {
                        childCities.set(i, city);
                        break;
                    }
                }
            }
        }

        Route child = new Route(parent1.getCities().size());
        child.setCities(childCities);

        return child;
    }

    private void mutate(Route route) {
        for (int routePos1 = 1; routePos1 < route.getCities().size() - 1; routePos1++) {
            if (Math.random() < mutationRate) {
                int routePos2 = 1 + (int) ((route.getCities().size() - 2) * Math.random());

                Coordinate city1 = route.getCities().get(routePos1);
                Coordinate city2 = route.getCities().get(routePos2);

                route.getCities().set(routePos2, city1);
                route.getCities().set(routePos1, city2);
            }
        }
    }

    private Route tournamentSelection(Population pop) {
        Population tournament = new Population(tournamentSize, pop.getRoutes().get(0).getCities());
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.getRoutes().size());
            tournament.getRoutes().set(i, pop.getRoutes().get(randomId));
        }
        return tournament.getRoutes().stream().min(Comparator.comparingDouble(Route::getDistance)).orElse(null);
    }
}
