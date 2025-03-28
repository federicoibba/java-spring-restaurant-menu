package com.restaurant.rest.repository;

import com.restaurant.rest.entity.Ingredient;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, String> {
}
