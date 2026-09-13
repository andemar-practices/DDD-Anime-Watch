package com.andemar.ddd.animewatch.infrastructure.input.adapter.rest.impl;

import static com.andemar.ddd.animewatch.infrastructure.utils.Utils.mapResponse;

import com.andemar.ddd.animewatch.application.input.port.GenreUseCase;
import com.andemar.ddd.animewatch.domain.model.Genre;
import com.andemar.ddd.animewatch.infrastructure.input.adapter.rest.mapper.GenreRestMapper;
import com.andemar.ddd.animewatch.infrastructure.input.adapter.rest.model.RequestGenre;
import com.andemar.ddd.animewatch.infrastructure.input.adapter.rest.model.ResponseGenre;
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
@RequestMapping("/api/v1/genres")
@RequiredArgsConstructor
public class GenreController {

  private final GenreUseCase genreUseCase;
  private final GenreRestMapper genreRestMapper;

  @GetMapping
  public ResponseEntity<List<ResponseGenre>> getAllGenres() {
    List<ResponseGenre> genres = genreRestMapper.toResponseGenreList(genreUseCase.getAllGenres());
    return mapResponse(genres, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseGenre> getGenreById(@PathVariable Long id) {
    ResponseGenre genre = genreRestMapper.toResponseGenre(genreUseCase.getGenreById(id));
    return mapResponse(genre, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<ResponseGenre> createGenre(@Valid @RequestBody RequestGenre requestGenre) {
    Genre domain = genreRestMapper.toDomain(requestGenre);
    Genre genre = genreUseCase.createGenre(domain);
    return mapResponse(genreRestMapper.toResponseGenre(genre), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseGenre> updateGenre(@PathVariable Long id, @Valid @RequestBody RequestGenre requestGenre) {
    Genre domain = genreRestMapper.toDomain(requestGenre);
    Genre genre = genreUseCase.updateGenre(id, domain);
    return mapResponse(genreRestMapper.toResponseGenre(genre), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deleteGenre(@PathVariable Long id) {
    genreUseCase.deleteGenre(id);
  }
}
