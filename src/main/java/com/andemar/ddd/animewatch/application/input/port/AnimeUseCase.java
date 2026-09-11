package com.andemar.ddd.animewatch.application.input.port;

import com.andemar.ddd.animewatch.domain.model.Anime;
import java.util.List;

public interface AnimeUseCase {
  List<Anime> getAllAnime();
  Anime createAnime(Anime anime);
  Anime updateAnime(Long id, Anime anime);
  void deleteAnime(Long id);
}
