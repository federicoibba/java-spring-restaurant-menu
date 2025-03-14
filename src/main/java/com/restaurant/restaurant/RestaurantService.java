package com.restaurant.restaurant;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RestaurantService implements RestaurantServiceInterface {
  private RestaurantRepository restaurantRepository;

  @Override
  public Restaurant getRestaurant(String id) {
    return restaurantRepository.findById(id).orElse(null);
  }

  @Override
  public List<Restaurant> getRestaurants() {
    return restaurantRepository.findAll();
  }

  @Override
  public Restaurant createRestaurant(Restaurant restaurant) {
    return restaurantRepository.save(restaurant);
  }

  @Transactional
  @Override
  public Restaurant updateRestaurant(String id, Restaurant restaurant) {
    Restaurant restaurantToUpdate = restaurantRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Restaurant not found"));

    updateRestaurantFields(restaurant, restaurantToUpdate);

    return restaurantToUpdate;
  }

  @Override
  public void deleteRestaurant(String id) {
    restaurantRepository.deleteById(id);
  }

  private void updateRestaurantFields(Restaurant source, Restaurant target) {
    Optional.ofNullable(source.getName()).ifPresent(target::setName);
    Optional.ofNullable(source.getAddress()).ifPresent(target::setAddress);
    Optional.ofNullable(source.getCity()).ifPresent(target::setCity);
    Optional.ofNullable(source.getZip()).ifPresent(target::setZip);
    Optional.ofNullable(source.getVatNumber()).ifPresent(target::setVatNumber);
  }
}
