package edu.hw10.task1.models;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;

public class Employee {
    private final int age;
    private final String name;

    public Employee(
        @Min(18) @Max(70) int age,
        @NotNull String name
    ) {
        this.age = age;
        this.name = name;
    }

    public static Employee create(
        @Min(18) @Max(70) int age,
        @NotNull String name
    ) {
        return new Employee(age, name);
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
