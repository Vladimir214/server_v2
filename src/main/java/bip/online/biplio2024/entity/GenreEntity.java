package bip.online.biplio2024.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "genres")
public class GenreEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Идентификатор")
  private Long id;
  @Schema(description = "Название")
  private String title;
  @Schema(description = "Книги")
  @JsonIgnore
  @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL)
  private List<BookEntity> books;
}
