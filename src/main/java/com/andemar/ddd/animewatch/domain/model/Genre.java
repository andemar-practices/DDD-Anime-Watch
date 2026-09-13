package com.andemar.ddd.animewatch.domain.model;

import java.util.Objects;

public class Genre {

    private Long id;
    private String name;

    public Genre() {}

    public Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Genre withId(Long id) {
        Genre genre = new Genre();
        genre.id = id;
        return genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre other)) return false;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Genre{id=" + id + ", name='" + name + "'}";
    }
}
