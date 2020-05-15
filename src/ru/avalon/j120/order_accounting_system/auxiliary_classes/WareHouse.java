package ru.avalon.j120.order_accounting_system.auxiliary_classes;

import javax.swing.*;
import java.util.ArrayList;

public class WareHouse {
    ArrayList<Product> products;

    public WareHouse( ArrayList<Product> prod) {
        products = prod;
    }

    /**
     * Метод добавляет новый продукт на склад.
     * @param product
     */
    public void addProdToList(Product product){
        for(Product p : products)
            if(p.getArticleNumber().equals(product.getArticleNumber())){
                JOptionPane.showMessageDialog(null, "The list almost contains the product with article " + product.getArticleNumber());
                }
        products.add(product);
    }

    /**
     * Метод позволяет получить канкретный продукт со склада (см. class Application).
     * @return
     */
    public ArrayList<Product> getProductsPosition(){
        return products;
    }


}