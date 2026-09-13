package com.andemar.ddd.animewatch.infrastructure.input.adapter.rest.impl;

import static com.andemar.ddd.animewatch.infrastructure.utils.Utils.mapResponse;

import com.andemar.ddd.animewatch.application.input.port.AnimeUseCase;
import com.andemar.ddd.animewatch.domain.model.Anime;
import com.andemar.ddd.animewatch.infrastructure.input.adapter.rest.mapper.AnimeRestMapper;
import com.andemar.ddd.animewatch.infrastructure.input.adapter.rest.model.RequestAnime;
import com.andemar.ddd.animewatch.infrastructure.input.adapter.rest.model.ResponseAnime;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/anime")
@RequiredArgsConstructor
public class AnimeController {

  private final AnimeUseCase animeUseCase;
  private final AnimeRestMapper animeRestMapper;

  @GetMapping
  public ResponseEntity<List<ResponseAnime>> getAllAnime() {
    List<ResponseAnime> responseAnimeList = animeRestMapper.toResponseAnimeList(animeUseCase.getAllAnime());
    return mapResponse(responseAnimeList, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseAnime> getAnimeById(@PathVariable Long id) {
    ResponseAnime responseAnime = animeRestMapper.toResponseAnime(animeUseCase.getAnimeById(id));
    return mapResponse(responseAnime, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<ResponseAnime> createAnime(@Valid @RequestBody RequestAnime requestAnime) {
    Anime anime = animeRestMapper.toDomain(requestAnime);
    ResponseAnime responseAnime = animeRestMapper.toResponseAnime(animeUseCase.createAnime(anime));
    return mapResponse(responseAnime, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseAnime> updateAnime(@PathVariable Long id, @Valid @RequestBody RequestAnime requestAnime) {
    Anime anime = animeRestMapper.toDomain(requestAnime);
    ResponseAnime responseAnime = animeRestMapper.toResponseAnime(animeUseCase.updateAnime(id, anime));
    return mapResponse(responseAnime, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deleteAnime(@PathVariable Long id) {
    animeUseCase.deleteAnime(id);
  }
}
