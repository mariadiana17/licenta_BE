package com.example.licenta.antcolony.rest;

import com.example.licenta.common.model.Coordinate;
import com.example.licenta.antcolony.service.AntColonyRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AntColonyRouteController {
    @Autowired
    AntColonyRouteService antColonyRouteService;

    @PostMapping("/optimize-route-ant-colony")
    public List<Coordinate> optimizeRoute(@RequestBody List<Coordinate> coordinates) {
        return antColonyRouteService.optimizeRoute(coordinates);
    }
}