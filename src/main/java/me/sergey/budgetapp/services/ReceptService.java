package me.sergey.budgetapp.services;

import me.sergey.budgetapp.model.Recept;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public interface ReceptService {
    Recept createRecept(Recept recept);

    Recept getReceptID(int id);

    Recept deleteRecept(int id);

    Recept updateRecept(int id, Recept recept);

    Map<Integer, Recept> getReceptMap();

    Path createReceptTextFiles() throws IOException;
}
