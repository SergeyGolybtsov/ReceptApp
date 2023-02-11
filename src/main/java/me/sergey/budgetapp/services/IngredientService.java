package me.sergey.budgetapp.services;

import me.sergey.budgetapp.model.Ingredient;

import java.util.Map;

public interface IngredientService {
    Ingredient createIngredient(Ingredient ingredient);
    Ingredient getIngredientID(int id);
    Ingredient deleteIngredient(int id);
    Ingredient updateIngredient(int id, Ingredient ingredient);

    Map<Integer, Ingredient> getIngredientMap();
}
