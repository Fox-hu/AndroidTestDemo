package com.example.algorithms.java8.stream;

public class Transaction {

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Transaction{" + "trader=" + trader + ", year=" + year + ", value=" + value + '}';
    }

    private final Trader trader;
    private final int year;
    private final int value;
}
