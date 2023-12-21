package edu.hw07.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class CacheServiceTest {
    private CacheService cacheService;

    @BeforeEach
    void setUp() {
        cacheService = new CacheService();
    }

    @Test
    void testFindByName() {
        // Arrange
        var person = new Person(1, "name", "address", "phone");
        cacheService.add(person);

        // Act
        Person result = cacheService.findByName("name");

        // Assert
        assertThat(result).isEqualTo(person);
    }

    @Test
    void testFindByAddress() {
        // Arrange
        var person = new Person(1, "name", "address", "phone");
        cacheService.add(person);

        // Act
        Person result = cacheService.findByAddress("address");

        // Assert
        assertThat(result).isEqualTo(person);
    }

    @Test
    void testFindByPhone() {
        // Arrange
        var person = new Person(1, "name", "address", "phone");
        cacheService.add(person);

        // Act
        Person result = cacheService.findByPhone("phone");

        // Assert
        assertThat(result).isEqualTo(person);
    }

    @Test
    void testAddPerson() {
        // Arrange
        var person = new Person(1, "name", "address", "phone");

        // Act
        cacheService.add(person);

        // Assert
        assertThat(cacheService.findByName("name")).isEqualTo(person);
        assertThat(cacheService.findByAddress("address")).isEqualTo(person);
        assertThat(cacheService.findByPhone("phone")).isEqualTo(person);
    }

    @Test
    void testDeletePerson() {
        // Arrange
        var person = new Person(1, "name", "address", "phone");
        cacheService.add(person);

        // Act
        cacheService.delete(1);

        // Assert
        assertThat(cacheService.findByName("name")).isNull();
        assertThat(cacheService.findByAddress("address")).isNull();
        assertThat(cacheService.findByPhone("phone")).isNull();
    }
}
