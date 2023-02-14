package me.sergey.budgetapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.sergey.budgetapp.model.Ingredient;
import me.sergey.budgetapp.services.IngredientService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("ingredient")
@RestController
@Tag(name = "Ингредиенты", description = "Команды с ингридиентами")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    @Operation(
            summary = "Создание ингридиента"
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Новый ингредиент в формате JSON",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Ingredient.class)
                    )
            }
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ингредиент создан",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Ingredient.class)
                    )
            }
    )
    public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient) {
        Ingredient createdIngredient = ingredientService.createIngredient(ingredient);
        return ResponseEntity.ok(createdIngredient);
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Получение ингредиента по ID"
    )
    @Parameter(
            name = "ingredientID",
            description = "ID необходимого ингредиента",
            example = "0"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингредиент найден",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = Ingredient.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ингредиент не найден",
                            content = @Content()
                    )
            }
    )

    public ResponseEntity<Ingredient> getIngredient(@PathVariable int id) {
        Ingredient ingredient = ingredientService.getIngredientID(id);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Удаление ингредиента по ID."
    )
    @Parameter(
            name = "ingredientID",
            description = "ID необходимого ингредиента",
            example = "0"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингредиент успешно удалён",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = Ingredient.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ингредиент не найден",
                            content = @Content()
                    )
            }
    )

    public ResponseEntity<Ingredient> deleteIngredient(@PathVariable int id) {
        Ingredient deleteIngredient = ingredientService.deleteIngredient(id);
        if (deleteIngredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleteIngredient);
    }

    @PutMapping("{id}")
    @Operation(
            summary = "Изменение ингредиента по ID."
    )
    @Parameter(
            name = "ingredientID",
            description = "ID необходимого ингредиента",
            example = "0"
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Изменённый ингредиент в формате JSON",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Ingredient.class)
                    )
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингредиент изменён",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = Ingredient.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ингредиент не найден",
                            content = @Content()
                    )
            }
    )

    public ResponseEntity<Ingredient> updateIngredient(@RequestBody Ingredient ingredient, @PathVariable int id) {
        Ingredient updateIngredient = ingredientService.updateIngredient(id, ingredient);
        if (ObjectUtils.isEmpty(ingredient)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updateIngredient);
    }

    @GetMapping()
    @Operation(
            summary = "Получение всех ингредиентов."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ингредиенты найдены",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                    )
            }
    )
    public ResponseEntity<Map<Integer, Ingredient>> getList() {
        return ResponseEntity.ok(ingredientService.getIngredientMap());
    }
}