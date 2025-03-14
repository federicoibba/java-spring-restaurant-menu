package com.restaurant.ingredient;

import java.util.List;

public interface IngredientServiceInterface {
  void saveIngredient(Ingredient ingredient);
  Ingredient getIngredient(String id);
  List<Ingredient> getIngredients();
  Ingredient updateIngredient(String id, String name);
  void deleteIngredient(String id);
}
