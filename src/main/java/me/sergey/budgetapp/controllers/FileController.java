package me.sergey.budgetapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import me.sergey.budgetapp.services.FilesService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileController {
    private final FilesService filesService;

    public FileController(FilesService filesService) {
        this.filesService = filesService;
    }

    @GetMapping(value = "export/recipes")
    @Operation(
            summary = "Скачка рецептов в файле"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Рецепты скачены"
    )

    public ResponseEntity<InputStreamResource> exportRecipeDataFile() throws FileNotFoundException {
        File file = filesService.getReceptDataFile();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Recipes.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "export/ingredient")
    @Operation(
            summary = "Скачка ингредиентов в файле"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ингредиенты скачены"
    )

    public ResponseEntity<InputStreamResource> exportIngredientDataFile() throws FileNotFoundException {
        File file = filesService.getIngredientDataFile();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Ingredient.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Operation(
            summary = "Добавление рецептов файлом"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Рецепты добалены"
    )
    @PostMapping(value = "import/recipes", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> importRecipeDataFile(@RequestParam MultipartFile file) {
        try {
            filesService.importReceptDataFile(file);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }

    @Operation(
            summary = "Добавление ингредиентов файлом"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ингредиенты добавлены"
    )
    @PostMapping(value = "import/ingredients", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> importIngredientDataFile(@RequestParam MultipartFile file) {
        try {
            filesService.importIngredientDataFile(file);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }
}