package com.thedevhorse.richdomainobjects.domain;

import java.util.Arrays;

public class Athlete {

    private final String name;

    private int age;

    private Category category;

    private Athlete(String name, int age) {
        this.name = name;
        setAge(age);
        setCategory(age);
    }

    public static Athlete create(String name, int age) {
        return new Athlete(name, age);
    }

    public String name() {
        return name;
    }

    public int age() {
        return age;
    }

    public Category category() {
        return category;
    }

    public void changeCategory(int age) {
        setAge(age);
        setCategory(age);
    }

    private void setAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Age must be a positive number.");
        }
        this.age = age;
    }

    private void setCategory(int age) {
        this.category = Arrays.stream(Category.values())
                .filter(c -> age >= c.minAge() && age <= c.maxAge())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Category not found for the age: " + age));
    }
}