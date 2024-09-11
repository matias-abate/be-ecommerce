CREATE DATABASE ecommerce_apis_uade;
USE ecommerce_apis_uade;

-- CREATE TABLES
CREATE TABLE users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       birth_date DATE,
                       firstname VARCHAR(50),
                       lastname VARCHAR(50),
                       role ENUM('ADMIN', 'CUSTOMER') NOT NULL
);

CREATE TABLE categories (
                            category_id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(50) NOT NULL
);

CREATE TABLE products (
                          product_id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          description VARCHAR(500),
                          images LONGBLOB,
                          price FLOAT NOT NULL,
                          stock INT NOT NULL,
                          category_id INT,
                          FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

CREATE TABLE orders (
                        order_id INT AUTO_INCREMENT PRIMARY KEY,
                        user_id INT NOT NULL,
                        order_date DATE,
                        status ENUM('SOLICITADA', 'APROBADA', 'COMPLETADA', 'CANCELADA') NOT NULL,
                        total_amount FLOAT NOT NULL,
                        FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE order_details (
                               order_detail_id INT AUTO_INCREMENT PRIMARY KEY,
                               order_id INT NOT NULL,
                               product_id INT NOT NULL,
                               unit_price FLOAT NOT NULL,
                               quantity INT NOT NULL,
                               total_price FLOAT NOT NULL,
                               FOREIGN KEY (order_id) REFERENCES orders(order_id),
                               FOREIGN KEY (product_id) REFERENCES products(product_id)
);

CREATE TABLE transactions (
                              transaction_id INT AUTO_INCREMENT PRIMARY KEY,
                              order_id INT NOT NULL,
                              transaction_date DATE,
                              amount FLOAT NOT NULL,
                              payment_method VARCHAR(20) NOT NULL,
                              status ENUM('PENDIENTE', 'EXITOSA', 'FALLIDA') NOT NULL,
                              FOREIGN KEY (order_id) REFERENCES orders(order_id)
);

CREATE TABLE wishlist (
                          wishlist_id INT AUTO_INCREMENT PRIMARY KEY,
                          user_id INT,
                          FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE wishlist_items (
                                wishlist_item_id INT AUTO_INCREMENT PRIMARY KEY,
                                wishlist_id INT,
                                product_id INT,
                                FOREIGN KEY (wishlist_id) REFERENCES wishlist(wishlist_id),
                                FOREIGN KEY (product_id) REFERENCES products(product_id)
);


CREATE TABLE cart (
                      cart_id INT AUTO_INCREMENT PRIMARY KEY,
                      user_id INT NOT NULL,
                      total_price FLOAT NOT NULL,
                      FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE cart_items (
                            cart_item_id INT AUTO_INCREMENT PRIMARY KEY,
                            cart_id INT NOT NULL,
                            product_id INT NOT NULL,
                            quantity INT NOT NULL,
                            unit_price FLOAT NOT NULL,
                            total_price FLOAT NOT NULL,
                            FOREIGN KEY (cart_id) REFERENCES cart(cart_id),
                            FOREIGN KEY (product_id) REFERENCES products(product_id)
);

-- INSERTAMOS VALORES EN NUESTRAS TABLAS
INSERT INTO users (username, password, email, birth_date, firstname, lastname, role) VALUES
                                                                                         ('jdoe', 'password123', 'jdoe@example.com', '1995-03-25', 'John', 'Doe', 'CUSTOMER'),
                                                                                         ('asmith', 'password456', 'asmith@example.com', '1988-07-14', 'Alice', 'Smith', 'ADMIN'),
                                                                                         ('bwilson', 'password789', 'bwilson@example.com', '1990-01-02', 'Bob', 'Wilson', 'CUSTOMER'),
                                                                                         ('cmartinez', 'password321', 'cmartinez@example.com', '1985-05-21', 'Carlos', 'Martinez', 'CUSTOMER'),
                                                                                         ('djohnson', 'password654', 'djohnson@example.com', '1992-09-19', 'Diana', 'Johnson', 'ADMIN'),
                                                                                         ('eroberts', 'password987', 'eroberts@example.com', '1997-11-04', 'Emily', 'Roberts', 'CUSTOMER'),
                                                                                         ('fgarcia', 'password111', 'fgarcia@example.com', '1993-02-28', 'Frank', 'Garcia', 'CUSTOMER'),
                                                                                         ('ggonzalez', 'password222', 'ggonzalez@example.com', '1991-06-11', 'Gabriel', 'Gonzalez', 'ADMIN'),
                                                                                         ('hwhite', 'password333', 'hwhite@example.com', '1989-12-17', 'Holly', 'White', 'CUSTOMER'),
                                                                                         ('iperez', 'password444', 'iperez@example.com', '1987-08-30', 'Isabel', 'Perez', 'CUSTOMER');

INSERT INTO categories (name) VALUES
                                  ('Tencnología'),
                                  ('Hogar'),
                                  ('Decoración'),
                                  ('Indumentaria');


INSERT INTO products (name, description, price, stock, category_id) VALUES
                                                                        ('Laptop X1', 'Laptop de alto rendimiento para profesionales', 1200.00, 50, 1),
                                                                        ('Smartphone Y7', 'Smartphone de última generación con características avanzadas', 800.00, 150, 1),
                                                                        ('Auriculares Inalámbricos Z3', 'Auriculares inalámbricos con cancelación de ruido', 150.00, 200, 1),
                                                                        ('Monitor 4K M27', 'Monitor ultra HD de 27 pulgadas para juegos y trabajo', 400.00, 75, 1),
                                                                        ('Ratón Gaming G5', 'Ratón ergonómico para juegos con iluminación RGB', 60.00, 300, 1),
                                                                        ('Teclado Mecánico K9', 'Teclado mecánico con teclas personalizables', 120.00, 100, 1),
                                                                        ('Reloj Inteligente S2', 'Reloj inteligente con seguimiento de actividad física', 250.00, 180, 1),
                                                                        ('Tableta T10', 'Tableta de 10 pulgadas para entretenimiento y trabajo', 300.00, 90, 2),
                                                                        ('Altavoz Bluetooth B8', 'Altavoz Bluetooth portátil con graves profundos', 90.00, 250, 3),
                                                                        ('Disco Duro Externo H1', 'Disco duro externo de 1TB para almacenamiento adicional', 100.00, 150, 4);