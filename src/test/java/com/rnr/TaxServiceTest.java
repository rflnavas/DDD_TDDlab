package com.rnr;

import com.rnr.domain.Money;
import com.rnr.domain.Percentage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaxServiceTest {

    @Test
    void testCalculateTax_CA() {
        TaxService taxService = new TaxService();
        var money = Money.of(100.00);
        var tax = taxService.calculateTax(money, "CA");
        assertEquals(Money.of(7.50).getAmount(), tax.getAmount());
    }

    @Test
    void testCalculateTotalWithTax() {
        TaxService taxService = new TaxService();
        var money = Money.of(200.00);
        var percentage = Percentage.of(21);
        var totalWithTax = taxService.calculateTotalWithTax(money, percentage);
        assertEquals(Money.of(242.00).getAmount(), totalWithTax.getAmount());
    }

}