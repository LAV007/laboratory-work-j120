package ru.avalon.j120.order_accounting_system.ui;

import ru.avalon.j120.order_accounting_system.auxiliary_classes.ListOfOrderItems;
import javax.swing.event.*;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class OrderItemsModel implements TableModel {
    private final String[] columnNames = {"Article", "Name", "Color", "Price per unit", "How many ordered", "Price total"};
    private final Class<?>[] columnTypes = {
            String.class,
            String.class,
            String.class,
            Integer.class,
            Integer.class,
            Integer.class,
            Integer.class
    };

    private List<ListOfOrderItems> listOfOrderItems;
    private List<TableModelListener> listeners = new ArrayList<>();

    public OrderItemsModel() {
        this.listOfOrderItems = new ArrayList<>();
    }

    @Override
    public int getColumnCount() {
        return 6;
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
        return listOfOrderItems.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ListOfOrderItems orderPosition = listOfOrderItems.get(rowIndex);
        switch(columnIndex){
            case 0: return orderPosition.getProduct().getArticleNumber();
            case 1: return orderPosition.getProduct().getName();
            case 2: return orderPosition.getProduct().getColor();
            case 3: return orderPosition.getProduct().getPrice();
            case 4: return orderPosition.getHowManyOrdered();
            case 5: return orderPosition.getTotalAmount();
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

    public void addPosition(ListOfOrderItems list){
        listOfOrderItems.add(list);
        TableModelEvent event = new TableModelEvent(this, listOfOrderItems.size() - 1, listOfOrderItems.size() - 1,
                TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
        for(TableModelListener listener: listeners)
            listener.tableChanged(event);
    }

    public void removePositionWithIndex(int rowNdx){
        listOfOrderItems.remove(rowNdx);
        TableModelEvent event = new TableModelEvent(this, rowNdx, rowNdx,
                TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
        for(TableModelListener listener: listeners)
            listener.tableChanged(event);
    }

    public boolean removePosition(){
        if(listOfOrderItems.size() == 0){
            return false;
        }
        int row = (int) (Math.random() * listOfOrderItems.size());
        listOfOrderItems.remove(row);
        removePositionWithIndex(row);
        return true;
    }
}