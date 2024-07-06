package com.example.licenta.common.dto;

import com.example.licenta.common.model.Coordinate;
import com.example.licenta.comparison.model.Result;

import java.util.List;

public class PerformancesDto {
    private Result result;
    private List<Coordinate> route;


    public Result getResult() {
        return result;
    }

    public List<Coordinate> getRoute() {
        return route;
    }

    public void setResult(Result result) {
       this.result = result;
    }

    public void setRoute(List<Coordinate> route) {
        this.route = route;
    }
}
