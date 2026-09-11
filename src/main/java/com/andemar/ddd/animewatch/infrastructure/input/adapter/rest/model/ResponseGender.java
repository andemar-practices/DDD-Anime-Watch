package com.andemar.ddd.animewatch.infrastructure.input.adapter.rest.model;

import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ResponseGender {
  private String name;
}
