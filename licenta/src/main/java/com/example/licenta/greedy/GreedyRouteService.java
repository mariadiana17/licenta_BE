package com.example.licenta.greedy;

import com.example.licenta.common.model.Coordinate;

import java.util.List;

public interface GreedyRouteService {
    List<Coordinate> optimizeRoute(List<Coordinate> coordinates);
}
