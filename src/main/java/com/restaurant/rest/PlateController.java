package com.restaurant.rest;

import com.restaurant.rest.docs.PlateControllerDocs;
import com.restaurant.rest.dto.PlateDto;
import com.restaurant.rest.entity.Ingredient;
import com.restaurant.rest.entity.Plate;
import com.restaurant.rest.service.PlateService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/plate")
@AllArgsConstructor
public class PlateController implements PlateControllerDocs {
  private PlateService plateService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<PlateDto> getPlate(@PathVariable("id") String id) {
    return ResponseEntity.ok().body(PlateDto.mapPlateToDto(plateService.getPlate(id)));
  }

  @GetMapping
  public ResponseEntity<List<PlateDto>> getPlates() {
    return ResponseEntity.ok().body(plateService.getPlates().stream().map(PlateDto::mapPlateToDto).toList());
  }

  @PostMapping
  public ResponseEntity<PlateDto> savePlate(@Valid @RequestBody Plate plate) {
    try {
      return new ResponseEntity<>(PlateDto.mapPlateToDto(plateService.savePlate(plate)), HttpStatusCode.valueOf(201));
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatusCode.valueOf(500));
    }
  }

  @PatchMapping(value = "/{id}")
  public ResponseEntity<PlateDto> updatePlate(@PathVariable("id") String id, @Valid @RequestBody Plate plate) {
    return ResponseEntity.ok().body(PlateDto.mapPlateToDto(plateService.updatePlate(id, plate.getName())));
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Object> deletePlate(@PathVariable("id") String id) {
    plateService.deletePlate(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PostMapping(value = "/{id}/ingredient")
  public ResponseEntity<PlateDto> addIngredient(@PathVariable("id") String plateId,
    @Valid @RequestBody Ingredient ingredient) {
    try {
      return new ResponseEntity<>(PlateDto.mapPlateToDto(plateService.addIngredient(plateId, ingredient.getId())),
        HttpStatusCode.valueOf(201));
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatusCode.valueOf(500));
    }
  }

  @DeleteMapping(value = "/{plateId}/ingredient/{ingredientId}")
  public ResponseEntity<Object> removeIngredient(@PathVariable("plateId") String plateId,
    @PathVariable("ingredientId") String ingredientId) {
    plateService.removeIngredient(plateId, ingredientId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
