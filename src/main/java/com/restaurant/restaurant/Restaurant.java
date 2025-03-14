package com.restaurant.restaurant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name="restaurants")
public class Restaurant {
  @Id
  @Column(name = "id", length = 36)
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(name = "name", length = 60, nullable = false)
  private String name;

  @Column(name = "address", length = 50, nullable = false)
  private String address;

  @Column(name = "city", length = 30, nullable = false)
  private String city;

  @Column(name = "zip", length = 10, nullable = false)
  private String zip;

  @Column(name = "vat_number", length = 20, nullable = false, unique = true)
  private String vatNumber;
}
