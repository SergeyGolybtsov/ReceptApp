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
import me.sergey.budgetapp.model.Recept;
import me.sergey.budgetapp.services.IngredientService;
import me.sergey.budgetapp.services.ReceptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("recipe")
@Tag(name = "Рецепты", description = "Меню с рецептами")
public class ReceptController {
    private final ReceptService receptService;
    private final IngredientService ingredientService;

    public ReceptController(ReceptService receptService, IngredientService ingredientService) {
        this.receptService = receptService;
        this.ingredientService = ingredientService;
    }

    @PostMapping
    @Operation(
            summary = "Создание рецепта."
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Новый рецепт в формате JSON",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Recept.class)
                    )
            }
    )
    @ApiResponse(
            responseCode = "200",
            description = "Рецепт создан",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Recept.class)
                    )
            }
    )
    public ResponseEntity<Recept> createRecept(@RequestBody Recept recept) {
        Recept createRecept = receptService.createRecept(recept);
        return ResponseEntity.ok(createRecept);
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Получение рецепта по ID."
    )
    @Parameter(
            name = "recipeID",
            description = "ID необходимого рецепта",
            example = "0"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Рецепт найден",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = Recept.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Рецепт не найден",
                            content = @Content()
                    )
            }
    )

    public ResponseEntity<Recept> getRecept(@PathVariable int id) {
        Recept recept = receptService.getReceptID(id);
        if (recept == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recept);
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Удаление рецепта по ID."
    )
    @Parameter(
            name = "recipeID",
            description = "ID необходимого рецепта",
            example = "0"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Рецепт успешно удалён",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = Recept.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Рецепт не найден",
                            content = @Content()
                    )
            }
    )

    public ResponseEntity<Recept> deleteRecept(@PathVariable int id) {
        Recept deleteRecept = receptService.deleteRecept(id);
        return ResponseEntity.ok(deleteRecept);
    }

    @PutMapping()
    @Operation(
            summary = "Изменение рецепта по ID."
    )
    @Parameter(
            name = "recipeID",
            description = "ID необходимого рецепта",
            example = "0"
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Изменённый рецепт в формате JSON",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Recept.class)
                    )
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Рецепт изменён",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = Recept.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Рецепт не найден",
                            content = @Content()
                    )
            }
    )

    public ResponseEntity<Recept> updateRecept(@RequestBody Recept recept, @PathVariable int id) {
        Recept updateRecept = receptService.updateRecept(id, recept);
        return ResponseEntity.ok(updateRecept);
    }

    @GetMapping()
    @Operation(
            summary = "Получение всех рецептов с возможностью фильтрации.",
            description = "Фильтрует рецепты по указанному id ингредиента или выдаёт все рецепты, если id не был указан"
    )
    @Parameter(
            name = "ingredientID",
            description = "ID необходимого ингредиента",
            example = "1"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Рецепты найдены",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Recept.class))
                    )
            }
    )
    public ResponseEntity getList() {
        return ResponseEntity.ok(receptService.getReceptMap());
    }

}
