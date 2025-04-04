package com.restaurant.rest.dto;

import com.restaurant.rest.entity.Ingredient;
import com.restaurant.rest.entity.Plate;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Data
public class PlateDto {
  private String id;
  private String name;
  private Set<IngredientDto> ingredients;

  public static PlateDto mapPlateToDto(Plate plate) {
    return PlateDto.builder()
      .id(plate.getId())
      .name(plate.getName())
      .ingredients(mapIngredientsToDto(plate.getIngredients()))
      .build();
  }

  private static Set<IngredientDto> mapIngredientsToDto(Set<Ingredient> ingredientList) {
    return ingredientList.stream().map(IngredientDto::mapIngredientToDto).collect(Collectors.toSet());
  }
}
