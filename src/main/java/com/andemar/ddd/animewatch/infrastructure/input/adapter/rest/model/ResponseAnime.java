package com.andemar.ddd.animewatch.infrastructure.input.adapter.rest.model;

import java.util.List;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ResponseAnime {
  private String title;
  private List<ResponseGender> genders;
}
