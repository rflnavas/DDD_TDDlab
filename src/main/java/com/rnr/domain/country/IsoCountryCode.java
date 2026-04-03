package com.rnr.domain.country;

import com.rnr.error.EmptyValueException;
import com.rnr.error.InvalidValue;
import com.rnr.error.UnsupportedValue;
import com.rnr.utils.StringUtility;

import java.util.Set;

public record IsoCountryCode (String isoCode){

    static final Set<String> SUPPORTED_COUNTRIES = Set.of("ES", "FR", "DE", "IT", "US", "CA", "JP");

    //We cannot reduce visibility over the canonical constructor, unlike classes
    public IsoCountryCode {
        if(StringUtility.isEmpty(isoCode)) {
            throw new EmptyValueException("Iso Country code cannot be empty");
        }
        if(!isoCode.matches("[A-Z]{2}")){
            throw new InvalidValue(isoCode + " is a not valid two-letter iso country code");
        }
        if(!SUPPORTED_COUNTRIES.contains(isoCode)) {
            throw new UnsupportedValue("Unsupported country for " + isoCode);
        }
    }

    public static IsoCountryCode of(String code) {
        return new IsoCountryCode(code);
    }
}
