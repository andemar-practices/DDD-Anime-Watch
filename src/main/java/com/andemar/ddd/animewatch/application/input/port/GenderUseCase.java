package com.andemar.ddd.animewatch.application.input.port;
import com.andemar.ddd.animewatch.domain.model.Gender;
import java.util.List;

public interface GenderUseCase {
  List<Gender> getAllGender();
  Gender createGender(Gender gender);
  Gender updateGender(Long id, Gender gender);
  void deleteGender(Long id);
}
