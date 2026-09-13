package com.andemar.ddd.animewatch.infrastructure.output.persistence.mapper;

import com.andemar.ddd.animewatch.domain.model.Gender;
import com.andemar.ddd.animewatch.infrastructure.output.persistence.entity.GenderEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenderEntityMapper {
    Gender toGender(GenderEntity genderEntity);
    GenderEntity toGenderEntity(Gender gender);
    List<Gender> toGenderList(List<GenderEntity> genderEntities);
    List<GenderEntity> toGenderEntityList(List<Gender> genders);
}
