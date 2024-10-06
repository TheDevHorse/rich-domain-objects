package com.thedevhorse.richdomainobjects.domain;

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

    public int minAge() {
        return minAge;
    }

    public int maxAge() {
        return maxAge;
    }
}
