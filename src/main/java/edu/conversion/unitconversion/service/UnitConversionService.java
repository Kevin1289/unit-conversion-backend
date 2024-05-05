package edu.conversion.unitconversion.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import edu.conversion.unitconversion.dto.UnitConversionRequest;
import edu.conversion.unitconversion.dto.UnitConversionResponse;
import edu.conversion.unitconversion.enums.ResponseStatus;

public class UnitConversionService {

    private static Map<String, List<String>> typeToUnits = new HashMap<>();

    static {
        typeToUnits.put("temperature", Arrays.asList("kelvin", "celsius", "fahrenheit", "rankine"));
        typeToUnits.put("volume", Arrays.asList("liters", "tablespoons", "cubic-inches", "cups", "cubic-feet", "gallons"));
    }

    public static UnitConversionResponse convert(UnitConversionRequest request) {

        // verify if type is a key of typeToUnits
        if (!typeToUnits.containsKey(request.getType())) {
            return new UnitConversionResponse(ResponseStatus.INVALID);
        }

        // verify if unit is a value of the key type
        if (!typeToUnits.get(request.getType()).contains(request.getUnit())) {
            return new UnitConversionResponse(ResponseStatus.INVALID);
        }

        // Perform Conversion using the UnitOf library

        switch (request.getType()) {
            case "temperature":
                double authoritativeAnswer = request.getValue();
                double studentResponse = request.getResponse();

                // Convert to target Temperature
                // Convert to Celsius first
                switch (request.getUnit()) {
                    case "celsius":
                        authoritativeAnswer = request.getValue();
                        break;
                    case "fahrenheit":
                        authoritativeAnswer = (request.getValue() - 32) * 5 / 9;
                        break;
                    case "kelvin":
                        authoritativeAnswer = request.getValue() - 273.15;
                        break;
                    case "rankine":
                        authoritativeAnswer = (request.getValue() - 491.67) * 5 / 9;
                        break;
                    default:
                        return new UnitConversionResponse(ResponseStatus.INVALID);
                }

                // Convert to target Temperature
                switch (request.getTarget()) {
                    case "celsius":
                        break;
                    case "fahrenheit":
                        authoritativeAnswer = (authoritativeAnswer * 9 / 5) + 32;
                        break;
                    case "kelvin":
                        authoritativeAnswer = authoritativeAnswer + 273.15;
                        break;
                    case "rankine":
                        authoritativeAnswer = (authoritativeAnswer * 9 / 5) + 491.67;
                        break;
                    default:
                        return new UnitConversionResponse(ResponseStatus.INVALID);
                }
                
                // round the authoritative answer to the tenths place
                authoritativeAnswer = roundToTenths(authoritativeAnswer);

                // round the student's response to the tenths place
                studentResponse = roundToTenths(studentResponse);

                // Check if the student's response is correct
                if (studentResponse == authoritativeAnswer) {
                    return new UnitConversionResponse(ResponseStatus.CORRECT);
                }

                return new UnitConversionResponse(ResponseStatus.INCORRECT);
                
            case "volume":
                double authoritativeAnswerVolume = request.getValue();
                double studentResponseVolume = request.getResponse();

                // Convert to target Volume
                // Convert to Liters first
                switch (request.getUnit()) {
                    case "liters":
                        break;
                    case "tablespoons":
                        authoritativeAnswerVolume = authoritativeAnswerVolume * 0.0147868;
                        break;
                    case "cubic-inches":
                        authoritativeAnswerVolume = authoritativeAnswerVolume * 0.0163871;
                        break;
                    case "cups":
                        authoritativeAnswerVolume = authoritativeAnswerVolume * 0.236588;
                        break;
                    case "cubic-feet":
                        authoritativeAnswerVolume = authoritativeAnswerVolume * 28.3168;
                        break;
                    case "gallons":
                        authoritativeAnswerVolume = authoritativeAnswerVolume * 3.78541;
                        break;
                    default:
                        return new UnitConversionResponse(ResponseStatus.INVALID);
                }

                // Convert to target Volume
                switch (request.getTarget()) {
                    case "liters":
                        break;
                    case "tablespoons":
                        authoritativeAnswerVolume = authoritativeAnswerVolume * 67.628;
                        break;
                    case "cubic-inches":
                        authoritativeAnswerVolume = authoritativeAnswerVolume * 61.0237;
                        break;
                    case "cups":
                        authoritativeAnswerVolume = authoritativeAnswerVolume * 4.22675;
                        break;
                    case "cubic-feet":
                        authoritativeAnswerVolume = authoritativeAnswerVolume * 0.0353147;
                        break;
                    case "gallons":
                        authoritativeAnswerVolume = authoritativeAnswerVolume * 0.264172;
                        break;
                    default:
                        return new UnitConversionResponse(ResponseStatus.INVALID);
                }

                // round the authoritative answer to the tenths place
                authoritativeAnswerVolume = roundToTenths(authoritativeAnswerVolume);

                // round the student's response to the tenths place
                studentResponseVolume = roundToTenths(studentResponseVolume);

                // Check if the student's response is correct
                if (studentResponseVolume == authoritativeAnswerVolume) {
                    return new UnitConversionResponse(ResponseStatus.CORRECT);
                }
                
                return new UnitConversionResponse(ResponseStatus.INCORRECT);
            
                default:
                break;
        }

        return new UnitConversionResponse(ResponseStatus.INVALID);
    }

    public static double roundToTenths(double value) {
        return Math.round(value * 10.0) / 10.0;
    }

}
