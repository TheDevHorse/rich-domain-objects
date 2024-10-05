package com.thedevhorse.richdomainobjects.domain;

public class Athlete {

    private String name;

    private int age;

    private Category category;

    private Athlete(String name, int age) {
        this.name = name;
        this.age = age;
        setCategory(age);
    }

    public static Athlete create(String name, int age){
        return new Athlete(name, age);
    }

    private void setCategory(int age) {
        this.category =  Category.getCategoryFromAge(age);
    }
}
