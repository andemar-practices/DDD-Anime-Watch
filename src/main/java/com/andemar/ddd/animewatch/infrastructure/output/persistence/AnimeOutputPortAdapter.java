package com.andemar.ddd.animewatch.infrastructure.output.persistence;

import com.andemar.ddd.animewatch.application.output.port.AnimeOutputPort;
import com.andemar.ddd.animewatch.domain.model.Anime;
import com.andemar.ddd.animewatch.infrastructure.output.persistence.mapper.AnimeEntityMapper;
import com.andemar.ddd.animewatch.infrastructure.output.persistence.repository.AnimeRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnimeOutputPortAdapter implements AnimeOutputPort {

  private final AnimeRepository repository;
  private final AnimeEntityMapper mapper;

  @Override
  public List<Anime> getAllAnime() {
    return mapper.toAnimeList(repository.findAll());
  }

  @Override
  public Optional<Anime> findAnimeById(Long id) {
    return repository.findById(id).map(mapper::toAnime);
  }

  @Override
  public Anime saveAnime(Anime anime) {
    return mapper.toAnime(repository.save(mapper.toAnimeEntity(anime)));
  }

  @Override
  public void deleteAnime(Long id) {
    repository.deleteById(id);
  }

  @Override
  public boolean existsAnimeById(Long id) {
    return repository.existsById(id);
  }
}
