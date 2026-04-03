package com.rnr;

import com.rnr.domain.country.Country;
import com.rnr.domain.country.IsoCountryCode;
import com.rnr.error.UnsupportedValue;
import com.rnr.validators.NationalIdValidator;
import com.rnr.validators.SpainNationalIdValidator;

import java.util.Arrays;

public enum NationalIdValidatorFactory {
    ES(IsoCountryCode.of("ES")) {
        @Override
        NationalIdValidator getNationalValidator() {
            return new SpainNationalIdValidator();
        }
    };

    abstract NationalIdValidator getNationalValidator();

    final IsoCountryCode isoCountryCode;

    NationalIdValidatorFactory(IsoCountryCode isoCountryCode){
        this.isoCountryCode = isoCountryCode;
    }

    public static NationalIdValidator forCountry(Country country){
        String isoCountryCodeValue = country.getIsoCountryCode().isoCode();
        NationalIdValidatorFactory nationalIdValidatorFactory = Arrays.stream(NationalIdValidatorFactory.values())
                .filter(v -> v.isoCountryCode.isoCode().equals(isoCountryCodeValue))
                .findFirst().orElseThrow(() -> new UnsupportedValue("No validation"));
        return nationalIdValidatorFactory.getNationalValidator();
    }
}
