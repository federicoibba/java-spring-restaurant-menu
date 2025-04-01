package com.restaurant.rest.service;

import com.restaurant.rest.entity.Restaurant;
import com.restaurant.rest.exception.BadRequestException;
import com.restaurant.rest.exception.ExceptionErrors;
import com.restaurant.rest.exception.NotFoundException;
import com.restaurant.rest.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RestaurantService {
  private RestaurantRepository restaurantRepository;

  public Restaurant getRestaurant(String id) {
    return restaurantRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(ExceptionErrors.RESTAURANT_NOT_FOUND.getMessage() + id));
  }

  public List<Restaurant> getRestaurants() {
    return restaurantRepository.findAll();
  }

  public Restaurant createRestaurant(Restaurant restaurant) {
    return restaurantRepository.save(restaurant);
  }

  @Transactional
  public Restaurant updateRestaurant(String id, Restaurant restaurant) {
    Restaurant restaurantToUpdate = restaurantRepository.findById(id)
      .orElseThrow(() -> new BadRequestException(ExceptionErrors.RESTAURANT_UPDATE_NOT_FOUND.getMessage()));

    updateRestaurantFields(restaurant, restaurantToUpdate);

    return restaurantToUpdate;
  }

  public void deleteRestaurant(String id) {
    Restaurant restaurant = restaurantRepository.findById(id)
      .orElseThrow(() -> new BadRequestException(ExceptionErrors.RESTAURANT_DELETE_NOT_FOUND.getMessage()));
    restaurantRepository.delete(restaurant);
  }

  public boolean doesRestaurantExist(String id) {
    return restaurantRepository.existsById(id);
  }

  private void updateRestaurantFields(Restaurant source, Restaurant target) {
    Optional.ofNullable(source.getName()).ifPresent(target::setName);
    Optional.ofNullable(source.getAddress()).ifPresent(target::setAddress);
    Optional.ofNullable(source.getCity()).ifPresent(target::setCity);
    Optional.ofNullable(source.getZip()).ifPresent(target::setZip);
    Optional.ofNullable(source.getVatNumber()).ifPresent(target::setVatNumber);
  }
}
