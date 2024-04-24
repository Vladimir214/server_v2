package bip.online.biplio2024.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class BookEntity {
  @Schema(description = "Идентификатор")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Schema(description = "Название")
  @NotNull
  @Pattern(regexp = "[А-Я][а-я]{1,20}")
  private String title;
  @Schema(description = "Авторы")
  @NotNull
  @ManyToOne
  @JoinColumn(name = "author_id")
  private AuthorEntity author;
  @Schema(description = "Издательства")
  @NotNull
  @ManyToOne
  @JoinColumn(name = "publisher_id")
  private PublisherEntity publisher;
  @Schema(description = "Жанры")
  @NotNull
  @ManyToOne
  @JoinColumn(name = "genre_id")
  private GenreEntity genre;
  @Schema(description = "Год")
  @Pattern(regexp = "[1-9]{3,4}")
  private String year;
}