package com.example.licenta.greedy;

import com.example.licenta.common.model.Coordinate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GreedyRouteServiceImpl implements GreedyRouteService{

    @Override
    public List<Coordinate> optimizeRoute( List<Coordinate> coordinates) {
        GreedyAlgorithm greedyAlgorithm = new GreedyAlgorithm(coordinates);
        List<Coordinate> optimizedRoute = greedyAlgorithm.findOptimalRoute();

        // Log the optimized route for debugging
        System.out.println("Optimized route: " + optimizedRoute);
        return optimizedRoute;
    }
}
