package me.sergey.budgetapp.services.impl;

import me.sergey.budgetapp.services.FilesService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
@Service
public class FilesServiceImpl implements FilesService {
    @Value(value = "${path.to.data.files}")
    private String dataFilesPath;

    @Value(value = "${name.of.recipe.data.file}")
    private String recipeDataFileName;

    @Value(value = "${name.of.ingredient.data.file}")
    private String ingredientDataFileName;

    @Override
    public String readIngredientFromFile() {
        return readFromFile(ingredientDataFileName);
    }

    @Override
    public boolean saveReceptToFile(String json) {
        return saveToFile(json, recipeDataFileName);
    }

    @Override
    public String readReceptFromFile() {
        return readFromFile(recipeDataFileName);
    }

    @Override
    public void importReceptDataFile(MultipartFile file) throws IOException {
        cleanDataFile(recipeDataFileName);
        File dataFile = getReceptDataFile();
        try (FileOutputStream fileOutputStream = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fileOutputStream);
        } catch (IOException e) {
            throw new IOException();
        }
    }

    @Override
    public void importIngredientDataFile(MultipartFile file) throws IOException {
        cleanDataFile(ingredientDataFileName);
        File dataFile = getIngredientDataFile();
        try (FileOutputStream fileOutputStream = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fileOutputStream);
        } catch (IOException e) {
            throw new IOException();
        }
    }

    @Override
    public File getReceptDataFile() {
        return new File(dataFilesPath + "/" + recipeDataFileName);
    }

    @Override
    public File getIngredientDataFile() {
        return new File(dataFilesPath + "/" + ingredientDataFileName);
    }

    @Override
    public boolean saveIngredientToFile(String json) {
        return saveToFile(json, ingredientDataFileName);
    }

    @Override
    public Path creatingEmptyFile(String emptiness){
        try {
            return Files.createTempFile(Path.of(dataFilesPath), "tempFile", emptiness);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean cleanDataFile(String dataFileName) {
        Path path = Path.of(dataFilesPath, dataFileName);
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String readFromFile(String dataFileName) {
        try {
            return Files.readString(Path.of(dataFilesPath, dataFileName));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public boolean saveToFile(String json, String dataFileName) {
        try {
            cleanDataFile(dataFileName);
            Files.writeString(Path.of(dataFilesPath, dataFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    @PostConstruct
    private void init() {
        try {
            if (Files.notExists(Path.of(dataFilesPath, recipeDataFileName))) {
                Files.createFile(Path.of(dataFilesPath, recipeDataFileName));
            }
            if (Files.notExists(Path.of(dataFilesPath, ingredientDataFileName))) {
                Files.createFile(Path.of(dataFilesPath, ingredientDataFileName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}