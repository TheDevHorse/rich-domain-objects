
# Introduction
In software development, especially when following Domain-Driven Design (DDD) principles, a Rich Domain Object is an object that encapsulates not only the domain data but also the associated business rules. This approach enhances code cohesion and clarity by embedding business rules directly within the domain model.

## Code Explanation

### The Athlete Class
The Athlete class represents the primary entity in this domain. Let’s break down each part:

```java
public class Athlete {
    private String name;
    private int age;
    private Category category;
}
```
**Attributes:** The class has three main attributes: name, age, and category. The category is determined based on the athlete’s age, encapsulating an essential business rule that categorizes athletes by age range.

```java
    private Athlete(String name, int age) {
        this.name = name;
        this.age = age;
        setCategory(age);
    }
```
**Private Constructor:** The constructor is private, allowing for the creation of an Athlete instance only through a factory method (create). This pattern controls object initialization and ensures that business rules are applied from the outset.

```java
    public static Athlete create(String name, int age) {
        return new Athlete(name, age);
    }
```
**Factory Method:** The create method is used to instantiate an Athlete. This method centralizes object creation, ensuring business rules are embedded right from the initialization phase, as the category is assigned upon creating an Athlete.

```java
    public String name() { return name; }
    public int age() { return age; }
    public Category category() { return category; }
```
**Accessor Methods:** The name(), age(), and category() methods serve as getters. They follow a fluent style, improving readability.

```java
    public Athlete changeCategory(int age) {
        setCategory(age);
        return this;
    }
```
**changeCategory Method:** This method allows updating the athlete's category when the age changes. It encapsulates the logic for recalculating the category based on age within the Athlete class itself, demonstrating the principle of encapsulating business rules within the domain object.

```java
    private void setCategory(int age) {
        this.category = Arrays.stream(Category.values())
                .filter(c -> age >= c.minAge() && age <= c.maxAge())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Category not found for the age: " + age));
    }
```
**Business Logic in setCategory:** This private method sets the category attribute based on the athlete’s age. It iterates through the values in the Category enumeration, finding the category that matches the age range. If no matching category is found, it throws an exception, ensuring that each age has a defined category.

### The Category Enumeration
The Category enumeration defines the possible categories for an athlete, along with their respective age ranges. Each category has a minimum and maximum age range.

```java
public enum Category {
    ELITE(20, 34),
    JUNIOR(15, 19),
    MASTER(35, 100)
}
```
**Enumeration Values:** Each category (ELITE, JUNIOR, MASTER) has a defined minimum and maximum age range. For example, the ELITE category is for athletes between the ages of 20 and 35.

```java
    Category(int minAge, int maxAge) {
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    private final int minAge;
    private final int maxAge;

    public int minAge() { return minAge; }
    public int maxAge() { return maxAge; }
```

## Summary and Conclusion
This example of a Rich Domain Object shows how to encapsulate business logic within domain entities. In the case of the Athlete class, all logic defining an athlete’s category based on their age is contained within the Athlete object itself. This design choice keeps the domain model cohesive, with business rules closely associated with the entity they describe, making the system easier to understand and maintain.

This approach is particularly useful for systems with complex and evolving business rules, as it allows changes to be made centrally within the domain model itself.
