package com.restaurant.rest.service;

import com.restaurant.rest.entity.Ingredient;
import com.restaurant.rest.entity.Plate;
import com.restaurant.rest.exception.BadRequestException;
import com.restaurant.rest.exception.NotFoundException;
import com.restaurant.rest.repository.PlateRepository;
import com.restaurant.rest.dto.IngredientDto;
import com.restaurant.rest.repository.IngredientRepository;
import com.restaurant.rest.dto.PlateDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlateService implements PlateServiceInterface {
  private PlateRepository plateRepository;
  private IngredientRepository ingredientRepository;

  @Override
  public void savePlate(Plate plate) {
    plateRepository.save(plate);
  }

  @Override
  public PlateDto getPlate(String id) {
    Plate repositoryPlate = plateRepository.findById(id).orElseThrow(
            () -> new NotFoundException("Plate not found with id " + id)
    );

    return PlateDto.mapPlateToDto(repositoryPlate);
  }

  @Override
  public List<PlateDto> getPlates() {
    return plateRepository.findAll().stream().map(PlateDto::mapPlateToDto).toList();
  }

  @Override
  @Transactional
  public Plate updatePlate(String id, String name) {
    Plate plate = plateRepository.findById(id)
      .orElseThrow(() -> new BadRequestException("Cannot update a plate that does not exist"));

    if (name != null) {
      plate.setName(name);
    }

    return plate;
  }


  @Override
  @Transactional
  public void deletePlate(String id) {
    Plate plate = plateRepository.findById(id).orElseThrow(
            () -> new BadRequestException("Cannot update a plate that does not exist")
    );
    plateRepository.delete(plate);
  }

  @Override
  @Transactional
  public List<IngredientDto> addIngredient(String plateId, String ingredientId) {
    Plate plate = plateRepository.findById(plateId).orElseThrow(
            () -> new BadRequestException("Cannot add an ingredient to a plate that does not exist")
    );
    Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow();

    plate.addIngredient(ingredient);

    return plate.getIngredients().stream().map(IngredientDto::mapIngredientToDto).toList();
  }

  @Override
  @Transactional
  public void removeIngredient(String plateId, String ingredientId) {
    Plate plate = plateRepository.findById(plateId).orElseThrow(
            () -> new BadRequestException("Cannot remove an ingredient from a plate that does not exist")
    );
    Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow(
            () -> new BadRequestException("Cannot remove an ingredient that does not exist from a plate")
    );

    if (plate.getIngredients().contains(ingredient)) {
      plate.removeIngredient(ingredient);
    } else {
      throw new RuntimeException("Ingredient " + ingredient.getName() + "is not present");
    }
  }
}
