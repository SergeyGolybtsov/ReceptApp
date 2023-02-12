package me.sergey.budgetapp.services.impl;

import me.sergey.budgetapp.model.Recept;
import me.sergey.budgetapp.services.ReceptService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReceptServiceImpl implements ReceptService {
    private static int generatorId = 0;
    private final Map<Integer, Recept> receptMap = new HashMap<>();

    @Override
    public Recept createRecept(Recept recept) {
        receptMap.put(generatorId++, recept);
        return recept;
    }

    @Override
    public Recept getReceptID(int id) {
        return receptMap.get(id);
    }

    @Override
    public Recept deleteRecept(int id) {
        if (receptMap.containsKey(id)) {
            return receptMap.remove(id);
        } else {
            return null;
        }
    }

    @Override
    public Recept updateRecept(int id, Recept recept) {
        if (receptMap.containsKey(id)){
            receptMap.put(id, recept);
            return recept;
        }
        return null;
    }

    @Override
    public Map<Integer, Recept> getReceptMap() {
        return receptMap;
    }
}
