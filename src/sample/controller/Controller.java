package sample.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.avalon.j120.order_accounting_system.auxiliary_classes.Product;
import ru.avalon.j120.order_accounting_system.auxiliary_classes.WareHouse;
import ru.avalon.j120.order_accounting_system.write_read_to_from_file.WriteReadProductToFromList;

import javax.swing.*;

public class Controller {
    @FXML
    private TextField articleTextField;
    @FXML
    private TextField nameOfProductTextField;
    @FXML
    private TextField colorTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField stockBalanceTextField;
    @FXML
    private TextField yearTextField;
    @FXML
    private TextField mothTextField;
    @FXML
    private TextField dayOfMonthTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField patronymicTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField countryTextField;
    @FXML
    private TextField postCodeTextField;
    @FXML
    private TextField regionTextField;
    @FXML
    private TextField cityTextField;
    @FXML
    private TextField streetTextField;
    @FXML
    private TextField houseNumTextField;
    @FXML
    private TextField phoneNumTextField;
    @FXML
    private TextField flatNumTextField;
    @FXML
    private TextField discountPercentageTextField;
    @FXML
    private TextField orderStatusTextField;
    @FXML
    private TextField howManyOrderedTextField;
    @FXML
    private Button createNewOrderBtn;
    @FXML
    private Button addNewProdToWarehBtn;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, String> articleTable;
    @FXML
    private TableColumn<Product, String> nameOfProductTable;
    @FXML
    private TableColumn<Product, String> colorTable;
    @FXML
    private TableColumn<Product, String> priceTable;
    @FXML
    private TableColumn<Product, String> stockBalanceTable;
    @FXML
    private TableView<?> orderTable;
    @FXML
    private TableColumn<?, ?> nameOfPersonTable;
    @FXML
    private TableColumn<?, ?> surnameTable;
    @FXML
    private TableColumn<?, ?> phoneNumberTable;
    @FXML
    private TableColumn<?, ?> countryTable;
    @FXML
    private TableColumn<?, ?> cityTable;
    @FXML
    private TableColumn<?, ?> hawManyTable;
    @FXML
    private TableColumn<?, ?> discountTable;
    @FXML
    private TableColumn<?, ?> orderStatusTable;


    List<Product> members = List.of(
            new Product("m-354", "milk", "color", 10, 110),
            new Product("c-588", "curd", "black", 778, 130),
            new Product("cH-57", "cheese", "white", 540, 150));
    @FXML
    void initialize() throws IOException {

        // Создаю default продукты
        Product milk = new Product("m-354", "milk", "color", 10, 110);
        Product cheese = new Product("cH-57", "cheese", "white", 540, 150);
        Product curd = new Product("c-588", "curd", "black", 778, 130);

        //Добавляю созданные продукты в список; (список созданных продуктов будет записан на склад):
        ArrayList<Product> products = new ArrayList<>();
        products.add(milk);
        products.add(cheese);
        products.add(curd);

        //Создаю склад
        WriteReadProductToFromList writeReadProductToFromList = new WriteReadProductToFromList();
        WareHouse wareHouse = new WareHouse( writeReadProductToFromList.read()); //Добавляю на склад default продукты

        addNewProdToWarehBtn.setOnAction(e -> {
            wareHouse.addProdToList(new Product(articleTextField.getText(), nameOfProductTextField.getText(), colorTextField.getText(),
                    Integer.parseInt(priceTextField.getText()), Integer.parseInt(stockBalanceTextField.getText())));

            //После добавления нового продукта на склад - очищаю поля в графическом интерфейсе
            articleTextField.setText("");
            nameOfProductTextField.setText("");
            colorTextField.setText("");
            priceTextField.setText("");
            stockBalanceTextField.setText("");
        try {
                //Записываю на склад продукты, которые создаю в пользовательском интерфейсе:
                writeReadProductToFromList.write(wareHouse.getProductsPosition());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        articleTable.setCellValueFactory(new PropertyValueFactory<Product, String>("articleNumber"));
        nameOfProductTable.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        colorTable.setCellValueFactory(new PropertyValueFactory<Product, String>("color"));
        priceTable.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
        stockBalanceTable.setCellValueFactory(new PropertyValueFactory<Product, String>("stockBalance"));
        productTable.setItems(getProduct());
        });
    }
    public ObservableList<Product> getProduct(){
        ObservableList<Product> products = FXCollections.observableArrayList(members);
        products.add(new Product("article", "name", "color",
                24, 12));
        return products;
    }
}