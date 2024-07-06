package com.example.licenta.comparison.rest;

import com.example.licenta.common.model.Coordinate;
import com.example.licenta.comparison.service.AlgorithmComparisonService;
import com.example.licenta.comparison.util.ChartUtility;
import com.example.licenta.comparison.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
public class RouteComparisonController {

    @Autowired
    private AlgorithmComparisonService comparisonService;

    @Autowired
    private ChartUtility chartUtility;

    @PostMapping("/compare-algorithms")
    public ResponseEntity<byte[]> compareAlgorithms(@RequestBody List<Coordinate> coordinates) {
        try {
            List<Result> results = comparisonService.compareAlgorithms(coordinates);
            byte[] distanceChart = chartUtility.generateDistanceChart(results);
            byte[] executionTimeChart = chartUtility.generateExecutionTimeChart(results);

            // Create a zip file containing both charts
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try (ZipOutputStream zos = new ZipOutputStream(baos)) {
                ZipEntry distanceEntry = new ZipEntry("distance_chart.png");
                zos.putNextEntry(distanceEntry);
                zos.write(distanceChart);
                zos.closeEntry();

                ZipEntry timeEntry = new ZipEntry("execution_time_chart.png");
                zos.putNextEntry(timeEntry);
                zos.write(executionTimeChart);
                zos.closeEntry();
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentLength(baos.size());
            headers.setContentDispositionFormData("attachment", "comparison_charts.zip");

            return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
