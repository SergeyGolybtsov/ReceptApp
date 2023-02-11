package me.sergey.budgetapp.services.impl;

import me.sergey.budgetapp.model.Ingredient;
import me.sergey.budgetapp.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class IngredientServiceImpl implements IngredientService {
    private static int generatorId = 0;
    private static Map<Integer, Ingredient> ingredientMap = new HashMap<>();
    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        ingredientMap.put(generatorId++, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient getIngredientID(int id) {
        return ingredientMap.get(id);
    }

}
