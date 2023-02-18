package me.sergey.budgetapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recept {

    private String name;

    private int cookingTime;

    private List<Ingredient> ingredients;

    private List<String> stepsCooking;



    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(name).append("\n");
        builder.append("Время приготовления: ").append(cookingTime).append(" минут\n");
        builder.append("Ингредиенты:\n");
        for (Ingredient ingredient : ingredients) {
            builder.append("- ").append(ingredient).append("\n");
        }
        builder.append("Инструкция по приготовлению:\n");
        for (int i = 0; i < stepsCooking.size(); i++) {
            builder.append(i + 1).append(" - ").append(stepsCooking.get(i)).append("\n");
        }
        return builder.toString();
    }
}


