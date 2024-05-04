package edu.conversion.unitconversion.enums;

public enum ResponseStatus {
    INVALID(0), CORRECT(1), INCORRECT(2);

    private final int value;

    ResponseStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}