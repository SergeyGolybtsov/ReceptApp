package me.sergey.budgetapp.services.impl;
import me.sergey.budgetapp.model.Recept;
import me.sergey.budgetapp.services.ReceptService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class ReceptServiceImpl implements ReceptService {
    private static int generatorId = 0;
    private static Map<Integer, Recept> receptMap = new HashMap<>();

    @Override
    public Recept createRecept(Recept recept) {
        receptMap.put(generatorId++, recept);
        return recept;
    }

    @Override
    public Recept getReceptID(int id) {
        return receptMap.get(id);
    }
}
