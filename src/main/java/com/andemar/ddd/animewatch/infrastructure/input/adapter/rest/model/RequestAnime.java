package com.andemar.ddd.animewatch.infrastructure.input.adapter.rest.model;

import java.util.List;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class RequestAnime {
    Long id;
    String tittle;
    List<Long> genderIds;
}
