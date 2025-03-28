package com.restaurant.rest.dto;

import com.restaurant.rest.entity.RestaurantPlate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantPlateDto {
  private String plateId;
  private String name;
  private double price;

  public static RestaurantPlateDto mapToDto(RestaurantPlate restaurantPlate) {
    return RestaurantPlateDto.builder()
      .plateId(restaurantPlate.getPlate().getId())
      .name(restaurantPlate.getPlate().getName())
      .price(restaurantPlate.getPrice())
      .build();
  }
}
