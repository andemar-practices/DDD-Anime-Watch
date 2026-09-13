package com.andemar.ddd.animewatch.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class AnimeTest {

    @Test
    void shouldCreateAnimeWithTitle() {
        Anime anime = Anime.create("Naruto");
        assertThat(anime.getTitle().getValue()).isEqualTo("Naruto");
        assertThat(anime.getGenres()).isEmpty();
        assertThat(anime.getId()).isNull();
    }

    @Test
    void shouldRejectBlankTitle() {
        assertThatThrownBy(() -> Anime.create(""))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldAddGenreToAnime() {
        Anime anime = Anime.create("Naruto");
        Genre action = new Genre(1L, "Action");
        anime.addGenre(action);
        assertThat(anime.getGenres()).hasSize(1).contains(action);
    }

    @Test
    void shouldNotAddDuplicateGenre() {
        Anime anime = Anime.create("Naruto");
        Genre action = new Genre(1L, "Action");
        anime.addGenre(action);
        anime.addGenre(action);
        assertThat(anime.getGenres()).hasSize(1);
    }

    @Test
    void shouldRemoveGenreFromAnime() {
        Anime anime = Anime.create("Naruto");
        Genre action = new Genre(1L, "Action");
        anime.addGenre(action);
        anime.removeGenre(1L);
        assertThat(anime.getGenres()).isEmpty();
    }

    @Test
    void shouldChangeTitle() {
        Anime anime = Anime.create("Naruto");
        anime.changeTitle("Naruto Shippuden");
        assertThat(anime.getTitle().getValue()).isEqualTo("Naruto Shippuden");
    }

    @Test
    void shouldRejectNullGenre() {
        Anime anime = Anime.create("Naruto");
        assertThatThrownBy(() -> anime.addGenre(null))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    void shouldReconstitute() {
        Genre action = new Genre(1L, "Action");
        Anime anime = Anime.reconstitute(10L, "Bleach", List.of(action));
        assertThat(anime.getId()).isEqualTo(10L);
        assertThat(anime.getTitle().getValue()).isEqualTo("Bleach");
        assertThat(anime.getGenres()).hasSize(1);
    }

    @Test
    void shouldReturnUnmodifiableGenreList() {
        Anime anime = Anime.create("Naruto");
        assertThatThrownBy(() -> anime.getGenres().add(new Genre(1L, "Action")))
            .isInstanceOf(UnsupportedOperationException.class);
    }
}
