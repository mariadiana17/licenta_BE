package com.example.licenta.geneticalgorithm.model;

import com.example.licenta.common.model.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Population {
    private List<Route> routes;

    public Population(int populationSize, List<Coordinate> cities) {
        this.routes = new ArrayList<>(populationSize);
        for (int i = 0; i < populationSize; i++) {
            this.routes.add(new Route(cities));
        }
    }

    public List<Route> getRoutes() {
        return routes;
    }
}
