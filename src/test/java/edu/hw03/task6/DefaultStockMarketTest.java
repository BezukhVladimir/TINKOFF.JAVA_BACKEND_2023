package edu.hw03.task6;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DefaultStockMarketTest {
    DefaultStockMarket defaultStockMarket;

    @BeforeEach
    void initNewDefaultStockMarket() {
        defaultStockMarket = new DefaultStockMarket();
    }

    @Test
    void testEmpty() {
        // Assert
        assertThat(defaultStockMarket.size()).isEqualTo(0);
    }

    @Test
    void testAdd() {
        // Arrange
        Stock singleStock = new Stock("TCSG", 3500.0);

        // Act
        boolean added = defaultStockMarket.add(singleStock);

        // Assert
        assertThat(added).isTrue();
    }

    @Test
    void testRemove() {
        // Arrange
        Stock singleStock = new Stock("TCSG", 3500.0);
        defaultStockMarket.add(singleStock);

        // Act
        boolean removed = defaultStockMarket.remove(singleStock);

        // Assert
        assertThat(removed).isTrue();
    }

    @Test
    void testRemoveDecreaseSize() {
        // Arrange
        List<Stock> stocks = List.of(
            new Stock("TCSG", 3500.0),
            new Stock("SBER", 270.0),
            new Stock("VTBR", 0.025)
        );

        for (Stock stock : stocks) {
            defaultStockMarket.add(stock);
        }

        // Act
        defaultStockMarket.remove(defaultStockMarket.mostValuableStock());

        // Assert
        assertThat(defaultStockMarket.size()).isEqualTo(stocks.size() - 1);
    }

    @Test
    void testRemoveLastElement() {
        // Arrange
        Stock singleStock = new Stock("TCSG", 3500.0);
        defaultStockMarket.add(singleStock);

        // Act
        defaultStockMarket.remove(singleStock);

        // Assert
        assertThat(defaultStockMarket.size()).isEqualTo(0);
    }

    @Test
    void testRemoveEmptyStockMarket() {
        // Act
        boolean removed = defaultStockMarket.remove(defaultStockMarket.mostValuableStock());

        // Assert
        assertThat(removed).isFalse();
    }

    @Test
    void testMostValuableStock() {
        // Arrange
        List<Stock> stocks = List.of(
            new Stock("VTBR", 0.025),
            new Stock("SBER", 270.0),
            new Stock("TCSG", 3500.0)
        );

        for (Stock stock : stocks) {
            defaultStockMarket.add(stock);
        }

        int expectedStockId = 2;

        // Assert
        assertThat(defaultStockMarket.mostValuableStock()).isEqualTo(stocks.get(expectedStockId));
    }
}
