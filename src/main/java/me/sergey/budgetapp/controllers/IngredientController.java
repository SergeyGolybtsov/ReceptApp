package me.sergey.budgetapp.controllers;

import me.sergey.budgetapp.model.Ingredient;
import me.sergey.budgetapp.services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("ingredient")
@RestController
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
        ingredientService.createIngredient(new Ingredient("Селедка", 200, "гр."));
    }

    @PostMapping
    public ResponseEntity createIngredient(@RequestBody Ingredient ingredient) {
        Ingredient createdIngredient = ingredientService.createIngredient(ingredient);
        return ResponseEntity.ok(createdIngredient);
    }

    @GetMapping("{id}")
    public ResponseEntity getIngredient(@PathVariable int id) {
        Ingredient ingredient = ingredientService.getIngredientID(id);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }
}
