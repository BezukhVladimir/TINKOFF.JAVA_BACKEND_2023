package edu.hw04;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class Task17Test {
    @Mock
    Animal animal1, animal2, animal3, animal4, animal5, animal6;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoSpidersBiteMoreThanDogs() {
        // Arrange
        when(animal1.type()).thenReturn(Animal.Type.DOG);
        when(animal2.type()).thenReturn(Animal.Type.DOG);
        when(animal3.type()).thenReturn(Animal.Type.DOG);
        when(animal4.type()).thenReturn(Animal.Type.SPIDER);
        when(animal5.type()).thenReturn(Animal.Type.SPIDER);
        when(animal6.type()).thenReturn(Animal.Type.SPIDER);

        when(animal1.bites()).thenReturn(true);
        when(animal2.bites()).thenReturn(false);
        when(animal3.bites()).thenReturn(false);
        when(animal4.bites()).thenReturn(true);
        when(animal5.bites()).thenReturn(true);
        when(animal6.bites()).thenReturn(false);

        List<Animal> animals = List.of(animal1, animal2, animal3, animal4, animal5, animal6);

        // Act
        boolean result = AnimalUtils.doSpidersBiteMoreThanDogs(animals);

        // Assert
        assertThat(result).isTrue();
    }
}
