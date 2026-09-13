package com.andemar.ddd.animewatch.infrastructure.output.persistence.mapper;

import com.andemar.ddd.animewatch.domain.model.Anime;
import com.andemar.ddd.animewatch.infrastructure.output.persistence.entity.AnimeEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = GenderEntityMapper.class)
public interface AnimeEntityMapper {
    Anime toAnime(AnimeEntity animeEntity);
    AnimeEntity toAnimeEntity(Anime anime);
    List<Anime> toAnimeList(List<AnimeEntity> animeEntities);
    List<AnimeEntity> toAnimeEntityList(List<Anime> animes);
}
