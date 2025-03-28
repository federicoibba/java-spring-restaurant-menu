package com.restaurant.rest.service;

import com.restaurant.rest.entity.Restaurant;
import com.restaurant.rest.dto.RestaurantDto;

import java.util.List;

public interface RestaurantServiceInterface {
  RestaurantDto getRestaurant(String id);
  List<RestaurantDto> getRestaurants();
  RestaurantDto createRestaurant(Restaurant restaurant);
  RestaurantDto updateRestaurant(String id, Restaurant restaurant);
  void deleteRestaurant(String id);
}
