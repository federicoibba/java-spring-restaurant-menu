package com.restaurant.rest.service;

import com.restaurant.rest.entity.Ingredient;
import com.restaurant.rest.exception.BadRequestException;
import com.restaurant.rest.exception.NotFoundException;
import com.restaurant.rest.repository.IngredientRepository;
import com.restaurant.rest.dto.IngredientDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IngredientService implements IngredientServiceInterface {
  private IngredientRepository ingredientRepository;

  @Override
  public IngredientDto saveIngredient(Ingredient ingredient) {
    if (ingredientRepository.findByName(ingredient.getName()).isPresent()) {
      throw new BadRequestException("Ingredient already exists");
    }

    return IngredientDto.mapIngredientToDto(ingredientRepository.save(ingredient));
  }

  @Override
  public IngredientDto getIngredient(String id) {
    return IngredientDto.mapIngredientToDto(ingredientRepository.findById(id).orElseThrow(
      () -> new NotFoundException("Ingredient not found with id " + id)
    ));
  }

  @Override
  public List<IngredientDto> getIngredients() {
    return ingredientRepository.findAll().stream().map(IngredientDto::mapIngredientToDto).toList();
  }

  @Override
  @Transactional
  public IngredientDto updateIngredient(String id, String name) {
    if (ingredientRepository.findByName(name).isPresent()) {
      throw new BadRequestException("Ingredient already exists");
    }

    Ingredient ingredient = ingredientRepository.findById(id)
      .orElseThrow(() -> new NotFoundException("Cannot update an ingredient that does not exist"));

    if (name != null) {
      ingredient.setName(name);
    }

    return IngredientDto.mapIngredientToDto(ingredient);
  }

  @Override
  public void deleteIngredient(String id) {
    if (!ingredientRepository.existsById(id)) {
      throw new NotFoundException("Cannot delete an ingredient that does not exist");
    }

    ingredientRepository.deleteById(id);
  }
}
