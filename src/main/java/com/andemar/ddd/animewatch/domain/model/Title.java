package com.andemar.ddd.animewatch.domain.model;

import java.util.Objects;

public final class Title {

    private final String value;

    private Title(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Title must not be blank");
        }
        if (value.length() > 255) {
            throw new IllegalArgumentException("Title must not exceed 255 characters");
        }
        this.value = value.trim();
    }

    public static Title of(String value) {
        return new Title(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Title other)) return false;
        return Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
