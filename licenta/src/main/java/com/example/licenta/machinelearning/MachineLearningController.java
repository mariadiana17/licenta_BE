package com.example.licenta.machinelearning;


import com.example.licenta.common.model.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ml")
public class MachineLearningController {

    @Autowired
    private MachineLearningRouteService routeOptimizationService;

    @PostMapping("/optimize-route")
    public List<Coordinate> optimizeRouteWithML(@RequestBody RequestData requestData) {
        List<Coordinate> coordinates = requestData.getCoordinates();
        Map<String, Double> trafficData = requestData.getTrafficData();

        return routeOptimizationService.optimizeRouteWithML(coordinates, trafficData);
    }
}

class RequestData {
    private List<Coordinate> coordinates;
    private Map<String, Double> trafficData;

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public Map<String, Double> getTrafficData() {
        return trafficData;
    }

    public void setTrafficData(Map<String, Double> trafficData) {
        this.trafficData = trafficData;
    }
}