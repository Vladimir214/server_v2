package bip.online.biplio2024.controller;

import bip.online.biplio2024.entity.BookEntity;
import bip.online.biplio2024.response.BaseResponse;
import bip.online.biplio2024.response.DataResponse;
import bip.online.biplio2024.response.ListResponse;
import bip.online.biplio2024.service.BookService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name="Книги", description = "Все действия над книгами")
@RestController
@RequestMapping("api/v1/book")
@AllArgsConstructor
public class BookController {
  private final BookService service;

  @Operation(
      summary = "Все книги",
      description = "Позволяет показать все книги"
  )
  @GetMapping("/all")
  public ResponseEntity<ListResponse<BookEntity>> getAll(){
    return ResponseEntity.ok(
        new ListResponse<BookEntity>(true, "Список книг", service.findAll()));
  }

  /*@GetMapping
  public ResponseEntity<BaseResponse> by_id(@RequestParam Long id) {
    try {
      return ResponseEntity.ok(
          new DataResponse<BookEntity>(true, "Найдена следующая книга", service.findById(id).orElseThrow()));
    } catch (RuntimeException e) {
      return ResponseEntity.ok(
          new BaseResponse(false, e.getMessage()));
    }
  }*/

  @Operation(
      summary = "Добавить книгу",
      description = "Позволяет добавить книгу"
  )
  @PostMapping
  public ResponseEntity<BaseResponse> save(@RequestBody BookEntity book){
    try {
    return ResponseEntity.ok(
        new DataResponse<BookEntity>(true, "Книга сохранена", service.save(book)));
    } catch (RuntimeException e) {
      return ResponseEntity.ok(
          new BaseResponse(false, e.getMessage()));
    }
  }

  @Operation(
      summary = "Обновить книгу",
      description = "Позволяет обновить книгу"
  )
  @PutMapping
  public ResponseEntity<BaseResponse> update(@RequestBody BookEntity book){
    try{
    service.update(book);
    return ResponseEntity.ok(
        new BaseResponse(true, "Книга сохранена"));
    } catch (RuntimeException e) {
      return ResponseEntity.ok(
          new BaseResponse(false, e.getMessage()));
    }
  }

  @Hidden
  @Tag(name="Секретный контроллер", description="Позволяет удалить все книги")
  @DeleteMapping("/{id}")
  public ResponseEntity<BaseResponse> delete(@PathVariable Long id){
    try {
      service.delete(id);
      return ResponseEntity.ok(
          new BaseResponse(true, "Книга удалена"));
    }catch (RuntimeException e){
      return ResponseEntity.ok(
          new BaseResponse(false, e.getMessage()));
    }
  }

  @Operation(
      summary = "Поиск книги",
      description = "Позволяет найти книгу по названию"
  )
  @GetMapping()
  public ResponseEntity getPublisher(@RequestParam String title) {
    try {
      return ResponseEntity.ok(new DataResponse<List<BookEntity>>(true, "Книга найдена",service.getPublisher(title)));
    } catch (RuntimeException e) {
      return ResponseEntity.ok(new BaseResponse(false, "Книга не найдена"));
    }
  }

}
