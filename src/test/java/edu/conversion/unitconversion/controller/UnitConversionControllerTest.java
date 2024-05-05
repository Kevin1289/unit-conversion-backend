package edu.conversion.unitconversion.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class UnitConversionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Test if the endpoint /unitconversion returns a status of 200 when given all required parameters
    @Test
    public void testUnitConversion() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/unitconversion")
                .param("type", "temperature")
                .param("value", "100")
                .param("unit", "celsius")
                .param("target", "fahrenheit")
                .param("response", "212"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    // Test if the endpoint /unitconversion returns a status of 400 when missing parameters
    @Test
    public void testUnitConversionMissingParams() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/unitconversion")
                .param("type", "temperature"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
