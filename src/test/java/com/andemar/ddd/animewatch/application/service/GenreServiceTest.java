package com.andemar.ddd.animewatch.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.andemar.ddd.animewatch.application.output.port.GenreOutputPort;
import com.andemar.ddd.animewatch.domain.exception.GenreNotFoundException;
import com.andemar.ddd.animewatch.domain.model.Genre;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GenreServiceTest {

    @Mock
    private GenreOutputPort genreOutputPort;

    @InjectMocks
    private GenreService genreService;

    @Test
    void getAllGenres_shouldReturnList() {
        Genre genre = new Genre(1L, "Action");
        when(genreOutputPort.getAllGenres()).thenReturn(List.of(genre));

        List<Genre> result = genreService.getAllGenres();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Action");
    }

    @Test
    void getGenreById_shouldReturnGenre_whenFound() {
        Genre genre = new Genre(1L, "Action");
        when(genreOutputPort.findGenreById(1L)).thenReturn(Optional.of(genre));

        Genre result = genreService.getGenreById(1L);

        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void getGenreById_shouldThrow_whenNotFound() {
        when(genreOutputPort.findGenreById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> genreService.getGenreById(99L))
            .isInstanceOf(GenreNotFoundException.class)
            .hasMessageContaining("99");
    }

    @Test
    void createGenre_shouldSaveAndReturn() {
        Genre input = new Genre(null, "Action");
        Genre saved = new Genre(1L, "Action");
        when(genreOutputPort.saveGenre(any())).thenReturn(saved);

        Genre result = genreService.createGenre(input);

        assertThat(result.getId()).isEqualTo(1L);
        verify(genreOutputPort).saveGenre(input);
    }

    @Test
    void updateGenre_shouldThrow_whenNotFound() {
        when(genreOutputPort.existsGenreById(99L)).thenReturn(false);

        assertThatThrownBy(() -> genreService.updateGenre(99L, new Genre(null, "Action")))
            .isInstanceOf(GenreNotFoundException.class);
    }

    @Test
    void deleteGenre_shouldDelete_whenExists() {
        when(genreOutputPort.existsGenreById(1L)).thenReturn(true);

        genreService.deleteGenre(1L);

        verify(genreOutputPort).deleteGenre(1L);
    }

    @Test
    void deleteGenre_shouldThrow_whenNotFound() {
        when(genreOutputPort.existsGenreById(99L)).thenReturn(false);

        assertThatThrownBy(() -> genreService.deleteGenre(99L))
            .isInstanceOf(GenreNotFoundException.class);
    }
}
