# Simple POS

A simple console-based Point of Sale (POS) system built with Java and MySQL. It is a starter project for learning JDBC, the DAO pattern, and how a Java app talks to a database.

## Features

- Connects to a MySQL database using JDBC
- Lists all products
- Looks up a product by ID
- Adds new products
- Updates product stock

## Tech Stack

- Java 21 (Eclipse Adoptium JDK)
- MySQL Server
- Maven
- MySQL Connector/J 8.4.0
- IntelliJ IDEA

## Project Structure

```
simple-pos/
├── pom.xml
└── src/
    └── main/
        └── java/
            ├── DB.java          # Database connection
            ├── Product.java     # Product model
            ├── ProductDAO.java  # All SQL for the products table
            └── Main.java        # Entry point
```

## Database Setup

1. Make sure MySQL Server is running.
2. Run the following in MySQL Workbench or the `mysql` command line:

```sql
CREATE DATABASE pos_db;
USE pos_db;

CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL DEFAULT 0
);

CREATE TABLE sales (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10,2) NOT NULL
);

CREATE TABLE sale_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sale_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (sale_id) REFERENCES sales(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

INSERT INTO products (name, price, stock) VALUES
('Bread', 60.00, 100),
('Milk 500ml', 65.00, 80),
('Sugar 1kg', 180.00, 50);
```

## Configuration

Open `DB.java` and set your MySQL credentials:

```java
private static final String URL = "jdbc:mysql://localhost:3306/pos_db";
private static final String USER = "root";
private static final String PASSWORD = "your_mysql_password";
```

## Maven Dependency

Add this to `pom.xml`, then reload Maven:

```xml
<dependencies>
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <version>8.4.0</version>
    </dependency>
</dependencies>
```

## Running the Project

1. Open the project in IntelliJ IDEA.
2. Reload Maven so the MySQL driver downloads.
3. Open `Main.java` and click the green **Run** arrow.

Expected output: the list of products from the database.

## Troubleshooting

| Error | Cause | Fix |
|-------|-------|-----|
| `No suitable driver found for jdbc:mysql://...` | MySQL driver jar is not on the classpath | Reload Maven and check that `mysql-connector-j` appears under External Libraries |
| `Access denied for user 'root'` | Wrong password in `DB.java` | Update `PASSWORD` to your real MySQL password |
| `Communications link failure` | MySQL server is not running | Start MySQL Server and confirm port 3306 |
| `Unknown database 'pos_db'` | Database was not created | Run the SQL in the Database Setup section |

## Roadmap

- [x] Database and tables
- [x] JDBC connection
- [x] Product model and ProductDAO
- [ ] Cart and checkout (save to `sales` and `sale_items`, reduce stock)
- [ ] Console menu
- [ ] Swing GUI
- [ ] Receipts and sales reports

## Author

Steve Ongera