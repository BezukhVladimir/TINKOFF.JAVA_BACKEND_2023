package edu.hw04;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class Task1Test {
    @Mock
    Animal animal1, animal2, animal3;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSortedAnimalsByHeightAscending() {
        // Arrange
        when(animal1.height()).thenReturn(30);
        when(animal2.height()).thenReturn(20);
        when(animal3.height()).thenReturn(10);

        List<Animal> animals = List.of(animal1, animal2, animal3);
        List<Animal> expectedSortedAnimals = List.of(animal3, animal2, animal1);

        // Act
        List<Animal> result = AnimalUtils.getSortedAnimalsByHeightAscending(animals);

        // Assert
        assertThat(result).isEqualTo(expectedSortedAnimals);
    }
}
