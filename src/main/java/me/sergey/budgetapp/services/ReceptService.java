package me.sergey.budgetapp.services;

import me.sergey.budgetapp.model.Recept;

public interface ReceptService {
    Recept createRecept(Recept recept);
    Recept getReceptID(int id);
}
