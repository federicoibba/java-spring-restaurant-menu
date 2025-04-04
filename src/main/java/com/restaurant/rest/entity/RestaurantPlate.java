package com.restaurant.rest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.stereotype.Component;

@Entity
@Component
@Data
public class RestaurantPlate {
  @EmbeddedId
  private RestaurantPlateId id;

  @ManyToOne
  @MapsId("restaurantId")
  @JoinColumn(name = "restaurant_id", columnDefinition = "VARCHAR(36)")
  private Restaurant restaurant;

  @ManyToOne
  @MapsId("plateId")
  @JoinColumn(name = "plate_id", columnDefinition = "VARCHAR(36)")
  private Plate plate;

  @NotNull
  @Column(nullable = false)
  private Double price;
}
