package me.sergey.budgetapp.services;

public interface FilesService {
    boolean saveIngredientToFile(String json);

    String readIngredientFromFile();
    boolean saveReceptToFile(String json);
    String readReceptFromFile();
}
