package edu.conversion.unitconversion.dto;

import edu.conversion.unitconversion.enums.ResponseStatus;

public class UnitConversionResponse {
    private ResponseStatus status;

    public UnitConversionResponse(ResponseStatus status) {
        this.status = status;
    }

    public UnitConversionResponse() {
        //TODO Auto-generated constructor stub
    }

    public ResponseStatus getStatus() {
        return status;
    }
}
