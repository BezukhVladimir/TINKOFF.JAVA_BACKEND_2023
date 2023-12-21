package edu.hw04;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class Task5Test {
    @Mock
    Animal animal1, animal2, animal3;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAnimalWithLongestName() {
        // Arrange
        when(animal1.sex()).thenReturn(Animal.Sex.M);
        when(animal2.sex()).thenReturn(Animal.Sex.M);
        when(animal3.sex()).thenReturn(Animal.Sex.F);

        List<Animal> animals = List.of(animal1, animal2, animal3);
        Animal.Sex expected = Animal.Sex.M;

        // Act
        Animal.Sex result = AnimalUtils.getMostCommonSex(animals);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expected);
    }
}
