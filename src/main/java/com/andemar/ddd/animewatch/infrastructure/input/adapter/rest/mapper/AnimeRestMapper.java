package com.andemar.ddd.animewatch.infrastructure.input.adapter.rest.mapper;

import com.andemar.ddd.animewatch.domain.model.Anime;
import com.andemar.ddd.animewatch.domain.model.Genre;
import com.andemar.ddd.animewatch.domain.model.Title;
import com.andemar.ddd.animewatch.infrastructure.input.adapter.rest.model.RequestAnime;
import com.andemar.ddd.animewatch.infrastructure.input.adapter.rest.model.ResponseAnime;
import java.util.Collections;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {GenreRestMapper.class})
public interface AnimeRestMapper {

    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    @Mapping(target = "title", source = "title", qualifiedByName = "stringToTitle")
    @Mapping(target = "genres", source = "genreIds", qualifiedByName = "mapGenreIdsToGenres")
    Anime toDomain(RequestAnime requestAnime);

    @Mapping(target = "title", source = "title", qualifiedByName = "titleToString")
    @Mapping(target = "genres", source = "genres")
    ResponseAnime toResponseAnime(Anime anime);

    List<ResponseAnime> toResponseAnimeList(List<Anime> animeList);

    @Named("stringToTitle")
    default Title stringToTitle(String value) {
        return value != null ? Title.of(value) : null;
    }

    @Named("titleToString")
    default String titleToString(Title title) {
        return title != null ? title.getValue() : null;
    }

    @Named("mapGenreIdsToGenres")
    default List<Genre> mapGenreIdsToGenres(List<Long> genreIds) {
        if (genreIds == null) {
            return Collections.emptyList();
        }
        return genreIds.stream()
                .map(Genre::withId)
                .toList();
    }
}
