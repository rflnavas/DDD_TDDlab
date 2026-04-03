package domain.client;

import com.rnr.domain.client.Name;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class NameTest {

    @ParameterizedTest
    @MethodSource("invalidNames")
    void shouldNamesNotBeCreatedWhenInvalidValues(String invalidName){
        Assertions.assertThatThrownBy(() -> Name.as(invalidName)).hasMessage(invalidName + " is not a valid name");
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

    static Stream<String> invalidNames() {
        return Stream.of("", "   ",
                "a very very very very looooooooooooooooooooooooooooooong name");
    }

    static Stream<String> validNames() {
        return Stream.of("Y", "Pi",
                "John II", "Nombre1 nombre2 nombre3 nombre4 nombre5");
    }
}
