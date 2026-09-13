package com.andemar.ddd.animewatch.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.andemar.ddd.animewatch.application.output.port.AnimeOutputPort;
import com.andemar.ddd.animewatch.domain.exception.AnimeNotFoundException;
import com.andemar.ddd.animewatch.domain.model.Anime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AnimeServiceTest {

    @Mock
    private AnimeOutputPort animeOutputPort;

    @InjectMocks
    private AnimeService animeService;

    @Test
    void getAllAnime_shouldReturnList() {
        Anime anime = Anime.reconstitute(1L, "Naruto", List.of());
        when(animeOutputPort.getAllAnime()).thenReturn(List.of(anime));

        List<Anime> result = animeService.getAllAnime();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle().getValue()).isEqualTo("Naruto");
    }

    @Test
    void getAnimeById_shouldReturnAnime_whenFound() {
        Anime anime = Anime.reconstitute(1L, "Naruto", List.of());
        when(animeOutputPort.findAnimeById(1L)).thenReturn(Optional.of(anime));

        Anime result = animeService.getAnimeById(1L);

        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void getAnimeById_shouldThrow_whenNotFound() {
        when(animeOutputPort.findAnimeById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> animeService.getAnimeById(99L))
            .isInstanceOf(AnimeNotFoundException.class)
            .hasMessageContaining("99");
    }

    @Test
    void createAnime_shouldSaveAndReturn() {
        Anime input = Anime.create("Bleach");
        Anime saved = Anime.reconstitute(1L, "Bleach", List.of());
        when(animeOutputPort.saveAnime(any())).thenReturn(saved);

        Anime result = animeService.createAnime(input);

        assertThat(result.getId()).isEqualTo(1L);
        verify(animeOutputPort).saveAnime(input);
    }

    @Test
    void updateAnime_shouldThrow_whenNotFound() {
        when(animeOutputPort.existsAnimeById(99L)).thenReturn(false);
        Anime anime = Anime.create("Test");

        assertThatThrownBy(() -> animeService.updateAnime(99L, anime))
            .isInstanceOf(AnimeNotFoundException.class);
    }

    @Test
    void updateAnime_shouldUpdateAndReturn() {
        Anime input = Anime.create("Bleach Updated");
        Anime saved = Anime.reconstitute(1L, "Bleach Updated", List.of());
        when(animeOutputPort.existsAnimeById(1L)).thenReturn(true);
        when(animeOutputPort.saveAnime(any())).thenReturn(saved);

        Anime result = animeService.updateAnime(1L, input);

        assertThat(result.getTitle().getValue()).isEqualTo("Bleach Updated");
    }

    @Test
    void deleteAnime_shouldThrow_whenNotFound() {
        when(animeOutputPort.existsAnimeById(99L)).thenReturn(false);

        assertThatThrownBy(() -> animeService.deleteAnime(99L))
            .isInstanceOf(AnimeNotFoundException.class);
    }

    @Test
    void deleteAnime_shouldDelete_whenExists() {
        when(animeOutputPort.existsAnimeById(1L)).thenReturn(true);

        animeService.deleteAnime(1L);

        verify(animeOutputPort).deleteAnime(1L);
    }
}
