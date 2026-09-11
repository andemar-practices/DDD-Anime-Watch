package com.andemar.ddd.animewatch.infrastructure.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Utils {

  public static <T> ResponseEntity<T> mapResponse(T t, HttpStatus httpStatus) {
    return new ResponseEntity<>(t, httpStatus);
  }
}
