package com.restaurant.rest.dto;

import com.restaurant.rest.entity.Ingredient;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class IngredientDto {
  private String id;
  private String name;

  public static IngredientDto mapIngredientToDto(Ingredient ingredient) {
    return IngredientDto.builder().id(ingredient.getId()).name(ingredient.getName()).build();
  }
}
