package com.restaurant.rest.docs;

import com.restaurant.rest.dto.ErrorDto;
import com.restaurant.rest.dto.PlateDto;
import com.restaurant.rest.entity.Ingredient;
import com.restaurant.rest.entity.Plate;
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

@Tag(name = "Plate", description = "APIs to interact with plates")
public interface PlateControllerDocs {

  @Operation(summary = "Get a plate by its ID")
  @ApiResponse(responseCode = "200", description = "Plate found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlateDto.class)))
  @ApiResponse(responseCode = "404", description = "Plate not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class, example = "{ \"code\": \"NOT_FOUND\", \"message\": \"Plate not found\" }")))
  ResponseEntity<PlateDto> getPlate(
    @Parameter(description = "ID of the plate to retrieve") @PathVariable("id") String id);

  @Operation(summary = "Get a list of plates")
  @ApiResponse(responseCode = "200", description = "Plates found", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PlateDto.class))))
  ResponseEntity<List<PlateDto>> getPlates();

  @Operation(summary = "Create a plate")
  @ApiResponse(responseCode = "201", description = "Plate created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlateDto.class)))
  @ApiResponse(responseCode = "400", description = "Bad request - name is mandatory", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class, example = "{ \"code\": \"BAD_REQUEST\", \"message\": \"name is mandatory\" }")))
  ResponseEntity<PlateDto> savePlate(@Valid
  @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Plate to create", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlateDto.class), examples = @ExampleObject(value = "{ \"name\": \"Onion soup\" }")))
  @RequestBody Plate plate);

  @Operation(summary = "Update a plate given its ID")
  @ApiResponse(responseCode = "200", description = "Plate updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlateDto.class)))
  @ApiResponse(responseCode = "400", description = "Bad request - name is mandatory", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class, example = "{ \"code\": \"BAD_REQUEST\", \"message\": \"name is mandatory\" }")))
  @ApiResponse(responseCode = "404", description = "Plate not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class, example = "{ \"code\": \"NOT_FOUND\", \"message\": \"Cannot update a plate that does not exist\" }")))
  ResponseEntity<PlateDto> updatePlate(@PathVariable("id") String id,
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Plate to update", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlateDto.class), examples = @ExampleObject(value = "{ \"name\": \"Onion soup\" }")))
    @RequestBody Plate plate);

  @Operation(summary = "Delete a plate by ID")
  @ApiResponse(responseCode = "204", description = "Plate deleted successfully", content = @Content())
  @ApiResponse(responseCode = "404", description = "Plate not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class, example = "{ \"code\": \"NOT_FOUND\", \"message\": \"Cannot delete a plate that does not exist\" }")))
  ResponseEntity<Object> deletePlate(@PathVariable("id") String id);

  @Operation(summary = "Add an ingredient to the plate")
  @ApiResponse(responseCode = "204", description = "Ingredient added successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlateDto.class)))
  @ApiResponse(responseCode = "400", description = "Bad request - Ingredient does not exist", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class, example = "{ \"code\": \"NOT_FOUND\", \"message\": \"Cannot add an ingredient that does not exist\" }")))
  @ApiResponse(responseCode = "404", description = "Not found - Plate does not exist", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class, example = "{ \"code\": \"NOT_FOUND\", \"message\": \"Cannot add an ingredient to a plate that does not exist\" }")))
  ResponseEntity<PlateDto> addIngredient(@PathVariable("id") String plateId,
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Ingrediet to add to the plate", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlateDto.class), examples = @ExampleObject(value = "{ \"id\": \"Ingredient ID\" }")))
    @RequestBody Ingredient ingredient);

  @Operation(summary = "Remove an ingredient from the plate")
  @ApiResponse(responseCode = "204", description = "Ingredient removed successfully", content = @Content)
  @ApiResponse(responseCode = "400", description = "Bad request - Ingredient was never in the plate", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class, example = "{ \"code\": \"NOT_FOUND\", \"message\": \"Cannot delete an ingredient from the plate because it was never one of its ingredients\" }")))
  @ApiResponse(responseCode = "404", description = "Not found - Plate does not exist / Ingredient does not exist", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class, example = "{ \"code\": \"NOT_FOUND\", \"message\": \"Cannot remove an ingredient that does not exist from a plate\" }")))
  ResponseEntity<Object> removeIngredient(@PathVariable("plateId") String plateId,
    @PathVariable("ingredientId") String ingredientId);

}
