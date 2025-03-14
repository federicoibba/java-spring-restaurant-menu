package com.restaurant.restaurant;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
public class RestaurantController {
  private RestaurantService restaurantService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<Restaurant> getRestaurant(@PathVariable("id") String id) {
    return ResponseEntity.ok(restaurantService.getRestaurant(id));
  }

  @GetMapping(value = "/")
  public ResponseEntity<List<Restaurant>> getRestaurants() {
    return ResponseEntity.ok(restaurantService.getRestaurants());
  }

  @PostMapping(value = "/")
  public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
    return ResponseEntity.ok(restaurantService.createRestaurant(restaurant));
  }

  @PatchMapping(value = "/{id}")
  public ResponseEntity<Restaurant> updateRestaurant(@PathVariable("id") String id, @RequestBody Restaurant restaurant) {
    return ResponseEntity.ok(restaurantService.updateRestaurant(id, restaurant));
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Object> deleteRestaurant(@PathVariable("id") String id) {
    restaurantService.deleteRestaurant(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
