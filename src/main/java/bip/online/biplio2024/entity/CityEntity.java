package bip.online.biplio2024.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "cities")
public class CityEntity {
  @Schema(description = "Идентификатор")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Schema(description = "Название")
  @NotBlank()
  @Pattern(regexp = "[А-Я][а-я]{1,20}")
  private String title;
  @Schema(description = "Издательство")
  @JsonIgnore
  @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
  private List<PublisherEntity> publisher;
}
