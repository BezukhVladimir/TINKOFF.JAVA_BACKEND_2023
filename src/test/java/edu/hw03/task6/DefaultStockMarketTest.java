package edu.hw03.task6;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class DefaultStockMarketTest {
    DefaultStockMarket defaultStockMarket;

    @BeforeEach
    void initNewDefaultStockMarket() {
        defaultStockMarket = new DefaultStockMarket();
    }

    @Test
    void empty() {
        // Assert
        assertThat(defaultStockMarket.size()).isEqualTo(0);
    }

    @Test
    void add() {
        // Arrange
        Stock singleStock = new Stock("TCSG", 3500.0);

        // Act
        boolean added = defaultStockMarket.add(singleStock);

        // Assert
        assertThat(added).isTrue();
    }

    @Test
    void remove() {
        // Arrange
        Stock singleStock = new Stock("TCSG", 3500.0);
        defaultStockMarket.add(singleStock);

        // Act
        boolean removed = defaultStockMarket.remove(singleStock);

        // Assert
        assertThat(removed).isTrue();
    }

    @Test
    void removeDecreaseSize() {
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
    void removeLastElement() {
        // Arrange
        Stock singleStock = new Stock("TCSG", 3500.0);
        defaultStockMarket.add(singleStock);

        // Act
        defaultStockMarket.remove(singleStock);

        // Assert
        assertThat(defaultStockMarket.size()).isEqualTo(0);
    }

    @Test
    void removeEmptyStockMarket() {
        // Act
        boolean removed = defaultStockMarket.remove(defaultStockMarket.mostValuableStock());

        // Assert
        assertThat(removed).isFalse();
    }

    @Test
    void mostValuableStock() {
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
