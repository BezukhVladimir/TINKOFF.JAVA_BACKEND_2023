package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class Task7Test {
    @Mock
    Animal animal1, animal2, animal3;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetKthOldestAnimal() {
        // Arrange
        when(animal1.age()).thenReturn(1);
        when(animal2.age()).thenReturn(2);
        when(animal3.age()).thenReturn(3);

        int k = 2;
        List<Animal> animals = List.of(animal1, animal2, animal3);
        Animal expected = animal2;

        // Act
        Animal kthOldestAnimal = AnimalUtils.getKthOldestAnimal(animals, k);

        // Assert
        assertThat(kthOldestAnimal).isNotNull();
        assertThat(kthOldestAnimal).isEqualTo(expected);
    }
}

