package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class Task16Test {
    @Mock
    Animal animal1, animal2, animal3, animal4, animal5, animal6;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSortedAnimalsByTypeSexNameAscending() {
        // Arrange
        when(animal1.type()).thenReturn(Animal.Type.FISH);
        when(animal2.type()).thenReturn(Animal.Type.DOG);
        when(animal3.type()).thenReturn(Animal.Type.DOG);
        when(animal4.type()).thenReturn(Animal.Type.CAT);
        when(animal5.type()).thenReturn(Animal.Type.CAT);
        when(animal6.type()).thenReturn(Animal.Type.CAT);

        when(animal1.sex()).thenReturn(Animal.Sex.M);
        when(animal2.sex()).thenReturn(Animal.Sex.F);
        when(animal3.sex()).thenReturn(Animal.Sex.M);
        when(animal4.sex()).thenReturn(Animal.Sex.F);
        when(animal5.sex()).thenReturn(Animal.Sex.M);
        when(animal6.sex()).thenReturn(Animal.Sex.F);

        when(animal1.name()).thenReturn("A");
        when(animal2.name()).thenReturn("B");
        when(animal3.name()).thenReturn("C");
        when(animal4.name()).thenReturn("C");
        when(animal5.name()).thenReturn("B");
        when(animal6.name()).thenReturn("A");

        List<Animal> animals = List.of(animal1, animal2, animal3, animal4, animal5, animal6);
        List<Animal> expected = List.of(animal5, animal6, animal4, animal3, animal2, animal1);

        // Act
        List<Animal> result = AnimalUtils.getSortedAnimalsByTypeSexNameAscending(animals);

        // Assert
        assertThat(result).isEqualTo(expected);
    }
}
