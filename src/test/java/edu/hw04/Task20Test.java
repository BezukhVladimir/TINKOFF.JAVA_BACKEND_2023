package edu.hw04;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class Task20Test {
    @Mock
    Animal animal1, animal2;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetValidationErrorMessages() {
        // Arrange
        when(animal1.name()).thenReturn("name");
        when(animal1.age()).thenReturn(1);
        when(animal1.height()).thenReturn(1);
        when(animal1.weight()).thenReturn(1);

        List<Animal> animals = List.of(animal1, animal2);
        Map<String, String> expected = new HashMap<String, String>() {{
            put("name", "");
            put(null, "age must be greater than zero, "
                + "height must be greater than zero, "
                + "weight must be greater than zero");
        }};

        // Act
        Map<String, String> result = AnimalUtils.getValidationErrorMessages(animals);

        // Assert
        assertThat(result)
            .usingRecursiveComparison()
            .ignoringCollectionOrder()
            .isEqualTo(expected);
    }
}
