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

        JPanel jPanel = new JPanel();
        articleNumberTextField = new JTextField(40);
        JLabel lblArticle = new JLabel("Article");
        lblArticle.setLabelFor(articleNumberTextField);
        jPanel.add(lblArticle);
        jPanel.add(articleNumberTextField);
        controlsPane.add(jPanel);

        jPanel = new JPanel();
        nameTextField = new JTextField(40);
        JLabel lblName = new JLabel("Name");
        lblName.setLabelFor(nameTextField);
        jPanel.add(lblName);
        jPanel.add(articleNumberTextField);
        controlsPane.add(jPanel);

        jPanel = new JPanel();
        colorTextField = new JTextField(40);
        JLabel lblColor = new JLabel("Color");
        lblColor.setLabelFor(colorTextField);
        jPanel.add(lblColor);
        jPanel.add(colorTextField);
        controlsPane.add(jPanel);

        jPanel = new JPanel();
        priceTextField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        JLabel lblPrice = new JLabel("Price per unit");
        lblPrice.setLabelFor(priceTextField);
        jPanel.add(lblPrice);
        jPanel.add(priceTextField);
        controlsPane.add(jPanel);

        jPanel = new JPanel();
        howManyOrderedTextField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        JLabel lblHowManyOrdered = new JLabel("How many ordered");
        lblHowManyOrdered.setLabelFor(howManyOrderedTextField);
        jPanel.add(lblHowManyOrdered);
        jPanel.add(howManyOrderedTextField);
        controlsPane.add(jPanel);
/*
        jPanel = new JPanel();
        totalAmountTextField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        JLabel lblTotalAmount = new JLabel("Price total");
        lblTotalAmount.setLabelFor(totalAmountTextField);
        jPanel.add(lblTotalAmount);
        jPanel.add(totalAmountTextField);
        controlsPane.add(jPanel);
*/
    }

    public ListOfOrderItems buildListOfOrderItemsFromFields(){
        Product product = new Product(articleNumberTextField, nameTextField, colorTextField, priceTextField);
        int howMany = Integer.parseInt(howManyOrderedTextField.getText());
        //int totalAm = Integer.parseInt(totalAmountTextField.getText());
        return new ListOfOrderItems(product, howMany);
    }
}
