package bip.online.biplio2024.controller;

import bip.online.biplio2024.entity.CityEntity;
import bip.online.biplio2024.response.BaseResponse;
import bip.online.biplio2024.response.DataResponse;
import bip.online.biplio2024.response.ListResponse;
import bip.online.biplio2024.service.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Города", description = "Все действия над городами")
@RestController
@RequestMapping("api/v1/city")
@AllArgsConstructor
public class CityController {
  private final CityService service;

  @Operation(
      summary = "Все города",
      description = "Позволяет показать все города"
  )
  @GetMapping("/all")
  public ResponseEntity<ListResponse<CityEntity>> getAll(){
    return ResponseEntity.ok(
        new ListResponse<CityEntity>(true, "Список городов", service.findAll()));
  }

  @Operation(
      summary = "Поиск города",
      description = "Позволяет найти города по id"
  )
  @GetMapping
  public ResponseEntity<BaseResponse> by_id(@RequestParam Long id){
    try {
      return ResponseEntity.ok(
          new DataResponse<CityEntity>(true, "Найден следующий город", service.findById(id).orElseThrow()));
    }catch (RuntimeException e) {
        return ResponseEntity.ok(
            new BaseResponse(false, e.getMessage()));
    }
  }

  @Operation(
      summary = "Добавить город",
      description = "Позволяет добавлять города"
  )
  @PostMapping
  public ResponseEntity<BaseResponse> save(@RequestBody CityEntity city){
    try {
    return ResponseEntity.ok(
        new DataResponse<CityEntity>(true, "Город сохранен", service.save(city)));
    } catch (RuntimeException e) {
      return ResponseEntity.ok(
          new BaseResponse(false, e.getMessage()));
    }
    }

  @Operation(
      summary = "Обновить город",
      description = "Позволяет обновить город"
  )
  @PutMapping
  public ResponseEntity<BaseResponse> update(@RequestBody CityEntity city){
    try{
    service.update(city);
    return ResponseEntity.ok(
        new BaseResponse(true, "Город сохранен"));
    } catch (RuntimeException e) {
      return ResponseEntity.ok(
          new BaseResponse(false, e.getMessage()));
    }
  }

  @Operation(
      summary = "Удалить город",
      description = "Позволяет удалять города"
  )
  @DeleteMapping("/{id}")
  public ResponseEntity<BaseResponse> delete(@PathVariable Long id){
    try {
      service.delete(id);
      return ResponseEntity.ok(
          new BaseResponse(true, "Город удален"));
    }catch (RuntimeException e){
      return ResponseEntity.ok(
          new BaseResponse(false, e.getMessage()));
    }
  }

}
