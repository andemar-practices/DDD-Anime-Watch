package com.andemar.ddd.animewatch.domain.service;

import com.andemar.ddd.animewatch.application.input.port.AnimeUseCase;
import com.andemar.ddd.animewatch.domain.model.Anime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AnimeService implements AnimeUseCase {

  @Override
  public List<Anime> getAllAnime() {
    return List.of();
  }

  @Override
  public Anime createAnime(Anime anime) {
    return null;
  }

  @Override
  public Anime updateAnime(Long id, Anime anime) {
    return null;
  }

  @Override
  public void deleteAnime(Long id) {

  }
}
