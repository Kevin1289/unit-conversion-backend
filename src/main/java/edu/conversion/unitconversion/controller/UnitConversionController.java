package edu.conversion.unitconversion.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.conversion.unitconversion.dto.UnitConversionRequest;
import edu.conversion.unitconversion.dto.UnitConversionResponse;
import edu.conversion.unitconversion.service.UnitConversionService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class UnitConversionController {

    /**
     * This method is a simple endpoint for unit conversion.
     * 
     * @param type:     type of conversion
     * @param value:    value to convert
     * @param unit:     unit of value
     * @param target:   target unit
     * @param response: student response value
     * 
     * @return UnitConversionResponse
     */
    @Operation(summary = "Convert units", description = "This is a simple endpoint for unit conversion.")
    @GetMapping("/unitconversion")
    public UnitConversionResponse unitConversion(@RequestParam(value = "type", required = true) String type,
            @RequestParam(value = "value", required = true) double value,
            @RequestParam(value = "unit", required = true) String unit,
            @RequestParam(value = "target", required = true) String target,
            @RequestParam(value = "response", required = true) double response) {

        UnitConversionRequest request = new UnitConversionRequest(type, value, unit, target, response);
        return UnitConversionService.convert(request);
    }
}
