package com.rnr;

import com.rnr.domain.Country;
import com.rnr.error.EmptyValueException;
import com.rnr.error.InvalidValue;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CountryTest {

    @Test
    void shouldCountryNotCreatedWhenEmptyIsoCode() {
        Assertions.assertThatThrownBy(() -> new Country("", "España"))
                .isInstanceOf(EmptyValueException.class)
                .hasMessage("isoCode cannot be empty");
    }

    @Test
    void shouldCountryNotCreatedWhenEmptyName() {
        Assertions.assertThatThrownBy(() -> new Country("ES", ""))
                .isInstanceOf(EmptyValueException.class)
                .hasMessage("Name for country cannot be empty");
    }

    @Test
    void shouldCountryNotCreatedWhenIsoCodeNotValid() {
        Assertions.assertThatThrownBy(() -> new Country("XXXX", "España"))
                .isInstanceOf(InvalidValue.class)
                .hasMessage("Non valid ISO Country Code");
    }

}
