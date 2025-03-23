package com.restaurant.ingredient;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IngredientService implements IngredientServiceInterface {
  private IngredientRepository ingredientRepository;

  @Override
  public void saveIngredient(Ingredient ingredient) {
    ingredientRepository.save(ingredient);
  }

  @Override
  public IngredientDto getIngredient(String id) {
    return IngredientDto.mapIngredientToDto(ingredientRepository.findById(id).orElseThrow());
  }

  @Override
  public List<IngredientDto> getIngredients() {
    return ingredientRepository.findAll().stream().map(IngredientDto::mapIngredientToDto).toList();
  }

  @Override
  @Transactional
  public IngredientDto updateIngredient(String id, String name) {
    Ingredient ingredient = ingredientRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Ingredient not found"));

    if (name != null) {
      ingredient.setName(name);
    }

    return IngredientDto.mapIngredientToDto(ingredient);
  }

  @Override
  public void deleteIngredient(String id) {
    ingredientRepository.deleteById(id);
  }
}
