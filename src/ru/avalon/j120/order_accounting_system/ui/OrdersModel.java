package ru.avalon.j120.order_accounting_system.ui;

import ru.avalon.j120.order_accounting_system.auxiliary_classes.Order;
import javax.swing.event.*;
import javax.swing.table.TableModel;
import java.util.*;

public class OrdersModel implements TableModel {
    private final String[] columnNames = {
            "Year", "Moth", "Day",
            "Name", "Patronymic", "Surname",
            "Country", "Post code", "Region", "City", "Street", "House`s number", "Flat`s number",
            "Phone number",
            "Discount",
            "Status"
    };
    private final Class<?>[] columnTypes = {
            Integer.class,
            Integer.class,
            Integer.class,
            String.class,
            String.class,
            String.class,
            String.class,
            String.class,
            String.class,
            String.class,
            String.class,
            String.class,
            String.class,
            String.class,
            Integer.class,
            String.class

    };

    private List<Order> orders;

    private List<ArrayList> orderList = new ArrayList<>();

    private List<TableModelListener> listeners = new ArrayList<>();

    public OrdersModel() {
        this.orders = new ArrayList<>();
    }

    @Override
    public int getColumnCount() {
        return 16;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnTypes[columnIndex];
    }

    @Override
    public int getRowCount() {
        return orders.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Order order = orders.get(rowIndex);
        switch(columnIndex){
            case 0: return order.getYear();
            case 1: return order.getMonth();
            case 2: return order.getDay();
            case 3: return order.getContactPerson().getPass().getName();
            case 4: return order.getContactPerson().getPass().getPatronumic();
            case 5: return order.getContactPerson().getPass().getSurName();
            case 6: return order.getDeliveryAddress().getCountry();
            case 7: return order.getDeliveryAddress().getPostCode();
            case 8: return order.getDeliveryAddress().getRegion();
            case 9: return order.getDeliveryAddress().getCity();
            case 10: return order.getDeliveryAddress().getStreet();
            case 11: return order.getDeliveryAddress().getHousesNumber();
            case 12: return order.getDeliveryAddress().getFlatsNumber();
            case 13: return order.getContactPhoneNumber();
            case 14: return order.getDiscountPercentage();
            case 15: return order.getOrderStatus();
            default:
                throw new Error ("Unreachable place.");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;//все ячейки нередактируемые
    }

    @Override
    public void setValueAt(Object o, int rowIndex, int columnIndex) {
        //ничего не редактируется - ничего не указываем в этом методе
    }

    @Override
    public void addTableModelListener(TableModelListener tableModelListener) {
        listeners.add(tableModelListener);
    }

    @Override
    public void removeTableModelListener(TableModelListener tableModelListener) {
        listeners.remove(tableModelListener);
    }

    public void addPosition(Order list){
        orders.add(list);
        TableModelEvent event = new TableModelEvent(this, orders.size() - 1, orders.size() - 1,
                TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
        for(TableModelListener listener: listeners)
            listener.tableChanged(event);
    }

    public void addPositions(ArrayList <Order> list){
        for(int i = 0; i < list.size(); i++){
            orders.add(list.get(i));
        }


        TableModelEvent event = new TableModelEvent(this, orders.size() - 1, orders.size() - 1,
                TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);

        for(TableModelListener listener: listeners)
            listener.tableChanged(event);
    }


    public void removePositionWithIndex(int rowNdx){
        orders.remove(rowNdx);
        TableModelEvent event = new TableModelEvent(this, rowNdx, rowNdx,
                TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
        for(TableModelListener listener: listeners)
            listener.tableChanged(event);
    }

    public boolean removePosition(){
        if(orders.size() == 0){
            return false;
        }
        int row = (int) (Math.random() * orders.size());
        orders.remove(row);
        removePositionWithIndex(row);
        return true;
    }
}
