package com.restaurant.rest.service;

import com.restaurant.rest.entity.Plate;
import com.restaurant.rest.dto.IngredientDto;
import com.restaurant.rest.dto.PlateDto;

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
