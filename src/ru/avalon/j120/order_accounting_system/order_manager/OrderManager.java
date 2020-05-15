package ru.avalon.j120.order_accounting_system.order_manager;

import ru.avalon.j120.order_accounting_system.auxiliary_classes.*;
import ru.avalon.j120.order_accounting_system.person.Person;
import ru.avalon.j120.order_accounting_system.person.address.Address;

import javax.swing.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Класс хранящий списки и обрабатывающий список заказов (добавление)
 */
public class OrderManager implements Serializable {
    ListOfOrderItems list;
    ArrayList<Order> orderArrayList = new ArrayList<>();
    ArrayList<ListOfOrderItems> listOfOrderItems = new ArrayList<>();

    public OrderManager(ArrayList<Order> orders){
        orderArrayList = orders;
    }

    public void addOrderPosition(Product product, int howMany){
        list = new ListOfOrderItems(product, howMany);
        listOfOrderItems.add(list);
    }

    public ListOfOrderItems getList() {
        return list;
    }

    /**
     * Метод проверяет существует ли передоваемый продукт в заказе
     * @param product
     * @param howMany
     */
    public void adddOrderPosition(Product product, int howMany){
        ListOfOrderItems list = new ListOfOrderItems(product, howMany);
        if (listOfOrderItems.contains(product)){
            JOptionPane.showMessageDialog(null, "This order is already contains this product");
        }
        else listOfOrderItems.add(list);
    }

    /**
     * Метод позводяет добавить новый заказ в хранилище заказов.
     */
    public void addNewOrder(LocalDate dateOfCreate, Person contactPerson, Address deliveryAddress, String contactPhoneNumber,
                                        int discountPercentage, OrderStatusEnum orderStatus) {
        orderArrayList.add(new Order(dateOfCreate, contactPerson, deliveryAddress,contactPhoneNumber,discountPercentage, orderStatus, this.listOfOrderItems));

    }

    public ArrayList<Order> getOrders(){
        return orderArrayList;
    }

    @Override
    public String toString() {
        return orderArrayList.toString();
    }
}