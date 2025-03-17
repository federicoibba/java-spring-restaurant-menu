package com.restaurant.plate;

import com.restaurant.ingredient.Ingredient;
import com.restaurant.restaurantplate.RestaurantPlate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "plates")
public class Plate {
  @Id
  @Column(name = "id", length = 36)
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(name = "name", length = 60, nullable = false)
  private String name;

  @OneToMany(mappedBy = "plate")
  private List<RestaurantPlate> restaurantPlates;

  @ManyToMany
  @JoinTable(
    name = "plate_ingredients",
    joinColumns = @JoinColumn(name = "plate_id"),
    inverseJoinColumns = @JoinColumn(name = "ingredient_id")
  )
  private List<Ingredient> ingredients;
}
