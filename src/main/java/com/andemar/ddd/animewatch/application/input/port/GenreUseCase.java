package com.andemar.ddd.animewatch.application.input.port;

import com.andemar.ddd.animewatch.domain.model.Genre;
import java.util.List;

public interface GenreUseCase {
  List<Genre> getAllGenres();
  Genre getGenreById(Long id);
  Genre createGenre(Genre genre);
  Genre updateGenre(Long id, Genre genre);
  void deleteGenre(Long id);
}
