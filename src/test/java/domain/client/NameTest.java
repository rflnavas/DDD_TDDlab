package domain.client;

import com.rnr.domain.client.FirstName;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class NameTest {

    @ParameterizedTest
    @MethodSource("invalidNames")
    void shouldFirstNamesNotBeCreatedWhenInvalidValues(String invalidName){
        Assertions.assertThatThrownBy(() -> FirstName.as(invalidName)).hasMessage(invalidName + " is not a valid name");
    }

    static Stream<String> invalidNames() {
        return Stream.of("", "   ",
                "a very very very very looooooooooooooooooooooooooooooong name");
    }
}
