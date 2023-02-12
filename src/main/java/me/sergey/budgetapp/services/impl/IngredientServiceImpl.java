package me.sergey.budgetapp.services.impl;

import me.sergey.budgetapp.model.Ingredient;
import me.sergey.budgetapp.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static int generatorId = 0;
    private final Map<Integer, Ingredient> ingredientMap = new HashMap<>();

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        ingredientMap.put(generatorId++, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient getIngredientID(int id) {
        return ingredientMap.get(id);
    }

    @Override
    public Ingredient deleteIngredient(int id) {
        if (ingredientMap.containsKey(id)) {
            return ingredientMap.remove(id);
        } else {
            return null;
        }
    }

    @Override
    public Ingredient updateIngredient(int id, Ingredient ingredient) {
        if (ingredientMap.containsKey(id)) {
            ingredientMap.put(id, ingredient);
            return ingredient;
        }
        return null;
    }

    @Override
    public Map<Integer, Ingredient> getIngredientMap() {
        return ingredientMap;
    }

}
