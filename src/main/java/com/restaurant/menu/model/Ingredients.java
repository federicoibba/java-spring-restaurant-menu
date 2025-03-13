package com.restaurant.menu.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Ingredients {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private String name;
}
