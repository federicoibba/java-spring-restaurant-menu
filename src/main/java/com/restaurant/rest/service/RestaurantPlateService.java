package com.restaurant.rest.service;

import com.restaurant.rest.entity.Plate;
import com.restaurant.rest.entity.RestaurantPlate;
import com.restaurant.rest.entity.RestaurantPlateId;
import com.restaurant.rest.exception.BadRequestException;
import com.restaurant.rest.repository.PlateRepository;
import com.restaurant.rest.repository.RestaurantPlateRepository;
import com.restaurant.rest.dto.RestaurantPlateDto;
import com.restaurant.rest.entity.Restaurant;
import com.restaurant.rest.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantPlateService {
    private RestaurantPlateRepository restaurantPlateRepository;
    private RestaurantRepository restaurantRepository;
    private PlateRepository plateRepository;

    public List<RestaurantPlateDto> getPlates(String restaurantId) {
        return restaurantPlateRepository.findByRestaurantId(restaurantId).stream().map(RestaurantPlateDto::mapToDto).toList();
    }

    @Transactional
    public RestaurantPlateDto addPlateToRestaurant(String restaurantId, RestaurantPlateDto restaurantPlateDto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new BadRequestException("Restaurant not found")
        );
        Plate plate = plateRepository.findById(restaurantPlateDto.getPlateId()).orElseThrow(
                () -> new BadRequestException("Plate not found")
        );

        RestaurantPlateId id = new RestaurantPlateId(restaurant.getId(), plate.getId());

        RestaurantPlate restaurantPlate = new RestaurantPlate();
        restaurantPlate.setId(id);
        restaurantPlate.setRestaurant(restaurant);
        restaurantPlate.setPlate(plate);
        restaurantPlate.setPrice(restaurantPlateDto.getPrice());

        return RestaurantPlateDto.mapToDto(restaurantPlateRepository.save(restaurantPlate));
    }

    public void removePlateFromRestaurant(String restaurantId, String plateId) {
        RestaurantPlate restaurantPlate = restaurantPlateRepository.findByRestaurantIdAndPlateId(restaurantId, plateId).orElseThrow(
                () -> new BadRequestException("Cannot delete the combination of plate and restaurant provided")
        );

        restaurantPlateRepository.delete(restaurantPlate);
    }
}
