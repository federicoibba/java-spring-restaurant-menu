package com.restaurant.rest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ingredients", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Ingredient {
  @Id
  @Column(name = "id", length = 36)
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @NotBlank(message = "name is mandatory")
  @Column(name = "name", length = 50, nullable = false, unique = true)
  private String name;

  @ManyToMany(mappedBy = "ingredients")
  private List<Plate> plates;
}
