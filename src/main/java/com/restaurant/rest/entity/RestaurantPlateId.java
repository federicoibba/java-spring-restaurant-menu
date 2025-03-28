package com.restaurant.rest.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantPlateId implements Serializable {
  private String restaurantId;
  private String plateId;
}
