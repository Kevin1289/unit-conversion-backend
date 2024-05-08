package edu.conversion.unitconversion.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseStatusTest {

    @Test
    public void testEnumValues() {
        assertEquals(0, ResponseStatus.INVALID.getValue());
        assertEquals(1, ResponseStatus.CORRECT.getValue());
        assertEquals(2, ResponseStatus.INCORRECT.getValue());
    }
}