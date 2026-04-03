package com.rnr;

import com.rnr.domain.country.Country;
import com.rnr.error.EmptyValueException;
import com.rnr.error.InvalidValue;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CountryTest {

    @Test
    void shouldCountryNotCreatedWhenEmptyIsoCode() {
        Assertions.assertThatThrownBy(() -> Country.of(null, "España"))
                .isInstanceOf(EmptyValueException.class)
                .hasMessage("Iso Country code cannot be empty");
    }

    @Test
    void shouldCountryNotCreatedWhenEmptyName() {
        Assertions.assertThatThrownBy(() -> Country.of("ES", "   "))
                .isInstanceOf(EmptyValueException.class)
                .hasMessage("Name for country cannot be empty");
    }

    @Test
    void shouldCountryNotCreatedWhenIsoCodeNotValid() {
        Assertions.assertThatThrownBy(() -> Country.of("XXXX", "MyCountry"))
                .isInstanceOf(InvalidValue.class)
                .hasMessage("XXXX is a not valid two-letter iso country code");
    }

}
