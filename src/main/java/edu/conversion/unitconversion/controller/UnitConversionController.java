package edu.conversion.unitconversion.controller;

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
        // print The temperature from Celsius to Fahrenheit is: 33.8
        System.out.println("The temperature from Celsius to Fahrenheit is: " + 0);
        return "Unit Conversion";
    }

}
