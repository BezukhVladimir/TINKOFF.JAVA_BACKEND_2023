package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class Task9Test {
    @Mock
    Animal animal1, animal2, animal3, animal4, animal5;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSumOfAllPaws() {
        // Arrange
        when(animal1.paws()).thenReturn(2); // BIRD
        when(animal2.paws()).thenReturn(4); // CAT
        when(animal3.paws()).thenReturn(4); // DOG
        when(animal4.paws()).thenReturn(0); // FISH
        when(animal5.paws()).thenReturn(8); // SPIDER

        List<Animal> animals = List.of(animal1, animal2, animal3, animal4, animal5);
        int expectedSumOfPaws = 18;

        // Act
        int sumOfPaws = AnimalUtils.getSumOfAllPaws(animals);

        // Assert
        assertThat(sumOfPaws).isEqualTo(expectedSumOfPaws);
    }
}
