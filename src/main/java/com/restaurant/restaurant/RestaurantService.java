package com.restaurant.restaurant;

import com.restaurant.plate.PlateDto;
import com.restaurant.plate.PlateService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RestaurantService implements RestaurantServiceInterface {
  private RestaurantRepository restaurantRepository;
  private PlateService plateService;

  @Override
  public RestaurantDto getRestaurant(String id) {
    return restaurantRepository.findById(id).map(RestaurantDto::mapToDto).orElse(null);
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
    Restaurant restaurantToUpdate = restaurantRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Restaurant not found"));

    updateRestaurantFields(restaurant, restaurantToUpdate);

    return RestaurantDto.mapToDto(restaurantToUpdate);
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
