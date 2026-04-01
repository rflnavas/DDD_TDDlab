package domain;

import com.rnr.domain.Money;
import com.rnr.domain.MoneyRange;
import com.rnr.error.InvalidRange;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class RangeTest {

    @Test
    void givenRange_whenMinOverMax_thenIsInvalidRange() {

        assertThatExceptionOfType(InvalidRange.class)
                .isThrownBy(() ->
                        new MoneyRange(Money.of(200), Money.of(100))
                )
                .withMessageContaining("Invalid Money Range: min should be less than or equal to max");
    }

    @Test
    void givenRange_whenValueIsInRange_thenSuccess() {
        MoneyRange range = new MoneyRange(Money.of(100), Money.of(200));
        assertThat(range.contains(Money.of(150))).isTrue();
    }

}
