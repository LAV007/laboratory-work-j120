package ru.avalon.j120.order_accounting_system.ui;

import javax.swing.*;
import java.awt.*;

public class MyAbstractModalDialog extends JDialog {
    private JPanel controlsPane;
    private boolean okPressed;

    public MyAbstractModalDialog(Frame owner, String title){
        super(owner, title, true);

        controlsPane = new JPanel();
        controlsPane.setLayout(new BoxLayout(controlsPane, BoxLayout.Y_AXIS));
        add(controlsPane, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton btnOk = new JButton("Ok");
        JButton btnCancel = new JButton("Cancel");

        btnOk.addActionListener(event -> {
            okPressed = true;
            setVisible(false);
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
