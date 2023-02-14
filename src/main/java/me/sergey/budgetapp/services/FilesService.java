package me.sergey.budgetapp.services;

public interface FilesService {
    boolean saveToFile(String json);
    String readIngredientsFromFile();
}
