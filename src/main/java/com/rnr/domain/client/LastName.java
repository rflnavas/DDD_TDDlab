package com.rnr.domain.client;

import com.rnr.domain.Name;

public class LastName extends Name {

    private LastName(String name) {
        this.name = name;
    }

    public static LastName as(String name) {
        return null;
    }
}
