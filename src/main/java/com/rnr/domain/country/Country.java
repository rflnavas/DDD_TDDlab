package com.rnr.domain.country;

import com.rnr.error.EmptyValueException;
import com.rnr.utils.StringUtility;

public class Country  {

    private final IsoCountryCode isoCountryCode;
    private final String name;

    private Country(IsoCountryCode isoCountryCode, String name) {
        if(isoCountryCode == null) {
          throw new EmptyValueException("isoCode cannot be empty");
        }
        if(StringUtility.isEmpty(name)) {
          throw new EmptyValueException("Name for country cannot be empty");
        }
        this.isoCountryCode = isoCountryCode;
        this.name = name;
    }

    public static Country of(String isoCountryCode, String name) {
        return new Country(IsoCountryCode.of(isoCountryCode), name);
    }

    public IsoCountryCode getIsoCountryCode() {
        return isoCountryCode;
    }

    public String getName() {
        return name;
    }
}
