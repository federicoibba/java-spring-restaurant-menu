package com.restaurant.restaurant;

import java.util.List;

public interface RestaurantServiceInterface {
  Restaurant getRestaurant(String id);
  List<Restaurant> getRestaurants();

  Restaurant createRestaurant(Restaurant restaurant);
  Restaurant updateRestaurant(String id, Restaurant restaurant);
  void deleteRestaurant(String id);
}
