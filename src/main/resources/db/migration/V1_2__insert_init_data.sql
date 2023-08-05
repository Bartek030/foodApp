INSERT INTO address (country, city, street, number, zip_code)
VALUES
('Polska', 'Krakow', 'Mickiewicza', '2A', '38-300'),
('Polska', 'Warszawa', 'Dluga', '5', '34-300'),
('Polska', 'Gdansk', 'Lewa', '1', '32-300'),
('Polska', 'Warszawa', 'Dluga', '23', '39-301'),
('Polska', 'Warszawa', 'Dluga', '24', '39-302'),
('Polska', 'Warszawa', 'Dluga', '25', '39-303'),
('Polska', 'Warszawa', 'Dluga', '26', '39-304');

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
('KEBAB', 1, 1),
('ROSOL', 1, 2),
('LODY', 1, 3),
('ZUPY', 1, 4),
('BAR', 1, 5),
('PUB', 1, 6),
('PIZZA', 1, 7);

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
(60, 2, 1),
(45, 2, 2),
(40, 2, 3),
(50, 2, 4),
(60, 2, 5),
(60, 2, 6),
(55, 2, 7);
