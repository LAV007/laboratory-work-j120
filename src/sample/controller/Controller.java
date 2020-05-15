package sample.controller;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import ru.avalon.j120.order_accounting_system.auxiliary_classes.Product;
import ru.avalon.j120.order_accounting_system.auxiliary_classes.WareHouse;
import ru.avalon.j120.order_accounting_system.write_read_to_from_file.WriteReadProductToFromList;

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
    private TableColumn<?, ?> articleTable;
    @FXML
    private TableColumn<?, ?> nameOfProductTable;
    @FXML
    private TableColumn<?, ?> colorTable;
    @FXML
    private TableColumn<?, ?> priceTable;
    @FXML
    private TableColumn<?, ?> stockBalanceTable;
    @FXML
    private TableColumn<?, ?> nameOfPersonTable;
    @FXML
    private TableColumn<?, ?> surnameTable;
    @FXML
    private TableColumn<?, ?> phobeNumberTable;
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
    @FXML
    void initialize() throws IOException {

        // Создаю продукты:
        Product milk = new Product("m-354", "milk", "color", 10, 110);
        Product cheese = new Product("cH-57", "cheese", "white", 540, 150);
        Product curd = new Product("c-588", "curd", "black", 778, 130);

        //Добавляю созданные продукты в список; (список созданных продуктов будет записан на склад):
        ArrayList<Product> products = new ArrayList<>();
        products.add(milk);
        products.add(cheese);
        products.add(curd);

        //Создаю склад
        WareHouse wareHouse = new WareHouse(products);

        WriteReadProductToFromList writeReadProductToFromList = new WriteReadProductToFromList();
        writeReadProductToFromList.read();

        addNewProdToWarehBtn.setOnAction(e -> {
            wareHouse.addProdToList(new Product(articleTextField.getText(), nameOfProductTextField.getText(), colorTextField.getText(),
                    Integer.parseInt(priceTextField.getText()), Integer.parseInt(stockBalanceTextField.getText())));
            try {
                writeReadProductToFromList.read();
                writeReadProductToFromList.write(wareHouse.getProductsPosition());
            } catch (IOException exception) {
                exception.printStackTrace();
            }

            articleTable.setText(milk.getArticleNumber());
        });
        writeReadProductToFromList.write(wareHouse.getProductsPosition());
    }
}