package com.restaurant.rest.service;

import com.restaurant.rest.entity.Restaurant;
import com.restaurant.rest.exception.BadRequestException;
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
public class RestaurantService implements RestaurantServiceInterface {
  private RestaurantRepository restaurantRepository;

  @Override
  public RestaurantDto getRestaurant(String id) {
    return restaurantRepository.findById(id).map(RestaurantDto::mapToDto).orElseThrow(
            () -> new NotFoundException("Restaurant not found with id" + id)
    );
  }

  @Override
  public List<RestaurantDto> getRestaurants() {
    return restaurantRepository.findAll().stream().map(RestaurantDto::mapToDto).toList();
  }

  @Override
  public RestaurantDto createRestaurant(Restaurant restaurant) {
    return RestaurantDto.mapToDto(restaurantRepository.save(restaurant));
  }

  @Transactional
  @Override
  public RestaurantDto updateRestaurant(String id, Restaurant restaurant) {
    Restaurant restaurantToUpdate = restaurantRepository.findById(id).orElseThrow(
            () -> new BadRequestException("Cannot update a restaurant that does not exist")
    );

    updateRestaurantFields(restaurant, restaurantToUpdate);

    return RestaurantDto.mapToDto(restaurantToUpdate);
  }

  @Override
  public void deleteRestaurant(String id) {
    Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(
            () -> new BadRequestException("Cannot update a restaurant that does not exist")
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
