package ru.avalon.j120.order_accounting_system.auxiliary_classes;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import ru.avalon.j120.order_accounting_system.person.Person;
import ru.avalon.j120.order_accounting_system.person.address.Address;

public class Order implements Serializable {

    private LocalDate dateOfCreate;
    private Person contactPerson;
    private Address deliveryAddress;
    private String contactPhoneNumber;
    private int discountPercentage;
    private OrderStatusEnum orderStatus;
    public ArrayList<ListOfOrderItems> listOfOrderItems;

    public Order(LocalDate dateOfCreate, Person contactPerson, Address deliveryAddress,
                 String contactPhoneNumber, OrderStatusEnum orderStatus, ArrayList orderRow) {
        this.dateOfCreate = dateOfCreate;
        this.contactPerson = contactPerson;
        this.deliveryAddress = deliveryAddress;
        this.contactPhoneNumber = contactPhoneNumber;
        this.orderStatus = orderStatus;
        this.listOfOrderItems = orderRow;
    }

    public Order(LocalDate dateOfCreate, Person contactPerson, Address deliveryAddress, String contactPhoneNumber,
                 int discountPercentage, OrderStatusEnum orderStatus, ArrayList orderRow) {
        this(dateOfCreate, contactPerson, deliveryAddress, contactPhoneNumber, orderStatus, orderRow);
        this.discountPercentage = discountPercentage;
    }

    public LocalDate getDateOfCreate() {
        return dateOfCreate;
    }
    public Person getContactPerson() {
        return contactPerson;
    }
    public Address getDeliveryAddress() {
        return deliveryAddress;
    }
    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }
    public int getDiscountPercentage() {
        return discountPercentage;
    }
    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }
    public ArrayList getListOfOrderItems() {
        return listOfOrderItems;
    }

    /**
     * Метод возвращает общую сумму заказа
     * @return
     */
    public int getTotalAmountOfOrder() {
        int totalAmount = 0;
        for(int i = 0; i < listOfOrderItems.size(); i++){
           totalAmount += listOfOrderItems.get(i).getTotalAmount();

        }
        return totalAmount;
    }

    @Override
    public String toString() {
        return  dateOfCreate + ", " +
                " " + contactPerson +
                " " + deliveryAddress + "," +
                " contact phone's number " + contactPhoneNumber  + "\n" +
                "discount percentage " + discountPercentage  + "\n" +
                "order's status " + orderStatus + "\n" +
                "list of order's items: \n " + listOfOrderItems;
    }
}