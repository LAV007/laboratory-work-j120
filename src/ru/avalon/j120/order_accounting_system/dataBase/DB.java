package ru.avalon.j120.order_accounting_system.dataBase;

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
     * Созданин таблицы продуктов
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
     * Созданин таблицы заказов
     * @param nameOfOrdersTable
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void createOrdersTable(String nameOfOrdersTable) throws ClassNotFoundException, SQLException {
        String strSql = "CREATE TABLE IF NOT EXISTS " + nameOfOrdersTable + " (" +
                " id INT NOT NULL AUTO_INCREMENT," +
                " dateOfCreate DATE NOT NULL," +
                " contactPerson VARCHAR (100) NOT NULL," +
                " deliveryAddress VARCHAR (100) NOT NULL," +
                " contactPhoneNumber VARCHAR (100) NOT NULL," +
                " discountPercentage VARCHAR (100) NOT NULL," +
                " orderStatus VARCHAR (100) NOT NULL," +
                " PRIMARY KEY (id)" +
                ");";
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(strSql);
    }

    /**
     * Созданин таблицы список позиций заказа
     * @param nameOfListOfOrderItemsTable
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void createListOfOrderItemsTable(String nameOfListOfOrderItemsTable) throws ClassNotFoundException, SQLException {
        String strSql = "CREATE TABLE IF NOT EXISTS" + nameOfListOfOrderItemsTable + " (" +
                " id  INT NOT NULL AUTO_INCREMENT," +
                " prodId INT NOT NULL," +
                " orderID INT NOT NULL," +
                " howManyOrdered INT NOT NULL," +
                " totalAmount INT NOT NULL," +
                " PRIMARY KEY (id)," +
                " FOREIGN KEY (prodId) REFERENCES products (id)," +
                " FOREIGN KEY (orderID) REFERENCES orders (id)" +
                ");";
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(strSql);
    }

    @Override
    public void close() throws Exception {
        dbConn.close();
    }
}