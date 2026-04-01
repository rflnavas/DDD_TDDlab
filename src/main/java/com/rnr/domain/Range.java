package com.rnr.domain;

public interface Range <T extends Comparable<T>> {

    default boolean isValid(T min, T max) {
        return min.compareTo(max) <= 0;
    }

    default boolean contains(T value) {
        return value.compareTo(getMin()) >= 0 && value.compareTo(getMax()) <= 0;
    }

    T getMin();

    T getMax();
}
