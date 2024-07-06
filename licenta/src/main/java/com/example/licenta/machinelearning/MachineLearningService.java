package com.example.licenta.machinelearning;


import com.example.licenta.common.model.Coordinate;
import org.pmml4s.metadata.Field;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;
import org.pmml4s.model.Model;

@Service
public class MachineLearningService {

    private Model model;

    public MachineLearningService() throws IOException, SAXException, JAXBException, jakarta.xml.bind.JAXBException, ParserConfigurationException {
        File pmmlFile = new File("C:/Users/Maria F15/Documents/licenta/machine learning/travel_time_model.pmml");
        model = Model.fromFile(pmmlFile);
        }



    public double predictTravelTime(Coordinate start, Coordinate end, double traffic) {
        Map<String, Object> arguments = new LinkedHashMap<>();
        arguments.put("start_lat", start.getLatitude());
        arguments.put("start_lng", start.getLongitude());
        arguments.put("end_lat", end.getLatitude());
        arguments.put("end_lng", end.getLongitude());
        arguments.put("traffic", traffic);

        Map<String, ?> results = model.predict(arguments);
        Field targetField = model.targetField();
        Object resultValue = results.get("predicted_" + targetField.name());

        return resultValue == null ? 0 : Double.parseDouble(resultValue.toString());
    }
}