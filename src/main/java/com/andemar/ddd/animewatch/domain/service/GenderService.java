package com.andemar.ddd.animewatch.domain.service;

import com.andemar.ddd.animewatch.application.input.port.GenderUseCase;
import com.andemar.ddd.animewatch.domain.model.Gender;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GenderService implements GenderUseCase {

  @Override
  public List<Gender> getAllGender() {
    return List.of();
  }

  @Override
  public Gender createGender(Gender gender) {
    return null;
  }

  @Override
  public Gender updateGender(Long id, Gender gender) {
    return null;
  }

  @Override
  public void deleteGender(Long id) {

  }
}
