package com.restaurant.menu.repository;

import com.restaurant.menu.model.Plate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlateRepository extends JpaRepository<Plate, String> {
}
