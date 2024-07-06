package com.example.licenta.comparison.model;

public class Result {
    private double distance;
    private double executionTime;

    public Result(double distance, double executionTime) {
        this.distance = distance;
        this.executionTime = executionTime;
    }

    public double getDistance() {
        return distance;
    }

    public double getExecutionTime() {
        return executionTime;
    }


}
