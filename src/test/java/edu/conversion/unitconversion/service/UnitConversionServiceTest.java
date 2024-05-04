package edu.conversion.unitconversion.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import edu.conversion.unitconversion.dto.UnitConversionRequest;
import edu.conversion.unitconversion.dto.UnitConversionResponse;
import edu.conversion.unitconversion.enums.ResponseStatus;
 
public class UnitConversionServiceTest {

    // Test case for when type is valid meaning is "volume" or "temperature"
    @Test
    public void testValidType() {
        UnitConversionRequest request = new UnitConversionRequest("temperature", 84.2 , "fahrenheit", "rankine", 543.94);
        UnitConversionResponse response = UnitConversionService.convert(request);
        assertEquals(ResponseStatus.CORRECT, response.getStatus());
    }
    
    // Test case for when type is invalid meaning not "volume" or"temperature"
    @Test
    public void testInvalidType() {
        UnitConversionRequest request = new UnitConversionRequest("invalid", 84.2 , "fahrenheit", "rankine", 543.94);
        UnitConversionResponse response = UnitConversionService.convert(request);
        assertEquals(ResponseStatus.INVALID, response.getStatus());
    }

    // Test case for when type is "temperature" and unit is invalid or not in the list
    @Test
    public void testInvalidTemperatureUnit() {
        UnitConversionRequest request = new UnitConversionRequest("temperature", 136.1, "dog", "celsius", 45.32);
        UnitConversionResponse response = UnitConversionService.convert(request);
        assertEquals(ResponseStatus.INVALID, response.getStatus());
    }

    // Test case for when type is "volume" and unit is invalid or not in the list
    @Test
    public void testInvalidVolumeUnit() {
        UnitConversionRequest request = new UnitConversionRequest("volume", 1.0, "celsius", "gallons", 0.264172);
        UnitConversionResponse response = UnitConversionService.convert(request);
        assertEquals(ResponseStatus.INVALID, response.getStatus());
    }

    // Test case for when the answer is correct
    @Test
    public void testCorrectAnswer() {
        UnitConversionRequest request = new UnitConversionRequest("temperature", 1.0, "celsius", "fahrenheit", 33.8);
        UnitConversionResponse response = UnitConversionService.convert(request);
        assertEquals(ResponseStatus.CORRECT, response.getStatus());
    }

    // Test case for when the answer is correct after rounding student's response to the tenths place
    @Test
    public void testCorrectAnswerRoundedStudent() {
        UnitConversionRequest request = new UnitConversionRequest("temperature", 5, "celsius", "fahrenheit", 41.02);
        UnitConversionResponse response = UnitConversionService.convert(request);
        assertEquals(ResponseStatus.CORRECT, response.getStatus());
    }

    // Test case for when the answer is correct after rounding authoritative answer to the tenths place
    @Test
    public void testCorrectAnswerRoundedAuth() {
        UnitConversionRequest request = new UnitConversionRequest("temperature", 317.33, "celsius", "fahrenheit", 603.2);
        UnitConversionResponse response = UnitConversionService.convert(request);
        assertEquals(ResponseStatus.CORRECT, response.getStatus());
    }

    // Test case for when the answer is correct after rounding both student's response and authoritative answer to the tenths place
    @Test
    public void testCorrectAnswerRoundedBoth() {
        UnitConversionRequest request = new UnitConversionRequest("temperature", 317.33, "celsius", "fahrenheit", 603.211);
        UnitConversionResponse response = UnitConversionService.convert(request);
        assertEquals(ResponseStatus.CORRECT, response.getStatus());
    }

    // Test case for rounding is needed
    @Test
    public void testRoundToTenths() {
        double rounded = UnitConversionService.roundToTenths(123.456);
        assertEquals(123.5, rounded);
    }

    // Test case for rounding is not needed
    @Test
    public void testNoRoundToTenths() {
        double rounded = UnitConversionService.roundToTenths(123.4);
        assertEquals(123.4, rounded);
    }
}
