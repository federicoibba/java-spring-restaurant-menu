package com.restaurant.rest;

import com.restaurant.rest.docs.IngredientControllerDocs;
import com.restaurant.rest.dto.IngredientDto;
import com.restaurant.rest.entity.Ingredient;
import com.restaurant.rest.service.IngredientService;
import jakarta.validation.Valid;
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
@RequestMapping("/v1/ingredient")
@AllArgsConstructor
public class IngredientController implements IngredientControllerDocs {
  private IngredientService ingredientService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<IngredientDto> getIngredient(@PathVariable("id") String id) {
    return ResponseEntity.ok().body(IngredientDto.mapIngredientToDto(ingredientService.getIngredient(id)));
  }

  @GetMapping
  public ResponseEntity<List<IngredientDto>> getIngredients() {
    return ResponseEntity.ok()
      .body(ingredientService.getIngredients().stream().map(IngredientDto::mapIngredientToDto).toList());
  }

  @PostMapping
  public ResponseEntity<IngredientDto> createIngredient(@Valid @RequestBody Ingredient ingredient) {
    return new ResponseEntity<>(IngredientDto.mapIngredientToDto(ingredientService.saveIngredient(ingredient)),
      HttpStatusCode.valueOf(201));
  }

  @PatchMapping(value = "/{id}")
  public ResponseEntity<IngredientDto> updateIngredient(@PathVariable("id") String id,
    @Valid @RequestBody Ingredient ingredient) {
    return ResponseEntity.ok()
      .body(IngredientDto.mapIngredientToDto(ingredientService.updateIngredient(id, ingredient.getName())));
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Object> deleteIngredient(@PathVariable("id") String id) {
    ingredientService.deleteIngredient(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}

