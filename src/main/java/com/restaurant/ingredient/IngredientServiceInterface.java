package com.restaurant.ingredient;

import java.util.List;

public interface IngredientServiceInterface {
  void saveIngredient(Ingredient ingredient);
  IngredientDto getIngredient(String id);
  List<IngredientDto> getIngredients();
  IngredientDto updateIngredient(String id, String name);
  void deleteIngredient(String id);
}
