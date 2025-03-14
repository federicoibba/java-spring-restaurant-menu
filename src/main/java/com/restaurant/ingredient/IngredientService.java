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
  public Ingredient getIngredient(String id) {
    return ingredientRepository.findById(id).orElse(null);
  }

  @Override
  public List<Ingredient> getIngredients() {
    return ingredientRepository.findAll();
  }

  @Override
  @Transactional
  public Ingredient updateIngredient(String id, String name) {
    Ingredient ingredient = ingredientRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Ingredient not found"));

    if (name != null) {
      ingredient.setName(name);
    }

    return ingredient;
  }

  @Override
  public void deleteIngredient(String id) {
    ingredientRepository.deleteById(id);
  }
}
