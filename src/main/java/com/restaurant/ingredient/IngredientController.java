package com.restaurant.ingredient;

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
public class IngredientController {
  private IngredientService ingredientService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<IngredientDto> getIngredient(@PathVariable("id") String id) {
    return ResponseEntity.ok().body(ingredientService.getIngredient(id));
  }

  @GetMapping
  public ResponseEntity<List<IngredientDto>> getIngrediens() {
    return ResponseEntity.ok().body(ingredientService.getIngredients());
  }

  @PostMapping
  public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient) {
    try {
      ingredientService.saveIngredient(ingredient);
      return new ResponseEntity<>(ingredient, HttpStatusCode.valueOf(201));
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatusCode.valueOf(500));
    }
  }

  @PatchMapping(value = "/{id}")
  public ResponseEntity<IngredientDto> updateIngredient(@PathVariable("id") String id, @RequestBody Ingredient ingredient){
    return ResponseEntity.ok().body(ingredientService.updateIngredient(id, ingredient.getName()));
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Object> deleteIngredient(@PathVariable("id") String id) {
    ingredientService.deleteIngredient(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}

