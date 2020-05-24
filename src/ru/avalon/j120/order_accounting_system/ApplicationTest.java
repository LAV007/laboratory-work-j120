package ru.avalon.j120.order_accounting_system;

import ru.avalon.j120.order_accounting_system.auxiliary_classes.*;
import static ru.avalon.j120.order_accounting_system.auxiliary_classes.OrderStatusEnum.*;
import ru.avalon.j120.order_accounting_system.order_manager.OrderManager;
import ru.avalon.j120.order_accounting_system.person.Person;
import ru.avalon.j120.order_accounting_system.person.address.Address;
import ru.avalon.j120.order_accounting_system.person.passport.Passport;
import ru.avalon.j120.order_accounting_system.write_read_to_from_file.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ApplicationTest {
    public static void main(String[] args) {
    try {
        // Создаю продукты:
        Product milk = new Product("m-354", "milk", "color", 10, 110);
        Product cheese = new Product("cH-57", "cheese", "white", 540, 150);
        Product curd = new Product("c-588", "curd", "black", 778, 130);

        //Добавляю созданные продукты в список; (список созданных продуктов будет записан на склад):
        ArrayList<Product> products = new ArrayList<>();
        products.add(milk);
        products.add(cheese);
        products.add(curd);

        //Создаю склад
        WareHouse wareHouse = new WareHouse(products);

        //Добавляю новые продукты в список, чтобы записать список на склад:
        //Данный метод не позволяет добавить товар с существующим индексом.
        //wareHouse.addProdToList(new Product("f-858", "flowers", "yellow", 20, 420));
        //wareHouse.addProdToList(new Product("f-858", "flowers", "yellow", 20, 420));

        //Записываю на склад список продуктов
        //Записываю склад с продуктами в файл:
        WriteReadProductToFromList writeReadProductToFromList = new WriteReadProductToFromList();
        writeReadProductToFromList.read();
        writeReadProductToFromList.write(wareHouse.getProductsPosition());

        //Создаю список заказов
        ArrayList<Order> orders = new ArrayList<>();

        //Создаю объект, который хранить список заказов
        OrderManager orderManager = new OrderManager(orders);

        //Добавляю продукты в заказ
        orderManager.addOrderPosition(wareHouse.getProductsPosition().get(0), 1);
        orderManager.addOrderPosition(wareHouse.getProductsPosition().get(1), 1);

        //Уменьшаю количество продукта на складе
        wareHouse.getProductsPosition().get(0).reduceBalance(orderManager.getList().getHowManyOrdered());
        wareHouse.getProductsPosition().get(1).reduceBalance(orderManager.getList().getHowManyOrdered());
        //Записываю изменение количества товара
        writeReadProductToFromList.read();
        writeReadProductToFromList.write(wareHouse.getProductsPosition());

        //Добавляю в объект, хранящий список заказов, новый заказ
        orderManager.addNewOrder(LocalDate.of(2020, 5, 5),
                new Person(new Passport("Connor", "Anthony ", "McGregor")),
                new Address("Ireland", "45957", "Laighean", "Dublin", "Wall street", "24", "57"),
                "+79117680007", 10, PREPARED);

        //Создаю список, хранящий заказы
        ArrayList<OrderManager> orderManagers = new ArrayList<>();
        orderManagers.add(orderManager);

        //Вывожу отдельный заказ:
        System.out.println(orderManagers.get(0));
        //Вывожу детальные данные о заказе (для заполнения таблици в окне программы)
        System.out.println(orderManager.getOrders().get(0).getContactPerson());
        System.out.println(orderManager.getOrders().get(0).getContactPhoneNumber());
        System.out.println(orderManager.getOrders().get(0).getDeliveryAddress().getCity());
        System.out.println(orderManager.getOrders().get(0).getTotalAmountOfOrder()); //общая сумма заказа
        System.out.println(orderManager.getOrders().get(0).getDiscountPercentage());
        System.out.println(orderManager.getOrders().get(0).getOrderStatus());

        //Записываю список заказов в файл:
        SerializationDeserializationOfOrder serDeserOfOrder = new SerializationDeserializationOfOrder();
        serDeserOfOrder.doSerialization(orderManagers);
        serDeserOfOrder.doDeserialization();

    } catch (Exception e){
            e.printStackTrace();
        }
    }
}
/*
        second.makeOrder(LocalDate.of(2020, 5, 6),
                new Person(new Passport("John", "Edward", "Smith")),
                new Address("U.S.A", "15848",  "California",  "Los Angeles",  "Gold\'s Gym",  "34",  "7"),
                "+79811895815", 10, QUANTITY,
                new OrderRow(productListForSecondOrder, 1));

        second.makeOrder(LocalDate.of(2020, 5, 7),
                new Person(new Passport("Anthony", "Armand", "Ferguson")),
                new Address("U.S.A", "75521", "California", "Oxnard", "Ventura street", "75", "35"),
                "+79217073707", 10, CANCELED,
                new OrderRow(productListForThirdOrder, 250));
 */