package bip.online.biplio2024.repository;

import bip.online.biplio2024.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<BookEntity, Long> {
  public List<BookEntity> findByTitleContains(String title);
}
