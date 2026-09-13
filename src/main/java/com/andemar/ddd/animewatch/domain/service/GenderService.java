package com.andemar.ddd.animewatch.domain.service;

import com.andemar.ddd.animewatch.application.input.port.GenderUseCase;
import com.andemar.ddd.animewatch.application.output.port.GenderOutputPort;
import com.andemar.ddd.animewatch.domain.model.Gender;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenderService implements GenderUseCase {

  private final GenderOutputPort genderOutputPort;

  @Override
  public List<Gender> getAllGender() {
    return genderOutputPort.getAllGender();
  }

  @Override
  public Gender createGender(Gender gender) {
    return genderOutputPort.createGender(gender);
  }

  @Override
  public Gender updateGender(Long id, Gender gender) {
    return genderOutputPort.updateGender(id, gender);
  }

  @Override
  public void deleteGender(Long id) {
    genderOutputPort.deleteGender(id);
  }
}
