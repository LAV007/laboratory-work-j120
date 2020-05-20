package ru.avalon.j120.order_accounting_system.ui;

import ru.avalon.j120.order_accounting_system.auxiliary_classes.ListOfOrderItems;
import ru.avalon.j120.order_accounting_system.auxiliary_classes.Order;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class OrdersModel implements TableModel {
    private final String[] columnNames = {
            "Year", "Moth", "Day", //integers
            "Name", "Patronymic", "Surname",
            "Country", "Post code", "Region", "City", "Street", "House`s number", "Flat`s number",
            "Phone number",
            "Discount",
            "Status"
    };
    private final Class<?>[] columnTypes = {
            String.class,
            String.class,
            String.class,
            Integer.class,
            Integer.class,
            Integer.class,
            Integer.class
    };

    private List<Order> orders;
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
        Order orderPosition = orders.get(rowIndex);
        switch(columnIndex){
            case 0: return orderPosition.getDateOfCreate();
            case 1: return orderPosition.getContactPerson();
            case 2: return orderPosition.getDeliveryAddress();
            case 3: return orderPosition.getContactPhoneNumber();
            case 4: return orderPosition.getDiscountPercentage();
            case 5: return orderPosition.getOrderStatus();
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
