package com.rnr.domain.client;

public class Name {

    private String name;
    private Name(String name) {
        this.name = name;
    }

    public static Name as(String name) {
        return new Name(name);
    }

    public String getName() {
        return name;
    }

}
