CREATE DATABASE orderSystem;

CREATE TABLE products (
    id INT NOT NULL AUTO_INCREMENT,
    articleNumber VARCHAR (100) NOT NULL,
    prodName VARCHAR (100) NOT NULL,
    color VARCHAR (100) NOT NULL,
    price INT NOT NULL,
    stockBalance INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE orders (
    id INT NOT NULL AUTO_INCREMENT,
    dateOfCreate DATE NOT NULL,
    contactPerson VARCHAR (100) NOT NULL,
    deliveryAddress VARCHAR (100) NOT NULL,
    contactPhoneNumber VARCHAR (100) NOT NULL,
    discountPercentage VARCHAR (100) NOT NULL,
    orderStatus VARCHAR (100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE listOfOrderItems (
    id  INT NOT NULL AUTO_INCREMENT,
    prodId INT NOT NULL,
    orderID INT NOT NULL,
    howManyOrdered INT NOT NULL,
    totalAmount INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (prodId) REFERENCES products (id),
    FOREIGN KEY (orderID) REFERENCES orders (id)
);