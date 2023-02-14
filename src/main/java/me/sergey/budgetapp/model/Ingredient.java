package me.sergey.budgetapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    private String name;

    private int count;

    private String measureUnit;
}
