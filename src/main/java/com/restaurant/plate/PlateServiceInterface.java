package com.restaurant.plate;

import com.restaurant.ingredient.IngredientDto;

import java.util.List;

public interface PlateServiceInterface {
  void savePlate(Plate plate);
  PlateDto getPlate(String id);
  List<PlateDto> getPlates();
  Plate updatePlate(String id, String name);
  void deletePlate(String id);
  List<IngredientDto> addIngredient(String plateId, String ingredientId);
  void removeIngredient(String plateId, String ingredientId);
}
