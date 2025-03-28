package com.restaurant.rest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ingredients")
public class Ingredient {
  @Id
  @Column(name="id", length = 36)
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(name = "name", length = 50, nullable = false)
  private String name;

  @ManyToMany(mappedBy = "ingredients")
  private List<Plate> plates;
}
