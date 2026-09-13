package com.andemar.ddd.animewatch.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class TitleTest {

    @Test
    void shouldCreateTitleWithValidValue() {
        Title title = Title.of("Naruto");
        assertThat(title.getValue()).isEqualTo("Naruto");
    }

    @Test
    void shouldTrimWhitespace() {
        Title title = Title.of("  Naruto  ");
        assertThat(title.getValue()).isEqualTo("Naruto");
    }

    @Test
    void shouldThrowWhenBlank() {
        assertThatThrownBy(() -> Title.of("   "))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("blank");
    }

    @Test
    void shouldThrowWhenNull() {
        assertThatThrownBy(() -> Title.of(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("blank");
    }

    @Test
    void shouldThrowWhenExceedsMaxLength() {
        String longTitle = "A".repeat(256);
        assertThatThrownBy(() -> Title.of(longTitle))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("255");
    }

    @Test
    void shouldBeEqualWhenSameValue() {
        Title a = Title.of("Naruto");
        Title b = Title.of("Naruto");
        assertThat(a).isEqualTo(b);
        assertThat(a.hashCode()).isEqualTo(b.hashCode());
    }

    @Test
    void shouldNotBeEqualWhenDifferentValue() {
        assertThat(Title.of("Naruto")).isNotEqualTo(Title.of("Bleach"));
    }
}
