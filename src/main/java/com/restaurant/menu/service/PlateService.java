package com.restaurant.menu.service;

import com.restaurant.menu.model.Plate;
import com.restaurant.menu.repository.PlateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class PlateService {
  private PlateRepository plateRepository;

  public void savePlate(Plate plate) {
    plateRepository.save(plate);
  }

  public Plate getPlate(String id) {
    return plateRepository.findById(id).orElse(null);
  }
}
