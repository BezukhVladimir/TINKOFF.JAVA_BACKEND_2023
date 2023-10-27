package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class Task4Test {
    @Mock
    Animal animal1, animal2, animal3;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAnimalWithLongestName() {
        // Arrange
        when(animal1.name()).thenReturn("name1");
        when(animal2.name()).thenReturn("name2");
        when(animal3.name()).thenReturn("longest name");

        List<Animal> animals = List.of(animal1, animal2, animal3);
        Animal expected = animal3;

        // Act
        Animal animalWithLongestName = AnimalUtils.getAnimalWithLongestName(animals);

        // Assert
        assertThat(animalWithLongestName).isNotNull();
        assertThat(animalWithLongestName).isEqualTo(expected);
    }
}
