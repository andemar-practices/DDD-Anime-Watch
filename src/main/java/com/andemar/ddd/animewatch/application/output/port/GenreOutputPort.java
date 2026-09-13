package com.andemar.ddd.animewatch.application.output.port;

import com.andemar.ddd.animewatch.domain.model.Genre;
import java.util.List;
import java.util.Optional;

public interface GenreOutputPort {
  List<Genre> getAllGenres();
  Optional<Genre> findGenreById(Long id);
  Genre saveGenre(Genre genre);
  void deleteGenre(Long id);
  boolean existsGenreById(Long id);
}
