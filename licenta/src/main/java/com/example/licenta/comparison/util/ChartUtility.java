package com.example.licenta.comparison.util;

import com.example.licenta.comparison.model.Result;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class ChartUtility {

    public byte[] generateDistanceChart(List<Result> results) throws IOException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String[] algorithms = {"Genetic Algorithm", "Simulated Annealing", "Greedy Algorithm", "Ant Colony Optimization"};
        for (int i = 0; i < results.size(); i++) {
            dataset.addValue(results.get(i).getDistance(), "Distance", algorithms[i]);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Algorithm Performance Comparison (Distance)",
                "Algorithm", "Distance",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ChartUtils.writeChartAsPNG(baos, barChart, 800, 600);
        return baos.toByteArray();
    }

    public byte[] generateExecutionTimeChart(List<Result> results) throws IOException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String[] algorithms = {"Genetic Algorithm", "Simulated Annealing", "Greedy Algorithm", "Ant Colony Optimization"};
        for (int i = 0; i < results.size(); i++) {
            dataset.addValue(results.get(i).getExecutionTime(), "Time", algorithms[i]);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Algorithm Performance Comparison (Execution Time)",
                "Algorithm", "Time (ms)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ChartUtils.writeChartAsPNG(baos, barChart, 800, 600);
        return baos.toByteArray();
    }
}
