package com.andemar.ddd.animewatch.infrastructure.input.adapter.rest.mapper;

import com.andemar.ddd.animewatch.domain.model.Anime;
import com.andemar.ddd.animewatch.domain.model.Gender;
import com.andemar.ddd.animewatch.infrastructure.input.adapter.rest.model.RequestAnime;
import com.andemar.ddd.animewatch.infrastructure.input.adapter.rest.model.ResponseAnime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring", uses = {GenderRestMapper.class})
public interface AnimeRestMapper {

    @Mapping(target = "genders", source = "genderIds", qualifiedByName = "mapGenderIdsToGenders")
    Anime toDomain(RequestAnime requestAnime);

    @Mapping(target = "genderIds", source = "genders", qualifiedByName = "mapGendersToGenderIds")
    RequestAnime toRequest(Anime anime);

    @Mapping(target = "title", source = "tittle")
    @Mapping(target = "genders", source = "genders")
    ResponseAnime toResponseAnime(Anime anime);

    List<Anime> toDomainList(List<RequestAnime> requestAnimeList);

    List<RequestAnime> toRequestList(List<Anime> animeList);

    List<ResponseAnime> toResponseAnimeList(List<Anime> animeList);

    @Named("mapGenderIdsToGenders")
    default List<Gender> mapGenderIdsToGenders(List<Long> genderIds) {
        if (genderIds == null) {
            return Collections.emptyList();
        }
        return genderIds.stream()
                .map(id -> {
                    Gender gender = new Gender();
                    gender.setId(id);
                    return gender;
                })
                .toList();
    }

    @Named("mapGendersToGenderIds")
    default List<Long> mapGendersToGenderIds(List<Gender> genders) {
        if (genders == null) {
            return Collections.emptyList();
        }
        return genders.stream()
                .map(Gender::getId)
                .toList();
    }
}
