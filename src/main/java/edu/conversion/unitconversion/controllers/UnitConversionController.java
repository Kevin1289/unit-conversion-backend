package edu.conversion.unitconversion.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UnitConversionController {

    @GetMapping("/unitconversion")
    public String unitConversion() {
        return "Unit Conversion";
    }

}
