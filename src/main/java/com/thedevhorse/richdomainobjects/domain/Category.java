package com.thedevhorse.richdomainobjects.domain;

import java.util.Arrays;

public enum Category {

    ELITE(20, 35),
    JUNIOR(15, 20),
    MASTER(35, 100);

    Category(int minAge, int maxAge) {
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    private final int minAge;
    private final int maxAge;

    public static Category getCategoryFromAge(int age){
        return Arrays.stream(values())
                .filter(category -> age >= category.minAge && age <= category.maxAge)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Category not found for the age: " + age));
    }
}
