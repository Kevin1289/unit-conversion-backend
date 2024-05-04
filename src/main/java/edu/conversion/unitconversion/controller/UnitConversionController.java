package edu.conversion.unitconversion.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.conversion.unitconversion.dto.UnitConversionResponse;
import edu.conversion.unitconversion.enums.ResponseStatus;

@RestController
public class UnitConversionController {

    /**
     * This method is a simple endpoint for unit conversion.
     */
    @GetMapping("/unitconversion")
    public UnitConversionResponse unitConversion() {
        return new UnitConversionResponse(ResponseStatus.INVALID);
    }

}
