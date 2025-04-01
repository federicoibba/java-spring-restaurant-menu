package com.restaurant.rest.repository;

import com.restaurant.rest.entity.Plate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlateRepository extends JpaRepository<Plate, String> {
  Optional<Plate> findByName(String name);
}
