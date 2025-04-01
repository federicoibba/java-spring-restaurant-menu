package com.restaurant.rest;

import com.restaurant.rest.docs.RestaurantPlateControllerDocs;
import com.restaurant.rest.dto.RestaurantPlateDto;
import com.restaurant.rest.service.RestaurantPlateService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/restaurant/{restaurantId}/plate")
public class RestaurantPlateController implements RestaurantPlateControllerDocs {
  private RestaurantPlateService restaurantPlateService;

  @GetMapping
  public ResponseEntity<List<RestaurantPlateDto>> getPlates(@PathVariable("restaurantId") String restaurantId) {
    return ResponseEntity.ok(
      restaurantPlateService.getPlates(restaurantId).stream().map(RestaurantPlateDto::mapToDto).toList());
  }

  @PostMapping
  public ResponseEntity<RestaurantPlateDto> addPlateToRestaurant(@PathVariable("restaurantId") String restaurantId,
    @Valid @RequestBody RestaurantPlateDto restaurantPlateDto) {
    return ResponseEntity.status(HttpStatus.CREATED)
      .body(RestaurantPlateDto.mapToDto(restaurantPlateService.addPlateToRestaurant(restaurantId, restaurantPlateDto)));
  }

  @DeleteMapping(value = "/{plateId}")
  public ResponseEntity<Void> removePlateFromRestaurant(@PathVariable("restaurantId") String restaurantId,
    @PathVariable("plateId") String plateId) {
    restaurantPlateService.removePlateFromRestaurant(restaurantId, plateId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
