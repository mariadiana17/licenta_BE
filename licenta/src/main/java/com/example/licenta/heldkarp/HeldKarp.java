package com.example.licenta.heldkarp;

import com.example.licenta.common.model.Coordinate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeldKarp {
    private double[][] distance;
    private int numberOfCities;
    private double[][] memo;
    private int[][] parent;

    public HeldKarp(List<Coordinate> coordinates) {
        this.numberOfCities = coordinates.size();
        this.distance = new double[numberOfCities][numberOfCities];
        this.memo = new double[numberOfCities][1 << numberOfCities];
        this.parent = new int[numberOfCities][1 << numberOfCities];

        // Initialize distances using Euclidean formula
        for (int i = 0; i < numberOfCities; i++) {
            for (int j = 0; j < numberOfCities; j++) {
                if (i != j) {
                    distance[i][j] = calculateEuclideanDistance(coordinates.get(i), coordinates.get(j));
                }
            }
        }

        // Initialize memo and parent tables
        for (double[] row : memo) {
            Arrays.fill(row, Double.POSITIVE_INFINITY);
        }
        for (int[] row : parent) {
            Arrays.fill(row, -1);
        }
    }

    private double calculateEuclideanDistance(Coordinate start, Coordinate end) {
        double dx = end.getLatitude() - start.getLatitude();
        double dy = end.getLongitude() - start.getLongitude();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public List<Coordinate> findOptimalRoute(List<Coordinate> coordinates) {
        // Start at the first city and finish at the last city
        memo[0][1] = 0; // Starting point

        for (int visited = 1; visited < (1 << (numberOfCities - 1)); visited += 2) {
            for (int last = 1; last < numberOfCities - 1; last++) {
                if ((visited & (1 << last)) != 0) {
                    for (int next = 1; next < numberOfCities - 1; next++) {
                        if ((visited & (1 << next)) == 0) {
                            double newDist = memo[last][visited] + distance[last][next];
                            int newVisited = visited | (1 << next);
                            if (newDist < memo[next][newVisited]) {
                                memo[next][newVisited] = newDist;
                                parent[next][newVisited] = last;
                            }
                        }
                    }
                }
            }
        }

        // Connect to the last city
        int allVisited = (1 << (numberOfCities - 1)) - 1; // Exclude the last city
        int lastCity = -1;
        double minDist = Double.POSITIVE_INFINITY;

        for (int i = 1; i < numberOfCities - 1; i++) {
            double dist = memo[i][allVisited] + distance[i][numberOfCities - 1];
            if (dist < minDist) {
                minDist = dist;
                lastCity = i;
            }
        }

        int[] path = new int[numberOfCities];
        path[0] = 0;
        path[numberOfCities - 1] = numberOfCities - 1;
        int index = numberOfCities - 2;

        int mask = allVisited;
        int pos = lastCity;

        while (pos != -1 && pos != 0) {
            path[index--] = pos;
            pos = parent[pos][mask];
            mask ^= (1 << path[index + 1]);
        }

        List<Coordinate> tourCoordinates = new ArrayList<>();
        for (int idx : path) {
            tourCoordinates.add(coordinates.get(idx));
        }

        // Log the final tour for debugging
        System.out.println("Final tour coordinates: " + tourCoordinates);
        return tourCoordinates;
    }
}
