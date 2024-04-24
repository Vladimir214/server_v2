package bip.online.biplio2024.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "publishers")
public class PublisherEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Идентификатор")
  private Long id;
  @Schema(description = "Название")
  private String title;
  @Schema(description = "Город")
  @NotNull
  @ManyToOne
  @JoinColumn(name = "city_id")
  private CityEntity city;
  @Schema(description = "Книги")
  @JsonIgnore
  @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
  private List<BookEntity> books;

}
