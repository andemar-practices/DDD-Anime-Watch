package com.andemar.ddd.animewatch.infrastructure.input.adapter.rest.mapper;

import com.andemar.ddd.animewatch.domain.model.Gender;
import com.andemar.ddd.animewatch.infrastructure.input.adapter.rest.model.RequestGender;
import com.andemar.ddd.animewatch.infrastructure.input.adapter.rest.model.ResponseGender;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenderRestMapper {

    Gender toDomain(RequestGender requestGender);

    RequestGender toRequest(Gender gender);

    ResponseGender toResponseGender(Gender gender);

    List<Gender> toDomainList(List<RequestGender> requestGenderList);

    List<RequestGender> toRequestList(List<Gender> genderList);

    List<ResponseGender> toResponseGenderList(List<Gender> genderList);
}
