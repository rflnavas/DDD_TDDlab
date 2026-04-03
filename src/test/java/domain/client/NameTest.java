package domain.client;

import com.rnr.domain.client.Name;
import com.rnr.error.EmptyValueException;
import com.rnr.error.InvalidValue;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class NameTest {

    @ParameterizedTest
    @MethodSource("invalidNames")
    void shouldNamesNotBeCreatedWhenInvalidValues(String invalidName, Class<? extends RuntimeException> exception){
        Assertions.assertThatThrownBy(() -> Name.as(invalidName))
                .isInstanceOf(exception);
    }

    @ParameterizedTest
    @MethodSource("validNames")
    void shouldNamesSuccessfulWhenValues(String validName){
       // Assertions.assertThatCode(() -> Name.as(validName)).doesNotThrowAnyException();
        Name name = Name.as(validName);
        Assertions.assertThat(validName).isEqualTo(name.getName());
    }

    @Test
    void shouldNamesEqualsWhenValuesAreSame() {
       Name john = Name.as("John");
       Assertions.assertThat(john).isEqualTo(Name.as("John"));
    }

    static Stream<Arguments> invalidNames() {
        return Stream.of(Arguments.of("", EmptyValueException.class),
                Arguments.of("   ", EmptyValueException.class),
                Arguments.of("a veeeeery veeeery veeery veery extreeeemely looooooooooooooooooooooooooooooong name", InvalidValue.class));
    }

    static Stream<String> validNames() {
        return Stream.of("Y", "Pi",
                "John II", "Nombre1 nombre2 nombre3 nombre4 nombre5");
    }
}
