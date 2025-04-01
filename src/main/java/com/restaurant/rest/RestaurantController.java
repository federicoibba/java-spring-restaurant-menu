package com.restaurant.rest;

import com.restaurant.rest.docs.RestaurantControllerDocs;
import com.restaurant.rest.dto.RestaurantDto;
import com.restaurant.rest.entity.Restaurant;
import com.restaurant.rest.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
@RequestMapping("v1/restaurant")
@AllArgsConstructor
public class RestaurantController implements RestaurantControllerDocs {
  private RestaurantService restaurantService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<RestaurantDto> getRestaurant(@PathVariable("id") String id) {
    return ResponseEntity.ok(RestaurantDto.mapToDto(restaurantService.getRestaurant(id)));
  }

  @GetMapping
  public ResponseEntity<List<RestaurantDto>> getRestaurants() {
    return ResponseEntity.ok(restaurantService.getRestaurants().stream().map(RestaurantDto::mapToDto).toList());
  }

  @PostMapping
  public ResponseEntity<RestaurantDto> createRestaurant(@Valid @RequestBody Restaurant restaurant) {
    return ResponseEntity.ok(RestaurantDto.mapToDto(restaurantService.createRestaurant(restaurant)));
  }

  @PatchMapping(value = "/{id}")
  public ResponseEntity<RestaurantDto> updateRestaurant(@PathVariable("id") String id,
    @Valid @RequestBody Restaurant restaurant) {
    return ResponseEntity.ok(RestaurantDto.mapToDto(restaurantService.updateRestaurant(id, restaurant)));
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Object> deleteRestaurant(@PathVariable("id") String id) {
    restaurantService.deleteRestaurant(id);
    return ResponseEntity.noContent().build();
  }
}
