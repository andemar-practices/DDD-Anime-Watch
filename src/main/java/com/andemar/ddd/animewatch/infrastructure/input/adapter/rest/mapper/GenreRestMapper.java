package com.andemar.ddd.animewatch.infrastructure.input.adapter.rest.mapper;

import com.andemar.ddd.animewatch.domain.model.Genre;
import com.andemar.ddd.animewatch.infrastructure.input.adapter.rest.model.RequestGenre;
import com.andemar.ddd.animewatch.infrastructure.input.adapter.rest.model.ResponseGenre;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface GenreRestMapper {

    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    Genre toDomain(RequestGenre requestGenre);

    ResponseGenre toResponseGenre(Genre genre);

    List<Genre> toDomainList(List<RequestGenre> requestGenreList);

    List<ResponseGenre> toResponseGenreList(List<Genre> genreList);
}
