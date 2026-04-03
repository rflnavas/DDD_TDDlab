package com.rnr;

import com.rnr.domain.country.IsoCountryCode;
import com.rnr.error.EmptyValueException;
import com.rnr.error.InvalidValue;
import com.rnr.error.UnsupportedValue;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class IsoCodeCountryTest {

    public static final String ISO_COUNTRY_CODE_CANNOT_BE_EMPTY = "Iso Country code cannot be empty";

    @ParameterizedTest
    @MethodSource("invalidIsoCountryCodes")
    void shouldIsoNotCreatedWhenCodeIsNotValid(String invalidIsoCountryCode,
                                               Class<? extends RuntimeException> exception,
                                               String exceptionMessage){
        Assertions.assertThatThrownBy(() -> IsoCountryCode.of(invalidIsoCountryCode))
                .isInstanceOf(exception)
                .hasMessage(exceptionMessage);
    }

    static Stream<Arguments> invalidIsoCountryCodes() {
        return Stream.of(
                Arguments.of(null, EmptyValueException.class, ISO_COUNTRY_CODE_CANNOT_BE_EMPTY),
                Arguments.of("", EmptyValueException.class, ISO_COUNTRY_CODE_CANNOT_BE_EMPTY),
                Arguments.of("      ", EmptyValueException.class, ISO_COUNTRY_CODE_CANNOT_BE_EMPTY),
                Arguments.of("COPS", InvalidValue.class, "COPS is a not valid two-letter iso country code"),
                Arguments.of("X", InvalidValue.class, "X is a not valid two-letter iso country code"),
                Arguments.of("TK", UnsupportedValue.class, "Unsupported country for TK"));
    }

}
