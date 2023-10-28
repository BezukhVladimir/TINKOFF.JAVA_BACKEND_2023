package edu.hw3.task6;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public final class DefaultStockMarket implements StockMarket {
    private final Queue<Stock> stocks;

    public DefaultStockMarket() {
        stocks = new PriorityQueue<>(
            Collections.reverseOrder()
        );
    }

    @Override
    public boolean add(Stock stock) {
        return stocks.add(stock);
    }

    @Override
    public boolean remove(Stock stock) {
        return stocks.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stocks.peek();
    }

    @Override
    public int size() {
        return stocks.size();
    }
}
