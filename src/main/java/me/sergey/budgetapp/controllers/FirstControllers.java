package me.sergey.budgetapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstControllers {

    @GetMapping
    public String nameProject() {
        return "Hello, web";
    }
    @GetMapping("/info")
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