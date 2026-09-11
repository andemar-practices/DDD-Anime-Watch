package com.andemar.ddd.animewatch.domain.model;

import java.util.List;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Anime {
    Long id;
    String tittle;
    List<Gender> genders;
}
