package com.restaurant.restaurantplate;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class RestaurantPlateId implements Serializable {
  private String restaurantId;
  private String plateId;
}
