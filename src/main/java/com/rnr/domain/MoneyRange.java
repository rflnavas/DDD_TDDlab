package com.rnr.domain;

import com.rnr.error.InvalidRange;

public class MoneyRange implements Range<Money> {

    private final Money min;
    private final Money max;

    public MoneyRange(Money min, Money max) {
        if(!isValid(min, max)){
            throw new InvalidRange("Invalid Money Range: min should be less than or equal to max");
        }
        this.min = min;
        this.max = max;
    }

    @Override
    public Money getMin() {
        return min;
    }

    @Override
    public Money getMax() {
        return max;
    }
}
