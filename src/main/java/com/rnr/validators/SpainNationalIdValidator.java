package com.rnr.validators;

import com.rnr.domain.client.NationalId;

public class SpainNationalIdValidator implements NationalIdValidator{
    @Override
    public boolean validate(NationalId nationalId) {
        return false;
    }
}
