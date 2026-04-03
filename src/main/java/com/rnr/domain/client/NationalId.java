package com.rnr.domain.client;

public class NationalId {

    private final String nationalId;

    private NationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getNationalId() {
        return nationalId;
    }

    public static NationalId of(String nationalId) {
        return new NationalId(nationalId);
    }

}
