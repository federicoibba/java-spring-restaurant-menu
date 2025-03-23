package com.restaurant.restaurantplate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantPlateRepository extends JpaRepository<RestaurantPlate, String> {
  List<RestaurantPlate> findByRestaurantId(String restaurantId);

  @Query("SELECT rp FROM RestaurantPlate rp WHERE rp.id.restaurantId = :restaurantId AND rp.id.plateId = :plateId")
  Optional<RestaurantPlate> findByRestaurantIdAndPlateId(@Param("restaurantId") String restaurantId, @Param("plateId") String plateId);
}
