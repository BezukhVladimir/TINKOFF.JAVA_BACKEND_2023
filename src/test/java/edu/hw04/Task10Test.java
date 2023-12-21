package edu.hw04;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class Task10Test {
    @TestFactory
    List<DynamicTest> dynamicTests() {
        return Arrays.asList(
            dynamicTest("1",
                createTest(
                    List.of(
                        createAnimal(Animal.Type.BIRD, 2)),
                    List.of()
                )),
            dynamicTest("2",
                createTest(
                    List.of(
                        createAnimal(Animal.Type.CAT, 2)),
                    List.of(
                        createAnimal(Animal.Type.CAT, 2))
                )),
            dynamicTest("3",
                createTest(
                    List.of(
                        createAnimal(Animal.Type.CAT, 2),
                        createAnimal(Animal.Type.DOG, 4),
                        createAnimal(Animal.Type.BIRD, 8)
                    ),
                    List.of(
                        createAnimal(Animal.Type.CAT, 2),
                        createAnimal(Animal.Type.BIRD, 8)
                    )
                ))
        );
    }

    private Executable createTest(List<Animal> animals, List<Animal> expected) {
        return () -> {
            // Act
            List<Animal> result = AnimalUtils.getAnimalsWithMismatchedAgeAndPaws(animals);

            // Assert
            assertThat(result).isEqualTo(expected);
        };
    }

    private Animal createAnimal(Animal.Type type, int age) {
        return new Animal("", type, Animal.Sex.M, age, 1, 1, false);
    }
}
