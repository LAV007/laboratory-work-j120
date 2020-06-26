package ru.avalon.j120.order_accounting_system.dataBase;

import ru.avalon.j120.order_accounting_system.auxiliary_classes.Order;

import java.sql.*;

public class DB implements AutoCloseable {

    // Подключение к БД
    private static final String HOST = "localhost";
    private static final String PORT = "3307";
    private static final String DB_NAME = "orderaccountingsystem";
    private static final String LOGIN = "root";
    private static final String PASS = "root";

    private Connection dbConn = null;

    /***
     * Получение подключения к базе данных
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connStr = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");
        //помещаем подключение в переменную:
        dbConn = DriverManager.getConnection(connStr, LOGIN, PASS); //должно быть try with resource
        return dbConn;
    }

    /**
     * Проверка на подключение к БД
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void isConnected() throws SQLException, ClassNotFoundException {
        dbConn = getDbConnection();
        System.out.println(dbConn.isValid(1000));
        // ?? dbConn.close();
    }

    /**
     * Создание новой базы данных
     * @param nameOfDB
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void createDataBase(String nameOfDB) throws ClassNotFoundException, SQLException {
        String strSql = "CREATE DATABASE IF NOT EXISTS " + nameOfDB + ";";
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(strSql);
    }

    /**
     * Создание таблицы продуктов
     * @param nameOfProductsTable
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void createProductsTable(String nameOfProductsTable) throws ClassNotFoundException, SQLException {
        String strSql = "CREATE TABLE IF NOT EXISTS " + nameOfProductsTable +
                " (id INT NOT NULL AUTO_INCREMENT, articleNumber VARCHAR (100), prodName VARCHAR (100), color VARCHAR (100)," +
                " price INT, stockBalance INT, PRIMARY KEY (id))"+
                " ENGINE=MYISAM;";
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(strSql);
    }

    public void createNewTable(String newTableName) throws ClassNotFoundException, SQLException {
        String strSql = "CREATE TABLE IF NOT EXISTS " + newTableName +
                " (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), password VARCHAR(50))" +
                " ENGINE=MYISAM;";
        try(Statement statement = dbConn.createStatement())
        {
            statement.executeUpdate(strSql);
        }
    }

    /**
     * Создание таблицы заказов
     * @param nameOfOrdersTable
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void createOrdersTable(String nameOfOrdersTable) throws ClassNotFoundException, SQLException {
        String strSql = "CREATE TABLE IF NOT EXISTS " + nameOfOrdersTable + " (" +
                " id INT NOT NULL AUTO_INCREMENT," +

                " year INT NOT NULL," +
                " month INT NOT NULL," +
                " day INT NOT NULL," +

                " name VARCHAR (100) NOT NULL," +
                " patronymic VARCHAR (100) NOT NULL," +
                " surname VARCHAR (100) NOT NULL," +

                " country VARCHAR (100) NOT NULL," +
                " postCode VARCHAR (100) NOT NULL," +
                " region VARCHAR (100) NOT NULL," +
                " city VARCHAR (100) NOT NULL," +
                " street VARCHAR (100) NOT NULL," +
                " numberOfHouse VARCHAR (100) NOT NULL," +
                " numberOfFlat VARCHAR (100) NOT NULL," +
                " phoneNumber VARCHAR (100) NOT NULL," +

                " discount INT NOT NULL," +
                " status VARCHAR (100) NOT NULL," +
                " PRIMARY KEY (id)" +
                ");";
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(strSql);
    }

    /**
     * Создание таблицы список позиций заказа
     * @param nameOfListOfOrderItemsTable
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void createListOfOrderItemsTable(String nameOfListOfOrderItemsTable) throws ClassNotFoundException, SQLException {
        String strSql = "CREATE TABLE IF NOT EXISTS " + nameOfListOfOrderItemsTable + " (" +
                " id INT NOT NULL AUTO_INCREMENT," +
                " prodId INT NOT NULL," +
                " orderID INT NOT NULL," +
                " howManyOrdered INT NOT NULL," +
                " totalAmount INT NOT NULL," +
                " PRIMARY KEY (id)," +
                " FOREIGN KEY (prodId) REFERENCES products(id)," +
                " FOREIGN KEY (orderId) REFERENCES orders(id));";
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(strSql);
    }

    //добавление данных в таблицу order
    public void addOrder(int id, int year, int month, int day,
                         String name, String patronymic, String surname,
                         String country, String postCode, String region, String city, String street, String numberOfHouse, String numberOfFlat,
                         String phoneNumber, int discount, String status) throws ClassNotFoundException, SQLException {

        String sqlCmd = "INSERT INTO orders (id, year, month, day, " +
                "name, patronymic, surname, " +
                "country, postCode, region, city, street, numberOfHouse, numberOfFlat, phoneNumber, " +
                "discount, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; //знак вопроса это placeholder

       PreparedStatement ps = getDbConnection().prepareStatement(sqlCmd);

        ps.setInt(1, id);
        ps.setInt(2, year);
        ps.setInt(3, month);
        ps.setInt(4, day);

        ps.setString(5, name);
        ps.setString(6, patronymic);
        ps.setString(7, surname);

        ps.setString(8, country);
        ps.setString(9, postCode);
        ps.setString(10, region);
        ps.setString(11, city);
        ps.setString(12, street);
        ps.setString(13, numberOfHouse);
        ps.setString(14, numberOfFlat);
        ps.setString(15, phoneNumber);

        ps.setInt(16, discount);
        ps.setString(17, status);

        ps.executeUpdate();
    }

    //выборка данных из таблицы
    public Order getOrder(String table) throws ClassNotFoundException, SQLException {
        Order order = null;

        String sqlCmd = "SELECT * FROM " + table;
        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sqlCmd);

        while (res.next()) {
            order = new Order(
                    res.getInt("year"),
                    res.getInt("month"),
                    res.getInt("day"),

                    res.getString("name"),
                    res.getString("patronymic"),
                    res.getString("surname"),

                    res.getString("country"),
                    res.getString("postCode"),
                    res.getString("region"),
                    res.getString("city"),
                    res.getString("street"),
                    res.getString("numberOfHouse"),
                    res.getString("numberOfFlat"),
                    res.getString("phoneNumber"),

                    res.getInt("discount"),
                    res.getString("status")
                    );
        }
        return order;
    }

    @Override
    public void close() throws Exception {
        dbConn.close();
    }
}