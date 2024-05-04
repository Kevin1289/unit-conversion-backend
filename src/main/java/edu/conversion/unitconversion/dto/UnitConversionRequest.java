package edu.conversion.unitconversion.dto;

// input {type: string, value: float, unit: string, target: string, response: float }

public class UnitConversionRequest {
    private String type;
    private double value;
    private String unit;
    private String target;
    private double response;

    public UnitConversionRequest(String type, double value, String unit, String target, double response) {
        this.type = type;
        this.value = value;
        this.unit = unit;
        this.target = target;
        this.response = response;
    }
}
