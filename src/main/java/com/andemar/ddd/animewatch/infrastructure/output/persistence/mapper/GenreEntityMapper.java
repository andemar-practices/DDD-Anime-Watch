package com.andemar.ddd.animewatch.infrastructure.output.persistence.mapper;

import com.andemar.ddd.animewatch.domain.model.Genre;
import com.andemar.ddd.animewatch.infrastructure.output.persistence.entity.GenreEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreEntityMapper {
    Genre toGenre(GenreEntity genreEntity);
    GenreEntity toGenreEntity(Genre genre);
    List<Genre> toGenreList(List<GenreEntity> genreEntities);
    List<GenreEntity> toGenreEntityList(List<Genre> genres);
}
