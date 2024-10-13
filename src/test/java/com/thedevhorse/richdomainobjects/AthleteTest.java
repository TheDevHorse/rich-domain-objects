package com.thedevhorse.richdomainobjects;

import com.thedevhorse.richdomainobjects.domain.Athlete;
import com.thedevhorse.richdomainobjects.domain.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AthleteTest {

    @Test
    void givenValidInputs_whenCreateAthleteIsCalled_thenAthleteIsReturned() {
        //given
        String name = "Horse Power";
        int age = 37;
        Category category = Category.MASTER;

        //when
        Athlete actual = Athlete.create(name, age);

        //then
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(category, actual.category());
        Assertions.assertEquals(age, actual.age());
        Assertions.assertEquals(name, actual.name());
    }

    @Test
    void givenInvalidAge_whenCreateAthleteIsCalled_thenIllegalArgumentExceptionThrown() {
        //given
        String expectedMessage = "Category not found for the age: 5";

        //when
        String actualMessage = assertThrows(IllegalArgumentException.class, () -> {
            Athlete.create("Horse", 5);
        }).getMessage();

        //then
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void givenNewAge_whenChangeCategoryIsCalled_thenCategoryIsChanged() {
        //given
        Athlete athlete = Athlete.create("Horse", 25);
        Category expected = Category.MASTER;
        int newAge = 37;

        //when
        Athlete actual = athlete.changeCategory(newAge);

        //then
        Assertions.assertEquals(expected, actual.category());
    }
}
