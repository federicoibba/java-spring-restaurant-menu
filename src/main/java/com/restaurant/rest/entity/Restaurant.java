package com.restaurant.rest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name = "restaurants")
public class Restaurant {
  @Id
  @Column(name = "id", length = 36)
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @NotBlank(message = "name is required")
  @Column(name = "name", length = 60, nullable = false)
  private String name;

  @NotBlank(message = "address is required")
  @Column(name = "address", length = 50, nullable = false)
  private String address;

  @NotBlank(message = "city is required")
  @Column(name = "city", length = 30, nullable = false)
  private String city;

  @NotBlank(message = "zip is required")
  @Column(name = "zip", length = 10, nullable = false)
  private String zip;

  @NotBlank(message = "vatNumber is required")
  @Column(name = "vat_number", length = 20, nullable = false, unique = true)
  private String vatNumber;

  @OneToMany(mappedBy = "restaurant")
  private Set<RestaurantPlate> restaurantPlates;
}
