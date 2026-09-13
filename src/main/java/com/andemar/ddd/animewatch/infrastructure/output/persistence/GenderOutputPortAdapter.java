package com.andemar.ddd.animewatch.infrastructure.output.persistence;

import com.andemar.ddd.animewatch.application.output.port.GenderOutputPort;
import com.andemar.ddd.animewatch.domain.model.Gender;
import com.andemar.ddd.animewatch.infrastructure.output.persistence.entity.AnimeEntity;
import com.andemar.ddd.animewatch.infrastructure.output.persistence.entity.GenderEntity;
import com.andemar.ddd.animewatch.infrastructure.output.persistence.mapper.GenderEntityMapper;
import com.andemar.ddd.animewatch.infrastructure.output.persistence.repository.GenderRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenderOutputPortAdapter implements GenderOutputPort {

  private final GenderRepository repository;
  private final GenderEntityMapper mapper;

  @Override
  public List<Gender> getAllGender() {
    List<GenderEntity> all = repository.findAll();
    return mapper.toGenderList(all);
  }

  @Override
  public Gender createGender(Gender gender) {
    GenderEntity genderEntity = mapper.toGenderEntity(gender);
    GenderEntity save = repository.save(genderEntity);
    return mapper.toGender(save);
  }

  @Override
  public Gender updateGender(Long id, Gender gender) {
    GenderEntity genderEntity = mapper.toGenderEntity(gender);
    genderEntity.setId(id);
    return mapper.toGender(repository.save(genderEntity));
  }

  @Override
  public void deleteGender(Long id) {
    repository.deleteById(id);
  }
}
