package bip.online.biplio2024.controller;

import bip.online.biplio2024.entity.GenreEntity;
import bip.online.biplio2024.response.BaseResponse;
import bip.online.biplio2024.response.DataResponse;
import bip.online.biplio2024.response.ListResponse;
import bip.online.biplio2024.service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Жанры", description = "Все действия над жанрами")
@RestController
@RequestMapping("api/v1/genre")
@AllArgsConstructor
public class GenreController {
  private final GenreService service;

  @Operation(
      summary = "Все жанры",
      description = "Позволяет показать все жанры"
  )
  @GetMapping("/all")
  public ResponseEntity<ListResponse<GenreEntity>> getAll(){
    return ResponseEntity.ok(
        new ListResponse<GenreEntity>(true, "Список жанров", service.findAll()));
  }

  @Operation(
      summary = "Поиск жанров",
      description = "Позволяет найти жанры по id"
  )
  @GetMapping
  public ResponseEntity<BaseResponse> by_id(@RequestParam Long id){
    try {
    return ResponseEntity.ok(
        new DataResponse<GenreEntity>(true, "Найден следующий жанр", service.findById(id).orElseThrow()));
    } catch (RuntimeException e) {
      return ResponseEntity.ok(
          new BaseResponse(false, e.getMessage()));
    }
  }

  @Operation(
      summary = "Добавление жанров",
      description = "Позволяет добавлять жанры"
  )
  @PostMapping
  public ResponseEntity<BaseResponse> save(@RequestBody GenreEntity genre){
    try {
    return ResponseEntity.ok(
        new DataResponse<GenreEntity>(true, "Жанр сохранен", service.save(genre)));
    } catch (RuntimeException e) {
      return ResponseEntity.ok(
          new BaseResponse(false, e.getMessage()));
    }
  }

  @Operation(
      summary = "Обновление жанров",
      description = "Позволяет обновлять жанры"
  )
  @PutMapping
  public ResponseEntity<BaseResponse> update(@RequestBody GenreEntity genre){
    try{
    service.update(genre);
    return ResponseEntity.ok(
        new BaseResponse(true, "Жанр сохранен"));
    } catch (RuntimeException e) {
      return ResponseEntity.ok(
          new BaseResponse(false, e.getMessage()));
    }
  }

  @Operation(
      summary = "Удаление жанров",
      description = "Позволяет удалять жанры"
  )
  @DeleteMapping("/{id}")
  public ResponseEntity<BaseResponse> delete(@PathVariable Long id){
    try {
      service.delete(id);
      return ResponseEntity.ok(
          new BaseResponse(true, "Жанр удален"));
    }catch (RuntimeException e){
      return ResponseEntity.ok(
          new BaseResponse(false, e.getMessage()));
    }
  }

}
