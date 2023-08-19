INSERT INTO address (country, city, street, number, zip_code)
VALUES
('Polska', 'Krakow', 'Mickiewicza', '2A', '38-300'),
('Polska', 'Warszawa', 'Dluga', '5', '34-300'),
('Polska', 'Gdansk', 'Lewa', '1', '32-300'),
('Polska', 'Warszawa', 'Dluga', '23', '39-301'),
('Polska', 'Warszawa', 'Dluga', '24', '39-302'),
('Polska', 'Warszawa', 'Dluga', '25', '39-303'),
('Polska', 'Warszawa', 'Dluga', '26', '39-304'),
('Polska', 'Krakow', 'Mickiewicza', '3A', '38-300'),
('Polska', 'Warszawa', 'Dluga', '6', '34-300'),
('Polska', 'Gdansk', 'Lewa', '7', '32-300'),
('Polska', 'Warszawa', 'Dluga', '231', '39-301'),
('Polska', 'Warszawa', 'Dluga', '241', '39-302'),
('Polska', 'Warszawa', 'Dluga', '251', '39-303'),
('Polska', 'Warszawa', 'Dluga', '261', '39-304');

INSERT INTO delivery_address (country, city, street)
VALUES
('Polska', 'Krakow', 'Mickiewicza'),
('Polska', 'Warszawa', 'Dluga'),
('Polska', 'Gdansk', 'Lewa'),
('Polska', 'Wroclaw', 'Prawa');

INSERT INTO application_role (role_id, role)
VALUES (1, 'ADMIN'), (2, 'OWNER'), (3, 'DEFAULT');

INSERT INTO application_user (user_name, password, active)
VALUES ('EMAIL@EMAIL.COM', '$2a$12$13xsHnFNTmmN9kv1M/TRcO5A4/jj6qPRvmJoEqs6dKKmabdjYXCSK', true);

INSERT INTO application_user_role (user_id, role_id)
VALUES (1, 2);

INSERT INTO food_app_user (name, surname, email, phone, address_id, user_id)
VALUES
('TEST1', 'TEST2', 'EMAIL@EMAIL.COM', '+48 555545555', 2, 1);

INSERT INTO restaurant (name, food_app_user_id, address_id)
VALUES
('KEBAB BAR', 1, 1),
('ROSOL BAR', 1, 2),
('LODY BAR', 1, 3),
('ZUPY BAR', 1, 4),
('BAR BAR', 1, 5),
('PUB BAR', 1, 6),
('PIZZA BAR', 1, 7),
('KEBAB BAR', 1, 8),
('ROSOL BAR', 1, 9),
('LODY BAR', 1, 10),
('ZUPY BAR', 1, 11),
('BAR BAR', 1, 12),
('PUB BAR', 1, 13),
('PIZZA BAR', 1, 14);

INSERT INTO menu (name, category, restaurant_id)
VALUES
('kebab menu', 'kebab', 1),
('Pizza menu', 'pizza', 1),
('Zupy', 'dania glowne', 1),
('Napoje', 'napoje', 1),
('Ciasta', 'dla dzieci', 1);

INSERT INTO food (name, price, description, menu_id)
VALUES
('kebab maly', 20.50, 'Malutki kebab z kurczaka', 1),
('kebab duzy', 25.50, 'Duzy kebab z kurczaka', 1),
('kebab średni', 23.50, 'Sredni kebab z kurczaka', 1),
('Pizza mala', 21.50, 'Mala pizza hawajska', 2),
('Pizza srednia', 23.50, 'Srednia pizza hawajska', 2),
('Pizza duza', 25.50, 'Duza pizza hawajska', 2),
('Pizza rodzinna', 26.50, 'Rodzinna pizza hawajska', 2);

INSERT INTO restaurant_delivery_address (delivery_time, delivery_address_id, restaurant_id)
VALUES
(60, 2, 1),
(45, 2, 2),
(40, 2, 3),
(50, 2, 4),
(60, 2, 5),
(60, 2, 6),
(55, 2, 7),
(60, 2, 8),
(45, 2, 9),
(40, 2, 10),
(50, 2, 11),
(60, 2, 12),
(60, 2, 13),
(55, 2, 14);