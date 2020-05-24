package ru.avalon.j120.order_accounting_system.ui;

import ru.avalon.j120.order_accounting_system.auxiliary_classes.*;
import ru.avalon.j120.order_accounting_system.person.Person;
import ru.avalon.j120.order_accounting_system.person.address.Address;
import ru.avalon.j120.order_accounting_system.person.passport.Passport;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.time.LocalDate;

public class OrderDataDialog extends MyAbstractModalDialog {
    private JTextField yearNumberTextField;
    private JTextField mothTextField;
    private JTextField dayTextField;
    private JTextField nameTextField;
    private JTextField patronymicTextField;
    private JTextField surnameTextField;
    private JTextField countryTextField;
    private JTextField postCodeTextField;
    private JTextField regionTextField;
    private JTextField cityTextField;
    private JTextField streetTextField;
    private JTextField houseNumberTextField;
    private JTextField flatNumberTextField;
    private JTextField phoneNumberTextField;
    private JTextField discountTextField;
    private JTextField statusTextField;


    public OrderDataDialog(Frame owner) {
        super(owner, "Adding new order");

        JPanel controlsPane = getControlsPane();

        JPanel jPanel = new JPanel(new FlowLayout((FlowLayout.LEFT)));
        yearNumberTextField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        yearNumberTextField = new JTextField(15);
        JLabel lblYear = new JLabel("Year");
        lblYear.setLabelFor(yearNumberTextField);
        jPanel.add(lblYear);
        jPanel.add(yearNumberTextField);
        controlsPane.add(jPanel);

        jPanel = new JPanel(new FlowLayout((FlowLayout.LEFT)));
        mothTextField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        mothTextField = new JTextField(15);
        JLabel lblMoth = new JLabel("Moth");
        lblMoth.setLabelFor(mothTextField);
        jPanel.add(lblMoth);
        jPanel.add(mothTextField);
        controlsPane.add(jPanel);

        jPanel = new JPanel(new FlowLayout((FlowLayout.LEFT)));
        dayTextField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        dayTextField = new JTextField(15);
        JLabel lblDay = new JLabel("Day");
        lblDay.setLabelFor(dayTextField);
        jPanel.add(lblDay);
        jPanel.add(dayTextField);
        controlsPane.add(jPanel);

        jPanel = new JPanel(new FlowLayout((FlowLayout.LEFT)));
        nameTextField = new JTextField(11);
        JLabel lblName = new JLabel("Name");
        lblName.setLabelFor(nameTextField);
        jPanel.add(lblName);
        jPanel.add(nameTextField);
        controlsPane.add(jPanel);

        jPanel = new JPanel(new FlowLayout((FlowLayout.LEFT)));
        patronymicTextField = new JTextField(8);
        JLabel lblHowManyOrdered = new JLabel("Patronymic");
        lblHowManyOrdered.setLabelFor(patronymicTextField);
        jPanel.add(lblHowManyOrdered);
        jPanel.add(patronymicTextField);
        controlsPane.add(jPanel);

        jPanel = new JPanel(new FlowLayout((FlowLayout.LEFT)));
        surnameTextField = new JTextField(15);
        JLabel lblSurname = new JLabel("Surname");
        lblSurname.setLabelFor(surnameTextField);
        jPanel.add(lblSurname);
        jPanel.add(surnameTextField);
        controlsPane.add(jPanel);

        jPanel = new JPanel(new FlowLayout((FlowLayout.LEFT)));
        countryTextField = new JTextField(15);
        JLabel lblCountry = new JLabel("Country");
        lblCountry.setLabelFor(countryTextField);
        jPanel.add(lblCountry);
        jPanel.add(countryTextField);
        controlsPane.add(jPanel);

        jPanel = new JPanel(new FlowLayout((FlowLayout.LEFT)));
        postCodeTextField = new JTextField(15);
        JLabel lblPostCode = new JLabel("Post code");
        lblPostCode.setLabelFor(postCodeTextField);
        jPanel.add(lblPostCode);
        jPanel.add(postCodeTextField);
        controlsPane.add(jPanel);

        jPanel = new JPanel(new FlowLayout((FlowLayout.LEFT)));
        regionTextField = new JTextField(15);
        JLabel lblRegion = new JLabel("Region");
        lblRegion.setLabelFor(regionTextField);
        jPanel.add(lblRegion);
        jPanel.add(regionTextField);
        controlsPane.add(jPanel);

        jPanel = new JPanel(new FlowLayout((FlowLayout.LEFT)));
        cityTextField = new JTextField(15);
        JLabel lblCity = new JLabel("City");
        lblCity.setLabelFor(cityTextField);
        jPanel.add(lblCity);
        jPanel.add(cityTextField);
        controlsPane.add(jPanel);

        jPanel = new JPanel(new FlowLayout((FlowLayout.LEFT)));
        streetTextField = new JTextField(15);
        JLabel lblStreet = new JLabel("Street");
        lblStreet.setLabelFor(streetTextField);
        jPanel.add(lblStreet);
        jPanel.add(streetTextField);
        controlsPane.add(jPanel);

        jPanel = new JPanel(new FlowLayout((FlowLayout.LEFT)));
        houseNumberTextField = new JTextField(15);
        JLabel lblHouseNumber = new JLabel("House`s number");
        lblHouseNumber.setLabelFor(houseNumberTextField);
        jPanel.add(lblHouseNumber);
        jPanel.add(houseNumberTextField);
        controlsPane.add(jPanel);

        jPanel = new JPanel(new FlowLayout((FlowLayout.LEFT)));
        flatNumberTextField = new JTextField(15);
        JLabel lblFlatNumber = new JLabel("Flat`s number");
        lblFlatNumber.setLabelFor(flatNumberTextField);
        jPanel.add(lblFlatNumber);
        jPanel.add(flatNumberTextField);
        controlsPane.add(jPanel);

        jPanel = new JPanel(new FlowLayout((FlowLayout.LEFT)));
        phoneNumberTextField = new JTextField(15);
        JLabel lblPhoneNumber = new JLabel("Phone number");
        lblPhoneNumber.setLabelFor(phoneNumberTextField);
        jPanel.add(lblPhoneNumber);
        jPanel.add(phoneNumberTextField);
        controlsPane.add(jPanel);

        jPanel = new JPanel(new FlowLayout((FlowLayout.LEFT)));
        discountTextField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        discountTextField = new JTextField(15);
        JLabel lblDiscount = new JLabel("Discount");
        lblDiscount.setLabelFor(discountTextField);
        jPanel.add(lblDiscount);
        jPanel.add(discountTextField);
        controlsPane.add(jPanel);

        jPanel = new JPanel(new FlowLayout((FlowLayout.LEFT)));
        statusTextField = new JTextField(15);
        JLabel lblStatus = new JLabel("Status");
        lblStatus.setLabelFor(statusTextField);
        jPanel.add(lblStatus);
        jPanel.add(statusTextField);
        controlsPane.add(jPanel);

        pack();
    }

   protected Order addRow(){
        Order order = new Order(LocalDate.of(Integer.parseInt(yearNumberTextField.getText()), Integer.parseInt(mothTextField.getText()), Integer.parseInt(dayTextField.getText())),
                                new Person(new Passport(nameTextField.getText(), patronymicTextField.getText(), surnameTextField.getText())),
                                new Address(countryTextField.getText(), postCodeTextField.getText(), regionTextField.getText(), cityTextField.getText(),streetTextField.getText(),  houseNumberTextField.getText(), flatNumberTextField.getText()),
                                phoneNumberTextField.getText(), Integer.parseInt(discountTextField.getText()), statusTextField.getText());
        return order;
    }
}


