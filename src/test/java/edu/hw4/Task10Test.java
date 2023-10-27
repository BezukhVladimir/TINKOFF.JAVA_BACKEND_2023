package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class Task10Test {
    @Mock
    Animal animal1, animal2, animal3, animal4, animal5;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAnimalsWithMismatchedAgeAndPaws() {
        // Arrange
        when(animal1.age()).thenReturn(0);
        when(animal2.age()).thenReturn(2);
        when(animal3.age()).thenReturn(4);
        when(animal4.age()).thenReturn(6);
        when(animal5.age()).thenReturn(8);

        when(animal1.paws()).thenReturn(2); // BIRD
        when(animal2.paws()).thenReturn(4); // CAT
        when(animal3.paws()).thenReturn(4); // DOG
        when(animal4.paws()).thenReturn(0); // FISH
        when(animal5.paws()).thenReturn(8); // SPIDER

        List<Animal> animals = List.of(animal1, animal2, animal3, animal4, animal5);
        List<Animal> expected = List.of(animal1, animal2, animal4);

        // Act
        List<Animal> mismatchedAnimals = AnimalUtils.getAnimalsWithMismatchedAgeAndPaws(animals);

        // Assert
        assertThat(mismatchedAnimals).isEqualTo(expected);
    }
}

