package domain.client;

import com.rnr.NationalIdValidatorFactory;
import com.rnr.domain.client.NationalId;
import com.rnr.domain.country.Country;
import com.rnr.validators.NationalIdValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class NationalIdTest {

    @Test
    void shouldValidateSpanishNationalIdWhenCountryIsSpain(){
        Country country = Country.of("ES", "Spain");
        NationalIdValidator nationalIdValidator = NationalIdValidatorFactory.forCountry(country);
        NationalId nationalId = NationalId.of("00000000R");
        boolean isValid = nationalIdValidator.validate(nationalId);
        Assertions.assertThat(isValid).isTrue();
    }
}
