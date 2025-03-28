package com.restaurant.rest;

import com.restaurant.rest.entity.Ingredient;
import com.restaurant.rest.entity.Plate;
import com.restaurant.rest.dto.IngredientDto;
import com.restaurant.rest.dto.PlateDto;
import com.restaurant.rest.service.PlateService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/plate")
@AllArgsConstructor
public class PlateController {
  private PlateService plateService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<PlateDto> getPlate(@PathVariable("id") String id) {
    return ResponseEntity.ok().body(plateService.getPlate(id));
  }

  @GetMapping
  public ResponseEntity<List<PlateDto>> getPlates() {
    return ResponseEntity.ok().body(plateService.getPlates());
  }

  @PostMapping
  public ResponseEntity<Plate> savePlate(@RequestBody Plate plate) {
    try {
      plateService.savePlate(plate);
      return new ResponseEntity<>(plate, HttpStatusCode.valueOf(201));
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatusCode.valueOf(500));
    }
  }

  @PatchMapping(value = "/{id}")
  public ResponseEntity<Plate> updatePlate(@PathVariable("id") String id, @RequestBody() Plate plate) {
    return ResponseEntity.ok().body(plateService.updatePlate(id, plate.getName()));
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Object> deletePlate(@PathVariable("id") String id) {
    plateService.deletePlate(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PostMapping(value = "/{id}/ingredient")
  public ResponseEntity<List<IngredientDto>> addIngredients(@PathVariable("id") String plateId, @RequestBody List<Ingredient> ingredients) {
    try {
      List<IngredientDto> ingredientList = plateService.addIngredient(plateId, ingredients.get(0).getId());
      return new ResponseEntity<>(ingredientList, HttpStatusCode.valueOf(201));
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatusCode.valueOf(500));
    }
  }

  @DeleteMapping(value = "/{plateId}/ingredient/{ingredientId}")
  public ResponseEntity<Object> removeIngredient(@PathVariable("plateId") String plateId, @PathVariable("ingredientId") String ingredientId) {
    plateService.removeIngredient(plateId, ingredientId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
