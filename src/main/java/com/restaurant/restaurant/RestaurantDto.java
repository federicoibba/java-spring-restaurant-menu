package com.restaurant.restaurant;

import com.restaurant.plate.PlateDto;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

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
