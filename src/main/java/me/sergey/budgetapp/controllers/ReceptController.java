package me.sergey.budgetapp.controllers;

import me.sergey.budgetapp.model.Ingredient;
import me.sergey.budgetapp.model.Recept;
import me.sergey.budgetapp.services.IngredientService;
import me.sergey.budgetapp.services.ReceptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("recept")
public class ReceptController {
    private final ReceptService receptService;
    private final IngredientService ingredientService;

    public ReceptController(ReceptService receptService, IngredientService ingredientService) {
        this.receptService = receptService;
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity<Recept> createRecept(@RequestBody Recept recept) {
        Recept createRecept = receptService.createRecept(recept);
        return ResponseEntity.ok(createRecept);
    }

    @GetMapping("{id}")
    public ResponseEntity<Recept> getRecept(@PathVariable int id) {
        Recept recept = receptService.getReceptID(id);
        if (recept == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recept);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Recept> deleteRecept(@PathVariable int id) {
        Recept deleteRecept = receptService.deleteRecept(id);
        return ResponseEntity.ok(deleteRecept);
    }

    @PutMapping()
    public ResponseEntity<Recept> updateRecept(@RequestBody Recept recept, @PathVariable int id) {
        Recept updateRecept = receptService.updateRecept(id, recept);
        return ResponseEntity.ok(updateRecept);
    }

    @GetMapping()
    public ResponseEntity getList() {
        return ResponseEntity.ok(receptService.getReceptMap());
    }

}
