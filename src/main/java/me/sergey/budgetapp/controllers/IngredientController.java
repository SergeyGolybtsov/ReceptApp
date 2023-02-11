package me.sergey.budgetapp.controllers;

import me.sergey.budgetapp.model.Ingredient;
import me.sergey.budgetapp.model.Recept;
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

    @DeleteMapping("{id}")
    public ResponseEntity deleteIngredient(@PathVariable int id) {
        Ingredient deleteIngredient = ingredientService.deleteIngredient(id);
        return ResponseEntity.ok(deleteIngredient);
    }

    @PutMapping("{id}")
    public ResponseEntity updateIngredient(@RequestBody Ingredient ingredient, @PathVariable int id) {
        Ingredient updateIngredient = ingredientService.updateIngredient(id, ingredient);
        return ResponseEntity.ok(updateIngredient);
    }
    @GetMapping()
    public ResponseEntity getList() {
        return ResponseEntity.ok(ingredientService.getIngredientMap());
    }
}