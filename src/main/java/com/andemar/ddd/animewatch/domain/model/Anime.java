package com.andemar.ddd.animewatch.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Anime {

    private Long id;
    private Title title;
    private List<Genre> genres;

    public Anime() {}

    public static Anime create(String title) {
        Anime anime = new Anime();
        anime.title = Title.of(title);
        anime.genres = new ArrayList<>();
        return anime;
    }

    public static Anime reconstitute(Long id, String title, List<Genre> genres) {
        Anime anime = new Anime();
        anime.id = id;
        anime.title = Title.of(title);
        anime.genres = genres != null ? new ArrayList<>(genres) : new ArrayList<>();
        return anime;
    }

    public void changeTitle(String newTitle) {
        this.title = Title.of(newTitle);
    }

    public void addGenre(Genre genre) {
        Objects.requireNonNull(genre, "Genre must not be null");
        Objects.requireNonNull(genre.getId(), "Genre id must not be null");
        boolean alreadyAdded = genres.stream().anyMatch(g -> g.getId().equals(genre.getId()));
        if (!alreadyAdded) {
            genres.add(genre);
        }
    }

    public void removeGenre(Long genreId) {
        genres.removeIf(g -> g.getId().equals(genreId));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = Objects.requireNonNull(title, "Title must not be null");
    }

    public List<Genre> getGenres() {
        return Collections.unmodifiableList(genres);
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres != null ? new ArrayList<>(genres) : new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Anime other)) return false;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Anime{id=" + id + ", title='" + title + "', genres=" + genres + "}";
    }
}
