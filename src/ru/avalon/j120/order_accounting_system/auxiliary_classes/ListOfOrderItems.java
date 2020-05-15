package ru.avalon.j120.order_accounting_system.auxiliary_classes;

import java.io.Serializable;

public class ListOfOrderItems implements Serializable {

    private Product product;
    private int howManyOrdered;
    private final int totalAmount;

    public ListOfOrderItems(Product product, int howManyOrder) {
        this.product = product;
        setHowManyOrdered(howManyOrder);
        totalAmount = product.getPrice() * howManyOrder;
    }

    public void setHowManyOrdered(int howManyOrdered) {
        if (howManyOrdered > product.getStockBalance()) System.out.println("Not enough product in the stock.");
        this.howManyOrdered = howManyOrdered;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public int getHowManyOrdered() {
        return howManyOrdered;
    }

    @Override
    public String toString() {
        return  product +
                " howManyOrdered " + howManyOrdered +
                " total amount " + totalAmount + " dollars" + "\n";
    }
}