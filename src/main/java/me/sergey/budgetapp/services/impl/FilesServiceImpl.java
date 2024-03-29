package me.sergey.budgetapp.services.impl;

import me.sergey.budgetapp.services.FilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
@Service
public class FilesServiceImpl implements FilesService {

    @Value("{path.to.data.file}")
    private String dataFilePath;
    @Value("{name.of.data.file}")
    private String dataFileName;

@Override
    public boolean saveToFile(String json) {
        try {
            Files.writeString(Path.of(dataFilePath), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String readIngredientsFromFile() {
        try {
            return Files.readString(Path.of(dataFilePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean cleanDataFile() {
        try {
            Path path = Path.of(dataFilePath);
            Files.deleteIfExists(Path.of(dataFilePath));
            Files.createFile(Path.of(dataFilePath));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

