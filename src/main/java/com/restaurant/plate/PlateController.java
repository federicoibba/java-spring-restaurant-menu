package com.restaurant.plate;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/plate")
@AllArgsConstructor
public class PlateController {
  private PlateService plateService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<Plate> getPlate(@PathVariable("id") String id) {
    return ResponseEntity.ok().body(plateService.getPlate(id));
  }

  @PostMapping(value = "/")
  public ResponseEntity<Plate> savePlate(@RequestBody Plate plate) {
    try {
      plateService.savePlate(plate);
      return new ResponseEntity<>(plate, HttpStatusCode.valueOf(201));
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatusCode.valueOf(500));
    }
  }
}
