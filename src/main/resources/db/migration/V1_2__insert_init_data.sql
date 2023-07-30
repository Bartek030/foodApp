INSERT INTO address (country, city, street, number, zip_code)
VALUES
('Polska', 'Krakow', 'Mickiewicza', '2A', '38-300'),
('Polska', 'Warszawa', 'Dluga', '5', '34-300'),
('Polska', 'Gdansk', 'Lewa', '1', '32-300'),
('Polska', 'Wroclaw', 'Prawa', '22', '39-300');

INSERT INTO food_app_user (name, surname, email, phone, address_id)
VALUES
('TESTname', 'TESTsurname', 'testemail@email.com', '+48 555555555', 2);

INSERT INTO delivery_address (country, city, street)
VALUES
('Polska', 'Krakow', 'Mickiewicza'),
('Polska', 'Warszawa', 'Dluga'),
('Polska', 'Gdansk', 'Lewa'),
('Polska', 'Wroclaw', 'Prawa');

INSERT INTO restaurant (name, food_app_user_id, address_id)
VALUES
('KEBAB', 1, 3),
('PIZZA', 1, 4);

INSERT INTO menu (name, category, restaurant_id)
VALUES
('kebab menu', 'kurczak', 1),
('kebab menu2', 'wołowinka', 1);

INSERT INTO food (name, price, description, menu_id)
VALUES
('kebab maly', 20.50, 'Malutki kebab z kurczaka', 1),
('kebab duzy', 25.50, 'Duzy kebab z kurczaka', 1);

INSERT INTO restaurant_delivery_address (delivery_time, delivery_address_id, restaurant_id)
VALUES
(220, 2, 1),
(300, 2, 2);
