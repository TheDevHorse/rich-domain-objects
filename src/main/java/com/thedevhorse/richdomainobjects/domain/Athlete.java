package com.thedevhorse.richdomainobjects.domain;

import java.util.Arrays;

public class Athlete {

    private String name;

    private int age;

    private Category category;

    private Athlete(String name, int age) {
        this.name = name;
        this.age = age;
        setCategory(age);
    }

    public static Athlete create(String name, int age) {
        return new Athlete(name, age);
    }

    public void changeCategory(Category category) {
        if (!isValidCategory(category)) {
            throw new IllegalArgumentException("Category not found");
        }

        this.category = category;
    }

    private boolean isValidCategory(Category category) {
        return Arrays.asList(Category.values())
                .contains(category);
    }

    private void setCategory(int age) {
        this.category = Arrays.stream(Category.values())
                .filter(c -> age >= c.minAge() && age <= c.maxAge())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Category not found for the age: " + age));
    }
}
