package com.restaurant.rest.dto;

import com.restaurant.rest.entity.Restaurant;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantDto {
  private String id;
  private String name;
  private String address;
  private String city;
  private String zip;
  private String vatNumber;

  public static RestaurantDto mapToDto(Restaurant restaurant) {
    return RestaurantDto.builder()
      .id(restaurant.getId())
      .name(restaurant.getName())
      .address(restaurant.getAddress())
      .city(restaurant.getCity())
      .zip(restaurant.getZip())
      .vatNumber(restaurant.getVatNumber())
      .build();
  }
}
