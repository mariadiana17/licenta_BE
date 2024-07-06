package com.example.licenta.common.db.entity;

import com.example.licenta.common.model.Coordinate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
@Table(name = "optimization_result")
@Getter
@Setter
public class OptimizationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "input_coordinates_json", nullable = false)
    private List<Coordinate> inputCoordinatesJson;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "ant_colony_result_json")
    private List<Coordinate> antColonyResultJson;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "genetic_result_json")
    private List<Coordinate> geneticResultJson;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "simulated_annealing_result_json")
    private List<Coordinate> simulatedAnnealingResultJson;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "greedy_result_json")
    private List<Coordinate> greedyResultJson;

    @Column(name = "ant_colony_execution_time")
    private double antColonyExecutionTime;

    @Column(name = "genetic_execution_time")
    private double geneticExecutionTime;

    @Column(name = "simulated_annealing_execution_time")
    private double simulatedAnnealingExecutionTime;

    @Column(name = "greedy_execution_time")
    private double greedyExecutionTime;

    @Column(name = "ant_colony_total_distance")
    private double antColonyTotalDistance;

    @Column(name = "genetic_total_distance")
    private double geneticTotalDistance;

    @Column(name = "simulated_annealing_total_distance")
    private double simulatedAnnealingTotalDistance;

    @Column(name = "greedy_total_distance")
    private double greedyTotalDistance;


}
