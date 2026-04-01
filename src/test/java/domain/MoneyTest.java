package domain;

import com.rnr.domain.Money;
import com.rnr.error.InvalidValue;
import com.rnr.error.SubstractionNotAllowed;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.assertj.core.api.Assertions.*;

class MoneyTest {

    static final String FACTOR_CANNOT_BE_NEGATIVE = "Factor cannot be negative";

    @Test
    void givenDoubleWithLotsOfDecimalHalfUpper_whenIsMoney_thenOnlyTwoDecimals() {
        Money money = Money.of(999.99999999);
        assertEquals(Money.of(999.99), money);
    }

    @Test
    void givenDoubleWithLotsOfDecimalHalfLower_whenIsMoney_thenOnlyTwoDecimals() {
        Money money = Money.of(1000.2555555);
        assertEquals(Money.of(1000.25), money);
    }

    @Test
    void givenSameAmount_whenComparingMoney_thenTheyAreEqual() {
        Money money = Money.of(100);
        assertEquals(Money.of(100), money);
    }

    @Test
    void givenDifferentAmounts_whenComparingMoney_thenTheyAreNotEqual() {
        Money money = Money.of(25);
        assertNotEquals(Money.of(50), money);
    }

    @Test
    void givenTwoMoney_whenSubstracting_thenCorrectResult() {
        Money money1 = Money.of(200);
        Money money2 = Money.of(50);
        Money result = money1.substract(money2);
        assertEquals(Money.of(150), result);
    }

    @Test
    void givenTwoMoney_whenSubstractingNotAllowingNegativeValues_thenError() {
        Money money1 = Money.of(1000);
        Money money2 = Money.of(1333.33);
        assertThatExceptionOfType(SubstractionNotAllowed.class)
                .isThrownBy(() ->
            money1.substract(money2))
            .withMessageContaining("Resulting amount cannot be negative")
            .satisfies( ex ->
                assertThat(ex.getQuantity()).isEqualTo(-333.33)
            );
    }

    @Test
    void givenTwoMoney_whenSubstractingAllowingNegativeValues_thenCorrectResult() {
        Money money1 = Money.of(1000);
        Money money2 = Money.of(1333.33);
        Money result = money1.substract(money2, com.rnr.domain.NegativeValuePolicy.ALLOW_NEGATIVE);
        assertEquals(Money.of(-333.33), result);
    }

    @Test
    void givenMoney_whenMultiplyingByFactor_thenCorrectResult() {
        Money money = Money.of(250);
        Money result = money.multiply(0.21);
        assertEquals(Money.of(52.5), result);
    }

    @Test
    void givenMoney_whenMultiplyingByNegativeFactor_thenError() {
        Money money = Money.of(250);
        assertThatExceptionOfType(InvalidValue.class)
                .isThrownBy(() ->
                        money.multiply(-2))
                .withMessageContaining(FACTOR_CANNOT_BE_NEGATIVE);
    }

    @Test
    void givenMoney_whenDividingByFactor_thenCorrectResult() {
        Money money = Money.of(334);
        Money result = money.divide(3);
        assertEquals(Money.of(111.33), result);
    }

    @Test
    void givenMoney_whenDividingByZeroFactor_thenError() {
        Money money = Money.of(333);
        assertThatExceptionOfType(InvalidValue.class)
                .isThrownBy(() ->
                        money.divide(0))
                .withMessageContaining("Division by zero is not allowed");
    }

    @Test
    void givenMoney_whenDividingByNegativeFactor_thenError() {
        Money money = Money.of(333);
        assertThatExceptionOfType(InvalidValue.class)
                .isThrownBy(() ->
                        money.divide(-3))
                .withMessageContaining(FACTOR_CANNOT_BE_NEGATIVE);
    }
}
