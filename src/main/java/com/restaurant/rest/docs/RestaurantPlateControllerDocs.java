package com.restaurant.rest.docs;

import com.restaurant.rest.dto.ErrorDto;
import com.restaurant.rest.dto.RestaurantDto;
import com.restaurant.rest.dto.RestaurantPlateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Restaurant Plates", description = "APIs to interact with plates of the restaurants")
public interface RestaurantPlateControllerDocs {

  @Operation(summary = "Get the plates of a restaurant by its ID")
  @ApiResponse(responseCode = "200", description = "Plates found", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RestaurantPlateDto.class))))
  @ApiResponse(responseCode = "404", description = "Restaurant not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class, example = "{ \"code\": \"NOT_FOUND\", \"message\": \"Restaurant not found\" }")))
  ResponseEntity<List<RestaurantPlateDto>> getPlates(
    @Parameter(description = "ID of the restaurant to get the plates from") @PathVariable("restaurantId")
    String restaurantId);

  @Operation(summary = "Update a plate price from a restaurant")
  @ApiResponse(responseCode = "200", description = "Plate updated with success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantPlateDto.class)))
  @ApiResponse(responseCode = "400", description = "Bad request - price is mandatory", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class, example = "{ \"code\": \"BAD_REQUEST\", \"message\": \"price is mandatory\" }")))
  @ApiResponse(responseCode = "404", description = "Restaurant not found / Plate not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class, example = "{ \"code\": \"NOT_FOUND\", \"message\": \"Cannot update the combination of plate and restaurant provided\" }")))
  ResponseEntity<RestaurantPlateDto> updateRestaurantPlate(
    @Parameter(description = "ID of the restaurant to update the plates from") @PathVariable("restaurantId")
    String restaurantId,
    @Parameter(description = "ID of the plate to update the price") @PathVariable("plateId") String plateId,
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Price to update", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantPlateDto.class), examples = @ExampleObject(value = "{ \"price\": \"29.99\" }")))
    @RequestBody RestaurantPlateDto restaurantPlateDto);

  @Operation(summary = "Add a plate to a restaurant")
  @ApiResponse(responseCode = "201", description = "Plate added with success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantPlateDto.class)))
  @ApiResponse(responseCode = "400", description = "Bad request - Plate does not exist", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class, example = "{ \"code\": \"BAD_REQUEST\", \"message\": \"Cannot add a plate that does not exist to a restaurant\" }")))
  @ApiResponse(responseCode = "404", description = "Restaurant not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class, example = "{ \"code\": \"NOT_FOUND\", \"message\": \"Cannot add a plate to a restaurant that does not exist\" }")))
  ResponseEntity<RestaurantPlateDto> addPlateToRestaurant(
    @Parameter(description = "ID of the restaurant to add the plate to") @PathVariable("restaurantId")
    String restaurantId,
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Plate to add", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantDto.class), examples = @ExampleObject(value = "{ \"id\": \"plate-id\" }")))
    @RequestBody RestaurantPlateDto restaurantPlateDto);

  @Operation(summary = "Remove a plate from a restaurant")

  @ApiResponse(responseCode = "204", description = "Plate removed from restaurant", content = @Content)
  @ApiResponse(responseCode = "400", description = "Bad request - Plate is not connected to restaurant or some of them does not exist", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class, example = "{ \"code\": \"BAD_REQUEST\", \"message\": \"Cannot delete the combination of plate and restaurant provided\" }")))
  ResponseEntity<Void> removePlateFromRestaurant(
    @Parameter(description = "ID of the restaurant to get the plate removed from") @PathVariable("restaurantId")
    String restaurantId, @Parameter(description = "ID of the plate to remove") @PathVariable("plateId") String plateId);
}
