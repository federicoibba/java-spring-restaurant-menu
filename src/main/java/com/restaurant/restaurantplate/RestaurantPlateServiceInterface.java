package com.restaurant.restaurantplate;

import java.util.List;

public interface RestaurantPlateServiceInterface {
  List<RestaurantPlateDto> getPlates(String restaurantId);
  RestaurantPlateDto addPlateToRestaurant(String restaurantId, RestaurantPlateDto restaurantPlateDto);
  void removePlateFromRestaurant(String restaurantId, String plateId);
}
