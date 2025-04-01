package com.restaurant.rest.docs;

import com.restaurant.rest.dto.ErrorDto;
import com.restaurant.rest.dto.RestaurantDto;
import com.restaurant.rest.entity.Restaurant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Restaurant", description = "APIs to interact with restaurants")
public interface RestaurantControllerDocs {

  @Operation(summary = "Get a restaurant by its ID")
  @ApiResponse(
    responseCode = "200",
    description = "Restaurant found",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantDto.class))
  )
  @ApiResponse(
    responseCode = "404",
    description = "Restaurant not found",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class, example = "{ \"code\": \"NOT_FOUND\", \"message\": \"Restaurant not found\" }"))
  )
  ResponseEntity<RestaurantDto> getRestaurant(@Parameter(description = "ID of the restaurant to retrieve") @PathVariable("id") String id);

  @Operation(summary = "Get a list of restaurants")
  @ApiResponse(
    responseCode = "200",
    description = "Restaurants found",
    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RestaurantDto.class)))
  )
  ResponseEntity<List<RestaurantDto>> getRestaurants();

  @Operation(summary = "Create a restaurant")
  @ApiResponse(
    responseCode = "201",
    description = "Restaurant created successfully",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantDto.class))
  )
  @ApiResponse(
    responseCode = "400",
    description = "Bad request - A field was mandatory",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class, example = "{ \"code\": \"BAD_REQUEST\", \"message\": \"name is mandatory\" }"))
  )
  ResponseEntity<RestaurantDto> createRestaurant(@Valid
  @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Restaurant to create", required = true,
    content = @Content(mediaType = "application/json",
      schema = @Schema(implementation = RestaurantDto.class),
      examples = @ExampleObject(value = "{ \"name\": \"My Restaurant\", \"address\": \"Via Roma\", \"city\": \"Guspini\", \"zip\": \"09036\", \"vatNumber\": \"0123456789\"  }")))
  @RequestBody Restaurant restaurant);

  @Operation(summary = "Update a restaurant given its ID")
  @ApiResponse(
    responseCode = "200",
    description = "Restaurant updated successfully",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantDto.class))
  )
  @ApiResponse(
    responseCode = "400",
    description = "Bad request - A field was mandatory",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class, example = "{ \"code\": \"BAD_REQUEST\", \"message\": \"name is mandatory\" }"))
  )
  @ApiResponse(
    responseCode = "404",
    description = "Not found",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class, example = "{ \"code\": \"NOT_FOUND\", \"message\": \"Cannot update a restaurant that does not exist\" }"))
  )
  ResponseEntity<RestaurantDto> updateRestaurant(@Parameter(description = "ID of the restaurant to update") @PathVariable("id") String id,
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Restaurant to update", required = true,
      content = @Content(mediaType = "application/json",
        schema = @Schema(implementation = RestaurantDto.class),
        examples = @ExampleObject(value = "{ \"name\": \"My Restaurant\", \"address\": \"Via Roma\", \"city\": \"Guspini\", \"zip\": \"09036\", \"vatNumber\": \"0123456789\"  }")))
    @RequestBody Restaurant restaurant);

  @Operation(summary = "Delete a restaurant by ID")
  @ApiResponse(
    responseCode = "204",
    description = "Restaurant deleted successfully",
    content = @Content()
  )
  @ApiResponse(
    responseCode = "404",
    description = "Restaurant to delete not found",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class, example = "{ \"code\": \"NOT_FOUND\", \"message\": \"Cannot delete a restaurant that does not exist\" }"))
  )
  ResponseEntity<Object> deleteRestaurant(@Parameter(description = "ID of the restaurant to delete") @PathVariable("id") String id);
}
