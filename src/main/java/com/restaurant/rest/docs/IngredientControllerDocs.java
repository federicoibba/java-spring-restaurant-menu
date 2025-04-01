package com.restaurant.rest.docs;

import com.restaurant.rest.dto.ErrorDto;
import com.restaurant.rest.dto.IngredientDto;
import com.restaurant.rest.entity.Ingredient;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Ingredient", description = "APIs to interact with ingredients")
public interface IngredientControllerDocs {

    @Operation(summary = "Get an ingredient by its ID")
    @ApiResponse(
            responseCode = "200",
            description = "Ingredient found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = IngredientDto.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Ingredient not found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class, example = "{ \"code\": \"NOT_FOUND\", \"message\": \"Ingredient not found\" }"))
    )
    ResponseEntity<IngredientDto> getIngredient(@Parameter(description = "ID of the ingredient to be searched") @PathVariable("id") String id);

    @Operation(summary = "Get a list of ingredients")
    @ApiResponse(
            responseCode = "200",
            description = "Ingredients found",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = IngredientDto.class)))
    )
    ResponseEntity<List<IngredientDto>> getIngredients();

    @Operation(summary = "Create an ingredient")
    @ApiResponse(
            responseCode = "201",
            description = "Ingredient created successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = IngredientDto.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Bad request",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class, example = "{ \"code\": \"BAD_REQUEST\", \"message\": \"name is mandatory\" }"))
    )
    ResponseEntity<IngredientDto> createIngredient(
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Ingredient to create", required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IngredientDto.class),
                            examples = @ExampleObject(value = "{ \"name\": \"Onion\" }")))
            @RequestBody Ingredient ingredient
    );

    @Operation(summary = "Update an ingredient given its ID")
    @ApiResponse(
            responseCode = "200",
            description = "Ingredient updated successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = IngredientDto.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Bad request",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class, example = "{ \"code\": \"BAD_REQUEST\", \"message\": \"name is mandatory\" }"))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class, example = "{ \"code\": \"NOT_FOUND\", \"message\": \"Cannot update an ingredient that does not exist\" }"))
    )
    ResponseEntity<IngredientDto> updateIngredient(
            @Parameter(description = "ID of the ingredient to update") @PathVariable("id") String id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Ingredient fields to update", required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IngredientDto.class),
                            examples = @ExampleObject(value = "{ \"name\": \"Onion\" }")))
            @RequestBody Ingredient ingredient
    );

    @Operation(summary = "Delete an ingredient by ID")
    @ApiResponse(
            responseCode = "204",
            description = "Ingredient deleted successfully",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class, example = "{ \"code\": \"NOT_FOUND\", \"message\": \"Cannot delete an ingredient that does not exist\" }"))
    )
    ResponseEntity<Object> deleteIngredient(@Parameter(description = "ID of the ingredient to delete") @PathVariable("id") String id);
}
