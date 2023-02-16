package me.sergey.budgetapp.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public interface FilesService {
    boolean saveIngredientToFile(String json);
    String readIngredientFromFile();
    boolean saveReceptToFile(String json);
    String readReceptFromFile();
    void importReceptDataFile(MultipartFile file) throws IOException;
    void importIngredientDataFile(MultipartFile file) throws IOException;
    File getReceptDataFile();
    File getIngredientDataFile();

    Path creatingEmptyFile(String emptiness);
}
