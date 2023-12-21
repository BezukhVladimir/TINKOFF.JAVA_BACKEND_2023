package edu.hw03.task6;

public interface StockMarket {
    boolean add(Stock stock);

    boolean remove(Stock stock);

    Stock mostValuableStock();

    int size();
}
