package com.restaurant.rest.service;

import com.restaurant.rest.entity.Ingredient;
import com.restaurant.rest.dto.IngredientDto;

import java.util.List;

public interface IngredientServiceInterface {
  IngredientDto saveIngredient(Ingredient ingredient);
  IngredientDto getIngredient(String id);
  List<IngredientDto> getIngredients();
  IngredientDto updateIngredient(String id, String name);
  void deleteIngredient(String id);
}
