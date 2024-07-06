//package com.example.licenta.machinelearning.two;
//
//import com.example.licenta.common.model.Coordinate;
//import org.pmml4s.metadata.Field;
//import org.pmml4s.model.Model;
//import org.springframework.stereotype.Service;
//import org.xml.sax.SAXException;
//
//import javax.xml.bind.JAXBException;
//import javax.xml.parsers.ParserConfigurationException;
//import java.io.File;
//import java.io.IOException;
//import java.util.*;
//
//@Service
//public class MachineLearningTwoService {
//    private Model model;
//
//    public MachineLearningTwoService() throws IOException, SAXException, JAXBException, jakarta.xml.bind.JAXBException, ParserConfigurationException {
//        File pmmlFile = new File("C:/Users/Maria F15/Documents/licenta/machine learning/route_optimization_model.pmml");
//        model = Model.fromFile(pmmlFile);
//    }
//
//    public List<Coordinate> predictOptimalRoute(List<Coordinate> coordinates) {
//        List<Coordinate> sortedCoordinates = new ArrayList<>(coordinates);
//        sortedCoordinates.sort(Comparator.comparingDouble(c -> predictOrder(c, coordinates.get(0), coordinates.get(coordinates.size() - 1))));
//        return sortedCoordinates;
//    }
//
//    public double predictOrder(Coordinate coordinate,Coordinate start, Coordinate end) {
//        Map<String, Object> arguments = new LinkedHashMap<>();
//        arguments.put("start_lat", start.getLatitude());
//        arguments.put("start_lng", start.getLongitude());
//        arguments.put("end_lat", end.getLatitude());
//        arguments.put("end_lng", end.getLongitude());
//        arguments.put("traffic", traffic);
//
//        Map<String, ?> results = model.predict(arguments);
//        Field targetField = model.targetField();
//        Object resultValue = results.get("predicted_" + targetField.name());
//
//        return resultValue == null ? 0 : Double.parseDouble(resultValue.toString());
//    }
//}
