package com.restaurant.restaurantplate;

import com.restaurant.plate.Plate;
import com.restaurant.plate.PlateRepository;
import com.restaurant.restaurant.Restaurant;
import com.restaurant.restaurant.RestaurantRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantPlateService implements RestaurantPlateServiceInterface {
  private RestaurantPlateRepository restaurantPlateRepository;
  private RestaurantRepository restaurantRepository;
  private PlateRepository plateRepository;

  @Override
  public List<RestaurantPlateDto> getPlates(String restaurantId) {
    return restaurantPlateRepository.findByRestaurantId(restaurantId).stream().map(RestaurantPlateDto::mapToDto).toList();
  }

  @Override
  @Transactional
  public RestaurantPlateDto addPlateToRestaurant(String restaurantId, RestaurantPlateDto restaurantPlateDto) {
    Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow();
    Plate plate = plateRepository.findById(restaurantPlateDto.getPlateId()).orElseThrow();

    RestaurantPlateId id = new RestaurantPlateId(restaurant.getId(), plate.getId());

    RestaurantPlate restaurantPlate = new RestaurantPlate();
    restaurantPlate.setId(id);
    restaurantPlate.setRestaurant(restaurant);
    restaurantPlate.setPlate(plate);
    restaurantPlate.setPrice(restaurantPlateDto.getPrice());

    return RestaurantPlateDto.mapToDto(restaurantPlateRepository.save(restaurantPlate));
  }

  @Override
  public void removePlateFromRestaurant(String restaurantId, String plateId) {
    RestaurantPlate restaurantPlate = restaurantPlateRepository.findByRestaurantIdAndPlateId(restaurantId, plateId).orElseThrow();

    restaurantPlateRepository.delete(restaurantPlate);
  }
}
