package edu.hw3.task6;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultStockMarketTest {
    DefaultStockMarket defaultStockMarket;

    @BeforeEach
    void initNewDefaultStockMarket() {
        defaultStockMarket = new DefaultStockMarket();
    }

    @Test
    void testAdd() {
        // Arrange
        List<Stock> stocks = List.of(
            new Stock("TCSG", 3500.0),
            new Stock("SBER", 270.0),
            new Stock("VTBR", 0.025)
        );

        int expectedSize = 0;
        for (Stock stock : stocks) {
            // Act
            defaultStockMarket.add(stock);
            ++expectedSize;

            // Assert
            assertEquals(expectedSize, defaultStockMarket.size());
        }
    }

    @Test
    void testRemove() {
        // Arrange
        List<Stock> stocks = List.of(
            new Stock("TCSG", 3500.0),
            new Stock("SBER", 270.0),
            new Stock("VTBR", 0.025)
        );

        for (Stock stock : stocks) {
            defaultStockMarket.add(stock);
        }

        for (int expectedSize = 2; expectedSize >= 0; --expectedSize) {
            // Act
            defaultStockMarket.remove(defaultStockMarket.mostValuableStock());

            // Assert
            assertEquals(expectedSize, defaultStockMarket.size());
        }
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

        for (int expectedStockId = 2; expectedStockId >= 0; --expectedStockId) {
            // Assert
            assertEquals(stocks.get(expectedStockId), defaultStockMarket.mostValuableStock());
            defaultStockMarket.remove(stocks.get(expectedStockId));
        }
    }
}
