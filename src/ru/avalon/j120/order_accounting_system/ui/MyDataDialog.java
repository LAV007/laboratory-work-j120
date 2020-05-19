package ru.avalon.j120.order_accounting_system.ui;

import ru.avalon.j120.order_accounting_system.auxiliary_classes.*;
import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class MyDataDialog extends MyAbstractModalDialog {
    private JTextField articleNumberTextField;
    private JTextField nameTextField;
    private JTextField colorTextField;
    private JTextField priceTextField;
    private JTextField howManyOrderedTextField;
    //private JTextField totalAmountTextField;


    public MyDataDialog(Frame owner) {
        super(owner, "Adding order`s position");

        JPanel controlsPane = getControlsPane();

        JPanel jPanel = new JPanel(new FlowLayout((FlowLayout.LEFT)));
        articleNumberTextField = new JTextField(15);
        JLabel lblArticle = new JLabel("Article");
        lblArticle.setLabelFor(articleNumberTextField);
        jPanel.add(lblArticle);
        jPanel.add(articleNumberTextField);
        controlsPane.add(jPanel);

        jPanel = new JPanel(new FlowLayout((FlowLayout.LEFT)));
        nameTextField = new JTextField(15);
        JLabel lblName = new JLabel("Name");
        lblName.setLabelFor(nameTextField);
        jPanel.add(lblName);
        jPanel.add(nameTextField);
        controlsPane.add(jPanel);

        jPanel = new JPanel(new FlowLayout((FlowLayout.LEFT)));
        colorTextField = new JTextField(15);
        JLabel lblColor = new JLabel("Color");
        lblColor.setLabelFor(colorTextField);
        jPanel.add(lblColor);
        jPanel.add(colorTextField);
        controlsPane.add(jPanel);

        jPanel = new JPanel(new FlowLayout((FlowLayout.LEFT)));
        priceTextField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        priceTextField.setColumns(11);
        JLabel lblPrice = new JLabel("Price per unit");
        lblPrice.setLabelFor(priceTextField);
        jPanel.add(lblPrice);
        jPanel.add(priceTextField);
        controlsPane.add(jPanel);

        jPanel = new JPanel(new FlowLayout((FlowLayout.LEFT)));
        howManyOrderedTextField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        howManyOrderedTextField.setColumns(8);
        JLabel lblHowManyOrdered = new JLabel("How many ordered");
        lblHowManyOrdered.setLabelFor(howManyOrderedTextField);
        jPanel.add(lblHowManyOrdered);
        jPanel.add(howManyOrderedTextField);
        controlsPane.add(jPanel);

        pack();
    }

    protected ListOfOrderItems addRow(){
        Product product = new Product(articleNumberTextField.getText(), nameTextField.getText(), colorTextField.getText(), Integer.parseInt(priceTextField.getText()));
        int howMany = Integer.parseInt(howManyOrderedTextField.getText());
        return new ListOfOrderItems(product, howMany);
    }
}
