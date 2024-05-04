package edu.conversion.unitconversion.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UnitConversionController {

    /**
     * This method is a simple endpoint for unit conversion.
     * Currently, it just prints "Hi" and returns "Unit Conversion".
     *
     * @return a string message
     */
    @GetMapping("/unitconversion")
    public String unitConversion() {
        System.out.println("Hi");
        return "Unit Conversion";
    }

}
