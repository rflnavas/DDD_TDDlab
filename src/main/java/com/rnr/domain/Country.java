package com.rnr.domain;

import com.rnr.error.EmptyValueException;
import com.rnr.utils.StringUtility;

public record Country (String isoCode, String name) {
  public Country {
      if(StringUtility.isEmpty(isoCode)) {
          throw new EmptyValueException("isoCode cannot be empty");
      }
      if(StringUtility.isEmpty(name)) {
          throw new EmptyValueException("Name for country cannot be empty");
      }

  }
}
