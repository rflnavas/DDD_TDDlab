package com.rnr;

import com.rnr.domain.Country;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CountryTest {

    @Test
    public void shouldCountryNotCreatedWhenEmptyIsoCode() {
        Assertions.assertThatThrownBy(() -> new Country("", "España"))
                .isInstanceOf(EmptyValueException.class)
                .hasMessage("isoCode cannot be empty");
    }

    @Test
    public void shouldCountryNotCreatedWhenEmptyName() {
        Assertions.assertThatThrownBy(() -> new Country("ES", "España"))
                .isInstanceOf(EmptyValueException.class)
                .hasMessage("Name for country cannot be empty");
    }
}
