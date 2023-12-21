package edu.hw04;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class Task3Test {
    @Mock
    Animal animal1, animal2, animal3, animal4, animal5;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCountAnimalsByType() {
        // Arrange
        when(animal1.type()).thenReturn(Animal.Type.CAT);
        when(animal2.type()).thenReturn(Animal.Type.CAT);
        when(animal3.type()).thenReturn(Animal.Type.DOG);
        when(animal4.type()).thenReturn(Animal.Type.DOG);
        when(animal5.type()).thenReturn(Animal.Type.FISH);

        List<Animal> animals = List.of(animal1, animal2, animal3, animal4, animal5);
        Map<Animal.Type, Long> expectedCounts = Map.of(
            Animal.Type.CAT, 2L,
            Animal.Type.DOG, 2L,
            Animal.Type.FISH, 1L
        );

        // Act
        Map<Animal.Type, Long> animalCountMap = AnimalUtils.getNumberOfAnimalsByType(animals);

        // Assert
        assertThat(animalCountMap).isEqualTo(expectedCounts);
    }
}
