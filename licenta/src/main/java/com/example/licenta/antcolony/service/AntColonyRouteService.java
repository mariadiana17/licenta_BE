package com.example.licenta.antcolony.service;

import com.example.licenta.common.model.Coordinate;

import java.util.List;

public interface AntColonyRouteService {
    List<Coordinate> optimizeRoute( List<Coordinate> coordinates);
}
