package com.restaurant.rest.service;

import com.restaurant.rest.entity.Ingredient;
import com.restaurant.rest.entity.Plate;
import com.restaurant.rest.exception.BadRequestException;
import com.restaurant.rest.exception.ExceptionErrors;
import com.restaurant.rest.exception.NotFoundException;
import com.restaurant.rest.repository.PlateRepository;
import com.restaurant.rest.repository.IngredientRepository;
import com.restaurant.rest.dto.PlateDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlateService {
  private PlateRepository plateRepository;
  private IngredientService ingredientService;

  public void savePlate(Plate plate) {
    if (plateRepository.findByName(plate.getName()).isPresent()) {
      throw new BadRequestException(ExceptionErrors.PLATE_ALREADY_EXIST.getMessage());
    }

    plateRepository.save(plate);
  }

  public PlateDto getPlate(String id) {
    Plate repositoryPlate = plateRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(ExceptionErrors.PLATE_NOT_FOUND.getMessage() + id));

    return PlateDto.mapPlateToDto(repositoryPlate);
  }

  public List<PlateDto> getPlates() {
    return plateRepository.findAll().stream().map(PlateDto::mapPlateToDto).toList();
  }

  @Transactional
  public Plate updatePlate(String id, String name) {
    Plate plate = plateRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(ExceptionErrors.PLATE_UPDATE_NOT_FOUND.getMessage()));

    if (name != null) {
      plate.setName(name);
    }

    return plate;
  }

  @Transactional
  public void deletePlate(String id) {
    Plate plate = plateRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(ExceptionErrors.PLATE_DELETE_NOT_FOUND.getMessage()));

    plateRepository.delete(plate);
  }

  @Transactional
  public PlateDto addIngredient(String plateId, String ingredientId) {
    Plate plate = plateRepository.findById(plateId)
      .orElseThrow(() -> new NotFoundException(ExceptionErrors.PLATE_ADD_INGREDIENT_PLATE_NOT_FOUND.getMessage()));

    Ingredient ingredient = ingredientService.getIngredient(ingredientId);

    plate.addIngredient(ingredient);

    return PlateDto.mapPlateToDto(plate);
  }

  @Transactional
  public void removeIngredient(String plateId, String ingredientId) {
    Plate plate = plateRepository.findById(plateId)
      .orElseThrow(() -> new NotFoundException(ExceptionErrors.PLATE_REMOVE_INGREDIENT_PLATE_NOT_FOUND.getMessage()));

    Ingredient ingredient = ingredientService.getIngredient(ingredientId);

    if (plate.getIngredients().contains(ingredient)) {
      plate.removeIngredient(ingredient);
    } else {
      throw new BadRequestException(ExceptionErrors.PLATE_REMOVE_INGREDIENT_NEVER_ADDED.getMessage());
    }

  }
}
