package com.andemar.ddd.animewatch.infrastructure.output.persistence.mapper;

import com.andemar.ddd.animewatch.domain.model.Anime;
import com.andemar.ddd.animewatch.domain.model.Title;
import com.andemar.ddd.animewatch.infrastructure.output.persistence.entity.AnimeEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = GenreEntityMapper.class)
public interface AnimeEntityMapper {

    @Mapping(target = "title", source = "title", qualifiedByName = "stringToTitle")
    Anime toAnime(AnimeEntity animeEntity);

    @Mapping(target = "title", source = "title", qualifiedByName = "titleToString")
    AnimeEntity toAnimeEntity(Anime anime);

    List<Anime> toAnimeList(List<AnimeEntity> animeEntities);
    List<AnimeEntity> toAnimeEntityList(List<Anime> animes);

    @Named("stringToTitle")
    default Title stringToTitle(String value) {
        return value != null ? Title.of(value) : null;
    }

    @Named("titleToString")
    default String titleToString(Title title) {
        return title != null ? title.getValue() : null;
    }
}
