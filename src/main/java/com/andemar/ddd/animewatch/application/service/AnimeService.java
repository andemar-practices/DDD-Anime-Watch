package com.andemar.ddd.animewatch.application.service;

import com.andemar.ddd.animewatch.application.input.port.AnimeUseCase;
import com.andemar.ddd.animewatch.application.output.port.AnimeOutputPort;
import com.andemar.ddd.animewatch.domain.exception.AnimeNotFoundException;
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
  public Anime getAnimeById(Long id) {
    return animeOutputPort.findAnimeById(id)
        .orElseThrow(() -> new AnimeNotFoundException(id));
  }

  @Override
  public Anime createAnime(Anime anime) {
    return animeOutputPort.saveAnime(anime);
  }

  @Override
  public Anime updateAnime(Long id, Anime anime) {
    if (!animeOutputPort.existsAnimeById(id)) {
      throw new AnimeNotFoundException(id);
    }
    anime.setId(id);
    return animeOutputPort.saveAnime(anime);
  }

  @Override
  public void deleteAnime(Long id) {
    if (!animeOutputPort.existsAnimeById(id)) {
      throw new AnimeNotFoundException(id);
    }
    animeOutputPort.deleteAnime(id);
  }
}
