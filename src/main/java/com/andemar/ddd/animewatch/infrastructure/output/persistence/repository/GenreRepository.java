package com.andemar.ddd.animewatch.infrastructure.output.persistence.repository;

import com.andemar.ddd.animewatch.infrastructure.output.persistence.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
}
