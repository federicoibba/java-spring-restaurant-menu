package com.restaurant.ingredient;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class IngredientDto {
  private String id;
  private String name;

  public static IngredientDto mapIngredientToDto(Ingredient ingredient) {
    return IngredientDto.builder()
      .id(ingredient.getId())
      .name(ingredient.getName())
      .build();
  }
}
