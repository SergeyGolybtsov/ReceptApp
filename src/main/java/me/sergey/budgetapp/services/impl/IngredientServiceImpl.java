package me.sergey.budgetapp.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.sergey.budgetapp.model.Ingredient;
import me.sergey.budgetapp.services.FilesService;
import me.sergey.budgetapp.services.IngredientService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

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
        saveToFile();
        return ingredient;
    }

    @Override
    public Ingredient getIngredientID(int id) {
        return ingredientMap.get(id);
    }

    @Override
    public Ingredient deleteIngredient(int id) {
        if (ingredientMap.containsKey(id)) {
            Ingredient removedIngredient = ingredientMap.remove(id);
            saveToFile();
            return removedIngredient;
        } else {
            return null;
        }
    }

    @Override
    public Ingredient updateIngredient(int id, Ingredient ingredient) {
        if (ingredientMap.containsKey(id)) {
            ingredientMap.put(id, ingredient);
            saveToFile();
            return ingredient;
        }
        return null;
    }

    @Override
    public Map<Integer, Ingredient> getIngredientMap() {
        return ingredientMap;
    }

    @PostConstruct
    private void use() {
        readFromFile();
        saveToFile();
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientMap);
            filesService.saveIngredientToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile() {
        String json = filesService.readIngredientFromFile();
        try {
            if (!json.isBlank()) {
                ingredientMap = new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Ingredient>>() {
                });
                generatorId = ingredientMap.size();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
