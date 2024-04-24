package bip.online.biplio2024.controller;

import bip.online.biplio2024.entity.AuthorEntity;
import bip.online.biplio2024.response.BaseResponse;
import bip.online.biplio2024.response.DataResponse;
import bip.online.biplio2024.response.ListResponse;
import bip.online.biplio2024.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Авторы", description = "Все действия над авторами")
@RestController
@RequestMapping("api/v1/author")
@AllArgsConstructor
public class AuthorController {
  private final AuthorService service;

  @Operation(
      summary = "Все авторы",
      description = "Позволяет показать всех авторов"
  )
  @GetMapping("/all")
  public ResponseEntity<BaseResponse> getAll() {
    return ResponseEntity.ok(
        new ListResponse<AuthorEntity>(true, "Список авторов", service.findAll()));
  }

  @Operation(
      summary = "Поиск автора",
      description = "Позволяет найти автора по id"
  )
  @GetMapping
  public ResponseEntity<BaseResponse> by_id(@RequestParam Long id) {
    try {
      return ResponseEntity.ok(
          new DataResponse<AuthorEntity>(true, "Найден следующий автор", service.findById(id).orElseThrow()));
    } catch (RuntimeException e) {
      return ResponseEntity.ok(
          new BaseResponse(false, e.getMessage()));
    }
  }

  @Operation(
      summary = "Добавить автора",
      description = "Позволяет добавить автора"
  )
  @PostMapping
  public ResponseEntity<BaseResponse> save(@RequestBody AuthorEntity author) {
    try {
      return ResponseEntity.ok(
          new DataResponse<AuthorEntity>(true, "Автор сохранен", service.save(author)));
    } catch (RuntimeException e) {
      return ResponseEntity.ok(
          new BaseResponse(false, e.getMessage()));
    }
  }


  @Operation(
      summary = "Обновить автора",
      description = "Позволяет обновить автора"
  )
  @PutMapping
  public ResponseEntity<BaseResponse> update(@RequestBody AuthorEntity author) {
    try {
      service.update(author);
      return ResponseEntity.ok(
          new BaseResponse(true, "Автор сохранен"));
    } catch (RuntimeException e) {
      return ResponseEntity.ok(
          new BaseResponse(false, e.getMessage()));
    }
  }

  @Operation(
      summary = "Удаление автора",
      description = "Позволяет удалить автора"
  )
  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable Long id) {
    try{
      service.delete(id);
      return ResponseEntity.ok(new BaseResponse(true, "Автор удален"));
    }catch (RuntimeException e) {
      return ResponseEntity.ok(new BaseResponse(false, e.getMessage()));
    }
  }
}