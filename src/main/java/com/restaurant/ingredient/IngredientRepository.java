package com.restaurant.ingredient;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, String> {
}
