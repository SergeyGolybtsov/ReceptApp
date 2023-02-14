package me.sergey.budgetapp.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.sergey.budgetapp.model.Ingredient;
import me.sergey.budgetapp.services.FilesService;
import me.sergey.budgetapp.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientServiceImpl implements IngredientService {

    final private FilesService filesService;
    private static int generatorId = 0;
    private Map<Integer, Ingredient> ingredientMap = new HashMap<>();

    public IngredientServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }


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

    private void saveToFile() {
        try {
            String jason = new ObjectMapper().writeValueAsString(ingredientMap);
            filesService.saveToFile(jason);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFrommFile() {
        String json = filesService.readIngredientsFromFile();
        try {
            if (!json.isBlank()) {
                ingredientMap = new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Ingredient>>() {
                });
                generatorId = ingredientMap.size();
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
