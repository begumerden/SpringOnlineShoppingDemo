CREATE TABLE category (
	id IDENTITY,
	name VARCHAR(50),
	description VARCHAR(255),
	image_url VARCHAR(50),
	is_active BOOLEAN,
	CONSTRAINT pk_category_id PRIMARY KEY (id)
);


INSERT INTO category (name, description,image_url,is_active) VALUES ('Mobile', 'This is description for Mobile category!', 'CAT_MOBILE.png', true);
INSERT INTO category (name, description,image_url,is_active) VALUES ('Laptop', 'This is description for Laptop category!', 'CAT_LAPTOP.png', true);
INSERT INTO category (name, description,image_url,is_active) VALUES ('Camera', 'This is description for Camera category!', 'CAT_CAMERA.png', true);


-------
CREATE TABLE user_info (
	id IDENTITY AUTO_INCREMENT,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	role VARCHAR(50),
	enabled BOOLEAN,
	password VARCHAR(60),
	email VARCHAR(100),
	contact_number VARCHAR(15),
	CONSTRAINT pk_user_id PRIMARY KEY(id)
);


INSERT INTO user_info
(first_name, last_name, role, enabled, password, email, contact_number)
VALUES ('Begum', 'xx', 'ADMIN', true, '$2b$10$EVT17TCFC87hfxHXSNbVIeDSbh4odUiJzB3T1oA3wCYg2tN1bok3y', 'be@mail.com', '1111111111');
INSERT INTO user_info  /*psw:user*/
(first_name, last_name, role, enabled, password, email, contact_number)
VALUES ('User', '', 'USER', true, '$2y$10$deuCqyNa.0.A.JzN4pNbs.eDvE6sA1hms4ZFyFzUjScsM/b5Zjole', 'user@mail.com', '1111111111');
INSERT INTO user_info /*psw:istanbul*/
(first_name, last_name, role, enabled, password, email, contact_number)
VALUES ('Turkey', 'Istanbul', 'SUPPLIER', true, '$2y$10$K6tOngf6NQaTdBti0bYdBurz5eXjr1.hSnVgKKpe7.gv7JA5AYY9W', 'turkey_istanbul@mail.com', '2222222222');
INSERT INTO user_info
(first_name, last_name, role, enabled, password, email, contact_number)
VALUES ('Turkey', 'Izmir', 'SUPPLIER', true, '$2y$10$AgyaP772inlk3JFOyWiMT.6GkZxYYD/dZsDYoV/ROexKrpebedqcu', 'turkey-izmir@mail.com', '3333333333');


---------
CREATE TABLE product (
	id IDENTITY,
	code VARCHAR(20),
	name VARCHAR(50),
	brand VARCHAR(50),
	description VARCHAR(255),
	unit_price DECIMAL(10,2),
	quantity INT,
	is_active BOOLEAN,
	category_id INT,
	supplier_id INT,
	purchases INT DEFAULT 0,
	views INT DEFAULT 0,
	CONSTRAINT pk_product_id PRIMARY KEY (id),
 	CONSTRAINT fk_product_category_id FOREIGN KEY (category_id) REFERENCES category (id),
	CONSTRAINT fk_product_supplier_id FOREIGN KEY (supplier_id) REFERENCES user(id),
);


INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES ('PRDCT000001', 'iPhone X', 'apple', 'One of the best smart phone available in the market right now!', 11490, 10, true, 1, 2, 0, 0 );
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES ('PRDCT000002', 'Canon EOS M50', 'canon', 'A Digital SLR Camera by Canon!', 3000, 5, true, 3, 3, 0, 0 );
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES ('PRDCT000003', 'iPhone 7s', 'apple', 'This is one of the best smart phone available in the market right now!', 10000, 5, true, 1, 2, 0, 0 );
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES ('PRDCT000004', ' Macbook Pro', 'apple', 'This is one of the best laptops available in the market right now!', 54000, 3, true, 1, 2, 0, 0 );
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES ('PRDCT000005', 'Dell Latitude E6510', 'dell', 'This is one of the best laptop series from dell that can be used!', 48000, 5, true, 2, 3, 0, 0 );



-----------
CREATE TABLE address (
	id IDENTITY,
	user_id int,
	address_line_one VARCHAR(100),
	address_line_two VARCHAR(100),
	city VARCHAR(20),
	state VARCHAR(20),
	country VARCHAR(20),
	postal_code VARCHAR(10),
	is_billing BOOLEAN,
	is_shipping BOOLEAN,
	CONSTRAINT fk_address_user_id FOREIGN KEY (user_id ) REFERENCES user_info (id),
	CONSTRAINT pk_address_id PRIMARY KEY (id)
);



----------------
CREATE TABLE cart (
	id IDENTITY,
	user_id int,
	grand_total DECIMAL(10,2),
	cart_lines int,
	CONSTRAINT fk_cart_user_id FOREIGN KEY (user_id) REFERENCES user_info (id),
	CONSTRAINT pk_cart_id PRIMARY KEY (id)
);