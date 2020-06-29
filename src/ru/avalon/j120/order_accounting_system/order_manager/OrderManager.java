package ru.avalon.j120.order_accounting_system.order_manager;

import ru.avalon.j120.order_accounting_system.auxiliary_classes.*;
import ru.avalon.j120.order_accounting_system.person.Person;
import ru.avalon.j120.order_accounting_system.person.address.Address;

import javax.swing.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Класс хранящий списки и обрабатывающий список заказов (добавление)
 */
public class OrderManager implements Serializable {
    ListOfOrderItems list;
    ArrayList<Order> orderArrayList;
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
            throw new IllegalArgumentException("This order is already contains this product");
            //JOptionPane.showMessageDialog(null, "This order is already contains this product");
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

    /**
     * This method can change the order, if it has order's status "PREPARED"
     * @param dateOfCreate
     * @param contactPerson
     * @param deliveryAddress
     * @param contactPhoneNumber
     * @param discountPercentage
     * @param orderStatus
     */
    public Order editOrder(LocalDate dateOfCreate, Person contactPerson, Address deliveryAddress, String contactPhoneNumber,
                          int discountPercentage, OrderStatusEnum orderStatus){
        Order order;
        if (orderStatus == OrderStatusEnum.PREPARED){
            order = new Order(dateOfCreate, contactPerson, deliveryAddress, contactPhoneNumber, discountPercentage, orderStatus, this.listOfOrderItems);
            order.setDateOfCreate(dateOfCreate);
            order.setContactPerson(contactPerson);
            order.setDeliveryAddress(deliveryAddress);
            order.setContactPhoneNumber(contactPhoneNumber);
            order.setDiscountPercentage(discountPercentage);
            order.setOrderStatus(orderStatus);
            } else throw new IllegalArgumentException("You can change the order, if it has order`s status " + OrderStatusEnum.PREPARED);
    return order;
    }

    public ArrayList<Order> getOrders(){
        return orderArrayList;
    }

    @Override
    public String toString() {
        return orderArrayList.toString();
    }
}