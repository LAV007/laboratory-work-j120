package ru.avalon.j120.order_accounting_system.write_read_to_from_file;

import ru.avalon.j120.order_accounting_system.configuration.Configuration;
import ru.avalon.j120.order_accounting_system.order_manager.OrderManager;
import java.io.*;
import java.util.ArrayList;

public class SerializationDeserializationOfOrder {
    Configuration configuration = Configuration.getInitialisation();
    final String ordersConfig = configuration.getProperties().getProperty("orders");
    ArrayList<OrderManager> list = new ArrayList<>();

    public SerializationDeserializationOfOrder() throws IOException {
    }

    public void doSerialization(ArrayList<OrderManager> list) throws IOException {
        FileOutputStream fos = new FileOutputStream(ordersConfig);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
        oos.close();
    }

    public void doDeserialization() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(ordersConfig);
        ObjectInputStream ois = new ObjectInputStream(fis);
        list = (ArrayList) ois.readObject();
        ois.close();
    }
}