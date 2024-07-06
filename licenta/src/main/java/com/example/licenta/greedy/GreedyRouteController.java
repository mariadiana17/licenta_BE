package com.example.licenta.greedy;

import com.example.licenta.common.model.Coordinate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class GreedyRouteController {
    @Autowired
    GreedyRouteService greedyRouteService;

    @PostMapping("/optimize-route-greedy")
    public List<Coordinate> optimizeRoute(@RequestBody List<Coordinate> coordinates) {
        return greedyRouteService.optimizeRoute(coordinates);
    }
}
