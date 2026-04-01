package com.rnr.domain;

import com.rnr.error.InvalidValue;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class Percentage {

    private final BigDecimal value;

    private Percentage(double value) {
        if (value < 0 || value > 100) {
            throw new InvalidValue("Percentage must be between 0 and 1");
        }
        this.value = BigDecimal.valueOf(value/100.0).setScale(3, RoundingMode.DOWN);
    }

    public static Percentage of(double value) {
        return new Percentage(value);
    }

    public BigDecimal getValue() {
        return value;
    }
}
