package com.example.licenta.heldkarp;

import com.example.licenta.common.model.Coordinate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class HeldKarpRouteController {

    @PostMapping("/optimize-route-held-karp")
    public List<Coordinate> optimizeRoute(@RequestBody List<Coordinate> coordinates) {
        HeldKarp heldKarp = new HeldKarp(coordinates);
        return heldKarp.findOptimalRoute(coordinates);
    }
}