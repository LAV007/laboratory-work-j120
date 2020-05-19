package ru.avalon.j120.order_accounting_system.ui;

import ru.avalon.j120.order_accounting_system.auxiliary_classes.ListOfOrderItems;
import ru.avalon.j120.order_accounting_system.auxiliary_classes.Product;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MyAbstractModalDialog extends JDialog {
    private JPanel controlsPane;
    private boolean okPressed;

    public MyAbstractModalDialog(Frame owner, String title){
        super(owner, title);

        controlsPane = new JPanel();
        controlsPane.setLayout(new BoxLayout(controlsPane, BoxLayout.Y_AXIS));
        add(controlsPane, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton btnOk = new JButton("Ok");
        JButton btnCancel = new JButton("Cancel");

        btnOk.addActionListener(event -> {
            okPressed = true;
            setVisible(false);

            //Если я перебрасываю ответственность за добавление позиции сюда, то при нажатии на кнопку Ок данный товар добавляется в позицию заказа
          /*  ListOfOrderItems listOfOrderItems = new ListOfOrderItems(new Product("f-858", "flowers", "yellow", 20, 420), 2);
            ListDemo.tableModel.addPosition(listOfOrderItems);*/
        });

        btnCancel.addActionListener(event -> {
            okPressed = false;
            setVisible(false);
        });

        bottom.add(btnOk);
        bottom.add(btnCancel);

        add(bottom, BorderLayout.SOUTH);

    }

    protected JPanel getControlsPane(){
        return controlsPane;
    }

    public boolean isOkPressed() {
        return okPressed;
    }
}
