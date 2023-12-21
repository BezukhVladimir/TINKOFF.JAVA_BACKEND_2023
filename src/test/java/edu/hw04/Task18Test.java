package edu.hw04;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class Task18Test {
    @Mock
    Animal animal1, animal2, animal3, animal4, animal5, animal6;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetHeaviestFishInLists() {
        // Arrange
        when(animal1.type()).thenReturn(Animal.Type.DOG);
        when(animal2.type()).thenReturn(Animal.Type.FISH);
        when(animal3.type()).thenReturn(Animal.Type.CAT);
        when(animal4.type()).thenReturn(Animal.Type.FISH);
        when(animal5.type()).thenReturn(Animal.Type.FISH);
        when(animal6.type()).thenReturn(Animal.Type.FISH);

        when(animal1.weight()).thenReturn(6);
        when(animal2.weight()).thenReturn(1);
        when(animal3.weight()).thenReturn(2);
        when(animal4.weight()).thenReturn(5);
        when(animal5.weight()).thenReturn(4);
        when(animal6.weight()).thenReturn(3);

        List<List<Animal>> animalLists = List.of(
            List.of(animal1, animal2),
            List.of(animal3, animal4),
            List.of(animal5, animal6)
        );

        // Act
        Animal result = AnimalUtils.getHeaviestFishInLists(animalLists);

        // Assert
        assertThat(result).isEqualTo(animal4);
    }
}
