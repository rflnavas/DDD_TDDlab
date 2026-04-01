package com.rnr.domain;

import com.rnr.EmptyValueException;
import com.rnr.utils.StringUtility;

public record Country (String isoCode, String name) {
  public Country {
      if(StringUtility.isEmpty(isoCode)) {
          throw new EmptyValueException("isoCode cannot be empty");
      }

  }
}
