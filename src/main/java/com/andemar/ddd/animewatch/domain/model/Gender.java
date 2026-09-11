package com.andemar.ddd.animewatch.domain.model;

import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Gender {
    Long id;
    String name;
}
