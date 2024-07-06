package com.example.licenta.geneticalgorithm;
import com.example.licenta.common.model.Coordinate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GeneticAlgorithmController {

    @Autowired
    GeneticAlgorithmService geneticAlgorithmService;

    @PostMapping("/optimize-route-genetic")
    public List<Coordinate> optimizeRoute(@RequestBody List<Coordinate> coordinates) {
       return geneticAlgorithmService.optimizeRoute(coordinates);
    }
    }

