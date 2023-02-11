package me.sergey.budgetapp.services;

import me.sergey.budgetapp.model.Ingredient;

public interface IngredientService {
    Ingredient createIngredient(Ingredient ingredient);
    Ingredient getIngredientID(int id);

}
