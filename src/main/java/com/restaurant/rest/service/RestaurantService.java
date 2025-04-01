package com.restaurant.rest.service;

import com.restaurant.rest.entity.Restaurant;
import com.restaurant.rest.exception.BadRequestException;
import com.restaurant.rest.exception.ExceptionErrors;
import com.restaurant.rest.exception.NotFoundException;
import com.restaurant.rest.repository.RestaurantRepository;
import com.restaurant.rest.dto.RestaurantDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RestaurantService {
  private RestaurantRepository restaurantRepository;

  public RestaurantDto getRestaurant(String id) {
    return restaurantRepository.findById(id).map(RestaurantDto::mapToDto).orElseThrow(
            () -> new NotFoundException(ExceptionErrors.RESTAURANT_NOT_FOUND.getMessage() + id)
    );
  }

  public List<RestaurantDto> getRestaurants() {
    return restaurantRepository.findAll().stream().map(RestaurantDto::mapToDto).toList();
  }

  public RestaurantDto createRestaurant(Restaurant restaurant) {
    return RestaurantDto.mapToDto(restaurantRepository.save(restaurant));
  }

  @Transactional
  public RestaurantDto updateRestaurant(String id, Restaurant restaurant) {
    Restaurant restaurantToUpdate = restaurantRepository.findById(id).orElseThrow(
            () -> new BadRequestException(ExceptionErrors.RESTAURANT_UPDATE_NOT_FOUND.getMessage())
    );

    updateRestaurantFields(restaurant, restaurantToUpdate);

    return RestaurantDto.mapToDto(restaurantToUpdate);
  }

  public void deleteRestaurant(String id) {
    Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(
            () -> new BadRequestException(ExceptionErrors.RESTAURANT_DELETE_NOT_FOUND.getMessage())
    );
    restaurantRepository.delete(restaurant);
  }

  private void updateRestaurantFields(Restaurant source, Restaurant target) {
    Optional.ofNullable(source.getName()).ifPresent(target::setName);
    Optional.ofNullable(source.getAddress()).ifPresent(target::setAddress);
    Optional.ofNullable(source.getCity()).ifPresent(target::setCity);
    Optional.ofNullable(source.getZip()).ifPresent(target::setZip);
    Optional.ofNullable(source.getVatNumber()).ifPresent(target::setVatNumber);
  }
}
