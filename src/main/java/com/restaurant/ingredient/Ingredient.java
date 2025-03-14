package com.restaurant.ingredient;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ingredients")
public class Ingredient {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private String name;
}
