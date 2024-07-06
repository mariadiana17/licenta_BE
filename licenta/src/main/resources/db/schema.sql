CREATE TABLE optimization_result
(
    id                                 SERIAL PRIMARY KEY,
    input_coordinates_json             jsonb     NOT NULL,
    ant_colony_result_json              jsonb,
    genetic_result_json                 jsonb,
    simulated_annealing_result_json     jsonb,
    greedy_result_json                  jsonb,
    ant_colony_execution_time          DOUBLE PRECISION,
    genetic_execution_time             DOUBLE PRECISION,
    simulated_annealing_execution_time DOUBLE PRECISION,
    greedy_execution_time              DOUBLE PRECISION,
    ant_colony_total_distance          DOUBLE PRECISION,
    genetic_total_distance             DOUBLE PRECISION,
    simulated_annealing_total_distance DOUBLE PRECISION,
    greedy_total_distance              DOUBLE PRECISION
);
