package com.restaurant.plate;

import java.util.List;

public interface PlateServiceInterface {
  void savePlate(Plate plate);
  Plate getPlate(String id);
  List<Plate> getPlates();
  Plate updatePlate(String id, String name);
  void deletePlate(String id);
}
