package com.rnr.domain.client;

import com.rnr.error.EmptyValueException;
import com.rnr.error.InvalidValue;
import com.rnr.utils.StringUtility;

import java.util.Objects;

public class Name {

    public static final int MAX_NAME_LENGTH = 80;
    private final String name;

    private Name(String name) {
        if(StringUtility.isEmpty(name)) {
            throw new EmptyValueException("Name cannot be blank");
        }
        if(name.length() > MAX_NAME_LENGTH) {
            throw new InvalidValue(
                    String.format("%s has more than %d characters -> %d",
                            name, MAX_NAME_LENGTH, name.length()));
        }
        this.name = name;
    }

    public static Name as(String name) {
        return new Name(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Name name1)) return false;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
