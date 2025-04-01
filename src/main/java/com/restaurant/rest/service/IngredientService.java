package com.restaurant.rest.service;

import com.restaurant.rest.entity.Ingredient;
import com.restaurant.rest.exception.BadRequestException;
import com.restaurant.rest.exception.ExceptionErrors;
import com.restaurant.rest.exception.NotFoundException;
import com.restaurant.rest.repository.IngredientRepository;
import com.restaurant.rest.dto.IngredientDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IngredientService {
  private IngredientRepository ingredientRepository;

  public IngredientDto saveIngredient(Ingredient ingredient) {
    if (ingredientRepository.findByName(ingredient.getName()).isPresent()) {
      throw new BadRequestException(ExceptionErrors.INGREDIENT_ALREADY_EXIST.getMessage());
    }

    return IngredientDto.mapIngredientToDto(ingredientRepository.save(ingredient));
  }

  public IngredientDto getIngredient(String id) {
    return IngredientDto.mapIngredientToDto(ingredientRepository.findById(id).orElseThrow(
      () -> new NotFoundException(ExceptionErrors.INGREDIENT_NOT_FOUND.getMessage() + id)
    ));
  }

  public List<IngredientDto> getIngredients() {
    return ingredientRepository.findAll().stream().map(IngredientDto::mapIngredientToDto).toList();
  }

  @Transactional
  public IngredientDto updateIngredient(String id, String name) {
    if (ingredientRepository.findByName(name).isPresent()) {
      throw new BadRequestException(ExceptionErrors.INGREDIENT_ALREADY_EXIST.getMessage());
    }

    Ingredient ingredient = ingredientRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(ExceptionErrors.INGREDIENT_UPDATE_NOT_FOUND.getMessage()));

    if (name != null) {
      ingredient.setName(name);
    }

    return IngredientDto.mapIngredientToDto(ingredient);
  }

  public void deleteIngredient(String id) {
    if (!ingredientRepository.existsById(id)) {
      throw new NotFoundException(ExceptionErrors.INGREDIENT_DELETE_NOT_FOUND.getMessage());
    }

    ingredientRepository.deleteById(id);
  }
}
