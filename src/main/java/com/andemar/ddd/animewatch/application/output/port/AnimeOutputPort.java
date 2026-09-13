package com.andemar.ddd.animewatch.application.output.port;

import com.andemar.ddd.animewatch.domain.model.Anime;
import java.util.List;
import java.util.Optional;

public interface AnimeOutputPort {
  List<Anime> getAllAnime();
  Optional<Anime> findAnimeById(Long id);
  Anime saveAnime(Anime anime);
  void deleteAnime(Long id);
  boolean existsAnimeById(Long id);
}
