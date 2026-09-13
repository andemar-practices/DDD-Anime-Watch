package com.andemar.ddd.animewatch.domain.service;

import com.andemar.ddd.animewatch.application.input.port.AnimeUseCase;
import com.andemar.ddd.animewatch.application.output.port.AnimeOutputPort;
import com.andemar.ddd.animewatch.domain.model.Anime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimeService implements AnimeUseCase {

  private final AnimeOutputPort animeOutputPort;

  @Override
  public List<Anime> getAllAnime() {
    return animeOutputPort.getAllAnime();
  }

  @Override
  public Anime createAnime(Anime anime) {
    return animeOutputPort.createAnime(anime);
  }

  @Override
  public Anime updateAnime(Long id, Anime anime) {
    return animeOutputPort.updateAnime(id, anime);
  }

  @Override
  public void deleteAnime(Long id) {
    animeOutputPort.deleteAnime(id);
  }
}
