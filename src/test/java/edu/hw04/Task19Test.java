package edu.hw04;

import edu.hw04.validator.errors.AgeError;
import edu.hw04.validator.errors.HeightError;
import edu.hw04.validator.errors.ValidationError;
import edu.hw04.validator.errors.WeightError;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class Task19Test {
    @Mock
    Animal animal1, animal2;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetValidationErrors() {
        // Arrange
        when(animal1.name()).thenReturn("name");
        when(animal1.age()).thenReturn(1);
        when(animal1.height()).thenReturn(1);
        when(animal1.weight()).thenReturn(1);

        List<Animal> animals = List.of(animal1, animal2);
        Map<String, List<ValidationError>> expected = new HashMap<String, List<ValidationError>>() {{
            put("name", List.of());
            put(null, List.of(
                new AgeError("age must be greater than zero"),
                new HeightError("height must be greater than zero"),
                new WeightError("weight must be greater than zero")
            ));
        }};

        // Act
        Map<String, List<ValidationError>> result = AnimalUtils.getValidationErrors(animals);

        // Assert
        assertThat(result)
            .usingRecursiveComparison()
            .ignoringCollectionOrder()
            .isEqualTo(expected);
    }
}
