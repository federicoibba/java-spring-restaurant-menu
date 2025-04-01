package com.restaurant.rest.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionErrors {

  // Ingredient Errors
  INGREDIENT_ALREADY_EXIST("Ingredient already exists"),
  INGREDIENT_NOT_FOUND("Ingredient not found with id "),
  INGREDIENT_UPDATE_NOT_FOUND("Cannot update an ingredient that does not exist"),
  INGREDIENT_DELETE_NOT_FOUND("Cannot delete an ingredient that does not exist"),

  // Plate Errors
  PLATE_NOT_FOUND("Plate not found with id "),
  PLATE_UPDATE_NOT_FOUND("Cannot update a plate that does not exist"),
  PLATE_DELETE_NOT_FOUND("Cannot delete a plate that does not exist"),
  PLATE_ALREADY_EXIST("Plate already exist"),
  PLATE_ADD_INGREDIENT_PLATE_NOT_FOUND("Cannot add an ingredient to a plate that does not exist"),
  PLATE_ADD_INGREDIENT_NOT_FOUND("Cannot add an ingredient that does not exist"),
  PLATE_REMOVE_INGREDIENT_PLATE_NOT_FOUND("Cannot remove an ingredient from a plate that does not exist"),
  PLATE_REMOVE_INGREDIENT_NOT_FOUND("Cannot remove an ingredient that does not exist from a plate");


  private final String message;
}
