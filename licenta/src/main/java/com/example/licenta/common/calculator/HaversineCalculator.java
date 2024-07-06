package com.example.licenta.common.calculator;


import com.example.licenta.common.model.Coordinate;

public class HaversineCalculator {
    private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM

    public static double calculateDistance(Coordinate start, Coordinate end) {
        double latDistance = Math.toRadians(end.getLatitude() - start.getLatitude());
        double lonDistance = Math.toRadians(end.getLongitude() - start.getLongitude());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(start.getLatitude())) * Math.cos(Math.toRadians(end.getLatitude())) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c; // returns the distance in kilometers
    }
}
