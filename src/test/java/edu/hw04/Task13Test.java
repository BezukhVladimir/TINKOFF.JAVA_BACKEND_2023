package edu.hw04;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class Task13Test {
    @Mock
    Animal animal1, animal2, animal3;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAnimalsWithNamesMoreThanTwoWords() {
        // Arrange
        when(animal1.name()).thenReturn("First");
        when(animal2.name()).thenReturn("Second animal");
        when(animal3.name()).thenReturn("Third animal name");

        List<Animal> animals = List.of(animal1, animal2, animal3);
        List<Animal> expected = List.of(animal3);

        // Act
        List<Animal> result = AnimalUtils.getAnimalsWithNamesMoreThanTwoWords(animals);

        // Assert
        assertThat(result).isEqualTo(expected);
    }
}
