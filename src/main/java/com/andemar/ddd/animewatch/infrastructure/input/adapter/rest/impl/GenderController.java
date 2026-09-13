package com.andemar.ddd.animewatch.infrastructure.input.adapter.rest.impl;

import static com.andemar.ddd.animewatch.infrastructure.utils.Utils.mapResponse;

import com.andemar.ddd.animewatch.application.input.port.GenderUseCase;
import com.andemar.ddd.animewatch.domain.model.Gender;
import com.andemar.ddd.animewatch.infrastructure.input.adapter.rest.mapper.GenderRestMapper;
import com.andemar.ddd.animewatch.infrastructure.input.adapter.rest.model.RequestGender;
import com.andemar.ddd.animewatch.infrastructure.input.adapter.rest.model.ResponseGender;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/genders")
@RequiredArgsConstructor
public class GenderController {

  private final GenderUseCase genderUseCase;
  private final GenderRestMapper genderRestMapper;

  @GetMapping
  public ResponseEntity<List<ResponseGender>> getAllGender() {
    List<ResponseGender> allGender = genderRestMapper.toResponseGenderList(genderUseCase.getAllGender());
    return mapResponse(allGender, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<ResponseGender> createGender(@RequestBody RequestGender requestGender) {
    Gender domain = genderRestMapper.toDomain(requestGender);
    Gender gender = genderUseCase.createGender(domain);
    return mapResponse(genderRestMapper.toResponseGender(gender), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseGender> updateGender(@PathVariable Long id, @RequestBody RequestGender requestGender) {
    Gender domain = genderRestMapper.toDomain(requestGender);
    Gender gender = genderUseCase.updateGender(id, domain);
    return mapResponse(genderRestMapper.toResponseGender(gender), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deleteGender(@PathVariable Long id) {
    genderUseCase.deleteGender(id);
  }

}
