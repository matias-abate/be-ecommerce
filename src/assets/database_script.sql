CREATE DATABASE ecommerce_apis;
USE ecommerce_apis;

-- 2) CREAMOS LAS TABLAS
CREATE TABLE IF NOT EXISTS users (
                                     user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                     username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    birth_date DATE DEFAULT NULL,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    role VARCHAR(20) NOT NULL
    );

CREATE TABLE IF NOT EXISTS orders (
                                      order_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                      user_id INT NOT NULL,
                                      order_date DATE DEFAULT NULL,
                                      status VARCHAR(20) NOT NULL,
    total_amount FLOAT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS products (
                                        product_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    images BLOB,
    price FLOAT NOT NULL,
    stock INT NOT NULL,
    category VARCHAR(50) NOT NULL,
    is_featured boolean NOT NULL
    );

CREATE TABLE IF NOT EXISTS order_details (
                                             order_detail_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                             order_id INT NOT NULL,
                                             product_id INT NOT NULL,
                                             unit_price FLOAT NOT NULL,
                                             quantity INT NOT NULL,
                                             total_price FLOAT NOT NULL,
                                             FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS transactions (
                                            transaction_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                            order_id INT NOT NULL,
                                            transaction_date DATE DEFAULT NULL,
                                            amount FLOAT NOT NULL,
                                            payment_method VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS wishlist (
                                        wishlist_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                        user_id INT NOT NULL,
                                        product_id INT NOT NULL,
                                        FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS cart(
                                   cart_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                   user_id INT NOT NULL,
                                   FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS cartItem(
                                       cartItem_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                       cart_id INT NOT NULL,
                                       product_id INT NOT NULL,
                                       quantity INT NOT NULL,
                                       FOREIGN KEY (cart_id) REFERENCES cart(cart_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE CASCADE
    );


-- 3) INSERTAMOS VALORES A NUESTRA BASE DE DATOS
-- 3.1) USERS

INSERT INTO users (username, password, email, birth_date, firstname, lastname, role)
VALUES
    ('juanperez', 'juanperez_password', 'juanperez@example.com', '1985-01-15', 'Juan', 'Pérez', 'USER'),
    ('mariafernandez', 'mariafernandez_password', 'mariafernandez@example.com', '1992-03-22', 'María', 'Fernández', 'ADMIN'),
    ('pedrolopez', 'pedrolopez_password', 'pedrolopez@example.com', '1988-07-30', 'Pedro', 'López', 'USER'),
    ('luisaortiz', 'luisaortiz_password', 'luisaortiz@example.com', '1990-11-05', 'Luisa', 'Ortiz', 'USER'),
    ('josegarcia', 'josegarcia_password', 'josegarcia@example.com', '1987-09-12', 'José', 'García', 'ADMIN'),
    ('anaquezada', 'anaquezada_password', 'anaquezada@example.com', '1995-02-18', 'Ana', 'Quezada', 'USER'),
    ('franciscogomez', 'franciscogomez_password', 'franciscogomez@example.com', '1989-12-25', 'Francisco', 'Gómez', 'ADMIN'),
    ('sofiareyes', 'sofiareyes_password', 'sofiareyes@example.com', '1994-04-27', 'Sofía', 'Reyes', 'USER'),
    ('carlosmedina', 'carlosmedina_password', 'carlosmedina@example.com', '1991-06-08', 'Carlos', 'Medina', 'ADMIN'),
    ('veronicaflores', 'veronicaflores_password', 'veronicaflores@example.com', '1986-08-16', 'Verónica', 'Flores', 'ADMIN'),
    ('manuelmorales', 'manuelmorales_password', 'manuelmorales@example.com', '1982-10-10', 'Manuel', 'Morales', 'USER'),
    ('camilagutierrez', 'camilagutierrez_password', 'camilagutierrez@example.com', '1993-05-03', 'Camila', 'Gutiérrez', 'USER'),
    ('alejandrocastro', 'alejandrocastro_password', 'alejandrocastro@example.com', '1984-07-14', 'Alejandro', 'Castro', 'ADMIN'),
    ('patriciajimenez', 'patriciajimenez_password', 'patriciajimenez@example.com', '1989-12-01', 'Patricia', 'Jiménez', 'USER'),
    ('nicolashernandez', 'nicolashernandez_password', 'nicolashernandez@example.com', '1990-02-20', 'Nicolás', 'Hernández', 'ADMIN');

-- 3.1) PRODUCTS
INSERT INTO products (name, description, images, price, stock, category, is_featured)
VALUES
    ('Laptop HP', 'Laptop HP de 15 pulgadas con 16GB RAM', NULL, 799.99, 10, 'Electrónica',true),
    ('Teléfono Samsung', 'Teléfono Samsung Galaxy S21', NULL, 999.99, 15, 'Electrónica',true),
    ('Smartwatch Garmin', 'Smartwatch Garmin Fenix 6', NULL, 299.99, 20, 'Electrónica',true),
    ('Auriculares Bose', 'Auriculares Bose QC35 II', NULL, 349.99, 25, 'Electrónica', false),
    ('Cámara Canon', 'Cámara Canon EOS 80D', NULL, 1199.99, 8, 'Electrónica', false),
    ('Televisor LG', 'Televisor LG 55 pulgadas 4K', NULL, 649.99, 12, 'Electrónica', true),
    ('Tablet Apple', 'Tablet Apple iPad Pro 11', NULL, 799.99, 18, 'Electrónica', true),
    ('Impresora HP', 'Impresora HP OfficeJet Pro 9015', NULL, 199.99, 14, 'Electrónica', false),
    ('Teclado Logitech', 'Teclado Logitech MX Keys', NULL, 129.99, 30, 'Electrónica', false),
    ('Ratón Razer', 'Ratón Razer DeathAdder V2', NULL, 89.99, 22, 'Electrónica', false),
    ('Ropa Deportiva Nike', 'Ropa deportiva Nike para mujer', NULL, 69.99, 50, 'Ropa', false),
    ('Zapatillas Adidas', 'Zapatillas Adidas Ultraboost', NULL, 139.99, 40, 'Ropa', false),
    ('Chaqueta North Face', 'Chaqueta North Face para invierno', NULL, 199.99, 15, 'Ropa', true),
    ('Gorra Puma', 'Gorra Puma con logo', NULL, 29.99, 35, 'Ropa', false);