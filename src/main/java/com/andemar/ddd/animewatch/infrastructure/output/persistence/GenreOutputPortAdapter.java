package com.andemar.ddd.animewatch.infrastructure.output.persistence;

import com.andemar.ddd.animewatch.application.output.port.GenreOutputPort;
import com.andemar.ddd.animewatch.domain.model.Genre;
import com.andemar.ddd.animewatch.infrastructure.output.persistence.mapper.GenreEntityMapper;
import com.andemar.ddd.animewatch.infrastructure.output.persistence.repository.GenreRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenreOutputPortAdapter implements GenreOutputPort {

  private final GenreRepository repository;
  private final GenreEntityMapper mapper;

  @Override
  public List<Genre> getAllGenres() {
    return mapper.toGenreList(repository.findAll());
  }

  @Override
  public Optional<Genre> findGenreById(Long id) {
    return repository.findById(id).map(mapper::toGenre);
  }

  @Override
  public Genre saveGenre(Genre genre) {
    return mapper.toGenre(repository.save(mapper.toGenreEntity(genre)));
  }

  @Override
  public void deleteGenre(Long id) {
    repository.deleteById(id);
  }

  @Override
  public boolean existsGenreById(Long id) {
    return repository.existsById(id);
  }
}
