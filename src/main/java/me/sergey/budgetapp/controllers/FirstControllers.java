package me.sergey.budgetapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Главная", description = "Главная страница с информацией")
public class FirstControllers {

    @GetMapping
    @Operation(
            summary = "Главная страница приложения"
    )
    public String nameProject() {
        return "Hello, web";
    }

    @GetMapping("/info")
    @Operation(
            summary = "Страница с информацией"
    )
    public String page() {
        return """
                <h1>Info</h1>
                <b>Ученик:</b> Сергей Голубцов <br>
                <b>Проект:</b> Сайт с рецептами.<br>
                <b>Начало проекта:</b> 05.02.2023<br>
                <b>Описание:</b> Сайт для тех кто любит вкусненько покушать.<br>
                <b>Язык программирования:</b> Java (corretto-17.0.6)<br>
                <b>Фреймворк</b> Spring<br>
                """;
    }
}