package com.rnr.domain;

import com.rnr.error.InvalidValue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class Money implements Comparable<Money> {

    private final BigDecimal amount;

    private static final int SCALE = 2;

    private Money(double anAmount) {
        this(anAmount, NegativeValuePolicy.FORBID_NEGATIVE);
    }

    private Money(double anAmount, NegativeValuePolicy negativeValuePolicy) {
        this(new BigDecimal(Double.toString(anAmount)), negativeValuePolicy);
    }

    private Money(BigDecimal anAmount) {
        this(anAmount, NegativeValuePolicy.FORBID_NEGATIVE);
    }

    private Money(BigDecimal anAmount, NegativeValuePolicy negativeValuePolicy) {
        negativeValuePolicy.validateSubstraction(anAmount);
        this.amount = truncate(anAmount, SCALE);
    }

    public static BigDecimal truncate(BigDecimal value, int scale) {
        return value.setScale(scale, RoundingMode.DOWN);
    }

    public static Money of(double amount) {
        return new Money(amount);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Money add(Money otherMoney) {
        checkNullMoney(otherMoney);
        return new Money(this.amount.add(otherMoney.amount).doubleValue());
    }

    public Money substract(Money money) {
        return substract(money, NegativeValuePolicy.FORBID_NEGATIVE);
    }

    public Money substract(Money money, NegativeValuePolicy negativeValuePolicy) {
        checkNullMoney(money);
        BigDecimal result = this.amount.subtract(money.amount);
        negativeValuePolicy.validateSubstraction(result);
        return new Money(result);
    }

    public Money multiply(double factor) {
        checkFactorNotNegative(factor);
        return new Money(this.amount.multiply(new BigDecimal(Double.toString(factor))));
    }

    public Money divide(double factor) {
        checkFactorNotNegative(factor);
        if(factor == 0) {
            throw new InvalidValue("Division by zero is not allowed");
        }
        BigDecimal result = this.amount.divide(BigDecimal.valueOf(factor), RoundingMode.DOWN);
        return new Money(result);
    }

    @Override
    public int compareTo(Money otherMoney) {
        checkNullMoney(otherMoney);
        return this.amount.compareTo(otherMoney.amount);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Money money)) return false;
        return Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }

    @Override
    public String toString() {
        return this.amount.toPlainString();
    }

    private static void checkNullMoney(Money otherMoney) {
        if(otherMoney == null) {
            throw new InvalidValue("Cannot operate with null Money");
        }
    }

    private static void checkFactorNotNegative(double factor) {
        if(factor < 0) {
            throw new InvalidValue("Factor cannot be negative");
        }
    }
}
