package edu.conversion.unitconversion.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import edu.conversion.unitconversion.dto.UnitConversionRequest;
import edu.conversion.unitconversion.dto.UnitConversionResponse;
import edu.conversion.unitconversion.enums.ResponseStatus;

import com.digidemic.unitof.UnitOf;

public class UnitConversionService {

    private static Map<String, List<String>> typeToUnits = new HashMap<>();

    static {
        typeToUnits.put("temperature", Arrays.asList("kelvin", "celsius", "fahrenheit", "rankine"));
        typeToUnits.put("volume", Arrays.asList("liters", "tablespoons", "cubic-inches", "cups", "cubic-feet", "gallons"));
        // Add more types and their units as needed
    }
    
    // public UnitConversionService() {
    //     typeToUnits = new HashMap<>();
    //     typeToUnits.put("temperature", Arrays.asList("kelvin", "celsius", "fahrenheit", "rankine"));
    //     typeToUnits.put("volume", Arrays.asList("liters", "tablespoons", "cubic-inches", "cups", "cubic-feet", "gallons"));
    //     // Add more types and their units as needed
    // }

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
                UnitOf.Temperature temperature = new UnitOf.Temperature();
                double authoritativeAnswer = 0.0;
                double studentResponse = request.getResponse();

                // Convert to target Temperature

                switch (request.getUnit()) {
                    case "celsius":
                        temperature.fromCelsius(request.getValue());
                        break;
                    case "fahrenheit":
                        temperature.fromFahrenheit(request.getValue());
                        break;
                    case "kelvin":
                        temperature.fromKelvin(request.getValue());
                        break;
                    case "rankine":
                        temperature.fromRankine(request.getValue());
                        break;
                    default:
                        return new UnitConversionResponse(ResponseStatus.INVALID);
                }

                switch (request.getTarget()) {
                    case "celsius":
                        authoritativeAnswer = temperature.toCelsius();
                        break;
                    case "fahrenheit":
                        authoritativeAnswer = temperature.toFahrenheit();
                        break;
                    case "kelvin":
                        authoritativeAnswer = temperature.toKelvin();
                        break;
                    case "rankine":
                        authoritativeAnswer = temperature.toRankine();
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
                UnitOf.Volume volume = new UnitOf.Volume();
                double authoritativeAnswerVolume = 0.0;
                double studentResponseVolume = request.getResponse();

                // Convert to target Volume

                switch (request.getUnit()) {
                    case "liters":
                        volume.fromLiters(request.getValue());
                        break;
                    case "tablespoons":
                        volume.fromTablespoonsUS(request.getValue());
                        break;
                    case "cubic-inches":
                        volume.fromCubicInches(request.getValue());
                        break;
                    case "cups":
                        volume.fromCupsUS(request.getValue());
                        break;
                    case "cubic-feet":
                        volume.fromCubicFeet(request.getValue());
                        break;
                    case "gallons":
                        volume.fromGallonsUS(request.getValue());
                        break;
                    default:
                        return new UnitConversionResponse(ResponseStatus.INVALID);
                }

                switch (request.getTarget()) {
                    case "liters":
                        authoritativeAnswerVolume = volume.toLiters();
                        break;
                    case "tablespoons":
                        authoritativeAnswerVolume = volume.toTablespoonsUS();
                        break;
                    case "cubic-inches":
                        authoritativeAnswerVolume = volume.toCubicInches();
                        break;
                    case "cups":
                        authoritativeAnswerVolume = volume.toCupsUS();
                        break;
                    case "cubic-feet":
                        authoritativeAnswerVolume = volume.toCubicFeet();
                        break;
                    case "gallons":
                        authoritativeAnswerVolume = volume.toGallonsUS();
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
