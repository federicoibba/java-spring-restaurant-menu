package com.restaurant.plate;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlateService implements PlateServiceInterface {
  private PlateRepository plateRepository;

  @Override
  public void savePlate(Plate plate) {
    plateRepository.save(plate);
  }

  @Override
  public Plate getPlate(String id) {
    return plateRepository.findById(id).orElse(null);
  }

  @Override
  public List<Plate> getPlates() {
    return plateRepository.findAll();
  }

  @Override
  @Transactional
  public Plate updatePlate(String id, String name) {
    Plate plate = plateRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Plate not found"));

    if (name != null) {
      plate.setName(name);
    }

    return plate;
  }


  @Override
  @Transactional
  public void deletePlate(String id) {
    plateRepository.deleteById(id);
  }
}
