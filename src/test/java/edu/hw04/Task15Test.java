package edu.hw04;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class Task15Test {
    @Mock
    Animal animal1, animal2, animal3, animal4, animal5, animal6;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSumWeightByTypeInAgeRange() {
        // Arrange
        when(animal1.type()).thenReturn(Animal.Type.CAT);
        when(animal2.type()).thenReturn(Animal.Type.CAT);
        when(animal3.type()).thenReturn(Animal.Type.DOG);
        when(animal4.type()).thenReturn(Animal.Type.DOG);
        when(animal5.type()).thenReturn(Animal.Type.FISH);
        when(animal6.type()).thenReturn(Animal.Type.FISH);

        when(animal1.weight()).thenReturn(10);
        when(animal2.weight()).thenReturn(10);
        when(animal3.weight()).thenReturn(20);
        when(animal4.weight()).thenReturn(20);
        when(animal5.weight()).thenReturn(30);
        when(animal6.weight()).thenReturn(30);

        when(animal1.age()).thenReturn(1);
        when(animal2.age()).thenReturn(5);
        when(animal3.age()).thenReturn(1);
        when(animal4.age()).thenReturn(2);
        when(animal5.age()).thenReturn(3);
        when(animal6.age()).thenReturn(4);

        int k = 1;
        int l = 3;
        List<Animal> animals = List.of(animal1, animal2, animal3, animal4, animal5, animal6);
        Map<Animal.Type, Integer> expected = Map.of(
            Animal.Type.CAT, 10,
            Animal.Type.DOG, 40,
            Animal.Type.FISH, 30
        );

        // Act
        Map<Animal.Type, Integer> result = AnimalUtils.getSumWeightByTypeInAgeRange(animals, k, l);

        // Assert
        assertThat(result).isEqualTo(expected);
    }
}
