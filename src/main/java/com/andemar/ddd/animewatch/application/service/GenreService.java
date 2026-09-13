package com.andemar.ddd.animewatch.application.service;

import com.andemar.ddd.animewatch.application.input.port.GenreUseCase;
import com.andemar.ddd.animewatch.application.output.port.GenreOutputPort;
import com.andemar.ddd.animewatch.domain.exception.GenreNotFoundException;
import com.andemar.ddd.animewatch.domain.model.Genre;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreService implements GenreUseCase {

  private final GenreOutputPort genreOutputPort;

  @Override
  public List<Genre> getAllGenres() {
    return genreOutputPort.getAllGenres();
  }

  @Override
  public Genre getGenreById(Long id) {
    return genreOutputPort.findGenreById(id)
        .orElseThrow(() -> new GenreNotFoundException(id));
  }

  @Override
  public Genre createGenre(Genre genre) {
    return genreOutputPort.saveGenre(genre);
  }

  @Override
  public Genre updateGenre(Long id, Genre genre) {
    if (!genreOutputPort.existsGenreById(id)) {
      throw new GenreNotFoundException(id);
    }
    genre.setId(id);
    return genreOutputPort.saveGenre(genre);
  }

  @Override
  public void deleteGenre(Long id) {
    if (!genreOutputPort.existsGenreById(id)) {
      throw new GenreNotFoundException(id);
    }
    genreOutputPort.deleteGenre(id);
  }
}
