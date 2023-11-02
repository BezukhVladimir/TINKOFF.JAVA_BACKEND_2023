package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class Task12Test {
    @Mock
    Animal animal1, animal2, animal3, animal4, animal5;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetNumberOfAnimalsWithWeightGreaterThanHeight() {
        // Arrange
        when(animal1.height()).thenReturn(1);
        when(animal2.height()).thenReturn(2);
        when(animal3.height()).thenReturn(3);
        when(animal3.height()).thenReturn(4);
        when(animal3.height()).thenReturn(5);

        when(animal1.weight()).thenReturn(5);
        when(animal2.weight()).thenReturn(4);
        when(animal3.weight()).thenReturn(3);
        when(animal3.weight()).thenReturn(2);
        when(animal3.weight()).thenReturn(1);

        List<Animal> animals = List.of(animal1, animal2, animal3, animal4, animal5);
        long expected = 2;

        // Act
        long result = AnimalUtils.getNumberOfAnimalsWithWeightGreaterThanHeight(animals);

        // Assert
        assertThat(result).isEqualTo(expected);
    }
}
