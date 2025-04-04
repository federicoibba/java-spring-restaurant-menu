package com.restaurant.rest.service;

import com.restaurant.rest.dto.RestaurantPlateDto;
import com.restaurant.rest.entity.Plate;
import com.restaurant.rest.entity.Restaurant;
import com.restaurant.rest.entity.RestaurantPlate;
import com.restaurant.rest.entity.RestaurantPlateId;
import com.restaurant.rest.exception.BadRequestException;
import com.restaurant.rest.exception.ExceptionErrors;
import com.restaurant.rest.exception.NotFoundException;
import com.restaurant.rest.repository.RestaurantPlateRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RestaurantPlateService {
  private RestaurantPlateRepository restaurantPlateRepository;
  private RestaurantService restaurantService;
  private PlateService plateService;

  public List<RestaurantPlate> getPlates(String restaurantId) {
    if (!restaurantService.doesRestaurantExist(restaurantId)) {
      throw new NotFoundException(ExceptionErrors.RESTAURANT_NOT_FOUND.getMessage() + restaurantId);
    }

    return restaurantPlateRepository.findByRestaurantId(restaurantId);
  }

  @Transactional
  public RestaurantPlate updateRestaurantPlate(String restaurantId, String plateId, Double price) {
    Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
    Plate plate = plateService.getPlate(plateId);

    RestaurantPlate restaurantPlateEntry = restaurantPlateRepository.findByRestaurantIdAndPlateId(restaurant.getId(),
        plate.getId())
      .orElseThrow(() -> new BadRequestException(ExceptionErrors.RESTAURANT_PLATE_CANNOT_UPDATE.getMessage()));

    restaurantPlateEntry.setPrice(price);

    return restaurantPlateEntry;
  }

  @Transactional
  public RestaurantPlate addPlateToRestaurant(String restaurantId, RestaurantPlateDto restaurantPlateDto) {
    Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
    Plate plate = plateService.getPlate(restaurantPlateDto.getPlateId());

    Optional<RestaurantPlate> restaurantPlateEntry = restaurantPlateRepository.findByRestaurantIdAndPlateId(
      restaurantId, restaurantPlateDto.getPlateId());

    if (restaurantPlateEntry.isPresent()) {
      throw new BadRequestException(ExceptionErrors.RESTAURANT_PLATE_ALREADY_PRESENT.getMessage());
    }

    RestaurantPlateId id = new RestaurantPlateId(restaurant.getId(), plate.getId());

    RestaurantPlate restaurantPlate = new RestaurantPlate();
    restaurantPlate.setId(id);
    restaurantPlate.setRestaurant(restaurant);
    restaurantPlate.setPlate(plate);
    restaurantPlate.setPrice(restaurantPlateDto.getPrice());

    return restaurantPlateRepository.save(restaurantPlate);
  }

  public void removePlateFromRestaurant(String restaurantId, String plateId) {
    RestaurantPlate restaurantPlate = restaurantPlateRepository.findByRestaurantIdAndPlateId(restaurantId, plateId)
      .orElseThrow(() -> new BadRequestException(ExceptionErrors.RESTAURANT_PLATE_CANNOT_DELETE.getMessage()));

    restaurantPlateRepository.delete(restaurantPlate);
  }
}
