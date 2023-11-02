package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class Task14Test {
    @Mock
    Animal animal1, animal2, animal3;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHasDogTallerThanK() {
        // Arrange
        when(animal1.type()).thenReturn(Animal.Type.CAT);
        when(animal2.type()).thenReturn(Animal.Type.DOG);
        when(animal3.type()).thenReturn(Animal.Type.DOG);

        when(animal1.height()).thenReturn(110);
        when(animal2.height()).thenReturn(90);
        when(animal3.height()).thenReturn(110);

        int k = 100;
        List<Animal> animals = List.of(animal1, animal2, animal3);

        // Act
        boolean result = AnimalUtils.hasDogTallerThanK(animals, k);

        // Assert
        assertThat(result).isTrue();
    }
}
