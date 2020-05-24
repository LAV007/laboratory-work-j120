package ru.avalon.j120.order_accounting_system.ui;

import ru.avalon.j120.order_accounting_system.auxiliary_classes.ListOfOrderItems;
import ru.avalon.j120.order_accounting_system.auxiliary_classes.Order;
import java.awt.BorderLayout;
import javax.swing.*;

public class ListDemo extends JFrame {
	private JTable orders;
	private JTable orderItems;
	private OrderItemsModel tableModel = new OrderItemsModel();
	private OrdersModel ordersModel = new OrdersModel();
	
	public ListDemo(){
		super("Order Accounting System");
		setBounds(30, 100, 1300, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JButton createOrder = new JButton("Create order");

		createOrder.addActionListener(event -> {
			OrderDataDialog dlg = new OrderDataDialog(this);
			dlg.setVisible(true);
			if(dlg.isOkPressed()){
				Order order = dlg.addRow();
				ordersModel.addPosition(order);
			}
		});

		JPanel top = new JPanel(new BorderLayout());
		JToolBar topTB = new JToolBar();

		topTB.add(createOrder);
		top.add(topTB, BorderLayout.NORTH);

		orders = new JTable(/*new Object[][] {},
				new String[] {
						"Year", "Moth", "Day", //integers
						"Name", "Patronymic", "Surname",
						"Country", "Post code", "Region", "City", "Street", "House`s number", "Flat`s number",
						"Phone number",
						"Discount",
						"Status"
				}*/ordersModel);

		
		JPanel topTblPnl = new JPanel(new BorderLayout());
		topTblPnl.add(orders.getTableHeader(), BorderLayout.NORTH);
		topTblPnl.add(orders, BorderLayout.CENTER);

		top.add(topTblPnl, BorderLayout.CENTER);

		//Отображаю таблицу с позициями заказа
		orderItems = new JTable(tableModel);

		JPanel bottom = new JPanel(new BorderLayout());//
		JToolBar bottomTB = new JToolBar();

		//Создаю кнопку добавлния позиции заказа в заказ
		JButton addPosition = new JButton("Add position..");
		//Создаю кнопку удаления позиции заказа из заказа
		JButton removePosition = new JButton("Remove position");

		//Обрадотка нажатия на кнопку Добавить позицию
		//addPosition.addActionListener(event -> tableModel.addPosition());
		addPosition.addActionListener(event -> {


			ProductDataDialog dlg = new ProductDataDialog(this);
			dlg.setVisible(true);
			if(dlg.isOkPressed()){
				ListOfOrderItems listOfOrderItems = dlg.addRow();
				tableModel.addPosition(listOfOrderItems);
			}


			});
		//Обрботка события при нажатии на кнопку "Remove position" (удаление ВЫБРАННОЙ(ЫХ) строки)
		removePosition.addActionListener(event ->{
			int[] selectedPositions = orderItems.getSelectedRows();
			for(int i = selectedPositions.length - 1; i >= 0; i--){
				tableModel.removePositionWithIndex(selectedPositions[i]);
			}
		});
		/*Обрботка события при нажатии на кнопку "Remove position" (удаление ПОСЛЕДНЕЙ строки)
		removePosition.addActionListener(event -> {
			if (!tableModel.removePosition()) {
				JOptionPane.showMessageDialog(this, "There aren't  more position to delete", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});*/

		//Добавляю кнопку "Add position" на JToolBar
		bottomTB.add(addPosition);
		//Добавляю кнопку "Remove position" на JToolBar
		bottomTB.add(removePosition);
		//Добавляю объект типа:JToolBar с наванием "bottomTB" на объект типа:JPanel с названием bottom
		bottom.add(bottomTB, BorderLayout.NORTH);

		JPanel bottomTblPnl = new JPanel(new BorderLayout());
		bottomTblPnl.add(new JScrollPane(orderItems), BorderLayout.CENTER);
		bottomTblPnl.add(orderItems.getTableHeader(), BorderLayout.NORTH);

		bottom.add(bottomTblPnl, BorderLayout.CENTER);
		JSplitPane jSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, top, bottom);
		jSplitPane.setDividerLocation(265);
		add(jSplitPane);
	}

	public static void main(String[] args) {
		new ListDemo().setVisible(true);
	}
}
