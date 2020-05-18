package ru.avalon.j120.order_accounting_system.auxiliary_classes;

import java.io.Serializable;

public class Product implements Serializable {

    private String articleNumber;
    private String name;
    private String color;
    private int price;
    private int stockBalance;

    public Product(String articleNumber, String name, int price, int stockBalance) {
        this.articleNumber = articleNumber;
        this.name = name;
        setPrice(price);
        this.stockBalance = stockBalance;
    }

    public Product(String articleNumber, String name, String color, int price, int stockBalance) {
        this(articleNumber,  name,  price,  stockBalance);
        this.color = color;
    }

    public String getArticleNumber() {
        return articleNumber;
    }
    public int getPrice() {
        return price;
    }
    public int getStockBalance() {
        return stockBalance;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void setPrice(int setPrice) {
        if(setPrice <= 0) System.err.println("There can't be a zero or negative price");// метод должен выбрасывать исключение
        else price = setPrice;
    }

    /**
     * Метод уменьшает количество товара на складе
     * @param value
     */
    public void reduceBalance(int value) {
        if(stockBalance < value) System.err.println("Not enough product in the stock.");
        else stockBalance -= value;
    }

    @Override
    public String toString() {
        return articleNumber + ';' +
                name + ';' +
                color + ';' +
                price + ';' +
                stockBalance;
    }
}