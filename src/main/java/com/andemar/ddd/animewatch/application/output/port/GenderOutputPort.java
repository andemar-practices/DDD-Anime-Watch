package com.andemar.ddd.animewatch.application.output.port;

import com.andemar.ddd.animewatch.domain.model.Gender;
import java.util.List;

public interface GenderOutputPort {
  List<Gender> getAllGender();
  Gender createGender(Gender gender);
  Gender updateGender(Long id, Gender gender);
  void deleteGender(Long id);
}
