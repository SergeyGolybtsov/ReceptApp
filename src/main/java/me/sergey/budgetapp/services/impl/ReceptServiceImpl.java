package me.sergey.budgetapp.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.sergey.budgetapp.model.Recept;
import me.sergey.budgetapp.services.FilesService;
import me.sergey.budgetapp.services.ReceptService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReceptServiceImpl implements ReceptService {
    private static int generatorId = 0;
    private Map<Integer, Recept> receptMap = new HashMap<>();
    private final FilesService filesService;

    public ReceptServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }

    @Override
    public Recept createRecept(Recept recept) {
        receptMap.put(generatorId++, recept);
        saveToFile();
        return recept;
    }

    @Override
    public Recept getReceptID(int id) {
        return receptMap.get(id);
    }

    @Override
    public Recept deleteRecept(int id) {
        if (receptMap.containsKey(id)) {
            Recept removedRecipe = receptMap.remove(id);
            saveToFile();
            return removedRecipe;
        } else {
            return null;
        }
    }

    @Override
    public Recept updateRecept(int id, Recept recept) {
        if (receptMap.containsKey(id)) {
            receptMap.put(id, recept);
            saveToFile();
            return recept;
        }
        return null;
    }

    @Override
    public Map<Integer, Recept> getReceptMap() {
        return receptMap;
    }

    @Override
    public Path createReceptTextFiles() throws IOException {
        Path path = filesService.creatingEmptyFile("recipes");
        try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            for (Recept recipe : receptMap.values()) {
                writer.append(recipe.toString());
                writer.append("\n");
            }
        }
        return path;
    }

    @PostConstruct
    private void use() {
        readFromFile();
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(receptMap);
            filesService.saveReceptToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile() {
        String json = filesService.readReceptFromFile();
        try {
            if (!json.isBlank()) {
                receptMap = new ObjectMapper().readValue(json, new TypeReference<>() {
                });
                generatorId = receptMap.size();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
