package edu.conversion.unitconversion.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import edu.conversion.unitconversion.dto.UnitConversionRequest;
import edu.conversion.unitconversion.dto.UnitConversionResponse;
import edu.conversion.unitconversion.enums.ResponseStatus;

public class UnitConversionServiceTest {

    @Test
    public void testConstructor() {
        UnitConversionService service = new UnitConversionService();
        assertNotNull(service);
    }

    @Test
    public void testValidTemperatureConversion() {
        UnitConversionRequest request = new UnitConversionRequest("temperature", 32.0, "fahrenheit", "celsius", 0.0);
        UnitConversionResponse response = UnitConversionService.convert(request);
        assertEquals(ResponseStatus.CORRECT, response.getStatus());
    }

    @Test
    public void testInvalidUnitTemperatureConversion() {
        UnitConversionRequest request = new UnitConversionRequest("temperature", 32.0, "invalidUnit", "celsius", 0.0);
        UnitConversionResponse response = UnitConversionService.convert(request);
        assertEquals(ResponseStatus.INVALID, response.getStatus());
    }

    @Test
    public void testInvalidTargetTemperatureConversion() {
        UnitConversionRequest request = new UnitConversionRequest("temperature", 32.0, "fahrenheit", "invalidUnit",
                0.0);
        UnitConversionResponse response = UnitConversionService.convert(request);
        assertEquals(ResponseStatus.INVALID, response.getStatus());
    }

    @Test
    public void testValidVolumeConversion() {
        UnitConversionRequest request = new UnitConversionRequest("volume", 1.0, "liters", "gallons", 0.264172);
        UnitConversionResponse response = UnitConversionService.convert(request);
        assertEquals(ResponseStatus.CORRECT, response.getStatus());
    }

    @Test
    public void testInvalidTargetVolumeConversion() {
        UnitConversionRequest request = new UnitConversionRequest("volume", 1.0, "liters", "invalidUnit", 0.264172);
        UnitConversionResponse response = UnitConversionService.convert(request);
        assertEquals(ResponseStatus.INVALID, response.getStatus());
    }

    @Test
    public void testInvalidUnitVolumeConversion() {
        UnitConversionRequest request = new UnitConversionRequest("volume", 1.0, "invalidUnit", "gallons", 0.264172);
        UnitConversionResponse response = UnitConversionService.convert(request);
        assertEquals(ResponseStatus.INVALID, response.getStatus());
    }

    @Test
    public void testIncorrectTemperatureConversion() {
        UnitConversionRequest request = new UnitConversionRequest("temperature", 32.0, "fahrenheit", "celsius", 100.0);
        UnitConversionResponse response = UnitConversionService.convert(request);
        assertEquals(ResponseStatus.INCORRECT, response.getStatus());
    }

    @Test
    public void testIncorrectVolumeConversion() {
        UnitConversionRequest request = new UnitConversionRequest("volume", 1.0, "liters", "gallons", 1.0);
        UnitConversionResponse response = UnitConversionService.convert(request);
        assertEquals(ResponseStatus.INCORRECT, response.getStatus());
    }

    @Test
    public void testRoundingToTenths() {
        double roundedValue = UnitConversionService.roundToTenths(3.56789);
        assertEquals(3.6, roundedValue);
    }

    // Test for all branches in the convert method
    @Test
    public void testInvalidTypeConversion() {
        UnitConversionRequest request = new UnitConversionRequest("invalidType", 1.0, "liters", "gallons", 1.0);
        UnitConversionResponse response = UnitConversionService.convert(request);
        assertEquals(ResponseStatus.INVALID, response.getStatus());
    }

    @Test
    public void testInvalidUnitConversion() {
        UnitConversionRequest request = new UnitConversionRequest("temperature", 1.0, "invalidUnit", "gallons", 1.0);
        UnitConversionResponse response = UnitConversionService.convert(request);
        assertEquals(ResponseStatus.INVALID, response.getStatus());
    }

    @Test
    public void testCorrectTemperatureConversion() {
        UnitConversionRequest request = new UnitConversionRequest("temperature", 32.0, "fahrenheit", "celsius", 0.0);
        UnitConversionResponse response = UnitConversionService.convert(request);
        assertEquals(ResponseStatus.CORRECT, response.getStatus());
    }

    @Test
    public void testCorrectVolumeConversion() {
        UnitConversionRequest request = new UnitConversionRequest("volume", 1.0, "liters", "gallons", 0.264172);
        UnitConversionResponse response = UnitConversionService.convert(request);
        assertEquals(ResponseStatus.CORRECT, response.getStatus());
    }

    // Test for conversion for each temperature unit

    @Test
    public void testCelsiusToFahrenheit() {
        UnitConversionRequest request = new UnitConversionRequest("temperature", 0.0, "celsius", "fahrenheit", 32.0);
        UnitConversionResponse response = UnitConversionService.convert(request);
        assertEquals(ResponseStatus.CORRECT, response.getStatus());
    }

    @Test
    public void testFarhenheitToKelvin() {
        UnitConversionRequest request = new UnitConversionRequest("temperature", 32.0, "fahrenheit", "kelvin", 273.15);
        UnitConversionResponse response = UnitConversionService.convert(request);
        assertEquals(ResponseStatus.CORRECT, response.getStatus());
    }

    @Test
    public void testKelvinToRankine() {
        UnitConversionRequest request = new UnitConversionRequest("temperature", 273.15, "kelvin", "rankine", 491.67);
        UnitConversionResponse response = UnitConversionService.convert(request);
        assertEquals(ResponseStatus.CORRECT, response.getStatus());
    }

    @Test
    public void testRankineToCelsius() {
        UnitConversionRequest request = new UnitConversionRequest("temperature", 491.67, "rankine", "celsius", 0.0);
        UnitConversionResponse response = UnitConversionService.convert(request);
        assertEquals(ResponseStatus.CORRECT, response.getStatus());
    }

    // Test for conversion for each volume unit

    @Test
    public void testLitersToTablespoons() {
        UnitConversionRequest request = new UnitConversionRequest("volume", 1.0, "liters", "tablespoons", 67.628);
        UnitConversionResponse response = UnitConversionService.convert(request);
        assertEquals(ResponseStatus.CORRECT, response.getStatus());
    }

    @Test
    public void testTablespoonsToCubicInches() {
        UnitConversionRequest request = new UnitConversionRequest("volume", 1.0, "tablespoons", "cubic-inches",
                0.902344);
        UnitConversionResponse response = UnitConversionService.convert(request);
        assertEquals(ResponseStatus.CORRECT, response.getStatus());
    }

    @Test
    public void testCubicInchesToCups() {
        UnitConversionRequest request = new UnitConversionRequest("volume", 1.0, "cubic-inches", "cups", 0.0692641);
        UnitConversionResponse response = UnitConversionService.convert(request);
        assertEquals(ResponseStatus.CORRECT, response.getStatus());
    }

    @Test
    public void testCupsToCubicFeet() {
        UnitConversionRequest request = new UnitConversionRequest("volume", 1.0, "cups", "cubic-feet", 0.00835503);
        UnitConversionResponse response = UnitConversionService.convert(request);
        assertEquals(ResponseStatus.CORRECT, response.getStatus());
    }

    @Test
    public void testCubicFeetToGallons() {
        UnitConversionRequest request = new UnitConversionRequest("volume", 1.0, "cubic-feet", "gallons", 7.48052);
        UnitConversionResponse response = UnitConversionService.convert(request);
        assertEquals(ResponseStatus.CORRECT, response.getStatus());
    }

    @Test
    public void testGallonsToLiters() {
        UnitConversionRequest request = new UnitConversionRequest("volume", 1.0, "gallons", "liters", 3.78541);
        UnitConversionResponse response = UnitConversionService.convert(request);
        assertEquals(ResponseStatus.CORRECT, response.getStatus());
    }
}
