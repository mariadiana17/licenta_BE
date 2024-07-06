package com.example.licenta.geneticalgorithm;

import com.example.licenta.common.model.Coordinate;

import java.util.List;

public interface GeneticAlgorithmService {
   List<Coordinate> optimizeRoute(List<Coordinate> coordinates);

    }
