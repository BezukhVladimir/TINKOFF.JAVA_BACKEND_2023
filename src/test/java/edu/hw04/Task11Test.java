package edu.hw04;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class Task11Test {
    @Mock
    Animal animal1, animal2, animal3;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getBitingAnimalsWithHeightOver100() {
        // Arrange
        when(animal1.bites()).thenReturn(false);
        when(animal2.bites()).thenReturn(true);
        when(animal3.bites()).thenReturn(true);

        when(animal1.height()).thenReturn(110);
        when(animal2.height()).thenReturn(90);
        when(animal3.height()).thenReturn(110);

        List<Animal> animals = List.of(animal1, animal2, animal3);
        List<Animal> expected = List.of(animal3);

        // Act
        List<Animal> bitingAnimalsWithHeightOver100 = AnimalUtils.getBitingAnimalsWithHeightOver100(animals);

        // Assert
        assertThat(bitingAnimalsWithHeightOver100).isEqualTo(expected);
    }
}
