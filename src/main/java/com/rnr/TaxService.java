package com.rnr;

import com.rnr.domain.Money;
import com.rnr.domain.Percentage;

public class TaxService {

    public Money calculateTax(Money money, String state) {
        Percentage taxRate = getTaxRateForState(state);
        return calculateTax(money, taxRate);
    }

    public Money calculateTax(Money money, Percentage percentage) {
        return money.multiply(percentage.getValue().doubleValue());
    }

    public Money calculateTotalWithTax(Money money, Percentage percentage) {
        Money tax = calculateTax(money, percentage);
        return money.add(tax);
    }

    private Percentage getTaxRateForState(String state) {
        return switch (state) {
            case "CA" -> Percentage.of(7.5);
            case "NY" -> Percentage.of(4);
            case "TX" -> Percentage.of(6.25);
            default -> Percentage.of(21); // Default tax rate
        };
    }
}
