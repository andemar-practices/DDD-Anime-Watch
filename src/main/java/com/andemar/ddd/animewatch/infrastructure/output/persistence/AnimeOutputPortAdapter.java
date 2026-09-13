package com.andemar.ddd.animewatch.infrastructure.output.persistence;

import com.andemar.ddd.animewatch.application.output.port.AnimeOutputPort;
import com.andemar.ddd.animewatch.domain.model.Anime;
import com.andemar.ddd.animewatch.infrastructure.output.persistence.entity.AnimeEntity;
import com.andemar.ddd.animewatch.infrastructure.output.persistence.mapper.AnimeEntityMapper;
import com.andemar.ddd.animewatch.infrastructure.output.persistence.repository.AnimeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnimeOutputPortAdapter implements AnimeOutputPort {

  private final AnimeRepository repository;
  private final AnimeEntityMapper mapper;

  @Override
  public List<Anime> getAllAnime() {
    List<AnimeEntity> all = repository.findAll();
    return mapper.toAnimeList(all);
  }

  @Override
  public Anime createAnime(Anime anime) {
    AnimeEntity animeEntity = mapper.toAnimeEntity(anime);
    AnimeEntity save = repository.save(animeEntity);
    return mapper.toAnime(save);
  }

  @Override
  public Anime updateAnime(Long id, Anime anime) {
    AnimeEntity animeEntity = mapper.toAnimeEntity(anime);
    animeEntity.setId(id);
    return mapper.toAnime(repository.save(animeEntity));
  }

  @Override
  public void deleteAnime(Long id) {
    repository.deleteById(id);
  }
}
