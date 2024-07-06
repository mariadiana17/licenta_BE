package com.example.licenta.antcolony.service;

import com.example.licenta.common.model.Coordinate;
import com.example.licenta.antcolony.algorithm.AntColonyOptimization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AntColonyRouteServiceImpl implements AntColonyRouteService{

    @Override
    public List<Coordinate> optimizeRoute(List<Coordinate> coordinates) {
        // Assuming the AntColonyOptimization class has been correctly configured to accept a list of Coordinate
        AntColonyOptimization aco = new AntColonyOptimization(coordinates.size(), 50, 100); // antCount and iterations are arbitrary
        aco.setCoordinates(coordinates);
        return aco.findOptimalRoute();
    }
}
