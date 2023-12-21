package edu.hw04;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class Task6Test {
    @Mock
    Animal animal1, animal2, animal3, animal4, animal5, animal6;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetHeaviestAnimalPerType() {
        // Arrange
        when(animal1.type()).thenReturn(Animal.Type.CAT);
        when(animal2.type()).thenReturn(Animal.Type.CAT);
        when(animal3.type()).thenReturn(Animal.Type.DOG);
        when(animal4.type()).thenReturn(Animal.Type.DOG);
        when(animal5.type()).thenReturn(Animal.Type.FISH);
        when(animal6.type()).thenReturn(Animal.Type.FISH);

        when(animal1.weight()).thenReturn(10);
        when(animal2.weight()).thenReturn(15);
        when(animal3.weight()).thenReturn(20);
        when(animal4.weight()).thenReturn(25);
        when(animal5.weight()).thenReturn(5);
        when(animal6.weight()).thenReturn(30);

        List<Animal> animals = List.of(animal1, animal2, animal3, animal4, animal5, animal6);
        Map<Animal.Type, Animal> expectedHeaviestAnimalPerType = Map.of(
            Animal.Type.CAT, animal2,
            Animal.Type.DOG, animal4,
            Animal.Type.FISH, animal6
        );

        // Act
        Map<Animal.Type, Animal> heaviestAnimalPerType = AnimalUtils.getHeaviestAnimalPerType(animals);

        // Assert
        assertThat(heaviestAnimalPerType).isEqualTo(expectedHeaviestAnimalPerType);
    }
}
