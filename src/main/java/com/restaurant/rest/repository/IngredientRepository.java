package com.restaurant.rest.repository;

import com.restaurant.rest.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, String> {
  Optional<Ingredient> findByName(String name);
}
