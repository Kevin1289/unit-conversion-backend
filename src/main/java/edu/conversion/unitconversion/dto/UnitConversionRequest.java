package edu.conversion.unitconversion.dto;

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

    public String getType() {
        return type;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public String getTarget() {
        return target;
    }

    public double getResponse() {
        return response;
    }

}
