package com.example.licenta.machinelearning;

import com.example.licenta.common.model.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MachineLearningRouteService {
    @Autowired
    private MachineLearningService machineLearningService;

    public List<Coordinate> optimizeRouteWithML(List<Coordinate> coordinates, Map<String, Double> trafficData) {
        List<Coordinate> optimalRoute = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        int start = 0; // Start at the first city
        int end = coordinates.size() - 1; // End at the last city
        visited.add(start);

        optimalRoute.add(coordinates.get(start));

        int currentCity = start;
        while (visited.size() < coordinates.size() - 1) {
            double minDistance = Double.POSITIVE_INFINITY;
            int nearestCity = -1;

            for (int i = 1; i < coordinates.size() - 1; i++) {
                if (!visited.contains(i)) {
                    String key = currentCity + "-" + i;
                    double traffic = trafficData.getOrDefault(key, 0.0);
                    double distance = machineLearningService.predictTravelTime(coordinates.get(currentCity), coordinates.get(i), traffic);
                    if (distance < minDistance) {
                        minDistance = distance;
                        nearestCity = i;
                    }
                }
            }

            if (nearestCity != -1) {
                visited.add(nearestCity);
                optimalRoute.add(coordinates.get(nearestCity));
                currentCity = nearestCity;
            }
        }

        // Finally, add the last city to complete the route
        optimalRoute.add(coordinates.get(end));

        return optimalRoute;
    }
}
