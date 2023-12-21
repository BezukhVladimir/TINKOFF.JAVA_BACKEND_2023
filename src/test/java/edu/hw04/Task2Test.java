package edu.hw04;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class Task2Test {
    @Mock
    Animal animal1, animal2, animal3;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTopKAnimalsByWeight() {
        // Arrange
        when(animal1.weight()).thenReturn(10);
        when(animal2.weight()).thenReturn(20);
        when(animal3.weight()).thenReturn(30);

        int k = 2;
        List<Animal> animals = List.of(animal1, animal2, animal3);
        List<Animal> expectedTopKAnimals = List.of(animal3, animal2);

        // Act
        List<Animal> result = AnimalUtils.getTopKHeaviestAnimals(animals, k);

        // Assert
        assertThat(result).isEqualTo(expectedTopKAnimals);
    }
}
