
# Specification Pattern - Weightlifting Example

## Overview

The **Specification Pattern** is a design pattern that allows you to encapsulate validation criteria in separate objects, making the code more modular and flexible. Instead of writing complex conditional logic directly in the main code, this responsibility is delegated to specification objects. This makes the code easier to understand, maintain, and extend.

## How the Specification Pattern is Used in the Code:

### 1. Specification Objects

Specification objects are classes that contain individual validation rules. In the given code, there are classes such as:

- `TotalOlympicsSpecification`
- `CleanAndJerkOlympicsSpecification`
- `SnatchOlympicsSpecification`
- Similarly, there are specifications for the World Championships (`TotalWorldChampionshipsSpecification`, `CleanAndJerkWorldChampionshipsSpecification`, etc.).

### 2. Composing Specifications

Specifications can be combined using logical operators such as `or` and `and`. For example, to check if the athlete is eligible for the Olympics, the code checks if the total weight or combinations of snatch and clean & jerk meet the requirements.

```java
private void setEligibleForWorldChampionships(double snatchWeight, double cleanAndJerkWeight) {
    this.eligibleForWorldChampionships = new TotalWorldChampionshipsSpecification()
            .toPredicate()
            .or(
                    new CleanAndJerkWorldChampionshipsSpecification()
                            .toPredicate()
                            .and(new SnatchWorldChampionshipsSpecification().toPredicate())
            )
            .test(snatchWeight, cleanAndJerkWeight);
}
```

### 3. Using Predicates

Each specification is transformed into a `Predicate`, which is a condition that can be tested. The `toPredicate()` method converts each specification into a boolean condition that can be tested using the athlete's snatch and clean & jerk weights.

### Advantages of the Specification Pattern:

- **Separation of Concerns**: Validation logic is separated from the main code, making it cleaner and easier to understand.
- **Reusability**: Each specification can be reused in different parts of the code or even in other contexts.
- **Flexible Composition**: Specifications can be combined in different ways, making it easy to add new validation rules without modifying the main class.
- **Testability**: Specifications can be tested independently, ensuring that validation logic works as expected.

### Example of a Specification:

A specification for the clean and jerk in the Olympics could look like this:

```java
public class CleanAndJerkOlympicsSpecification extends CompositeSpecification<Double> {
    
    private static final double MINIMUM_WEIGHT = 200.0;

    @Override
    public BiPredicate<Double, Double> toPredicate() {
        return (snatch, cleanAndJerk) -> cleanAndJerk > MINIMUM_WEIGHT;
    }
}

```

### Conclusion:

The **Specification Pattern** allows you to encapsulate business rules into separate, composable components, making the code modular, reusable, and easier to maintain. It is particularly useful when there are multiple complex conditions that need to be evaluated against objects, such as checking an athlete's eligibility for competitions.
