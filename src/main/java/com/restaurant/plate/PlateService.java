package com.restaurant.plate;

import com.restaurant.ingredient.Ingredient;
import com.restaurant.ingredient.IngredientDto;
import com.restaurant.ingredient.IngredientService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlateService implements PlateServiceInterface {
  private PlateRepository plateRepository;
  private IngredientService ingredientService;

  @Override
  public void savePlate(Plate plate) {
    plateRepository.save(plate);
  }

  @Override
  public PlateDto getPlate(String id) {
    Plate repositoryPlate = plateRepository.findById(id).orElseThrow();

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
      .orElseThrow(() -> new RuntimeException("Plate not found"));

    if (name != null) {
      plate.setName(name);
    }

    return plate;
  }


  @Override
  @Transactional
  public void deletePlate(String id) {
    plateRepository.deleteById(id);
  }

  @Override
  @Transactional
  public List<IngredientDto> addIngredient(String plateId, String ingredientId) {
    Plate plate = plateRepository.findById(plateId).orElseThrow();
    Ingredient ingredient = ingredientService.getIngredient(ingredientId);

    if (ingredient == null) {
      throw new RuntimeException("Ingredient does not exist.");
    }

    plate.addIngredient(ingredient);

    return plate.getIngredients().stream().map(IngredientDto::mapIngredientToDto).toList();
  }

  @Override
  @Transactional
  public void removeIngredient(String plateId, String ingredientId) {
    Plate plate = plateRepository.findById(plateId).orElseThrow();
    Ingredient ingredient = ingredientService.getIngredient(ingredientId);

    if (ingredient != null && plate.getIngredients().contains(ingredient)) {
      plate.removeIngredient(ingredient);
    } else {
      throw new RuntimeException("Ingredient " + ingredient.getName() + "is not present");
    }
  }
}
