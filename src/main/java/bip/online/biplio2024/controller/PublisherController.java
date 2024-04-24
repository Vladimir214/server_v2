package bip.online.biplio2024.controller;

import bip.online.biplio2024.entity.PublisherEntity;
import bip.online.biplio2024.response.BaseResponse;
import bip.online.biplio2024.response.DataResponse;
import bip.online.biplio2024.response.ListResponse;
import bip.online.biplio2024.service.PublisherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Издательства", description = "Все действия над издательствами")
@RestController
@RequestMapping("api/v1/publisher")
@AllArgsConstructor
public class PublisherController {
  private final PublisherService service;

  @Operation(
      summary = "Все издательства",
      description = "Позволяет показать все издательства"
  )
  @GetMapping("/all")
  public ResponseEntity<ListResponse<PublisherEntity>> getAll(){
    return ResponseEntity.ok(
        new ListResponse<PublisherEntity>(true, "Список издательств", service.findAll()));
  }

  @Operation(
      summary = "Поиск издательств",
      description = "Позволяет показать все издательства"
  )
  @GetMapping
  public ResponseEntity<BaseResponse> by_id(@RequestParam Long id){
    try{
    return ResponseEntity.ok(
        new DataResponse<PublisherEntity>(true, "Найдено следующее издательство", service.findById(id).orElseThrow()));
    } catch (RuntimeException e) {
      return ResponseEntity.ok(
          new BaseResponse(false, e.getMessage()));
    }
  }

  @Operation(
      summary = "Добавление издательств",
      description = "Позволяет добавлять издательства"
  )
  @PostMapping
  public ResponseEntity<BaseResponse> save(@RequestBody PublisherEntity publisher){
    try {
    return ResponseEntity.ok(
        new DataResponse<PublisherEntity>(true, "Издательство сохранено", service.save(publisher)));
    } catch (RuntimeException e) {
      return ResponseEntity.ok(
          new BaseResponse(false, e.getMessage()));
    }
  }

  @Operation(
      summary = "Обновление издательств",
      description = "Позволяет обновлять издательства"
  )
  @PutMapping
  public ResponseEntity<BaseResponse> update(@RequestBody PublisherEntity publisher){
    try{
    service.update(publisher);
    return ResponseEntity.ok(
        new BaseResponse(true, "Издательство сохранено"));
    } catch (RuntimeException e) {
      return ResponseEntity.ok(
          new BaseResponse(false, e.getMessage()));
    }
  }

  @Operation(
      summary = "Удаление издательств",
      description = "Позволяет удалять издательства"
  )
  @DeleteMapping("/{id}")
  public ResponseEntity<BaseResponse> delete(@PathVariable Long id){
    try {
      service.delete(id);
      return ResponseEntity.ok(
          new BaseResponse(true, "Издательство удалено"));
    }catch (RuntimeException e){
      return ResponseEntity.ok(
          new BaseResponse(false, e.getMessage()));
    }
  }

}
