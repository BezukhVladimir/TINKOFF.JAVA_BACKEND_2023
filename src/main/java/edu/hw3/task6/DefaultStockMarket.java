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
    public void add(Stock stock) {
        stocks.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        stocks.remove(stock);
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
