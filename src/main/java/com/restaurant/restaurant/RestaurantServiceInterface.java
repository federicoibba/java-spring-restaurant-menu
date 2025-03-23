package com.restaurant.restaurant;

import java.util.List;

public interface RestaurantServiceInterface {
  RestaurantDto getRestaurant(String id);
  List<RestaurantDto> getRestaurants();
  RestaurantDto createRestaurant(Restaurant restaurant);
  RestaurantDto updateRestaurant(String id, Restaurant restaurant);
  void deleteRestaurant(String id);
}
