package com.example.licenta.geneticalgorithm.model;

import com.example.licenta.common.model.Coordinate;
import com.example.licenta.common.calculator.HaversineCalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Route {
    private List<Coordinate> cities;
    private double distance = 0;

    public Route(List<Coordinate> cities) {
        this.cities = new ArrayList<>(cities);
        Collections.shuffle(this.cities.subList(1, this.cities.size() - 1)); // Shuffle except first and last
    }

    public Route(int numCities) {
        this.cities = new ArrayList<>(Collections.nCopies(numCities, null));
    }

    public List<Coordinate> getCities() {
        return cities;
    }

    public void setCities(List<Coordinate> cities) {
        this.cities = cities;
        this.distance = 0; // Reset distance so it will be recalculated
    }

    public double getDistance() {
        if (distance == 0) {
            for (int i = 0; i < cities.size() - 1; i++) {
                distance += HaversineCalculator.calculateDistance(cities.get(i), cities.get(i + 1));
            }
        }
        return distance;
    }

    public void setCity(int index, Coordinate city) {
        this.cities.set(index, city);
        this.distance = 0; // Reset distance so it will be recalculated
    }
}
